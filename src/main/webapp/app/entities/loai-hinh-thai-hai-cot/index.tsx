import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LoaiHinhThaiHaiCot from './loai-hinh-thai-hai-cot';
import LoaiHinhThaiHaiCotDetail from './loai-hinh-thai-hai-cot-detail';
import LoaiHinhThaiHaiCotUpdate from './loai-hinh-thai-hai-cot-update';
import LoaiHinhThaiHaiCotDeleteDialog from './loai-hinh-thai-hai-cot-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoaiHinhThaiHaiCotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoaiHinhThaiHaiCotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoaiHinhThaiHaiCotDetail} />
      <ErrorBoundaryRoute path={match.url} component={LoaiHinhThaiHaiCot} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoaiHinhThaiHaiCotDeleteDialog} />
  </>
);

export default Routes;
