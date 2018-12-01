import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThaoTac, defaultValue } from 'app/shared/model/thao-tac.model';

export const ACTION_TYPES = {
  SEARCH_THAOTACS: 'thaoTac/SEARCH_THAOTACS',
  FETCH_THAOTAC_LIST: 'thaoTac/FETCH_THAOTAC_LIST',
  FETCH_THAOTAC: 'thaoTac/FETCH_THAOTAC',
  CREATE_THAOTAC: 'thaoTac/CREATE_THAOTAC',
  UPDATE_THAOTAC: 'thaoTac/UPDATE_THAOTAC',
  DELETE_THAOTAC: 'thaoTac/DELETE_THAOTAC',
  RESET: 'thaoTac/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThaoTac>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ThaoTacState = Readonly<typeof initialState>;

// Reducer

export default (state: ThaoTacState = initialState, action): ThaoTacState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_THAOTACS):
    case REQUEST(ACTION_TYPES.FETCH_THAOTAC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THAOTAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_THAOTAC):
    case REQUEST(ACTION_TYPES.UPDATE_THAOTAC):
    case REQUEST(ACTION_TYPES.DELETE_THAOTAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_THAOTACS):
    case FAILURE(ACTION_TYPES.FETCH_THAOTAC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THAOTAC):
    case FAILURE(ACTION_TYPES.CREATE_THAOTAC):
    case FAILURE(ACTION_TYPES.UPDATE_THAOTAC):
    case FAILURE(ACTION_TYPES.DELETE_THAOTAC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_THAOTACS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THAOTAC_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THAOTAC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_THAOTAC):
    case SUCCESS(ACTION_TYPES.UPDATE_THAOTAC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_THAOTAC):
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

const apiUrl = 'api/thao-tacs';
const apiSearchUrl = 'api/_search/thao-tacs';

// Actions

export const getSearchEntities: ICrudSearchAction<IThaoTac> = query => ({
  type: ACTION_TYPES.SEARCH_THAOTACS,
  payload: axios.get<IThaoTac>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IThaoTac> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_THAOTAC_LIST,
  payload: axios.get<IThaoTac>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IThaoTac> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THAOTAC,
    payload: axios.get<IThaoTac>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IThaoTac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THAOTAC,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThaoTac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THAOTAC,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThaoTac> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THAOTAC,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
