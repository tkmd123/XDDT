import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoaChat, defaultValue } from 'app/shared/model/hoa-chat.model';

export const ACTION_TYPES = {
  SEARCH_HOACHATS: 'hoaChat/SEARCH_HOACHATS',
  FETCH_HOACHAT_LIST: 'hoaChat/FETCH_HOACHAT_LIST',
  FETCH_HOACHAT: 'hoaChat/FETCH_HOACHAT',
  CREATE_HOACHAT: 'hoaChat/CREATE_HOACHAT',
  UPDATE_HOACHAT: 'hoaChat/UPDATE_HOACHAT',
  DELETE_HOACHAT: 'hoaChat/DELETE_HOACHAT',
  RESET: 'hoaChat/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoaChat>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HoaChatState = Readonly<typeof initialState>;

// Reducer

export default (state: HoaChatState = initialState, action): HoaChatState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOACHATS):
    case REQUEST(ACTION_TYPES.FETCH_HOACHAT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOACHAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOACHAT):
    case REQUEST(ACTION_TYPES.UPDATE_HOACHAT):
    case REQUEST(ACTION_TYPES.DELETE_HOACHAT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOACHATS):
    case FAILURE(ACTION_TYPES.FETCH_HOACHAT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOACHAT):
    case FAILURE(ACTION_TYPES.CREATE_HOACHAT):
    case FAILURE(ACTION_TYPES.UPDATE_HOACHAT):
    case FAILURE(ACTION_TYPES.DELETE_HOACHAT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOACHATS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHAT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHAT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOACHAT):
    case SUCCESS(ACTION_TYPES.UPDATE_HOACHAT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOACHAT):
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

const apiUrl = 'api/hoa-chats';
const apiSearchUrl = 'api/_search/hoa-chats';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoaChat> = query => ({
  type: ACTION_TYPES.SEARCH_HOACHATS,
  payload: axios.get<IHoaChat>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoaChat> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HOACHAT_LIST,
  payload: axios.get<IHoaChat>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHoaChat> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOACHAT,
    payload: axios.get<IHoaChat>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoaChat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOACHAT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoaChat> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOACHAT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoaChat> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOACHAT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
