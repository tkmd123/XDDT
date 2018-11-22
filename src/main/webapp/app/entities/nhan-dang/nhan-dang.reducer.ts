import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INhanDang, defaultValue } from 'app/shared/model/nhan-dang.model';

export const ACTION_TYPES = {
  SEARCH_NHANDANGS: 'nhanDang/SEARCH_NHANDANGS',
  FETCH_NHANDANG_LIST: 'nhanDang/FETCH_NHANDANG_LIST',
  FETCH_NHANDANG: 'nhanDang/FETCH_NHANDANG',
  CREATE_NHANDANG: 'nhanDang/CREATE_NHANDANG',
  UPDATE_NHANDANG: 'nhanDang/UPDATE_NHANDANG',
  DELETE_NHANDANG: 'nhanDang/DELETE_NHANDANG',
  RESET: 'nhanDang/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INhanDang>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type NhanDangState = Readonly<typeof initialState>;

// Reducer

export default (state: NhanDangState = initialState, action): NhanDangState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_NHANDANGS):
    case REQUEST(ACTION_TYPES.FETCH_NHANDANG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NHANDANG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NHANDANG):
    case REQUEST(ACTION_TYPES.UPDATE_NHANDANG):
    case REQUEST(ACTION_TYPES.DELETE_NHANDANG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_NHANDANGS):
    case FAILURE(ACTION_TYPES.FETCH_NHANDANG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NHANDANG):
    case FAILURE(ACTION_TYPES.CREATE_NHANDANG):
    case FAILURE(ACTION_TYPES.UPDATE_NHANDANG):
    case FAILURE(ACTION_TYPES.DELETE_NHANDANG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_NHANDANGS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANDANG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NHANDANG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NHANDANG):
    case SUCCESS(ACTION_TYPES.UPDATE_NHANDANG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NHANDANG):
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

const apiUrl = 'api/nhan-dangs';
const apiSearchUrl = 'api/_search/nhan-dangs';

// Actions

export const getSearchEntities: ICrudSearchAction<INhanDang> = query => ({
  type: ACTION_TYPES.SEARCH_NHANDANGS,
  payload: axios.get<INhanDang>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<INhanDang> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_NHANDANG_LIST,
  payload: axios.get<INhanDang>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<INhanDang> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NHANDANG,
    payload: axios.get<INhanDang>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INhanDang> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NHANDANG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INhanDang> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NHANDANG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INhanDang> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NHANDANG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
