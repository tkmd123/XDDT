import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DonVi from './don-vi';
import DonViDetail from './don-vi-detail';
import DonViUpdate from './don-vi-update';
import DonViDeleteDialog from './don-vi-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DonViUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DonViUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DonViDetail} />
      <ErrorBoundaryRoute path={match.url} component={DonVi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DonViDeleteDialog} />
  </>
);

export default Routes;
