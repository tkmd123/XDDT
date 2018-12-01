import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDiemDotBien, defaultValue } from 'app/shared/model/diem-dot-bien.model';

export const ACTION_TYPES = {
  SEARCH_DIEMDOTBIENS: 'diemDotBien/SEARCH_DIEMDOTBIENS',
  FETCH_DIEMDOTBIEN_LIST: 'diemDotBien/FETCH_DIEMDOTBIEN_LIST',
  FETCH_DIEMDOTBIEN: 'diemDotBien/FETCH_DIEMDOTBIEN',
  CREATE_DIEMDOTBIEN: 'diemDotBien/CREATE_DIEMDOTBIEN',
  UPDATE_DIEMDOTBIEN: 'diemDotBien/UPDATE_DIEMDOTBIEN',
  DELETE_DIEMDOTBIEN: 'diemDotBien/DELETE_DIEMDOTBIEN',
  RESET: 'diemDotBien/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDiemDotBien>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type DiemDotBienState = Readonly<typeof initialState>;

// Reducer

export default (state: DiemDotBienState = initialState, action): DiemDotBienState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DIEMDOTBIENS):
    case REQUEST(ACTION_TYPES.FETCH_DIEMDOTBIEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DIEMDOTBIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DIEMDOTBIEN):
    case REQUEST(ACTION_TYPES.UPDATE_DIEMDOTBIEN):
    case REQUEST(ACTION_TYPES.DELETE_DIEMDOTBIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DIEMDOTBIENS):
    case FAILURE(ACTION_TYPES.FETCH_DIEMDOTBIEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DIEMDOTBIEN):
    case FAILURE(ACTION_TYPES.CREATE_DIEMDOTBIEN):
    case FAILURE(ACTION_TYPES.UPDATE_DIEMDOTBIEN):
    case FAILURE(ACTION_TYPES.DELETE_DIEMDOTBIEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DIEMDOTBIENS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIEMDOTBIEN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DIEMDOTBIEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DIEMDOTBIEN):
    case SUCCESS(ACTION_TYPES.UPDATE_DIEMDOTBIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DIEMDOTBIEN):
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

const apiUrl = 'api/diem-dot-biens';
const apiSearchUrl = 'api/_search/diem-dot-biens';

// Actions

export const getSearchEntities: ICrudSearchAction<IDiemDotBien> = query => ({
  type: ACTION_TYPES.SEARCH_DIEMDOTBIENS,
  payload: axios.get<IDiemDotBien>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IDiemDotBien> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_DIEMDOTBIEN_LIST,
    payload: axios.get<IDiemDotBien>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IDiemDotBien> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DIEMDOTBIEN,
    payload: axios.get<IDiemDotBien>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDiemDotBien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DIEMDOTBIEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDiemDotBien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DIEMDOTBIEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDiemDotBien> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DIEMDOTBIEN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
