import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IHoaChatTachChiet, defaultValue } from 'app/shared/model/hoa-chat-tach-chiet.model';

export const ACTION_TYPES = {
  SEARCH_HOACHATTACHCHIETS: 'hoaChatTachChiet/SEARCH_HOACHATTACHCHIETS',
  FETCH_HOACHATTACHCHIET_LIST: 'hoaChatTachChiet/FETCH_HOACHATTACHCHIET_LIST',
  FETCH_HOACHATTACHCHIET: 'hoaChatTachChiet/FETCH_HOACHATTACHCHIET',
  CREATE_HOACHATTACHCHIET: 'hoaChatTachChiet/CREATE_HOACHATTACHCHIET',
  UPDATE_HOACHATTACHCHIET: 'hoaChatTachChiet/UPDATE_HOACHATTACHCHIET',
  DELETE_HOACHATTACHCHIET: 'hoaChatTachChiet/DELETE_HOACHATTACHCHIET',
  RESET: 'hoaChatTachChiet/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IHoaChatTachChiet>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type HoaChatTachChietState = Readonly<typeof initialState>;

// Reducer

export default (state: HoaChatTachChietState = initialState, action): HoaChatTachChietState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_HOACHATTACHCHIETS):
    case REQUEST(ACTION_TYPES.FETCH_HOACHATTACHCHIET_LIST):
    case REQUEST(ACTION_TYPES.FETCH_HOACHATTACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_HOACHATTACHCHIET):
    case REQUEST(ACTION_TYPES.UPDATE_HOACHATTACHCHIET):
    case REQUEST(ACTION_TYPES.DELETE_HOACHATTACHCHIET):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_HOACHATTACHCHIETS):
    case FAILURE(ACTION_TYPES.FETCH_HOACHATTACHCHIET_LIST):
    case FAILURE(ACTION_TYPES.FETCH_HOACHATTACHCHIET):
    case FAILURE(ACTION_TYPES.CREATE_HOACHATTACHCHIET):
    case FAILURE(ACTION_TYPES.UPDATE_HOACHATTACHCHIET):
    case FAILURE(ACTION_TYPES.DELETE_HOACHATTACHCHIET):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_HOACHATTACHCHIETS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHATTACHCHIET_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_HOACHATTACHCHIET):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_HOACHATTACHCHIET):
    case SUCCESS(ACTION_TYPES.UPDATE_HOACHATTACHCHIET):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_HOACHATTACHCHIET):
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

const apiUrl = 'api/hoa-chat-tach-chiets';
const apiSearchUrl = 'api/_search/hoa-chat-tach-chiets';

// Actions

export const getSearchEntities: ICrudSearchAction<IHoaChatTachChiet> = query => ({
  type: ACTION_TYPES.SEARCH_HOACHATTACHCHIETS,
  payload: axios.get<IHoaChatTachChiet>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IHoaChatTachChiet> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_HOACHATTACHCHIET_LIST,
  payload: axios.get<IHoaChatTachChiet>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IHoaChatTachChiet> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_HOACHATTACHCHIET,
    payload: axios.get<IHoaChatTachChiet>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IHoaChatTachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_HOACHATTACHCHIET,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IHoaChatTachChiet> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_HOACHATTACHCHIET,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IHoaChatTachChiet> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_HOACHATTACHCHIET,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
