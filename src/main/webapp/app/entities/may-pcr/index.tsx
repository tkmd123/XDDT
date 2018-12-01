import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MayPCR from './may-pcr';
import MayPCRDetail from './may-pcr-detail';
import MayPCRUpdate from './may-pcr-update';
import MayPCRDeleteDialog from './may-pcr-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MayPCRUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MayPCRUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MayPCRDetail} />
      <ErrorBoundaryRoute path={match.url} component={MayPCR} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MayPCRDeleteDialog} />
  </>
);

export default Routes;
