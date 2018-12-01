import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import PCRKetQua from './pcr-ket-qua';
import PCRKetQuaDetail from './pcr-ket-qua-detail';
import PCRKetQuaUpdate from './pcr-ket-qua-update';
import PCRKetQuaDeleteDialog from './pcr-ket-qua-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PCRKetQuaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PCRKetQuaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PCRKetQuaDetail} />
      <ErrorBoundaryRoute path={match.url} component={PCRKetQua} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PCRKetQuaDeleteDialog} />
  </>
);

export default Routes;
