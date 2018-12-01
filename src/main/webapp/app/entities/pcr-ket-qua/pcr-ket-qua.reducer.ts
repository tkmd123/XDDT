import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCRKetQua, defaultValue } from 'app/shared/model/pcr-ket-qua.model';

export const ACTION_TYPES = {
  SEARCH_PCRKETQUAS: 'pCRKetQua/SEARCH_PCRKETQUAS',
  FETCH_PCRKETQUA_LIST: 'pCRKetQua/FETCH_PCRKETQUA_LIST',
  FETCH_PCRKETQUA: 'pCRKetQua/FETCH_PCRKETQUA',
  CREATE_PCRKETQUA: 'pCRKetQua/CREATE_PCRKETQUA',
  UPDATE_PCRKETQUA: 'pCRKetQua/UPDATE_PCRKETQUA',
  DELETE_PCRKETQUA: 'pCRKetQua/DELETE_PCRKETQUA',
  RESET: 'pCRKetQua/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCRKetQua>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRKetQuaState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRKetQuaState = initialState, action): PCRKetQuaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRKETQUAS):
    case REQUEST(ACTION_TYPES.FETCH_PCRKETQUA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCRKETQUA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCRKETQUA):
    case REQUEST(ACTION_TYPES.UPDATE_PCRKETQUA):
    case REQUEST(ACTION_TYPES.DELETE_PCRKETQUA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRKETQUAS):
    case FAILURE(ACTION_TYPES.FETCH_PCRKETQUA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCRKETQUA):
    case FAILURE(ACTION_TYPES.CREATE_PCRKETQUA):
    case FAILURE(ACTION_TYPES.UPDATE_PCRKETQUA):
    case FAILURE(ACTION_TYPES.DELETE_PCRKETQUA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRKETQUAS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRKETQUA_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRKETQUA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCRKETQUA):
    case SUCCESS(ACTION_TYPES.UPDATE_PCRKETQUA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCRKETQUA):
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

const apiUrl = 'api/pcr-ket-quas';
const apiSearchUrl = 'api/_search/pcr-ket-quas';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCRKetQua> = query => ({
  type: ACTION_TYPES.SEARCH_PCRKETQUAS,
  payload: axios.get<IPCRKetQua>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCRKetQua> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCRKETQUA_LIST,
  payload: axios.get<IPCRKetQua>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCRKetQua> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCRKETQUA,
    payload: axios.get<IPCRKetQua>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCRKetQua> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCRKETQUA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCRKetQua> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCRKETQUA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCRKetQua> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCRKETQUA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
