import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCRMoi, defaultValue } from 'app/shared/model/pcr-moi.model';

export const ACTION_TYPES = {
  SEARCH_PCRMOIS: 'pCRMoi/SEARCH_PCRMOIS',
  FETCH_PCRMOI_LIST: 'pCRMoi/FETCH_PCRMOI_LIST',
  FETCH_PCRMOI: 'pCRMoi/FETCH_PCRMOI',
  CREATE_PCRMOI: 'pCRMoi/CREATE_PCRMOI',
  UPDATE_PCRMOI: 'pCRMoi/UPDATE_PCRMOI',
  DELETE_PCRMOI: 'pCRMoi/DELETE_PCRMOI',
  RESET: 'pCRMoi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCRMoi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRMoiState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRMoiState = initialState, action): PCRMoiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRMOIS):
    case REQUEST(ACTION_TYPES.FETCH_PCRMOI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCRMOI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCRMOI):
    case REQUEST(ACTION_TYPES.UPDATE_PCRMOI):
    case REQUEST(ACTION_TYPES.DELETE_PCRMOI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRMOIS):
    case FAILURE(ACTION_TYPES.FETCH_PCRMOI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCRMOI):
    case FAILURE(ACTION_TYPES.CREATE_PCRMOI):
    case FAILURE(ACTION_TYPES.UPDATE_PCRMOI):
    case FAILURE(ACTION_TYPES.DELETE_PCRMOI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRMOIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRMOI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRMOI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCRMOI):
    case SUCCESS(ACTION_TYPES.UPDATE_PCRMOI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCRMOI):
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

const apiUrl = 'api/pcr-mois';
const apiSearchUrl = 'api/_search/pcr-mois';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCRMoi> = query => ({
  type: ACTION_TYPES.SEARCH_PCRMOIS,
  payload: axios.get<IPCRMoi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCRMoi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCRMOI_LIST,
  payload: axios.get<IPCRMoi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCRMoi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCRMOI,
    payload: axios.get<IPCRMoi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCRMoi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCRMOI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCRMoi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCRMOI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCRMoi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCRMOI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
