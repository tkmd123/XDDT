import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TachChiet from './tach-chiet';
import TachChietDetail from './tach-chiet-detail';
import TachChietUpdate from './tach-chiet-update';
import TachChietDeleteDialog from './tach-chiet-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TachChietDetail} />
      <ErrorBoundaryRoute path={match.url} component={TachChiet} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TachChietDeleteDialog} />
  </>
);

export default Routes;
