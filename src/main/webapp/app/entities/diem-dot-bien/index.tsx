import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DiemDotBien from './diem-dot-bien';
import DiemDotBienDetail from './diem-dot-bien-detail';
import DiemDotBienUpdate from './diem-dot-bien-update';
import DiemDotBienDeleteDialog from './diem-dot-bien-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DiemDotBienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DiemDotBienUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DiemDotBienDetail} />
      <ErrorBoundaryRoute path={match.url} component={DiemDotBien} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DiemDotBienDeleteDialog} />
  </>
);

export default Routes;
