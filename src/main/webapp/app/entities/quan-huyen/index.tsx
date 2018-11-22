import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import QuanHuyen from './quan-huyen';
import QuanHuyenDetail from './quan-huyen-detail';
import QuanHuyenUpdate from './quan-huyen-update';
import QuanHuyenDeleteDialog from './quan-huyen-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QuanHuyenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QuanHuyenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QuanHuyenDetail} />
      <ErrorBoundaryRoute path={match.url} component={QuanHuyen} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={QuanHuyenDeleteDialog} />
  </>
);

export default Routes;
