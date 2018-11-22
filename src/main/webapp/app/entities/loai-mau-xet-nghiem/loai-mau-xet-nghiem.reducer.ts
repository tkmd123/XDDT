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

import { ILoaiMauXetNghiem, defaultValue } from 'app/shared/model/loai-mau-xet-nghiem.model';

export const ACTION_TYPES = {
  SEARCH_LOAIMAUXETNGHIEMS: 'loaiMauXetNghiem/SEARCH_LOAIMAUXETNGHIEMS',
  FETCH_LOAIMAUXETNGHIEM_LIST: 'loaiMauXetNghiem/FETCH_LOAIMAUXETNGHIEM_LIST',
  FETCH_LOAIMAUXETNGHIEM: 'loaiMauXetNghiem/FETCH_LOAIMAUXETNGHIEM',
  CREATE_LOAIMAUXETNGHIEM: 'loaiMauXetNghiem/CREATE_LOAIMAUXETNGHIEM',
  UPDATE_LOAIMAUXETNGHIEM: 'loaiMauXetNghiem/UPDATE_LOAIMAUXETNGHIEM',
  DELETE_LOAIMAUXETNGHIEM: 'loaiMauXetNghiem/DELETE_LOAIMAUXETNGHIEM',
  RESET: 'loaiMauXetNghiem/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoaiMauXetNghiem>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LoaiMauXetNghiemState = Readonly<typeof initialState>;

// Reducer

export default (state: LoaiMauXetNghiemState = initialState, action): LoaiMauXetNghiemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LOAIMAUXETNGHIEMS):
    case REQUEST(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOAIMAUXETNGHIEM):
    case REQUEST(ACTION_TYPES.UPDATE_LOAIMAUXETNGHIEM):
    case REQUEST(ACTION_TYPES.DELETE_LOAIMAUXETNGHIEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LOAIMAUXETNGHIEMS):
    case FAILURE(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.CREATE_LOAIMAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.UPDATE_LOAIMAUXETNGHIEM):
    case FAILURE(ACTION_TYPES.DELETE_LOAIMAUXETNGHIEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LOAIMAUXETNGHIEMS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOAIMAUXETNGHIEM):
    case SUCCESS(ACTION_TYPES.UPDATE_LOAIMAUXETNGHIEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOAIMAUXETNGHIEM):
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

const apiUrl = 'api/loai-mau-xet-nghiems';
const apiSearchUrl = 'api/_search/loai-mau-xet-nghiems';

// Actions

export const getSearchEntities: ICrudSearchAction<ILoaiMauXetNghiem> = query => ({
  type: ACTION_TYPES.SEARCH_LOAIMAUXETNGHIEMS,
  payload: axios.get<ILoaiMauXetNghiem>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILoaiMauXetNghiem> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM_LIST,
    payload: axios.get<ILoaiMauXetNghiem>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILoaiMauXetNghiem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOAIMAUXETNGHIEM,
    payload: axios.get<ILoaiMauXetNghiem>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoaiMauXetNghiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOAIMAUXETNGHIEM,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<ILoaiMauXetNghiem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOAIMAUXETNGHIEM,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoaiMauXetNghiem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOAIMAUXETNGHIEM,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
