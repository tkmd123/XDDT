import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCRMau, defaultValue } from 'app/shared/model/pcr-mau.model';

export const ACTION_TYPES = {
  SEARCH_PCRMAUS: 'pCRMau/SEARCH_PCRMAUS',
  FETCH_PCRMAU_LIST: 'pCRMau/FETCH_PCRMAU_LIST',
  FETCH_PCRMAU: 'pCRMau/FETCH_PCRMAU',
  CREATE_PCRMAU: 'pCRMau/CREATE_PCRMAU',
  UPDATE_PCRMAU: 'pCRMau/UPDATE_PCRMAU',
  DELETE_PCRMAU: 'pCRMau/DELETE_PCRMAU',
  RESET: 'pCRMau/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCRMau>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRMauState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRMauState = initialState, action): PCRMauState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRMAUS):
    case REQUEST(ACTION_TYPES.FETCH_PCRMAU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCRMAU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCRMAU):
    case REQUEST(ACTION_TYPES.UPDATE_PCRMAU):
    case REQUEST(ACTION_TYPES.DELETE_PCRMAU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRMAUS):
    case FAILURE(ACTION_TYPES.FETCH_PCRMAU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCRMAU):
    case FAILURE(ACTION_TYPES.CREATE_PCRMAU):
    case FAILURE(ACTION_TYPES.UPDATE_PCRMAU):
    case FAILURE(ACTION_TYPES.DELETE_PCRMAU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRMAUS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRMAU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRMAU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCRMAU):
    case SUCCESS(ACTION_TYPES.UPDATE_PCRMAU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCRMAU):
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

const apiUrl = 'api/pcr-maus';
const apiSearchUrl = 'api/_search/pcr-maus';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCRMau> = query => ({
  type: ACTION_TYPES.SEARCH_PCRMAUS,
  payload: axios.get<IPCRMau>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCRMau> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCRMAU_LIST,
  payload: axios.get<IPCRMau>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCRMau> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCRMAU,
    payload: axios.get<IPCRMau>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCRMau> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCRMAU,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCRMau> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCRMAU,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCRMau> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCRMAU,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
