import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoSoLietSi, defaultValue } from 'app/shared/model/ho-so-liet-si.model';

export const ACTION_TYPES = {
  SEARCH_HOSOLIETSIS: 'hoSoLietSi/SEARCH_HOSOLIETSIS',
  FETCH_HOSOLIETSI_LIST: 'hoSoLietSi/FETCH_HOSOLIETSI_LIST',
  FETCH_HOSOLIETSI: 'hoSoLietSi/FETCH_HOSOLIETSI',
  CREATE_HOSOLIETSI: 'hoSoLietSi/CREATE_HOSOLIETSI',
  UPDATE_HOSOLIETSI: 'hoSoLietSi/UPDATE_HOSOLIETSI',
  DELETE_HOSOLIETSI: 'hoSoLietSi/DELETE_HOSOLIETSI',
  RESET: 'hoSoLietSi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoSoLietSi>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type HoSoLietSiState = Readonly<typeof initialState>;

// Reducer

export default (state: HoSoLietSiState = initialState, action): HoSoLietSiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOSOLIETSIS):
    case REQUEST(ACTION_TYPES.FETCH_HOSOLIETSI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOSOLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOSOLIETSI):
    case REQUEST(ACTION_TYPES.UPDATE_HOSOLIETSI):
    case REQUEST(ACTION_TYPES.DELETE_HOSOLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOSOLIETSIS):
    case FAILURE(ACTION_TYPES.FETCH_HOSOLIETSI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOSOLIETSI):
    case FAILURE(ACTION_TYPES.CREATE_HOSOLIETSI):
    case FAILURE(ACTION_TYPES.UPDATE_HOSOLIETSI):
    case FAILURE(ACTION_TYPES.DELETE_HOSOLIETSI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOSOLIETSIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOLIETSI_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOLIETSI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOSOLIETSI):
    case SUCCESS(ACTION_TYPES.UPDATE_HOSOLIETSI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOSOLIETSI):
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

const apiUrl = 'api/ho-so-liet-sis';
const apiSearchUrl = 'api/_search/ho-so-liet-sis';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoSoLietSi> = query => ({
  type: ACTION_TYPES.SEARCH_HOSOLIETSIS,
  payload: axios.get<IHoSoLietSi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoSoLietSi> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_HOSOLIETSI_LIST,
    payload: axios.get<IHoSoLietSi>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IHoSoLietSi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOSOLIETSI,
    payload: axios.get<IHoSoLietSi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoSoLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOSOLIETSI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoSoLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOSOLIETSI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoSoLietSi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOSOLIETSI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
