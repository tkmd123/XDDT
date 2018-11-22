import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PhongBan from './phong-ban';
import PhongBanDetail from './phong-ban-detail';
import PhongBanUpdate from './phong-ban-update';
import PhongBanDeleteDialog from './phong-ban-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PhongBanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PhongBanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PhongBanDetail} />
      <ErrorBoundaryRoute path={match.url} component={PhongBan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PhongBanDeleteDialog} />
  </>
);

export default Routes;
