import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThongTinMo from './thong-tin-mo';
import ThongTinMoDetail from './thong-tin-mo-detail';
import ThongTinMoUpdate from './thong-tin-mo-update';
import ThongTinMoDeleteDialog from './thong-tin-mo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThongTinMoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThongTinMoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThongTinMoDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThongTinMo} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ThongTinMoDeleteDialog} />
  </>
);

export default Routes;
