import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCRPhanUngChuan from './pcr-phan-ung-chuan';
import PCRPhanUngChuanDetail from './pcr-phan-ung-chuan-detail';
import PCRPhanUngChuanUpdate from './pcr-phan-ung-chuan-update';
import PCRPhanUngChuanDeleteDialog from './pcr-phan-ung-chuan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRPhanUngChuanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRPhanUngChuanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRPhanUngChuanDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCRPhanUngChuan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRPhanUngChuanDeleteDialog} />
  </>
);

export default Routes;
