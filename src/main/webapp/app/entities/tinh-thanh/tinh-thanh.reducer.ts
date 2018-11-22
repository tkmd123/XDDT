import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITinhThanh, defaultValue } from 'app/shared/model/tinh-thanh.model';

export const ACTION_TYPES = {
  SEARCH_TINHTHANHS: 'tinhThanh/SEARCH_TINHTHANHS',
  FETCH_TINHTHANH_LIST: 'tinhThanh/FETCH_TINHTHANH_LIST',
  FETCH_TINHTHANH: 'tinhThanh/FETCH_TINHTHANH',
  CREATE_TINHTHANH: 'tinhThanh/CREATE_TINHTHANH',
  UPDATE_TINHTHANH: 'tinhThanh/UPDATE_TINHTHANH',
  DELETE_TINHTHANH: 'tinhThanh/DELETE_TINHTHANH',
  RESET: 'tinhThanh/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITinhThanh>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type TinhThanhState = Readonly<typeof initialState>;

// Reducer

export default (state: TinhThanhState = initialState, action): TinhThanhState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TINHTHANHS):
    case REQUEST(ACTION_TYPES.FETCH_TINHTHANH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TINHTHANH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TINHTHANH):
    case REQUEST(ACTION_TYPES.UPDATE_TINHTHANH):
    case REQUEST(ACTION_TYPES.DELETE_TINHTHANH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TINHTHANHS):
    case FAILURE(ACTION_TYPES.FETCH_TINHTHANH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TINHTHANH):
    case FAILURE(ACTION_TYPES.CREATE_TINHTHANH):
    case FAILURE(ACTION_TYPES.UPDATE_TINHTHANH):
    case FAILURE(ACTION_TYPES.DELETE_TINHTHANH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TINHTHANHS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHTHANH_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHTHANH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TINHTHANH):
    case SUCCESS(ACTION_TYPES.UPDATE_TINHTHANH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TINHTHANH):
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

const apiUrl = 'api/tinh-thanhs';
const apiSearchUrl = 'api/_search/tinh-thanhs';

// Actions

export const getSearchEntities: ICrudSearchAction<ITinhThanh> = query => ({
  type: ACTION_TYPES.SEARCH_TINHTHANHS,
  payload: axios.get<ITinhThanh>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITinhThanh> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TINHTHANH_LIST,
    payload: axios.get<ITinhThanh>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ITinhThanh> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TINHTHANH,
    payload: axios.get<ITinhThanh>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITinhThanh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TINHTHANH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITinhThanh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TINHTHANH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITinhThanh> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TINHTHANH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
