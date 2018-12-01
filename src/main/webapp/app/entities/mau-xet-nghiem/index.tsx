import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MauXetNghiem from './mau-xet-nghiem';
import MauXetNghiemDetail from './mau-xet-nghiem-detail';
import MauXetNghiemUpdate from './mau-xet-nghiem-update';
import MauXetNghiemDeleteDialog from './mau-xet-nghiem-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MauXetNghiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MauXetNghiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MauXetNghiemDetail} />
      <ErrorBoundaryRoute path={match.url} component={MauXetNghiem} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MauXetNghiemDeleteDialog} />
  </>
);

export default Routes;
