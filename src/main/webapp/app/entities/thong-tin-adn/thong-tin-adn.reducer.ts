import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThongTinADN, defaultValue } from 'app/shared/model/thong-tin-adn.model';

export const ACTION_TYPES = {
  SEARCH_THONGTINADNS: 'thongTinADN/SEARCH_THONGTINADNS',
  FETCH_THONGTINADN_LIST: 'thongTinADN/FETCH_THONGTINADN_LIST',
  FETCH_THONGTINADN: 'thongTinADN/FETCH_THONGTINADN',
  CREATE_THONGTINADN: 'thongTinADN/CREATE_THONGTINADN',
  UPDATE_THONGTINADN: 'thongTinADN/UPDATE_THONGTINADN',
  DELETE_THONGTINADN: 'thongTinADN/DELETE_THONGTINADN',
  RESET: 'thongTinADN/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThongTinADN>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ThongTinADNState = Readonly<typeof initialState>;

// Reducer

export default (state: ThongTinADNState = initialState, action): ThongTinADNState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_THONGTINADNS):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINADN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINADN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_THONGTINADN):
    case REQUEST(ACTION_TYPES.UPDATE_THONGTINADN):
    case REQUEST(ACTION_TYPES.DELETE_THONGTINADN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_THONGTINADNS):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINADN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINADN):
    case FAILURE(ACTION_TYPES.CREATE_THONGTINADN):
    case FAILURE(ACTION_TYPES.UPDATE_THONGTINADN):
    case FAILURE(ACTION_TYPES.DELETE_THONGTINADN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_THONGTINADNS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINADN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINADN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_THONGTINADN):
    case SUCCESS(ACTION_TYPES.UPDATE_THONGTINADN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_THONGTINADN):
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

const apiUrl = 'api/thong-tin-adns';
const apiSearchUrl = 'api/_search/thong-tin-adns';

// Actions

export const getSearchEntities: ICrudSearchAction<IThongTinADN> = query => ({
  type: ACTION_TYPES.SEARCH_THONGTINADNS,
  payload: axios.get<IThongTinADN>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IThongTinADN> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINADN_LIST,
    payload: axios.get<IThongTinADN>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IThongTinADN> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINADN,
    payload: axios.get<IThongTinADN>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IThongTinADN> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THONGTINADN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThongTinADN> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THONGTINADN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThongTinADN> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THONGTINADN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
