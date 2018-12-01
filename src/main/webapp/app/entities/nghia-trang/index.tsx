import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NghiaTrang from './nghia-trang';
import NghiaTrangDetail from './nghia-trang-detail';
import NghiaTrangUpdate from './nghia-trang-update';
import NghiaTrangDeleteDialog from './nghia-trang-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NghiaTrangUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NghiaTrangUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NghiaTrangDetail} />
      <ErrorBoundaryRoute path={match.url} component={NghiaTrang} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NghiaTrangDeleteDialog} />
  </>
);

export default Routes;
