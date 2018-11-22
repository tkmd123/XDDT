import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IChucVu, defaultValue } from 'app/shared/model/chuc-vu.model';

export const ACTION_TYPES = {
  SEARCH_CHUCVUS: 'chucVu/SEARCH_CHUCVUS',
  FETCH_CHUCVU_LIST: 'chucVu/FETCH_CHUCVU_LIST',
  FETCH_CHUCVU: 'chucVu/FETCH_CHUCVU',
  CREATE_CHUCVU: 'chucVu/CREATE_CHUCVU',
  UPDATE_CHUCVU: 'chucVu/UPDATE_CHUCVU',
  DELETE_CHUCVU: 'chucVu/DELETE_CHUCVU',
  RESET: 'chucVu/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IChucVu>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ChucVuState = Readonly<typeof initialState>;

// Reducer

export default (state: ChucVuState = initialState, action): ChucVuState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_CHUCVUS):
    case REQUEST(ACTION_TYPES.FETCH_CHUCVU_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CHUCVU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CHUCVU):
    case REQUEST(ACTION_TYPES.UPDATE_CHUCVU):
    case REQUEST(ACTION_TYPES.DELETE_CHUCVU):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_CHUCVUS):
    case FAILURE(ACTION_TYPES.FETCH_CHUCVU_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CHUCVU):
    case FAILURE(ACTION_TYPES.CREATE_CHUCVU):
    case FAILURE(ACTION_TYPES.UPDATE_CHUCVU):
    case FAILURE(ACTION_TYPES.DELETE_CHUCVU):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_CHUCVUS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHUCVU_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHUCVU):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CHUCVU):
    case SUCCESS(ACTION_TYPES.UPDATE_CHUCVU):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CHUCVU):
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

const apiUrl = 'api/chuc-vus';
const apiSearchUrl = 'api/_search/chuc-vus';

// Actions

export const getSearchEntities: ICrudSearchAction<IChucVu> = query => ({
  type: ACTION_TYPES.SEARCH_CHUCVUS,
  payload: axios.get<IChucVu>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IChucVu> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CHUCVU_LIST,
  payload: axios.get<IChucVu>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IChucVu> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CHUCVU,
    payload: axios.get<IChucVu>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IChucVu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHUCVU,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IChucVu> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CHUCVU,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IChucVu> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CHUCVU,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
