import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TinhThanh from './tinh-thanh';
import TinhThanhDetail from './tinh-thanh-detail';
import TinhThanhUpdate from './tinh-thanh-update';
import TinhThanhDeleteDialog from './tinh-thanh-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TinhThanhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TinhThanhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TinhThanhDetail} />
      <ErrorBoundaryRoute path={match.url} component={TinhThanh} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TinhThanhDeleteDialog} />
  </>
);

export default Routes;
