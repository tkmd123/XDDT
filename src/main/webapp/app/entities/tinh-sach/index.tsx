import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TinhSach from './tinh-sach';
import TinhSachDetail from './tinh-sach-detail';
import TinhSachUpdate from './tinh-sach-update';
import TinhSachDeleteDialog from './tinh-sach-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TinhSachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TinhSachUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TinhSachDetail} />
      <ErrorBoundaryRoute path={match.url} component={TinhSach} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TinhSachDeleteDialog} />
  </>
);

export default Routes;
