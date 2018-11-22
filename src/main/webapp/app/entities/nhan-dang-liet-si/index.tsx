import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NhanDangLietSi from './nhan-dang-liet-si';
import NhanDangLietSiDetail from './nhan-dang-liet-si-detail';
import NhanDangLietSiUpdate from './nhan-dang-liet-si-update';
import NhanDangLietSiDeleteDialog from './nhan-dang-liet-si-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NhanDangLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NhanDangLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NhanDangLietSiDetail} />
      <ErrorBoundaryRoute path={match.url} component={NhanDangLietSi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NhanDangLietSiDeleteDialog} />
  </>
);

export default Routes;
