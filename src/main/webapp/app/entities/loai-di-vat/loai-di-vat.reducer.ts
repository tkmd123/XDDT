import axios from 'axios';
import {
  ICrudSearchAction,
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILoaiDiVat, defaultValue } from 'app/shared/model/loai-di-vat.model';

export const ACTION_TYPES = {
  SEARCH_LOAIDIVATS: 'loaiDiVat/SEARCH_LOAIDIVATS',
  FETCH_LOAIDIVAT_LIST: 'loaiDiVat/FETCH_LOAIDIVAT_LIST',
  FETCH_LOAIDIVAT: 'loaiDiVat/FETCH_LOAIDIVAT',
  CREATE_LOAIDIVAT: 'loaiDiVat/CREATE_LOAIDIVAT',
  UPDATE_LOAIDIVAT: 'loaiDiVat/UPDATE_LOAIDIVAT',
  DELETE_LOAIDIVAT: 'loaiDiVat/DELETE_LOAIDIVAT',
  RESET: 'loaiDiVat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoaiDiVat>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LoaiDiVatState = Readonly<typeof initialState>;

// Reducer

export default (state: LoaiDiVatState = initialState, action): LoaiDiVatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LOAIDIVATS):
    case REQUEST(ACTION_TYPES.FETCH_LOAIDIVAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOAIDIVAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOAIDIVAT):
    case REQUEST(ACTION_TYPES.UPDATE_LOAIDIVAT):
    case REQUEST(ACTION_TYPES.DELETE_LOAIDIVAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LOAIDIVATS):
    case FAILURE(ACTION_TYPES.FETCH_LOAIDIVAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOAIDIVAT):
    case FAILURE(ACTION_TYPES.CREATE_LOAIDIVAT):
    case FAILURE(ACTION_TYPES.UPDATE_LOAIDIVAT):
    case FAILURE(ACTION_TYPES.DELETE_LOAIDIVAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LOAIDIVATS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIDIVAT_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIDIVAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOAIDIVAT):
    case SUCCESS(ACTION_TYPES.UPDATE_LOAIDIVAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOAIDIVAT):
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

const apiUrl = 'api/loai-di-vats';
const apiSearchUrl = 'api/_search/loai-di-vats';

// Actions

export const getSearchEntities: ICrudSearchAction<ILoaiDiVat> = query => ({
  type: ACTION_TYPES.SEARCH_LOAIDIVATS,
  payload: axios.get<ILoaiDiVat>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILoaiDiVat> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIDIVAT_LIST,
    payload: axios.get<ILoaiDiVat>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILoaiDiVat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIDIVAT,
    payload: axios.get<ILoaiDiVat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoaiDiVat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOAIDIVAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<ILoaiDiVat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOAIDIVAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoaiDiVat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOAIDIVAT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
