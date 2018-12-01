import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoSoGiamDinh from './ho-so-giam-dinh';
import HoSoGiamDinhDetail from './ho-so-giam-dinh-detail';
import HoSoGiamDinhUpdate from './ho-so-giam-dinh-update';
import HoSoGiamDinhDeleteDialog from './ho-so-giam-dinh-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoSoGiamDinhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoSoGiamDinhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoSoGiamDinhDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoSoGiamDinh} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoSoGiamDinhDeleteDialog} />
  </>
);

export default Routes;
