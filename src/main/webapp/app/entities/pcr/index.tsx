import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCR from './pcr';
import PCRDetail from './pcr-detail';
import PCRUpdate from './pcr-update';
import PCRDeleteDialog from './pcr-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCR} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRDeleteDialog} />
  </>
);

export default Routes;
