import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITrungTam, defaultValue } from 'app/shared/model/trung-tam.model';

export const ACTION_TYPES = {
  SEARCH_TRUNGTAMS: 'trungTam/SEARCH_TRUNGTAMS',
  FETCH_TRUNGTAM_LIST: 'trungTam/FETCH_TRUNGTAM_LIST',
  FETCH_TRUNGTAM: 'trungTam/FETCH_TRUNGTAM',
  CREATE_TRUNGTAM: 'trungTam/CREATE_TRUNGTAM',
  UPDATE_TRUNGTAM: 'trungTam/UPDATE_TRUNGTAM',
  DELETE_TRUNGTAM: 'trungTam/DELETE_TRUNGTAM',
  RESET: 'trungTam/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITrungTam>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TrungTamState = Readonly<typeof initialState>;

// Reducer

export default (state: TrungTamState = initialState, action): TrungTamState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TRUNGTAMS):
    case REQUEST(ACTION_TYPES.FETCH_TRUNGTAM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRUNGTAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TRUNGTAM):
    case REQUEST(ACTION_TYPES.UPDATE_TRUNGTAM):
    case REQUEST(ACTION_TYPES.DELETE_TRUNGTAM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TRUNGTAMS):
    case FAILURE(ACTION_TYPES.FETCH_TRUNGTAM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRUNGTAM):
    case FAILURE(ACTION_TYPES.CREATE_TRUNGTAM):
    case FAILURE(ACTION_TYPES.UPDATE_TRUNGTAM):
    case FAILURE(ACTION_TYPES.DELETE_TRUNGTAM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TRUNGTAMS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRUNGTAM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRUNGTAM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRUNGTAM):
    case SUCCESS(ACTION_TYPES.UPDATE_TRUNGTAM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRUNGTAM):
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

const apiUrl = 'api/trung-tams';
const apiSearchUrl = 'api/_search/trung-tams';

// Actions

export const getSearchEntities: ICrudSearchAction<ITrungTam> = query => ({
  type: ACTION_TYPES.SEARCH_TRUNGTAMS,
  payload: axios.get<ITrungTam>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITrungTam> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRUNGTAM_LIST,
  payload: axios.get<ITrungTam>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITrungTam> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRUNGTAM,
    payload: axios.get<ITrungTam>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITrungTam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRUNGTAM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITrungTam> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRUNGTAM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITrungTam> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRUNGTAM,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
