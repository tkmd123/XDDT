import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NhanVien from './nhan-vien';
import NhanVienDetail from './nhan-vien-detail';
import NhanVienUpdate from './nhan-vien-update';
import NhanVienDeleteDialog from './nhan-vien-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NhanVienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NhanVienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NhanVienDetail} />
      <ErrorBoundaryRoute path={match.url} component={NhanVien} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NhanVienDeleteDialog} />
  </>
);

export default Routes;
