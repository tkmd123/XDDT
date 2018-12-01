import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TrungTam from './trung-tam';
import TrungTamDetail from './trung-tam-detail';
import TrungTamUpdate from './trung-tam-update';
import TrungTamDeleteDialog from './trung-tam-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TrungTamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TrungTamUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TrungTamDetail} />
      <ErrorBoundaryRoute path={match.url} component={TrungTam} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TrungTamDeleteDialog} />
  </>
);

export default Routes;
