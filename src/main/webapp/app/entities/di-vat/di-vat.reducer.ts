import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDiVat, defaultValue } from 'app/shared/model/di-vat.model';

export const ACTION_TYPES = {
  SEARCH_DIVATS: 'diVat/SEARCH_DIVATS',
  FETCH_DIVAT_LIST: 'diVat/FETCH_DIVAT_LIST',
  FETCH_DIVAT: 'diVat/FETCH_DIVAT',
  CREATE_DIVAT: 'diVat/CREATE_DIVAT',
  UPDATE_DIVAT: 'diVat/UPDATE_DIVAT',
  DELETE_DIVAT: 'diVat/DELETE_DIVAT',
  RESET: 'diVat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDiVat>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DiVatState = Readonly<typeof initialState>;

// Reducer

export default (state: DiVatState = initialState, action): DiVatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DIVATS):
    case REQUEST(ACTION_TYPES.FETCH_DIVAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIVAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DIVAT):
    case REQUEST(ACTION_TYPES.UPDATE_DIVAT):
    case REQUEST(ACTION_TYPES.DELETE_DIVAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DIVATS):
    case FAILURE(ACTION_TYPES.FETCH_DIVAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIVAT):
    case FAILURE(ACTION_TYPES.CREATE_DIVAT):
    case FAILURE(ACTION_TYPES.UPDATE_DIVAT):
    case FAILURE(ACTION_TYPES.DELETE_DIVAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DIVATS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIVAT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIVAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIVAT):
    case SUCCESS(ACTION_TYPES.UPDATE_DIVAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIVAT):
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

const apiUrl = 'api/di-vats';
const apiSearchUrl = 'api/_search/di-vats';

// Actions

export const getSearchEntities: ICrudSearchAction<IDiVat> = query => ({
  type: ACTION_TYPES.SEARCH_DIVATS,
  payload: axios.get<IDiVat>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IDiVat> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DIVAT_LIST,
  payload: axios.get<IDiVat>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDiVat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIVAT,
    payload: axios.get<IDiVat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDiVat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIVAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDiVat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIVAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDiVat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIVAT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
