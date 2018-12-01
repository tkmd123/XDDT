import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQuanHuyen, defaultValue } from 'app/shared/model/quan-huyen.model';

export const ACTION_TYPES = {
  SEARCH_QUANHUYENS: 'quanHuyen/SEARCH_QUANHUYENS',
  FETCH_QUANHUYEN_LIST: 'quanHuyen/FETCH_QUANHUYEN_LIST',
  FETCH_QUANHUYEN: 'quanHuyen/FETCH_QUANHUYEN',
  CREATE_QUANHUYEN: 'quanHuyen/CREATE_QUANHUYEN',
  UPDATE_QUANHUYEN: 'quanHuyen/UPDATE_QUANHUYEN',
  DELETE_QUANHUYEN: 'quanHuyen/DELETE_QUANHUYEN',
  RESET: 'quanHuyen/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQuanHuyen>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type QuanHuyenState = Readonly<typeof initialState>;

// Reducer

export default (state: QuanHuyenState = initialState, action): QuanHuyenState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_QUANHUYENS):
    case REQUEST(ACTION_TYPES.FETCH_QUANHUYEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QUANHUYEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_QUANHUYEN):
    case REQUEST(ACTION_TYPES.UPDATE_QUANHUYEN):
    case REQUEST(ACTION_TYPES.DELETE_QUANHUYEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_QUANHUYENS):
    case FAILURE(ACTION_TYPES.FETCH_QUANHUYEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QUANHUYEN):
    case FAILURE(ACTION_TYPES.CREATE_QUANHUYEN):
    case FAILURE(ACTION_TYPES.UPDATE_QUANHUYEN):
    case FAILURE(ACTION_TYPES.DELETE_QUANHUYEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_QUANHUYENS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUANHUYEN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUANHUYEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_QUANHUYEN):
    case SUCCESS(ACTION_TYPES.UPDATE_QUANHUYEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_QUANHUYEN):
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

const apiUrl = 'api/quan-huyens';
const apiSearchUrl = 'api/_search/quan-huyens';

// Actions

export const getSearchEntities: ICrudSearchAction<IQuanHuyen> = query => ({
  type: ACTION_TYPES.SEARCH_QUANHUYENS,
  payload: axios.get<IQuanHuyen>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IQuanHuyen> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_QUANHUYEN_LIST,
    payload: axios.get<IQuanHuyen>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IQuanHuyen> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QUANHUYEN,
    payload: axios.get<IQuanHuyen>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IQuanHuyen> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QUANHUYEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IQuanHuyen> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QUANHUYEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQuanHuyen> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QUANHUYEN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
