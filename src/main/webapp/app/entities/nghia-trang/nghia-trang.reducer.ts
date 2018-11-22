import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { INghiaTrang, defaultValue } from 'app/shared/model/nghia-trang.model';

export const ACTION_TYPES = {
  SEARCH_NGHIATRANGS: 'nghiaTrang/SEARCH_NGHIATRANGS',
  FETCH_NGHIATRANG_LIST: 'nghiaTrang/FETCH_NGHIATRANG_LIST',
  FETCH_NGHIATRANG: 'nghiaTrang/FETCH_NGHIATRANG',
  CREATE_NGHIATRANG: 'nghiaTrang/CREATE_NGHIATRANG',
  UPDATE_NGHIATRANG: 'nghiaTrang/UPDATE_NGHIATRANG',
  DELETE_NGHIATRANG: 'nghiaTrang/DELETE_NGHIATRANG',
  RESET: 'nghiaTrang/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<INghiaTrang>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type NghiaTrangState = Readonly<typeof initialState>;

// Reducer

export default (state: NghiaTrangState = initialState, action): NghiaTrangState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_NGHIATRANGS):
    case REQUEST(ACTION_TYPES.FETCH_NGHIATRANG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_NGHIATRANG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_NGHIATRANG):
    case REQUEST(ACTION_TYPES.UPDATE_NGHIATRANG):
    case REQUEST(ACTION_TYPES.DELETE_NGHIATRANG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_NGHIATRANGS):
    case FAILURE(ACTION_TYPES.FETCH_NGHIATRANG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_NGHIATRANG):
    case FAILURE(ACTION_TYPES.CREATE_NGHIATRANG):
    case FAILURE(ACTION_TYPES.UPDATE_NGHIATRANG):
    case FAILURE(ACTION_TYPES.DELETE_NGHIATRANG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_NGHIATRANGS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NGHIATRANG_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_NGHIATRANG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_NGHIATRANG):
    case SUCCESS(ACTION_TYPES.UPDATE_NGHIATRANG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_NGHIATRANG):
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

const apiUrl = 'api/nghia-trangs';
const apiSearchUrl = 'api/_search/nghia-trangs';

// Actions

export const getSearchEntities: ICrudSearchAction<INghiaTrang> = query => ({
  type: ACTION_TYPES.SEARCH_NGHIATRANGS,
  payload: axios.get<INghiaTrang>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<INghiaTrang> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_NGHIATRANG_LIST,
    payload: axios.get<INghiaTrang>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<INghiaTrang> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_NGHIATRANG,
    payload: axios.get<INghiaTrang>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<INghiaTrang> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_NGHIATRANG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<INghiaTrang> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_NGHIATRANG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<INghiaTrang> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_NGHIATRANG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
