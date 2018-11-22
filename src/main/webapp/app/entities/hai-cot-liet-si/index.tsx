import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HaiCotLietSi from './hai-cot-liet-si';
import HaiCotLietSiDetail from './hai-cot-liet-si-detail';
import HaiCotLietSiUpdate from './hai-cot-liet-si-update';
import HaiCotLietSiDeleteDialog from './hai-cot-liet-si-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HaiCotLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HaiCotLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HaiCotLietSiDetail} />
      <ErrorBoundaryRoute path={match.url} component={HaiCotLietSi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HaiCotLietSiDeleteDialog} />
  </>
);

export default Routes;
