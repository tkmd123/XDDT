import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMauTachChiet, defaultValue } from 'app/shared/model/mau-tach-chiet.model';

export const ACTION_TYPES = {
  SEARCH_MAUTACHCHIETS: 'mauTachChiet/SEARCH_MAUTACHCHIETS',
  FETCH_MAUTACHCHIET_LIST: 'mauTachChiet/FETCH_MAUTACHCHIET_LIST',
  FETCH_MAUTACHCHIET: 'mauTachChiet/FETCH_MAUTACHCHIET',
  CREATE_MAUTACHCHIET: 'mauTachChiet/CREATE_MAUTACHCHIET',
  UPDATE_MAUTACHCHIET: 'mauTachChiet/UPDATE_MAUTACHCHIET',
  DELETE_MAUTACHCHIET: 'mauTachChiet/DELETE_MAUTACHCHIET',
  RESET: 'mauTachChiet/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMauTachChiet>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MauTachChietState = Readonly<typeof initialState>;

// Reducer

export default (state: MauTachChietState = initialState, action): MauTachChietState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MAUTACHCHIETS):
    case REQUEST(ACTION_TYPES.FETCH_MAUTACHCHIET_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MAUTACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MAUTACHCHIET):
    case REQUEST(ACTION_TYPES.UPDATE_MAUTACHCHIET):
    case REQUEST(ACTION_TYPES.DELETE_MAUTACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_MAUTACHCHIETS):
    case FAILURE(ACTION_TYPES.FETCH_MAUTACHCHIET_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MAUTACHCHIET):
    case FAILURE(ACTION_TYPES.CREATE_MAUTACHCHIET):
    case FAILURE(ACTION_TYPES.UPDATE_MAUTACHCHIET):
    case FAILURE(ACTION_TYPES.DELETE_MAUTACHCHIET):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MAUTACHCHIETS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAUTACHCHIET_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAUTACHCHIET):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MAUTACHCHIET):
    case SUCCESS(ACTION_TYPES.UPDATE_MAUTACHCHIET):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MAUTACHCHIET):
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

const apiUrl = 'api/mau-tach-chiets';
const apiSearchUrl = 'api/_search/mau-tach-chiets';

// Actions

export const getSearchEntities: ICrudSearchAction<IMauTachChiet> = query => ({
  type: ACTION_TYPES.SEARCH_MAUTACHCHIETS,
  payload: axios.get<IMauTachChiet>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IMauTachChiet> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MAUTACHCHIET_LIST,
  payload: axios.get<IMauTachChiet>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMauTachChiet> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MAUTACHCHIET,
    payload: axios.get<IMauTachChiet>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMauTachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MAUTACHCHIET,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMauTachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MAUTACHCHIET,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMauTachChiet> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MAUTACHCHIET,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
