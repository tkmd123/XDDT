import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITachChiet, defaultValue } from 'app/shared/model/tach-chiet.model';

export const ACTION_TYPES = {
  SEARCH_TACHCHIETS: 'tachChiet/SEARCH_TACHCHIETS',
  FETCH_TACHCHIET_LIST: 'tachChiet/FETCH_TACHCHIET_LIST',
  FETCH_TACHCHIET: 'tachChiet/FETCH_TACHCHIET',
  CREATE_TACHCHIET: 'tachChiet/CREATE_TACHCHIET',
  UPDATE_TACHCHIET: 'tachChiet/UPDATE_TACHCHIET',
  DELETE_TACHCHIET: 'tachChiet/DELETE_TACHCHIET',
  RESET: 'tachChiet/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITachChiet>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TachChietState = Readonly<typeof initialState>;

// Reducer

export default (state: TachChietState = initialState, action): TachChietState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TACHCHIETS):
    case REQUEST(ACTION_TYPES.FETCH_TACHCHIET_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TACHCHIET):
    case REQUEST(ACTION_TYPES.UPDATE_TACHCHIET):
    case REQUEST(ACTION_TYPES.DELETE_TACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TACHCHIETS):
    case FAILURE(ACTION_TYPES.FETCH_TACHCHIET_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TACHCHIET):
    case FAILURE(ACTION_TYPES.CREATE_TACHCHIET):
    case FAILURE(ACTION_TYPES.UPDATE_TACHCHIET):
    case FAILURE(ACTION_TYPES.DELETE_TACHCHIET):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TACHCHIETS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TACHCHIET_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TACHCHIET):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TACHCHIET):
    case SUCCESS(ACTION_TYPES.UPDATE_TACHCHIET):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TACHCHIET):
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

const apiUrl = 'api/tach-chiets';
const apiSearchUrl = 'api/_search/tach-chiets';

// Actions

export const getSearchEntities: ICrudSearchAction<ITachChiet> = query => ({
  type: ACTION_TYPES.SEARCH_TACHCHIETS,
  payload: axios.get<ITachChiet>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITachChiet> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TACHCHIET_LIST,
  payload: axios.get<ITachChiet>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITachChiet> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TACHCHIET,
    payload: axios.get<ITachChiet>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TACHCHIET,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TACHCHIET,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITachChiet> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TACHCHIET,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
