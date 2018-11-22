import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DonViThoiKy from './don-vi-thoi-ky';
import DonViThoiKyDetail from './don-vi-thoi-ky-detail';
import DonViThoiKyUpdate from './don-vi-thoi-ky-update';
import DonViThoiKyDeleteDialog from './don-vi-thoi-ky-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DonViThoiKyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DonViThoiKyUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DonViThoiKyDetail} />
      <ErrorBoundaryRoute path={match.url} component={DonViThoiKy} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DonViThoiKyDeleteDialog} />
  </>
);

export default Routes;
