import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INhanDangLietSi, defaultValue } from 'app/shared/model/nhan-dang-liet-si.model';

export const ACTION_TYPES = {
  SEARCH_NHANDANGLIETSIS: 'nhanDangLietSi/SEARCH_NHANDANGLIETSIS',
  FETCH_NHANDANGLIETSI_LIST: 'nhanDangLietSi/FETCH_NHANDANGLIETSI_LIST',
  FETCH_NHANDANGLIETSI: 'nhanDangLietSi/FETCH_NHANDANGLIETSI',
  CREATE_NHANDANGLIETSI: 'nhanDangLietSi/CREATE_NHANDANGLIETSI',
  UPDATE_NHANDANGLIETSI: 'nhanDangLietSi/UPDATE_NHANDANGLIETSI',
  DELETE_NHANDANGLIETSI: 'nhanDangLietSi/DELETE_NHANDANGLIETSI',
  RESET: 'nhanDangLietSi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INhanDangLietSi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NhanDangLietSiState = Readonly<typeof initialState>;

// Reducer

export default (state: NhanDangLietSiState = initialState, action): NhanDangLietSiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_NHANDANGLIETSIS):
    case REQUEST(ACTION_TYPES.FETCH_NHANDANGLIETSI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NHANDANGLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NHANDANGLIETSI):
    case REQUEST(ACTION_TYPES.UPDATE_NHANDANGLIETSI):
    case REQUEST(ACTION_TYPES.DELETE_NHANDANGLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_NHANDANGLIETSIS):
    case FAILURE(ACTION_TYPES.FETCH_NHANDANGLIETSI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NHANDANGLIETSI):
    case FAILURE(ACTION_TYPES.CREATE_NHANDANGLIETSI):
    case FAILURE(ACTION_TYPES.UPDATE_NHANDANGLIETSI):
    case FAILURE(ACTION_TYPES.DELETE_NHANDANGLIETSI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_NHANDANGLIETSIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANDANGLIETSI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANDANGLIETSI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NHANDANGLIETSI):
    case SUCCESS(ACTION_TYPES.UPDATE_NHANDANGLIETSI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NHANDANGLIETSI):
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

const apiUrl = 'api/nhan-dang-liet-sis';
const apiSearchUrl = 'api/_search/nhan-dang-liet-sis';

// Actions

export const getSearchEntities: ICrudSearchAction<INhanDangLietSi> = query => ({
  type: ACTION_TYPES.SEARCH_NHANDANGLIETSIS,
  payload: axios.get<INhanDangLietSi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<INhanDangLietSi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NHANDANGLIETSI_LIST,
  payload: axios.get<INhanDangLietSi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INhanDangLietSi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NHANDANGLIETSI,
    payload: axios.get<INhanDangLietSi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INhanDangLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NHANDANGLIETSI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INhanDangLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NHANDANGLIETSI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INhanDangLietSi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NHANDANGLIETSI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
