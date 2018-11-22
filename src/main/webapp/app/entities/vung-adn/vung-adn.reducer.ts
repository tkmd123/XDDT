import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IVungADN, defaultValue } from 'app/shared/model/vung-adn.model';

export const ACTION_TYPES = {
  SEARCH_VUNGADNS: 'vungADN/SEARCH_VUNGADNS',
  FETCH_VUNGADN_LIST: 'vungADN/FETCH_VUNGADN_LIST',
  FETCH_VUNGADN: 'vungADN/FETCH_VUNGADN',
  CREATE_VUNGADN: 'vungADN/CREATE_VUNGADN',
  UPDATE_VUNGADN: 'vungADN/UPDATE_VUNGADN',
  DELETE_VUNGADN: 'vungADN/DELETE_VUNGADN',
  RESET: 'vungADN/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IVungADN>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type VungADNState = Readonly<typeof initialState>;

// Reducer

export default (state: VungADNState = initialState, action): VungADNState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_VUNGADNS):
    case REQUEST(ACTION_TYPES.FETCH_VUNGADN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_VUNGADN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_VUNGADN):
    case REQUEST(ACTION_TYPES.UPDATE_VUNGADN):
    case REQUEST(ACTION_TYPES.DELETE_VUNGADN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_VUNGADNS):
    case FAILURE(ACTION_TYPES.FETCH_VUNGADN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_VUNGADN):
    case FAILURE(ACTION_TYPES.CREATE_VUNGADN):
    case FAILURE(ACTION_TYPES.UPDATE_VUNGADN):
    case FAILURE(ACTION_TYPES.DELETE_VUNGADN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_VUNGADNS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VUNGADN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_VUNGADN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_VUNGADN):
    case SUCCESS(ACTION_TYPES.UPDATE_VUNGADN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_VUNGADN):
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

const apiUrl = 'api/vung-adns';
const apiSearchUrl = 'api/_search/vung-adns';

// Actions

export const getSearchEntities: ICrudSearchAction<IVungADN> = query => ({
  type: ACTION_TYPES.SEARCH_VUNGADNS,
  payload: axios.get<IVungADN>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IVungADN> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_VUNGADN_LIST,
  payload: axios.get<IVungADN>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IVungADN> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_VUNGADN,
    payload: axios.get<IVungADN>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IVungADN> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_VUNGADN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IVungADN> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_VUNGADN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IVungADN> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_VUNGADN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
