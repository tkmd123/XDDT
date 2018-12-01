import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INhanVien, defaultValue } from 'app/shared/model/nhan-vien.model';

export const ACTION_TYPES = {
  SEARCH_NHANVIENS: 'nhanVien/SEARCH_NHANVIENS',
  FETCH_NHANVIEN_LIST: 'nhanVien/FETCH_NHANVIEN_LIST',
  FETCH_NHANVIEN: 'nhanVien/FETCH_NHANVIEN',
  CREATE_NHANVIEN: 'nhanVien/CREATE_NHANVIEN',
  UPDATE_NHANVIEN: 'nhanVien/UPDATE_NHANVIEN',
  DELETE_NHANVIEN: 'nhanVien/DELETE_NHANVIEN',
  RESET: 'nhanVien/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INhanVien>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NhanVienState = Readonly<typeof initialState>;

// Reducer

export default (state: NhanVienState = initialState, action): NhanVienState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_NHANVIENS):
    case REQUEST(ACTION_TYPES.FETCH_NHANVIEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NHANVIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NHANVIEN):
    case REQUEST(ACTION_TYPES.UPDATE_NHANVIEN):
    case REQUEST(ACTION_TYPES.DELETE_NHANVIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_NHANVIENS):
    case FAILURE(ACTION_TYPES.FETCH_NHANVIEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NHANVIEN):
    case FAILURE(ACTION_TYPES.CREATE_NHANVIEN):
    case FAILURE(ACTION_TYPES.UPDATE_NHANVIEN):
    case FAILURE(ACTION_TYPES.DELETE_NHANVIEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_NHANVIENS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANVIEN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANVIEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NHANVIEN):
    case SUCCESS(ACTION_TYPES.UPDATE_NHANVIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NHANVIEN):
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

const apiUrl = 'api/nhan-viens';
const apiSearchUrl = 'api/_search/nhan-viens';

// Actions

export const getSearchEntities: ICrudSearchAction<INhanVien> = query => ({
  type: ACTION_TYPES.SEARCH_NHANVIENS,
  payload: axios.get<INhanVien>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<INhanVien> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NHANVIEN_LIST,
  payload: axios.get<INhanVien>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INhanVien> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NHANVIEN,
    payload: axios.get<INhanVien>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INhanVien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NHANVIEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INhanVien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NHANVIEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INhanVien> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NHANVIEN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
