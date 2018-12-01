import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HinhThaiHaiCot from './hinh-thai-hai-cot';
import HinhThaiHaiCotDetail from './hinh-thai-hai-cot-detail';
import HinhThaiHaiCotUpdate from './hinh-thai-hai-cot-update';
import HinhThaiHaiCotDeleteDialog from './hinh-thai-hai-cot-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HinhThaiHaiCotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HinhThaiHaiCotUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HinhThaiHaiCotDetail} />
      <ErrorBoundaryRoute path={match.url} component={HinhThaiHaiCot} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HinhThaiHaiCotDeleteDialog} />
  </>
);

export default Routes;
