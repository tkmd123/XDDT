import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ChucVu from './chuc-vu';
import ChucVuDetail from './chuc-vu-detail';
import ChucVuUpdate from './chuc-vu-update';
import ChucVuDeleteDialog from './chuc-vu-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ChucVuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ChucVuUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ChucVuDetail} />
      <ErrorBoundaryRoute path={match.url} component={ChucVu} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ChucVuDeleteDialog} />
  </>
);

export default Routes;
