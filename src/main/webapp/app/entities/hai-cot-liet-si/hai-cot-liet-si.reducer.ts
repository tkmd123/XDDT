import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHaiCotLietSi, defaultValue } from 'app/shared/model/hai-cot-liet-si.model';

export const ACTION_TYPES = {
  SEARCH_HAICOTLIETSIS: 'haiCotLietSi/SEARCH_HAICOTLIETSIS',
  FETCH_HAICOTLIETSI_LIST: 'haiCotLietSi/FETCH_HAICOTLIETSI_LIST',
  FETCH_HAICOTLIETSI: 'haiCotLietSi/FETCH_HAICOTLIETSI',
  CREATE_HAICOTLIETSI: 'haiCotLietSi/CREATE_HAICOTLIETSI',
  UPDATE_HAICOTLIETSI: 'haiCotLietSi/UPDATE_HAICOTLIETSI',
  DELETE_HAICOTLIETSI: 'haiCotLietSi/DELETE_HAICOTLIETSI',
  RESET: 'haiCotLietSi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHaiCotLietSi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HaiCotLietSiState = Readonly<typeof initialState>;

// Reducer

export default (state: HaiCotLietSiState = initialState, action): HaiCotLietSiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HAICOTLIETSIS):
    case REQUEST(ACTION_TYPES.FETCH_HAICOTLIETSI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HAICOTLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HAICOTLIETSI):
    case REQUEST(ACTION_TYPES.UPDATE_HAICOTLIETSI):
    case REQUEST(ACTION_TYPES.DELETE_HAICOTLIETSI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HAICOTLIETSIS):
    case FAILURE(ACTION_TYPES.FETCH_HAICOTLIETSI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HAICOTLIETSI):
    case FAILURE(ACTION_TYPES.CREATE_HAICOTLIETSI):
    case FAILURE(ACTION_TYPES.UPDATE_HAICOTLIETSI):
    case FAILURE(ACTION_TYPES.DELETE_HAICOTLIETSI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HAICOTLIETSIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HAICOTLIETSI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HAICOTLIETSI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HAICOTLIETSI):
    case SUCCESS(ACTION_TYPES.UPDATE_HAICOTLIETSI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HAICOTLIETSI):
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

const apiUrl = 'api/hai-cot-liet-sis';
const apiSearchUrl = 'api/_search/hai-cot-liet-sis';

// Actions

export const getSearchEntities: ICrudSearchAction<IHaiCotLietSi> = query => ({
  type: ACTION_TYPES.SEARCH_HAICOTLIETSIS,
  payload: axios.get<IHaiCotLietSi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHaiCotLietSi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HAICOTLIETSI_LIST,
  payload: axios.get<IHaiCotLietSi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHaiCotLietSi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HAICOTLIETSI,
    payload: axios.get<IHaiCotLietSi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHaiCotLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HAICOTLIETSI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHaiCotLietSi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HAICOTLIETSI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHaiCotLietSi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HAICOTLIETSI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
