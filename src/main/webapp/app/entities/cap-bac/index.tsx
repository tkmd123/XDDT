import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CapBac from './cap-bac';
import CapBacDetail from './cap-bac-detail';
import CapBacUpdate from './cap-bac-update';
import CapBacDeleteDialog from './cap-bac-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CapBacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CapBacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CapBacDetail} />
      <ErrorBoundaryRoute path={match.url} component={CapBac} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CapBacDeleteDialog} />
  </>
);

export default Routes;
