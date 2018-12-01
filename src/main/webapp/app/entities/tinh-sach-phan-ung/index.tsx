import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TinhSachPhanUng from './tinh-sach-phan-ung';
import TinhSachPhanUngDetail from './tinh-sach-phan-ung-detail';
import TinhSachPhanUngUpdate from './tinh-sach-phan-ung-update';
import TinhSachPhanUngDeleteDialog from './tinh-sach-phan-ung-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TinhSachPhanUngUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TinhSachPhanUngUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TinhSachPhanUngDetail} />
      <ErrorBoundaryRoute path={match.url} component={TinhSachPhanUng} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TinhSachPhanUngDeleteDialog} />
  </>
);

export default Routes;
