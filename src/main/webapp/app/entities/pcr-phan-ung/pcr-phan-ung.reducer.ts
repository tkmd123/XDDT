import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPCRPhanUng, defaultValue } from 'app/shared/model/pcr-phan-ung.model';

export const ACTION_TYPES = {
  SEARCH_PCRPHANUNGS: 'pCRPhanUng/SEARCH_PCRPHANUNGS',
  FETCH_PCRPHANUNG_LIST: 'pCRPhanUng/FETCH_PCRPHANUNG_LIST',
  FETCH_PCRPHANUNG: 'pCRPhanUng/FETCH_PCRPHANUNG',
  CREATE_PCRPHANUNG: 'pCRPhanUng/CREATE_PCRPHANUNG',
  UPDATE_PCRPHANUNG: 'pCRPhanUng/UPDATE_PCRPHANUNG',
  DELETE_PCRPHANUNG: 'pCRPhanUng/DELETE_PCRPHANUNG',
  RESET: 'pCRPhanUng/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPCRPhanUng>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PCRPhanUngState = Readonly<typeof initialState>;

// Reducer

export default (state: PCRPhanUngState = initialState, action): PCRPhanUngState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PCRPHANUNGS):
    case REQUEST(ACTION_TYPES.FETCH_PCRPHANUNG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PCRPHANUNG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PCRPHANUNG):
    case REQUEST(ACTION_TYPES.UPDATE_PCRPHANUNG):
    case REQUEST(ACTION_TYPES.DELETE_PCRPHANUNG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PCRPHANUNGS):
    case FAILURE(ACTION_TYPES.FETCH_PCRPHANUNG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PCRPHANUNG):
    case FAILURE(ACTION_TYPES.CREATE_PCRPHANUNG):
    case FAILURE(ACTION_TYPES.UPDATE_PCRPHANUNG):
    case FAILURE(ACTION_TYPES.DELETE_PCRPHANUNG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PCRPHANUNGS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRPHANUNG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PCRPHANUNG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PCRPHANUNG):
    case SUCCESS(ACTION_TYPES.UPDATE_PCRPHANUNG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PCRPHANUNG):
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

const apiUrl = 'api/pcr-phan-ungs';
const apiSearchUrl = 'api/_search/pcr-phan-ungs';

// Actions

export const getSearchEntities: ICrudSearchAction<IPCRPhanUng> = query => ({
  type: ACTION_TYPES.SEARCH_PCRPHANUNGS,
  payload: axios.get<IPCRPhanUng>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPCRPhanUng> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PCRPHANUNG_LIST,
  payload: axios.get<IPCRPhanUng>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPCRPhanUng> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PCRPHANUNG,
    payload: axios.get<IPCRPhanUng>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPCRPhanUng> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PCRPHANUNG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPCRPhanUng> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PCRPHANUNG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPCRPhanUng> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PCRPHANUNG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
