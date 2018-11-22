import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPhuongXa, defaultValue } from 'app/shared/model/phuong-xa.model';

export const ACTION_TYPES = {
  SEARCH_PHUONGXAS: 'phuongXa/SEARCH_PHUONGXAS',
  FETCH_PHUONGXA_LIST: 'phuongXa/FETCH_PHUONGXA_LIST',
  FETCH_PHUONGXA: 'phuongXa/FETCH_PHUONGXA',
  CREATE_PHUONGXA: 'phuongXa/CREATE_PHUONGXA',
  UPDATE_PHUONGXA: 'phuongXa/UPDATE_PHUONGXA',
  DELETE_PHUONGXA: 'phuongXa/DELETE_PHUONGXA',
  RESET: 'phuongXa/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPhuongXa>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type PhuongXaState = Readonly<typeof initialState>;

// Reducer

export default (state: PhuongXaState = initialState, action): PhuongXaState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PHUONGXAS):
    case REQUEST(ACTION_TYPES.FETCH_PHUONGXA_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PHUONGXA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PHUONGXA):
    case REQUEST(ACTION_TYPES.UPDATE_PHUONGXA):
    case REQUEST(ACTION_TYPES.DELETE_PHUONGXA):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PHUONGXAS):
    case FAILURE(ACTION_TYPES.FETCH_PHUONGXA_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PHUONGXA):
    case FAILURE(ACTION_TYPES.CREATE_PHUONGXA):
    case FAILURE(ACTION_TYPES.UPDATE_PHUONGXA):
    case FAILURE(ACTION_TYPES.DELETE_PHUONGXA):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PHUONGXAS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHUONGXA_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHUONGXA):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PHUONGXA):
    case SUCCESS(ACTION_TYPES.UPDATE_PHUONGXA):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PHUONGXA):
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

const apiUrl = 'api/phuong-xas';
const apiSearchUrl = 'api/_search/phuong-xas';

// Actions

export const getSearchEntities: ICrudSearchAction<IPhuongXa> = query => ({
  type: ACTION_TYPES.SEARCH_PHUONGXAS,
  payload: axios.get<IPhuongXa>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPhuongXa> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PHUONGXA_LIST,
    payload: axios.get<IPhuongXa>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IPhuongXa> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PHUONGXA,
    payload: axios.get<IPhuongXa>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPhuongXa> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PHUONGXA,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPhuongXa> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PHUONGXA,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPhuongXa> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PHUONGXA,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
