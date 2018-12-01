import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MappingDotBien from './mapping-dot-bien';
import MappingDotBienDetail from './mapping-dot-bien-detail';
import MappingDotBienUpdate from './mapping-dot-bien-update';
import MappingDotBienDeleteDialog from './mapping-dot-bien-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MappingDotBienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MappingDotBienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MappingDotBienDetail} />
      <ErrorBoundaryRoute path={match.url} component={MappingDotBien} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={MappingDotBienDeleteDialog} />
  </>
);

export default Routes;
