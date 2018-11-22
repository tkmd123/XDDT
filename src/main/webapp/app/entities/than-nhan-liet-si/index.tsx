import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThanNhanLietSi from './than-nhan-liet-si';
import ThanNhanLietSiDetail from './than-nhan-liet-si-detail';
import ThanNhanLietSiUpdate from './than-nhan-liet-si-update';
import ThanNhanLietSiDeleteDialog from './than-nhan-liet-si-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThanNhanLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThanNhanLietSiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThanNhanLietSiDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThanNhanLietSi} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ThanNhanLietSiDeleteDialog} />
  </>
);

export default Routes;
