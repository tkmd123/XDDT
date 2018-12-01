import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITinhSachPhanUng, defaultValue } from 'app/shared/model/tinh-sach-phan-ung.model';

export const ACTION_TYPES = {
  SEARCH_TINHSACHPHANUNGS: 'tinhSachPhanUng/SEARCH_TINHSACHPHANUNGS',
  FETCH_TINHSACHPHANUNG_LIST: 'tinhSachPhanUng/FETCH_TINHSACHPHANUNG_LIST',
  FETCH_TINHSACHPHANUNG: 'tinhSachPhanUng/FETCH_TINHSACHPHANUNG',
  CREATE_TINHSACHPHANUNG: 'tinhSachPhanUng/CREATE_TINHSACHPHANUNG',
  UPDATE_TINHSACHPHANUNG: 'tinhSachPhanUng/UPDATE_TINHSACHPHANUNG',
  DELETE_TINHSACHPHANUNG: 'tinhSachPhanUng/DELETE_TINHSACHPHANUNG',
  RESET: 'tinhSachPhanUng/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITinhSachPhanUng>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TinhSachPhanUngState = Readonly<typeof initialState>;

// Reducer

export default (state: TinhSachPhanUngState = initialState, action): TinhSachPhanUngState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_TINHSACHPHANUNGS):
    case REQUEST(ACTION_TYPES.FETCH_TINHSACHPHANUNG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TINHSACHPHANUNG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TINHSACHPHANUNG):
    case REQUEST(ACTION_TYPES.UPDATE_TINHSACHPHANUNG):
    case REQUEST(ACTION_TYPES.DELETE_TINHSACHPHANUNG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_TINHSACHPHANUNGS):
    case FAILURE(ACTION_TYPES.FETCH_TINHSACHPHANUNG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TINHSACHPHANUNG):
    case FAILURE(ACTION_TYPES.CREATE_TINHSACHPHANUNG):
    case FAILURE(ACTION_TYPES.UPDATE_TINHSACHPHANUNG):
    case FAILURE(ACTION_TYPES.DELETE_TINHSACHPHANUNG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_TINHSACHPHANUNGS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHSACHPHANUNG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TINHSACHPHANUNG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TINHSACHPHANUNG):
    case SUCCESS(ACTION_TYPES.UPDATE_TINHSACHPHANUNG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TINHSACHPHANUNG):
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

const apiUrl = 'api/tinh-sach-phan-ungs';
const apiSearchUrl = 'api/_search/tinh-sach-phan-ungs';

// Actions

export const getSearchEntities: ICrudSearchAction<ITinhSachPhanUng> = query => ({
  type: ACTION_TYPES.SEARCH_TINHSACHPHANUNGS,
  payload: axios.get<ITinhSachPhanUng>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<ITinhSachPhanUng> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TINHSACHPHANUNG_LIST,
  payload: axios.get<ITinhSachPhanUng>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITinhSachPhanUng> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TINHSACHPHANUNG,
    payload: axios.get<ITinhSachPhanUng>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITinhSachPhanUng> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TINHSACHPHANUNG,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITinhSachPhanUng> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TINHSACHPHANUNG,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITinhSachPhanUng> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TINHSACHPHANUNG,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
