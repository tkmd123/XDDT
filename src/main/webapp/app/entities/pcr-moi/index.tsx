import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCRMoi from './pcr-moi';
import PCRMoiDetail from './pcr-moi-detail';
import PCRMoiUpdate from './pcr-moi-update';
import PCRMoiDeleteDialog from './pcr-moi-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRMoiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRMoiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRMoiDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCRMoi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRMoiDeleteDialog} />
  </>
);

export default Routes;
