import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThaoTac from './thao-tac';
import ThaoTacDetail from './thao-tac-detail';
import ThaoTacUpdate from './thao-tac-update';
import ThaoTacDeleteDialog from './thao-tac-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThaoTacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThaoTacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThaoTacDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThaoTac} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ThaoTacDeleteDialog} />
  </>
);

export default Routes;
