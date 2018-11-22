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

import { ILoaiHinhThaiHaiCot, defaultValue } from 'app/shared/model/loai-hinh-thai-hai-cot.model';

export const ACTION_TYPES = {
  SEARCH_LOAIHINHTHAIHAICOTS: 'loaiHinhThaiHaiCot/SEARCH_LOAIHINHTHAIHAICOTS',
  FETCH_LOAIHINHTHAIHAICOT_LIST: 'loaiHinhThaiHaiCot/FETCH_LOAIHINHTHAIHAICOT_LIST',
  FETCH_LOAIHINHTHAIHAICOT: 'loaiHinhThaiHaiCot/FETCH_LOAIHINHTHAIHAICOT',
  CREATE_LOAIHINHTHAIHAICOT: 'loaiHinhThaiHaiCot/CREATE_LOAIHINHTHAIHAICOT',
  UPDATE_LOAIHINHTHAIHAICOT: 'loaiHinhThaiHaiCot/UPDATE_LOAIHINHTHAIHAICOT',
  DELETE_LOAIHINHTHAIHAICOT: 'loaiHinhThaiHaiCot/DELETE_LOAIHINHTHAIHAICOT',
  RESET: 'loaiHinhThaiHaiCot/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoaiHinhThaiHaiCot>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LoaiHinhThaiHaiCotState = Readonly<typeof initialState>;

// Reducer

export default (state: LoaiHinhThaiHaiCotState = initialState, action): LoaiHinhThaiHaiCotState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LOAIHINHTHAIHAICOTS):
    case REQUEST(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOAIHINHTHAIHAICOT):
    case REQUEST(ACTION_TYPES.UPDATE_LOAIHINHTHAIHAICOT):
    case REQUEST(ACTION_TYPES.DELETE_LOAIHINHTHAIHAICOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LOAIHINHTHAIHAICOTS):
    case FAILURE(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.CREATE_LOAIHINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.UPDATE_LOAIHINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.DELETE_LOAIHINHTHAIHAICOT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LOAIHINHTHAIHAICOTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOAIHINHTHAIHAICOT):
    case SUCCESS(ACTION_TYPES.UPDATE_LOAIHINHTHAIHAICOT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOAIHINHTHAIHAICOT):
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

const apiUrl = 'api/loai-hinh-thai-hai-cots';
const apiSearchUrl = 'api/_search/loai-hinh-thai-hai-cots';

// Actions

export const getSearchEntities: ICrudSearchAction<ILoaiHinhThaiHaiCot> = query => ({
  type: ACTION_TYPES.SEARCH_LOAIHINHTHAIHAICOTS,
  payload: axios.get<ILoaiHinhThaiHaiCot>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILoaiHinhThaiHaiCot> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT_LIST,
    payload: axios.get<ILoaiHinhThaiHaiCot>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILoaiHinhThaiHaiCot> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIHINHTHAIHAICOT,
    payload: axios.get<ILoaiHinhThaiHaiCot>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoaiHinhThaiHaiCot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOAIHINHTHAIHAICOT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<ILoaiHinhThaiHaiCot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOAIHINHTHAIHAICOT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoaiHinhThaiHaiCot> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOAIHINHTHAIHAICOT,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
