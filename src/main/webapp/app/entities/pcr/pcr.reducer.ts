import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCR, defaultValue } from 'app/shared/model/pcr.model';

export const ACTION_TYPES = {
  SEARCH_PCRS: 'pCR/SEARCH_PCRS',
  FETCH_PCR_LIST: 'pCR/FETCH_PCR_LIST',
  FETCH_PCR: 'pCR/FETCH_PCR',
  CREATE_PCR: 'pCR/CREATE_PCR',
  UPDATE_PCR: 'pCR/UPDATE_PCR',
  DELETE_PCR: 'pCR/DELETE_PCR',
  RESET: 'pCR/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCR>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRState = initialState, action): PCRState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRS):
    case REQUEST(ACTION_TYPES.FETCH_PCR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCR):
    case REQUEST(ACTION_TYPES.UPDATE_PCR):
    case REQUEST(ACTION_TYPES.DELETE_PCR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRS):
    case FAILURE(ACTION_TYPES.FETCH_PCR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCR):
    case FAILURE(ACTION_TYPES.CREATE_PCR):
    case FAILURE(ACTION_TYPES.UPDATE_PCR):
    case FAILURE(ACTION_TYPES.DELETE_PCR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCR):
    case SUCCESS(ACTION_TYPES.UPDATE_PCR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/pcrs';
const apiSearchUrl = 'api/_search/pcrs';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCR> = query => ({
  type: ACTION_TYPES.SEARCH_PCRS,
  payload: axios.get<IPCR>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCR> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCR_LIST,
  payload: axios.get<IPCR>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCR> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCR,
    payload: axios.get<IPCR>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCR> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCR> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCR> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
