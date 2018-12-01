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

import { ILoaiThaoTac, defaultValue } from 'app/shared/model/loai-thao-tac.model';

export const ACTION_TYPES = {
  SEARCH_LOAITHAOTACS: 'loaiThaoTac/SEARCH_LOAITHAOTACS',
  FETCH_LOAITHAOTAC_LIST: 'loaiThaoTac/FETCH_LOAITHAOTAC_LIST',
  FETCH_LOAITHAOTAC: 'loaiThaoTac/FETCH_LOAITHAOTAC',
  CREATE_LOAITHAOTAC: 'loaiThaoTac/CREATE_LOAITHAOTAC',
  UPDATE_LOAITHAOTAC: 'loaiThaoTac/UPDATE_LOAITHAOTAC',
  DELETE_LOAITHAOTAC: 'loaiThaoTac/DELETE_LOAITHAOTAC',
  RESET: 'loaiThaoTac/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILoaiThaoTac>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type LoaiThaoTacState = Readonly<typeof initialState>;

// Reducer

export default (state: LoaiThaoTacState = initialState, action): LoaiThaoTacState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_LOAITHAOTACS):
    case REQUEST(ACTION_TYPES.FETCH_LOAITHAOTAC_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LOAITHAOTAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LOAITHAOTAC):
    case REQUEST(ACTION_TYPES.UPDATE_LOAITHAOTAC):
    case REQUEST(ACTION_TYPES.DELETE_LOAITHAOTAC):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_LOAITHAOTACS):
    case FAILURE(ACTION_TYPES.FETCH_LOAITHAOTAC_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LOAITHAOTAC):
    case FAILURE(ACTION_TYPES.CREATE_LOAITHAOTAC):
    case FAILURE(ACTION_TYPES.UPDATE_LOAITHAOTAC):
    case FAILURE(ACTION_TYPES.DELETE_LOAITHAOTAC):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_LOAITHAOTACS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAITHAOTAC_LIST):
      const links = parseHeaderForLinks(action.payload.headers.link);
      return {
        ...state,
        links,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links)
      };
    case SUCCESS(ACTION_TYPES.FETCH_LOAITHAOTAC):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LOAITHAOTAC):
    case SUCCESS(ACTION_TYPES.UPDATE_LOAITHAOTAC):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LOAITHAOTAC):
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

const apiUrl = 'api/loai-thao-tacs';
const apiSearchUrl = 'api/_search/loai-thao-tacs';

// Actions

export const getSearchEntities: ICrudSearchAction<ILoaiThaoTac> = query => ({
  type: ACTION_TYPES.SEARCH_LOAITHAOTACS,
  payload: axios.get<ILoaiThaoTac>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ILoaiThaoTac> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_LOAITHAOTAC_LIST,
    payload: axios.get<ILoaiThaoTac>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<ILoaiThaoTac> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LOAITHAOTAC,
    payload: axios.get<ILoaiThaoTac>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILoaiThaoTac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LOAITHAOTAC,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const updateEntity: ICrudPutAction<ILoaiThaoTac> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LOAITHAOTAC,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILoaiThaoTac> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LOAITHAOTAC,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
