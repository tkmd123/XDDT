import axios from 'axios';
import { ICrudSearchAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMappingDotBien, defaultValue } from 'app/shared/model/mapping-dot-bien.model';

export const ACTION_TYPES = {
  SEARCH_MAPPINGDOTBIENS: 'mappingDotBien/SEARCH_MAPPINGDOTBIENS',
  FETCH_MAPPINGDOTBIEN_LIST: 'mappingDotBien/FETCH_MAPPINGDOTBIEN_LIST',
  FETCH_MAPPINGDOTBIEN: 'mappingDotBien/FETCH_MAPPINGDOTBIEN',
  CREATE_MAPPINGDOTBIEN: 'mappingDotBien/CREATE_MAPPINGDOTBIEN',
  UPDATE_MAPPINGDOTBIEN: 'mappingDotBien/UPDATE_MAPPINGDOTBIEN',
  DELETE_MAPPINGDOTBIEN: 'mappingDotBien/DELETE_MAPPINGDOTBIEN',
  RESET: 'mappingDotBien/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMappingDotBien>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type MappingDotBienState = Readonly<typeof initialState>;

// Reducer

export default (state: MappingDotBienState = initialState, action): MappingDotBienState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.SEARCH_MAPPINGDOTBIENS):
    case REQUEST(ACTION_TYPES.FETCH_MAPPINGDOTBIEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MAPPINGDOTBIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_MAPPINGDOTBIEN):
    case REQUEST(ACTION_TYPES.UPDATE_MAPPINGDOTBIEN):
    case REQUEST(ACTION_TYPES.DELETE_MAPPINGDOTBIEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.SEARCH_MAPPINGDOTBIENS):
    case FAILURE(ACTION_TYPES.FETCH_MAPPINGDOTBIEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MAPPINGDOTBIEN):
    case FAILURE(ACTION_TYPES.CREATE_MAPPINGDOTBIEN):
    case FAILURE(ACTION_TYPES.UPDATE_MAPPINGDOTBIEN):
    case FAILURE(ACTION_TYPES.DELETE_MAPPINGDOTBIEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.SEARCH_MAPPINGDOTBIENS):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAPPINGDOTBIEN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_MAPPINGDOTBIEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_MAPPINGDOTBIEN):
    case SUCCESS(ACTION_TYPES.UPDATE_MAPPINGDOTBIEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_MAPPINGDOTBIEN):
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

const apiUrl = 'api/mapping-dot-biens';
const apiSearchUrl = 'api/_search/mapping-dot-biens';

// Actions

export const getSearchEntities: ICrudSearchAction<IMappingDotBien> = query => ({
  type: ACTION_TYPES.SEARCH_MAPPINGDOTBIENS,
  payload: axios.get<IMappingDotBien>(`${apiSearchUrl}?query=` + query)
});

export const getEntities: ICrudGetAllAction<IMappingDotBien> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MAPPINGDOTBIEN_LIST,
  payload: axios.get<IMappingDotBien>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IMappingDotBien> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MAPPINGDOTBIEN,
    payload: axios.get<IMappingDotBien>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IMappingDotBien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MAPPINGDOTBIEN,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMappingDotBien> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MAPPINGDOTBIEN,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMappingDotBien> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MAPPINGDOTBIEN,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
