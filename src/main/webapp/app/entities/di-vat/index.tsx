import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DiVat from './di-vat';
import DiVatDetail from './di-vat-detail';
import DiVatUpdate from './di-vat-update';
import DiVatDeleteDialog from './di-vat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DiVatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DiVatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DiVatDetail} />
      <ErrorBoundaryRoute path={match.url} component={DiVat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DiVatDeleteDialog} />
  </>
);

export default Routes;
