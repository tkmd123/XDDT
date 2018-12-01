import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LoaiDiVat from './loai-di-vat';
import LoaiDiVatDetail from './loai-di-vat-detail';
import LoaiDiVatUpdate from './loai-di-vat-update';
import LoaiDiVatDeleteDialog from './loai-di-vat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoaiDiVatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoaiDiVatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoaiDiVatDetail} />
      <ErrorBoundaryRoute path={match.url} component={LoaiDiVat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoaiDiVatDeleteDialog} />
  </>
);

export default Routes;
