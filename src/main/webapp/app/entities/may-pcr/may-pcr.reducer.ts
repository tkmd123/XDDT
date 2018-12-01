import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMayPCR, defaultValue } from 'app/shared/model/may-pcr.model';

export const ACTION_TYPES = {
  SEARCH_MAYPCRS: 'mayPCR/SEARCH_MAYPCRS',
  FETCH_MAYPCR_LIST: 'mayPCR/FETCH_MAYPCR_LIST',
  FETCH_MAYPCR: 'mayPCR/FETCH_MAYPCR',
  CREATE_MAYPCR: 'mayPCR/CREATE_MAYPCR',
  UPDATE_MAYPCR: 'mayPCR/UPDATE_MAYPCR',
  DELETE_MAYPCR: 'mayPCR/DELETE_MAYPCR',
  RESET: 'mayPCR/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMayPCR>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MayPCRState = Readonly<typeof initialState>;

// Reducer

export default (state: MayPCRState = initialState, action): MayPCRState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MAYPCRS):
    case REQUEST(ACTION_TYPES.FETCH_MAYPCR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MAYPCR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MAYPCR):
    case REQUEST(ACTION_TYPES.UPDATE_MAYPCR):
    case REQUEST(ACTION_TYPES.DELETE_MAYPCR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_MAYPCRS):
    case FAILURE(ACTION_TYPES.FETCH_MAYPCR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MAYPCR):
    case FAILURE(ACTION_TYPES.CREATE_MAYPCR):
    case FAILURE(ACTION_TYPES.UPDATE_MAYPCR):
    case FAILURE(ACTION_TYPES.DELETE_MAYPCR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MAYPCRS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAYPCR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAYPCR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MAYPCR):
    case SUCCESS(ACTION_TYPES.UPDATE_MAYPCR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MAYPCR):
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

const apiUrl = 'api/may-pcrs';
const apiSearchUrl = 'api/_search/may-pcrs';

// Actions

export const getSearchEntities: ICrudSearchAction<IMayPCR> = query => ({
  type: ACTION_TYPES.SEARCH_MAYPCRS,
  payload: axios.get<IMayPCR>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IMayPCR> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MAYPCR_LIST,
  payload: axios.get<IMayPCR>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMayPCR> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MAYPCR,
    payload: axios.get<IMayPCR>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMayPCR> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MAYPCR,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMayPCR> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MAYPCR,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMayPCR> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MAYPCR,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
