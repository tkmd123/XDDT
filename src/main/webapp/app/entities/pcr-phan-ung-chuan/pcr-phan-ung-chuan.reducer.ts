import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCRPhanUngChuan, defaultValue } from 'app/shared/model/pcr-phan-ung-chuan.model';

export const ACTION_TYPES = {
  SEARCH_PCRPHANUNGCHUANS: 'pCRPhanUngChuan/SEARCH_PCRPHANUNGCHUANS',
  FETCH_PCRPHANUNGCHUAN_LIST: 'pCRPhanUngChuan/FETCH_PCRPHANUNGCHUAN_LIST',
  FETCH_PCRPHANUNGCHUAN: 'pCRPhanUngChuan/FETCH_PCRPHANUNGCHUAN',
  CREATE_PCRPHANUNGCHUAN: 'pCRPhanUngChuan/CREATE_PCRPHANUNGCHUAN',
  UPDATE_PCRPHANUNGCHUAN: 'pCRPhanUngChuan/UPDATE_PCRPHANUNGCHUAN',
  DELETE_PCRPHANUNGCHUAN: 'pCRPhanUngChuan/DELETE_PCRPHANUNGCHUAN',
  RESET: 'pCRPhanUngChuan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCRPhanUngChuan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRPhanUngChuanState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRPhanUngChuanState = initialState, action): PCRPhanUngChuanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRPHANUNGCHUANS):
    case REQUEST(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCRPHANUNGCHUAN):
    case REQUEST(ACTION_TYPES.UPDATE_PCRPHANUNGCHUAN):
    case REQUEST(ACTION_TYPES.DELETE_PCRPHANUNGCHUAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRPHANUNGCHUANS):
    case FAILURE(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN):
    case FAILURE(ACTION_TYPES.CREATE_PCRPHANUNGCHUAN):
    case FAILURE(ACTION_TYPES.UPDATE_PCRPHANUNGCHUAN):
    case FAILURE(ACTION_TYPES.DELETE_PCRPHANUNGCHUAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRPHANUNGCHUANS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRPHANUNGCHUAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCRPHANUNGCHUAN):
    case SUCCESS(ACTION_TYPES.UPDATE_PCRPHANUNGCHUAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCRPHANUNGCHUAN):
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

const apiUrl = 'api/pcr-phan-ung-chuans';
const apiSearchUrl = 'api/_search/pcr-phan-ung-chuans';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCRPhanUngChuan> = query => ({
  type: ACTION_TYPES.SEARCH_PCRPHANUNGCHUANS,
  payload: axios.get<IPCRPhanUngChuan>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCRPhanUngChuan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCRPHANUNGCHUAN_LIST,
  payload: axios.get<IPCRPhanUngChuan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCRPhanUngChuan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCRPHANUNGCHUAN,
    payload: axios.get<IPCRPhanUngChuan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCRPhanUngChuan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCRPHANUNGCHUAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCRPhanUngChuan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCRPHANUNGCHUAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCRPhanUngChuan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCRPHANUNGCHUAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
