import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICapBac, defaultValue } from 'app/shared/model/cap-bac.model';

export const ACTION_TYPES = {
  SEARCH_CAPBACS: 'capBac/SEARCH_CAPBACS',
  FETCH_CAPBAC_LIST: 'capBac/FETCH_CAPBAC_LIST',
  FETCH_CAPBAC: 'capBac/FETCH_CAPBAC',
  CREATE_CAPBAC: 'capBac/CREATE_CAPBAC',
  UPDATE_CAPBAC: 'capBac/UPDATE_CAPBAC',
  DELETE_CAPBAC: 'capBac/DELETE_CAPBAC',
  RESET: 'capBac/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICapBac>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type CapBacState = Readonly<typeof initialState>;

// Reducer

export default (state: CapBacState = initialState, action): CapBacState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_CAPBACS):
    case REQUEST(ACTION_TYPES.FETCH_CAPBAC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CAPBAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CAPBAC):
    case REQUEST(ACTION_TYPES.UPDATE_CAPBAC):
    case REQUEST(ACTION_TYPES.DELETE_CAPBAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_CAPBACS):
    case FAILURE(ACTION_TYPES.FETCH_CAPBAC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CAPBAC):
    case FAILURE(ACTION_TYPES.CREATE_CAPBAC):
    case FAILURE(ACTION_TYPES.UPDATE_CAPBAC):
    case FAILURE(ACTION_TYPES.DELETE_CAPBAC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_CAPBACS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAPBAC_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CAPBAC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CAPBAC):
    case SUCCESS(ACTION_TYPES.UPDATE_CAPBAC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CAPBAC):
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

const apiUrl = 'api/cap-bacs';
const apiSearchUrl = 'api/_search/cap-bacs';

// Actions

export const getSearchEntities: ICrudSearchAction<ICapBac> = query => ({
  type: ACTION_TYPES.SEARCH_CAPBACS,
  payload: axios.get<ICapBac>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ICapBac> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CAPBAC_LIST,
  payload: axios.get<ICapBac>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ICapBac> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CAPBAC,
    payload: axios.get<ICapBac>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ICapBac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CAPBAC,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICapBac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CAPBAC,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICapBac> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CAPBAC,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
