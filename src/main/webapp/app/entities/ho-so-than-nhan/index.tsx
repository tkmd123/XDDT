import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoSoThanNhan from './ho-so-than-nhan';
import HoSoThanNhanDetail from './ho-so-than-nhan-detail';
import HoSoThanNhanUpdate from './ho-so-than-nhan-update';
import HoSoThanNhanDeleteDialog from './ho-so-than-nhan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoSoThanNhanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoSoThanNhanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoSoThanNhanDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoSoThanNhan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoSoThanNhanDeleteDialog} />
  </>
);

export default Routes;
