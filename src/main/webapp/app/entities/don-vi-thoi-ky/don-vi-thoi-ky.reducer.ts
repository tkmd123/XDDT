import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDonViThoiKy, defaultValue } from 'app/shared/model/don-vi-thoi-ky.model';

export const ACTION_TYPES = {
  SEARCH_DONVITHOIKIES: 'donViThoiKy/SEARCH_DONVITHOIKIES',
  FETCH_DONVITHOIKY_LIST: 'donViThoiKy/FETCH_DONVITHOIKY_LIST',
  FETCH_DONVITHOIKY: 'donViThoiKy/FETCH_DONVITHOIKY',
  CREATE_DONVITHOIKY: 'donViThoiKy/CREATE_DONVITHOIKY',
  UPDATE_DONVITHOIKY: 'donViThoiKy/UPDATE_DONVITHOIKY',
  DELETE_DONVITHOIKY: 'donViThoiKy/DELETE_DONVITHOIKY',
  RESET: 'donViThoiKy/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDonViThoiKy>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DonViThoiKyState = Readonly<typeof initialState>;

// Reducer

export default (state: DonViThoiKyState = initialState, action): DonViThoiKyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DONVITHOIKIES):
    case REQUEST(ACTION_TYPES.FETCH_DONVITHOIKY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DONVITHOIKY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DONVITHOIKY):
    case REQUEST(ACTION_TYPES.UPDATE_DONVITHOIKY):
    case REQUEST(ACTION_TYPES.DELETE_DONVITHOIKY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DONVITHOIKIES):
    case FAILURE(ACTION_TYPES.FETCH_DONVITHOIKY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DONVITHOIKY):
    case FAILURE(ACTION_TYPES.CREATE_DONVITHOIKY):
    case FAILURE(ACTION_TYPES.UPDATE_DONVITHOIKY):
    case FAILURE(ACTION_TYPES.DELETE_DONVITHOIKY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DONVITHOIKIES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DONVITHOIKY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DONVITHOIKY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DONVITHOIKY):
    case SUCCESS(ACTION_TYPES.UPDATE_DONVITHOIKY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DONVITHOIKY):
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

const apiUrl = 'api/don-vi-thoi-kies';
const apiSearchUrl = 'api/_search/don-vi-thoi-kies';

// Actions

export const getSearchEntities: ICrudSearchAction<IDonViThoiKy> = query => ({
  type: ACTION_TYPES.SEARCH_DONVITHOIKIES,
  payload: axios.get<IDonViThoiKy>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IDonViThoiKy> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DONVITHOIKY_LIST,
  payload: axios.get<IDonViThoiKy>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDonViThoiKy> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DONVITHOIKY,
    payload: axios.get<IDonViThoiKy>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDonViThoiKy> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DONVITHOIKY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDonViThoiKy> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DONVITHOIKY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDonViThoiKy> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DONVITHOIKY,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
