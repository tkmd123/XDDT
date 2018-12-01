import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import QuanHeThanNhan from './quan-he-than-nhan';
import QuanHeThanNhanDetail from './quan-he-than-nhan-detail';
import QuanHeThanNhanUpdate from './quan-he-than-nhan-update';
import QuanHeThanNhanDeleteDialog from './quan-he-than-nhan-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QuanHeThanNhanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QuanHeThanNhanUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QuanHeThanNhanDetail} />
      <ErrorBoundaryRoute path={match.url} component={QuanHeThanNhan} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={QuanHeThanNhanDeleteDialog} />
  </>
);

export default Routes;
