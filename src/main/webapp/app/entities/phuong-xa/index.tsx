import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PhuongXa from './phuong-xa';
import PhuongXaDetail from './phuong-xa-detail';
import PhuongXaUpdate from './phuong-xa-update';
import PhuongXaDeleteDialog from './phuong-xa-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PhuongXaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PhuongXaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PhuongXaDetail} />
      <ErrorBoundaryRoute path={match.url} component={PhuongXa} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PhuongXaDeleteDialog} />
  </>
);

export default Routes;
