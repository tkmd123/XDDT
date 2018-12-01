import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDonVi, defaultValue } from 'app/shared/model/don-vi.model';

export const ACTION_TYPES = {
  SEARCH_DONVIS: 'donVi/SEARCH_DONVIS',
  FETCH_DONVI_LIST: 'donVi/FETCH_DONVI_LIST',
  FETCH_DONVI: 'donVi/FETCH_DONVI',
  CREATE_DONVI: 'donVi/CREATE_DONVI',
  UPDATE_DONVI: 'donVi/UPDATE_DONVI',
  DELETE_DONVI: 'donVi/DELETE_DONVI',
  RESET: 'donVi/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDonVi>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DonViState = Readonly<typeof initialState>;

// Reducer

export default (state: DonViState = initialState, action): DonViState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_DONVIS):
    case REQUEST(ACTION_TYPES.FETCH_DONVI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DONVI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DONVI):
    case REQUEST(ACTION_TYPES.UPDATE_DONVI):
    case REQUEST(ACTION_TYPES.DELETE_DONVI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_DONVIS):
    case FAILURE(ACTION_TYPES.FETCH_DONVI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DONVI):
    case FAILURE(ACTION_TYPES.CREATE_DONVI):
    case FAILURE(ACTION_TYPES.UPDATE_DONVI):
    case FAILURE(ACTION_TYPES.DELETE_DONVI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_DONVIS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DONVI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DONVI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DONVI):
    case SUCCESS(ACTION_TYPES.UPDATE_DONVI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DONVI):
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

const apiUrl = 'api/don-vis';
const apiSearchUrl = 'api/_search/don-vis';

// Actions

export const getSearchEntities: ICrudSearchAction<IDonVi> = query => ({
  type: ACTION_TYPES.SEARCH_DONVIS,
  payload: axios.get<IDonVi>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IDonVi> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DONVI_LIST,
  payload: axios.get<IDonVi>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDonVi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DONVI,
    payload: axios.get<IDonVi>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDonVi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DONVI,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDonVi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DONVI,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDonVi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DONVI,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
