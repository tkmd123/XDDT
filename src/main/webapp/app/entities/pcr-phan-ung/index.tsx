import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCRPhanUng from './pcr-phan-ung';
import PCRPhanUngDetail from './pcr-phan-ung-detail';
import PCRPhanUngUpdate from './pcr-phan-ung-update';
import PCRPhanUngDeleteDialog from './pcr-phan-ung-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRPhanUngUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRPhanUngUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRPhanUngDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCRPhanUng} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRPhanUngDeleteDialog} />
  </>
);

export default Routes;
