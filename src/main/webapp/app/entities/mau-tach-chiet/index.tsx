import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MauTachChiet from './mau-tach-chiet';
import MauTachChietDetail from './mau-tach-chiet-detail';
import MauTachChietUpdate from './mau-tach-chiet-update';
import MauTachChietDeleteDialog from './mau-tach-chiet-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MauTachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MauTachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MauTachChietDetail} />
      <ErrorBoundaryRoute path={match.url} component={MauTachChiet} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MauTachChietDeleteDialog} />
  </>
);

export default Routes;
