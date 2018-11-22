import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoSoLietSi from './ho-so-liet-si';
import HoSoLietSiDetail from './ho-so-liet-si-detail';
import HoSoLietSiUpdate from './ho-so-liet-si-update';
import HoSoLietSiDeleteDialog from './ho-so-liet-si-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoSoLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoSoLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoSoLietSiDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoSoLietSi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoSoLietSiDeleteDialog} />
  </>
);

export default Routes;
