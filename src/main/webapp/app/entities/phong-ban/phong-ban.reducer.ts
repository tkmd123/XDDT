import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPhongBan, defaultValue } from 'app/shared/model/phong-ban.model';

export const ACTION_TYPES = {
  SEARCH_PHONGBANS: 'phongBan/SEARCH_PHONGBANS',
  FETCH_PHONGBAN_LIST: 'phongBan/FETCH_PHONGBAN_LIST',
  FETCH_PHONGBAN: 'phongBan/FETCH_PHONGBAN',
  CREATE_PHONGBAN: 'phongBan/CREATE_PHONGBAN',
  UPDATE_PHONGBAN: 'phongBan/UPDATE_PHONGBAN',
  DELETE_PHONGBAN: 'phongBan/DELETE_PHONGBAN',
  RESET: 'phongBan/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPhongBan>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type PhongBanState = Readonly<typeof initialState>;

// Reducer

export default (state: PhongBanState = initialState, action): PhongBanState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_PHONGBANS):
    case REQUEST(ACTION_TYPES.FETCH_PHONGBAN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PHONGBAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_PHONGBAN):
    case REQUEST(ACTION_TYPES.UPDATE_PHONGBAN):
    case REQUEST(ACTION_TYPES.DELETE_PHONGBAN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_PHONGBANS):
    case FAILURE(ACTION_TYPES.FETCH_PHONGBAN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PHONGBAN):
    case FAILURE(ACTION_TYPES.CREATE_PHONGBAN):
    case FAILURE(ACTION_TYPES.UPDATE_PHONGBAN):
    case FAILURE(ACTION_TYPES.DELETE_PHONGBAN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_PHONGBANS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHONGBAN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_PHONGBAN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_PHONGBAN):
    case SUCCESS(ACTION_TYPES.UPDATE_PHONGBAN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_PHONGBAN):
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

const apiUrl = 'api/phong-bans';
const apiSearchUrl = 'api/_search/phong-bans';

// Actions

export const getSearchEntities: ICrudSearchAction<IPhongBan> = query => ({
  type: ACTION_TYPES.SEARCH_PHONGBANS,
  payload: axios.get<IPhongBan>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IPhongBan> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PHONGBAN_LIST,
  payload: axios.get<IPhongBan>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IPhongBan> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PHONGBAN,
    payload: axios.get<IPhongBan>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IPhongBan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PHONGBAN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPhongBan> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PHONGBAN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPhongBan> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PHONGBAN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
