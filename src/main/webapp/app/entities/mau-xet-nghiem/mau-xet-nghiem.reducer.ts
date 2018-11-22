import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMauXetNghiem, defaultValue } from 'app/shared/model/mau-xet-nghiem.model';

export const ACTION_TYPES = {
  SEARCH_MAUXETNGHIEMS: 'mauXetNghiem/SEARCH_MAUXETNGHIEMS',
  FETCH_MAUXETNGHIEM_LIST: 'mauXetNghiem/FETCH_MAUXETNGHIEM_LIST',
  FETCH_MAUXETNGHIEM: 'mauXetNghiem/FETCH_MAUXETNGHIEM',
  CREATE_MAUXETNGHIEM: 'mauXetNghiem/CREATE_MAUXETNGHIEM',
  UPDATE_MAUXETNGHIEM: 'mauXetNghiem/UPDATE_MAUXETNGHIEM',
  DELETE_MAUXETNGHIEM: 'mauXetNghiem/DELETE_MAUXETNGHIEM',
  RESET: 'mauXetNghiem/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMauXetNghiem>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type MauXetNghiemState = Readonly<typeof initialState>;

// Reducer

export default (state: MauXetNghiemState = initialState, action): MauXetNghiemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MAUXETNGHIEMS):
    case REQUEST(ACTION_TYPES.FETCH_MAUXETNGHIEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MAUXETNGHIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MAUXETNGHIEM):
    case REQUEST(ACTION_TYPES.UPDATE_MAUXETNGHIEM):
    case REQUEST(ACTION_TYPES.DELETE_MAUXETNGHIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_MAUXETNGHIEMS):
    case FAILURE(ACTION_TYPES.FETCH_MAUXETNGHIEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.CREATE_MAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.UPDATE_MAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.DELETE_MAUXETNGHIEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MAUXETNGHIEMS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAUXETNGHIEM_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAUXETNGHIEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MAUXETNGHIEM):
    case SUCCESS(ACTION_TYPES.UPDATE_MAUXETNGHIEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MAUXETNGHIEM):
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

const apiUrl = 'api/mau-xet-nghiems';
const apiSearchUrl = 'api/_search/mau-xet-nghiems';

// Actions

export const getSearchEntities: ICrudSearchAction<IMauXetNghiem> = query => ({
  type: ACTION_TYPES.SEARCH_MAUXETNGHIEMS,
  payload: axios.get<IMauXetNghiem>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IMauXetNghiem> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_MAUXETNGHIEM_LIST,
    payload: axios.get<IMauXetNghiem>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IMauXetNghiem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MAUXETNGHIEM,
    payload: axios.get<IMauXetNghiem>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMauXetNghiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MAUXETNGHIEM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMauXetNghiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MAUXETNGHIEM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMauXetNghiem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MAUXETNGHIEM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
