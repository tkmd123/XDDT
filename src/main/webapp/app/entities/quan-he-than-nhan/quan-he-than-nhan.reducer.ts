import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IQuanHeThanNhan, defaultValue } from 'app/shared/model/quan-he-than-nhan.model';

export const ACTION_TYPES = {
  SEARCH_QUANHETHANNHANS: 'quanHeThanNhan/SEARCH_QUANHETHANNHANS',
  FETCH_QUANHETHANNHAN_LIST: 'quanHeThanNhan/FETCH_QUANHETHANNHAN_LIST',
  FETCH_QUANHETHANNHAN: 'quanHeThanNhan/FETCH_QUANHETHANNHAN',
  CREATE_QUANHETHANNHAN: 'quanHeThanNhan/CREATE_QUANHETHANNHAN',
  UPDATE_QUANHETHANNHAN: 'quanHeThanNhan/UPDATE_QUANHETHANNHAN',
  DELETE_QUANHETHANNHAN: 'quanHeThanNhan/DELETE_QUANHETHANNHAN',
  RESET: 'quanHeThanNhan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IQuanHeThanNhan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type QuanHeThanNhanState = Readonly<typeof initialState>;

// Reducer

export default (state: QuanHeThanNhanState = initialState, action): QuanHeThanNhanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_QUANHETHANNHANS):
    case REQUEST(ACTION_TYPES.FETCH_QUANHETHANNHAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_QUANHETHANNHAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_QUANHETHANNHAN):
    case REQUEST(ACTION_TYPES.UPDATE_QUANHETHANNHAN):
    case REQUEST(ACTION_TYPES.DELETE_QUANHETHANNHAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_QUANHETHANNHANS):
    case FAILURE(ACTION_TYPES.FETCH_QUANHETHANNHAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_QUANHETHANNHAN):
    case FAILURE(ACTION_TYPES.CREATE_QUANHETHANNHAN):
    case FAILURE(ACTION_TYPES.UPDATE_QUANHETHANNHAN):
    case FAILURE(ACTION_TYPES.DELETE_QUANHETHANNHAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_QUANHETHANNHANS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUANHETHANNHAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_QUANHETHANNHAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_QUANHETHANNHAN):
    case SUCCESS(ACTION_TYPES.UPDATE_QUANHETHANNHAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_QUANHETHANNHAN):
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

const apiUrl = 'api/quan-he-than-nhans';
const apiSearchUrl = 'api/_search/quan-he-than-nhans';

// Actions

export const getSearchEntities: ICrudSearchAction<IQuanHeThanNhan> = query => ({
  type: ACTION_TYPES.SEARCH_QUANHETHANNHANS,
  payload: axios.get<IQuanHeThanNhan>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IQuanHeThanNhan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_QUANHETHANNHAN_LIST,
  payload: axios.get<IQuanHeThanNhan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IQuanHeThanNhan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_QUANHETHANNHAN,
    payload: axios.get<IQuanHeThanNhan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IQuanHeThanNhan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_QUANHETHANNHAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IQuanHeThanNhan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_QUANHETHANNHAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IQuanHeThanNhan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_QUANHETHANNHAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
