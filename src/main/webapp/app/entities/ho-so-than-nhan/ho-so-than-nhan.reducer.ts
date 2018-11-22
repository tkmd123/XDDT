import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoSoThanNhan, defaultValue } from 'app/shared/model/ho-so-than-nhan.model';

export const ACTION_TYPES = {
  SEARCH_HOSOTHANNHANS: 'hoSoThanNhan/SEARCH_HOSOTHANNHANS',
  FETCH_HOSOTHANNHAN_LIST: 'hoSoThanNhan/FETCH_HOSOTHANNHAN_LIST',
  FETCH_HOSOTHANNHAN: 'hoSoThanNhan/FETCH_HOSOTHANNHAN',
  CREATE_HOSOTHANNHAN: 'hoSoThanNhan/CREATE_HOSOTHANNHAN',
  UPDATE_HOSOTHANNHAN: 'hoSoThanNhan/UPDATE_HOSOTHANNHAN',
  DELETE_HOSOTHANNHAN: 'hoSoThanNhan/DELETE_HOSOTHANNHAN',
  RESET: 'hoSoThanNhan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoSoThanNhan>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false
};

export type HoSoThanNhanState = Readonly<typeof initialState>;

// Reducer

export default (state: HoSoThanNhanState = initialState, action): HoSoThanNhanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOSOTHANNHANS):
    case REQUEST(ACTION_TYPES.FETCH_HOSOTHANNHAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOSOTHANNHAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOSOTHANNHAN):
    case REQUEST(ACTION_TYPES.UPDATE_HOSOTHANNHAN):
    case REQUEST(ACTION_TYPES.DELETE_HOSOTHANNHAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOSOTHANNHANS):
    case FAILURE(ACTION_TYPES.FETCH_HOSOTHANNHAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOSOTHANNHAN):
    case FAILURE(ACTION_TYPES.CREATE_HOSOTHANNHAN):
    case FAILURE(ACTION_TYPES.UPDATE_HOSOTHANNHAN):
    case FAILURE(ACTION_TYPES.DELETE_HOSOTHANNHAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOSOTHANNHANS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOTHANNHAN_LIST):
      return {
        ...state,
        loading: false,
        totalItems: action.payload.headers['x-total-count'],
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOSOTHANNHAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOSOTHANNHAN):
    case SUCCESS(ACTION_TYPES.UPDATE_HOSOTHANNHAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOSOTHANNHAN):
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

const apiUrl = 'api/ho-so-than-nhans';
const apiSearchUrl = 'api/_search/ho-so-than-nhans';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoSoThanNhan> = query => ({
  type: ACTION_TYPES.SEARCH_HOSOTHANNHANS,
  payload: axios.get<IHoSoThanNhan>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoSoThanNhan> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_HOSOTHANNHAN_LIST,
    payload: axios.get<IHoSoThanNhan>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`)
  };
};

export const getEntity: ICrudGetAction<IHoSoThanNhan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOSOTHANNHAN,
    payload: axios.get<IHoSoThanNhan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoSoThanNhan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOSOTHANNHAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoSoThanNhan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOSOTHANNHAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoSoThanNhan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOSOTHANNHAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
