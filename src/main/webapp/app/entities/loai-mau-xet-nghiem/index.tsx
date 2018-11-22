import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LoaiMauXetNghiem from './loai-mau-xet-nghiem';
import LoaiMauXetNghiemDetail from './loai-mau-xet-nghiem-detail';
import LoaiMauXetNghiemUpdate from './loai-mau-xet-nghiem-update';
import LoaiMauXetNghiemDeleteDialog from './loai-mau-xet-nghiem-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoaiMauXetNghiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoaiMauXetNghiemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoaiMauXetNghiemDetail} />
      <ErrorBoundaryRoute path={match.url} component={LoaiMauXetNghiem} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoaiMauXetNghiemDeleteDialog} />
  </>
);

export default Routes;
