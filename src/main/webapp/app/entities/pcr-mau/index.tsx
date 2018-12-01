import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCRMau from './pcr-mau';
import PCRMauDetail from './pcr-mau-detail';
import PCRMauUpdate from './pcr-mau-update';
import PCRMauDeleteDialog from './pcr-mau-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRMauUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRMauUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRMauDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCRMau} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRMauDeleteDialog} />
  </>
);

export default Routes;
