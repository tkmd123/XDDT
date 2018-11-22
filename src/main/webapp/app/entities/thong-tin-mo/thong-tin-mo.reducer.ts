import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThongTinMo, defaultValue } from 'app/shared/model/thong-tin-mo.model';

export const ACTION_TYPES = {
  SEARCH_THONGTINMOS: 'thongTinMo/SEARCH_THONGTINMOS',
  FETCH_THONGTINMO_LIST: 'thongTinMo/FETCH_THONGTINMO_LIST',
  FETCH_THONGTINMO: 'thongTinMo/FETCH_THONGTINMO',
  CREATE_THONGTINMO: 'thongTinMo/CREATE_THONGTINMO',
  UPDATE_THONGTINMO: 'thongTinMo/UPDATE_THONGTINMO',
  DELETE_THONGTINMO: 'thongTinMo/DELETE_THONGTINMO',
  RESET: 'thongTinMo/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThongTinMo>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ThongTinMoState = Readonly<typeof initialState>;

// Reducer

export default (state: ThongTinMoState = initialState, action): ThongTinMoState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_THONGTINMOS):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINMO_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINMO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_THONGTINMO):
    case REQUEST(ACTION_TYPES.UPDATE_THONGTINMO):
    case REQUEST(ACTION_TYPES.DELETE_THONGTINMO):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_THONGTINMOS):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINMO_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINMO):
    case FAILURE(ACTION_TYPES.CREATE_THONGTINMO):
    case FAILURE(ACTION_TYPES.UPDATE_THONGTINMO):
    case FAILURE(ACTION_TYPES.DELETE_THONGTINMO):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_THONGTINMOS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINMO_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINMO):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_THONGTINMO):
    case SUCCESS(ACTION_TYPES.UPDATE_THONGTINMO):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_THONGTINMO):
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

const apiUrl = 'api/thong-tin-mos';
const apiSearchUrl = 'api/_search/thong-tin-mos';

// Actions

export const getSearchEntities: ICrudSearchAction<IThongTinMo> = query => ({
  type: ACTION_TYPES.SEARCH_THONGTINMOS,
  payload: axios.get<IThongTinMo>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IThongTinMo> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINMO_LIST,
    payload: axios.get<IThongTinMo>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IThongTinMo> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINMO,
    payload: axios.get<IThongTinMo>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IThongTinMo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THONGTINMO,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThongTinMo> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THONGTINMO,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThongTinMo> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THONGTINMO,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
