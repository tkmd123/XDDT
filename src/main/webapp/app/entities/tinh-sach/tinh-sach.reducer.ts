import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITinhSach, defaultValue } from 'app/shared/model/tinh-sach.model';

export const ACTION_TYPES = {
  SEARCH_TINHSACHES: 'tinhSach/SEARCH_TINHSACHES',
  FETCH_TINHSACH_LIST: 'tinhSach/FETCH_TINHSACH_LIST',
  FETCH_TINHSACH: 'tinhSach/FETCH_TINHSACH',
  CREATE_TINHSACH: 'tinhSach/CREATE_TINHSACH',
  UPDATE_TINHSACH: 'tinhSach/UPDATE_TINHSACH',
  DELETE_TINHSACH: 'tinhSach/DELETE_TINHSACH',
  RESET: 'tinhSach/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITinhSach>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TinhSachState = Readonly<typeof initialState>;

// Reducer

export default (state: TinhSachState = initialState, action): TinhSachState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TINHSACHES):
    case REQUEST(ACTION_TYPES.FETCH_TINHSACH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TINHSACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TINHSACH):
    case REQUEST(ACTION_TYPES.UPDATE_TINHSACH):
    case REQUEST(ACTION_TYPES.DELETE_TINHSACH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TINHSACHES):
    case FAILURE(ACTION_TYPES.FETCH_TINHSACH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TINHSACH):
    case FAILURE(ACTION_TYPES.CREATE_TINHSACH):
    case FAILURE(ACTION_TYPES.UPDATE_TINHSACH):
    case FAILURE(ACTION_TYPES.DELETE_TINHSACH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TINHSACHES):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHSACH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHSACH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TINHSACH):
    case SUCCESS(ACTION_TYPES.UPDATE_TINHSACH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TINHSACH):
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

const apiUrl = 'api/tinh-saches';
const apiSearchUrl = 'api/_search/tinh-saches';

// Actions

export const getSearchEntities: ICrudSearchAction<ITinhSach> = query => ({
  type: ACTION_TYPES.SEARCH_TINHSACHES,
  payload: axios.get<ITinhSach>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITinhSach> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TINHSACH_LIST,
  payload: axios.get<ITinhSach>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITinhSach> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TINHSACH,
    payload: axios.get<ITinhSach>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITinhSach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TINHSACH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITinhSach> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TINHSACH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITinhSach> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TINHSACH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
