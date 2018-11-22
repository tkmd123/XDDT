import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThanNhanLietSi, defaultValue } from 'app/shared/model/than-nhan-liet-si.model';

export const ACTION_TYPES = {
  SEARCH_THANNHANLIETSIS: 'thanNhanLietSi/SEARCH_THANNHANLIETSIS',
  FETCH_THANNHANLIETSI_LIST: 'thanNhanLietSi/FETCH_THANNHANLIETSI_LIST',
  FETCH_THANNHANLIETSI: 'thanNhanLietSi/FETCH_THANNHANLIETSI',
  CREATE_THANNHANLIETSI: 'thanNhanLietSi/CREATE_THANNHANLIETSI',
  UPDATE_THANNHANLIETSI: 'thanNhanLietSi/UPDATE_THANNHANLIETSI',
  DELETE_THANNHANLIETSI: 'thanNhanLietSi/DELETE_THANNHANLIETSI',
  RESET: 'thanNhanLietSi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThanNhanLietSi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ThanNhanLietSiState = Readonly<typeof initialState>;

// Reducer

export default (state: ThanNhanLietSiState = initialState, action): ThanNhanLietSiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_THANNHANLIETSIS):
    case REQUEST(ACTION_TYPES.FETCH_THANNHANLIETSI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THANNHANLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_THANNHANLIETSI):
    case REQUEST(ACTION_TYPES.UPDATE_THANNHANLIETSI):
    case REQUEST(ACTION_TYPES.DELETE_THANNHANLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_THANNHANLIETSIS):
    case FAILURE(ACTION_TYPES.FETCH_THANNHANLIETSI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THANNHANLIETSI):
    case FAILURE(ACTION_TYPES.CREATE_THANNHANLIETSI):
    case FAILURE(ACTION_TYPES.UPDATE_THANNHANLIETSI):
    case FAILURE(ACTION_TYPES.DELETE_THANNHANLIETSI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_THANNHANLIETSIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THANNHANLIETSI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THANNHANLIETSI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_THANNHANLIETSI):
    case SUCCESS(ACTION_TYPES.UPDATE_THANNHANLIETSI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_THANNHANLIETSI):
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

const apiUrl = 'api/than-nhan-liet-sis';
const apiSearchUrl = 'api/_search/than-nhan-liet-sis';

// Actions

export const getSearchEntities: ICrudSearchAction<IThanNhanLietSi> = query => ({
  type: ACTION_TYPES.SEARCH_THANNHANLIETSIS,
  payload: axios.get<IThanNhanLietSi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IThanNhanLietSi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_THANNHANLIETSI_LIST,
  payload: axios.get<IThanNhanLietSi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IThanNhanLietSi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THANNHANLIETSI,
    payload: axios.get<IThanNhanLietSi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IThanNhanLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THANNHANLIETSI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThanNhanLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THANNHANLIETSI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThanNhanLietSi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THANNHANLIETSI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
