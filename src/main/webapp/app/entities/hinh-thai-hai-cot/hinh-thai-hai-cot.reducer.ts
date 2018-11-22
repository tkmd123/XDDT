import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHinhThaiHaiCot, defaultValue } from 'app/shared/model/hinh-thai-hai-cot.model';

export const ACTION_TYPES = {
  SEARCH_HINHTHAIHAICOTS: 'hinhThaiHaiCot/SEARCH_HINHTHAIHAICOTS',
  FETCH_HINHTHAIHAICOT_LIST: 'hinhThaiHaiCot/FETCH_HINHTHAIHAICOT_LIST',
  FETCH_HINHTHAIHAICOT: 'hinhThaiHaiCot/FETCH_HINHTHAIHAICOT',
  CREATE_HINHTHAIHAICOT: 'hinhThaiHaiCot/CREATE_HINHTHAIHAICOT',
  UPDATE_HINHTHAIHAICOT: 'hinhThaiHaiCot/UPDATE_HINHTHAIHAICOT',
  DELETE_HINHTHAIHAICOT: 'hinhThaiHaiCot/DELETE_HINHTHAIHAICOT',
  RESET: 'hinhThaiHaiCot/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHinhThaiHaiCot>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HinhThaiHaiCotState = Readonly<typeof initialState>;

// Reducer

export default (state: HinhThaiHaiCotState = initialState, action): HinhThaiHaiCotState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HINHTHAIHAICOTS):
    case REQUEST(ACTION_TYPES.FETCH_HINHTHAIHAICOT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HINHTHAIHAICOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HINHTHAIHAICOT):
    case REQUEST(ACTION_TYPES.UPDATE_HINHTHAIHAICOT):
    case REQUEST(ACTION_TYPES.DELETE_HINHTHAIHAICOT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HINHTHAIHAICOTS):
    case FAILURE(ACTION_TYPES.FETCH_HINHTHAIHAICOT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.CREATE_HINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.UPDATE_HINHTHAIHAICOT):
    case FAILURE(ACTION_TYPES.DELETE_HINHTHAIHAICOT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HINHTHAIHAICOTS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HINHTHAIHAICOT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HINHTHAIHAICOT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HINHTHAIHAICOT):
    case SUCCESS(ACTION_TYPES.UPDATE_HINHTHAIHAICOT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HINHTHAIHAICOT):
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

const apiUrl = 'api/hinh-thai-hai-cots';
const apiSearchUrl = 'api/_search/hinh-thai-hai-cots';

// Actions

export const getSearchEntities: ICrudSearchAction<IHinhThaiHaiCot> = query => ({
  type: ACTION_TYPES.SEARCH_HINHTHAIHAICOTS,
  payload: axios.get<IHinhThaiHaiCot>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHinhThaiHaiCot> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HINHTHAIHAICOT_LIST,
  payload: axios.get<IHinhThaiHaiCot>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHinhThaiHaiCot> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HINHTHAIHAICOT,
    payload: axios.get<IHinhThaiHaiCot>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHinhThaiHaiCot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HINHTHAIHAICOT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHinhThaiHaiCot> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HINHTHAIHAICOT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHinhThaiHaiCot> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HINHTHAIHAICOT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
