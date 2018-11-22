import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NhanDang from './nhan-dang';
import NhanDangDetail from './nhan-dang-detail';
import NhanDangUpdate from './nhan-dang-update';
import NhanDangDeleteDialog from './nhan-dang-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NhanDangUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NhanDangUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NhanDangDetail} />
      <ErrorBoundaryRoute path={match.url} component={NhanDang} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NhanDangDeleteDialog} />
  </>
);

export default Routes;
