import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoaChatMacDinh, defaultValue } from 'app/shared/model/hoa-chat-mac-dinh.model';

export const ACTION_TYPES = {
  SEARCH_HOACHATMACDINHS: 'hoaChatMacDinh/SEARCH_HOACHATMACDINHS',
  FETCH_HOACHATMACDINH_LIST: 'hoaChatMacDinh/FETCH_HOACHATMACDINH_LIST',
  FETCH_HOACHATMACDINH: 'hoaChatMacDinh/FETCH_HOACHATMACDINH',
  CREATE_HOACHATMACDINH: 'hoaChatMacDinh/CREATE_HOACHATMACDINH',
  UPDATE_HOACHATMACDINH: 'hoaChatMacDinh/UPDATE_HOACHATMACDINH',
  DELETE_HOACHATMACDINH: 'hoaChatMacDinh/DELETE_HOACHATMACDINH',
  RESET: 'hoaChatMacDinh/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoaChatMacDinh>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HoaChatMacDinhState = Readonly<typeof initialState>;

// Reducer

export default (state: HoaChatMacDinhState = initialState, action): HoaChatMacDinhState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOACHATMACDINHS):
    case REQUEST(ACTION_TYPES.FETCH_HOACHATMACDINH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOACHATMACDINH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOACHATMACDINH):
    case REQUEST(ACTION_TYPES.UPDATE_HOACHATMACDINH):
    case REQUEST(ACTION_TYPES.DELETE_HOACHATMACDINH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOACHATMACDINHS):
    case FAILURE(ACTION_TYPES.FETCH_HOACHATMACDINH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOACHATMACDINH):
    case FAILURE(ACTION_TYPES.CREATE_HOACHATMACDINH):
    case FAILURE(ACTION_TYPES.UPDATE_HOACHATMACDINH):
    case FAILURE(ACTION_TYPES.DELETE_HOACHATMACDINH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOACHATMACDINHS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHATMACDINH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHATMACDINH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOACHATMACDINH):
    case SUCCESS(ACTION_TYPES.UPDATE_HOACHATMACDINH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOACHATMACDINH):
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

const apiUrl = 'api/hoa-chat-mac-dinhs';
const apiSearchUrl = 'api/_search/hoa-chat-mac-dinhs';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoaChatMacDinh> = query => ({
  type: ACTION_TYPES.SEARCH_HOACHATMACDINHS,
  payload: axios.get<IHoaChatMacDinh>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoaChatMacDinh> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HOACHATMACDINH_LIST,
  payload: axios.get<IHoaChatMacDinh>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHoaChatMacDinh> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOACHATMACDINH,
    payload: axios.get<IHoaChatMacDinh>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoaChatMacDinh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOACHATMACDINH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoaChatMacDinh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOACHATMACDINH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoaChatMacDinh> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOACHATMACDINH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
