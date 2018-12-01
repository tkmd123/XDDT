import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IThongTinKhaiQuat, defaultValue } from 'app/shared/model/thong-tin-khai-quat.model';

export const ACTION_TYPES = {
  SEARCH_THONGTINKHAIQUATS: 'thongTinKhaiQuat/SEARCH_THONGTINKHAIQUATS',
  FETCH_THONGTINKHAIQUAT_LIST: 'thongTinKhaiQuat/FETCH_THONGTINKHAIQUAT_LIST',
  FETCH_THONGTINKHAIQUAT: 'thongTinKhaiQuat/FETCH_THONGTINKHAIQUAT',
  CREATE_THONGTINKHAIQUAT: 'thongTinKhaiQuat/CREATE_THONGTINKHAIQUAT',
  UPDATE_THONGTINKHAIQUAT: 'thongTinKhaiQuat/UPDATE_THONGTINKHAIQUAT',
  DELETE_THONGTINKHAIQUAT: 'thongTinKhaiQuat/DELETE_THONGTINKHAIQUAT',
  RESET: 'thongTinKhaiQuat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IThongTinKhaiQuat>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type ThongTinKhaiQuatState = Readonly<typeof initialState>;

// Reducer

export default (state: ThongTinKhaiQuatState = initialState, action): ThongTinKhaiQuatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_THONGTINKHAIQUATS):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINKHAIQUAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_THONGTINKHAIQUAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_THONGTINKHAIQUAT):
    case REQUEST(ACTION_TYPES.UPDATE_THONGTINKHAIQUAT):
    case REQUEST(ACTION_TYPES.DELETE_THONGTINKHAIQUAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_THONGTINKHAIQUATS):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINKHAIQUAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_THONGTINKHAIQUAT):
    case FAILURE(ACTION_TYPES.CREATE_THONGTINKHAIQUAT):
    case FAILURE(ACTION_TYPES.UPDATE_THONGTINKHAIQUAT):
    case FAILURE(ACTION_TYPES.DELETE_THONGTINKHAIQUAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_THONGTINKHAIQUATS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINKHAIQUAT_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_THONGTINKHAIQUAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_THONGTINKHAIQUAT):
    case SUCCESS(ACTION_TYPES.UPDATE_THONGTINKHAIQUAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_THONGTINKHAIQUAT):
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

const apiUrl = 'api/thong-tin-khai-quats';
const apiSearchUrl = 'api/_search/thong-tin-khai-quats';

// Actions

export const getSearchEntities: ICrudSearchAction<IThongTinKhaiQuat> = query => ({
  type: ACTION_TYPES.SEARCH_THONGTINKHAIQUATS,
  payload: axios.get<IThongTinKhaiQuat>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IThongTinKhaiQuat> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINKHAIQUAT_LIST,
    payload: axios.get<IThongTinKhaiQuat>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IThongTinKhaiQuat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_THONGTINKHAIQUAT,
    payload: axios.get<IThongTinKhaiQuat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IThongTinKhaiQuat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_THONGTINKHAIQUAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IThongTinKhaiQuat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_THONGTINKHAIQUAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IThongTinKhaiQuat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_THONGTINKHAIQUAT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
