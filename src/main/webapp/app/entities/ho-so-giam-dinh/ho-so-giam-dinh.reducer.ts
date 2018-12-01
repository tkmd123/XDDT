import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoSoGiamDinh, defaultValue } from 'app/shared/model/ho-so-giam-dinh.model';

export const ACTION_TYPES = {
  SEARCH_HOSOGIAMDINHS: 'hoSoGiamDinh/SEARCH_HOSOGIAMDINHS',
  FETCH_HOSOGIAMDINH_LIST: 'hoSoGiamDinh/FETCH_HOSOGIAMDINH_LIST',
  FETCH_HOSOGIAMDINH: 'hoSoGiamDinh/FETCH_HOSOGIAMDINH',
  CREATE_HOSOGIAMDINH: 'hoSoGiamDinh/CREATE_HOSOGIAMDINH',
  UPDATE_HOSOGIAMDINH: 'hoSoGiamDinh/UPDATE_HOSOGIAMDINH',
  DELETE_HOSOGIAMDINH: 'hoSoGiamDinh/DELETE_HOSOGIAMDINH',
  RESET: 'hoSoGiamDinh/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoSoGiamDinh>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HoSoGiamDinhState = Readonly<typeof initialState>;

// Reducer

export default (state: HoSoGiamDinhState = initialState, action): HoSoGiamDinhState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOSOGIAMDINHS):
    case REQUEST(ACTION_TYPES.FETCH_HOSOGIAMDINH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOSOGIAMDINH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOSOGIAMDINH):
    case REQUEST(ACTION_TYPES.UPDATE_HOSOGIAMDINH):
    case REQUEST(ACTION_TYPES.DELETE_HOSOGIAMDINH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOSOGIAMDINHS):
    case FAILURE(ACTION_TYPES.FETCH_HOSOGIAMDINH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOSOGIAMDINH):
    case FAILURE(ACTION_TYPES.CREATE_HOSOGIAMDINH):
    case FAILURE(ACTION_TYPES.UPDATE_HOSOGIAMDINH):
    case FAILURE(ACTION_TYPES.DELETE_HOSOGIAMDINH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOSOGIAMDINHS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOGIAMDINH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOGIAMDINH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOSOGIAMDINH):
    case SUCCESS(ACTION_TYPES.UPDATE_HOSOGIAMDINH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOSOGIAMDINH):
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

const apiUrl = 'api/ho-so-giam-dinhs';
const apiSearchUrl = 'api/_search/ho-so-giam-dinhs';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoSoGiamDinh> = query => ({
  type: ACTION_TYPES.SEARCH_HOSOGIAMDINHS,
  payload: axios.get<IHoSoGiamDinh>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoSoGiamDinh> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HOSOGIAMDINH_LIST,
  payload: axios.get<IHoSoGiamDinh>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHoSoGiamDinh> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOSOGIAMDINH,
    payload: axios.get<IHoSoGiamDinh>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoSoGiamDinh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOSOGIAMDINH,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoSoGiamDinh> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOSOGIAMDINH,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoSoGiamDinh> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOSOGIAMDINH,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
