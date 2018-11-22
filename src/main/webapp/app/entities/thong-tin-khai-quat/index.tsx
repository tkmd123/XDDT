import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThongTinKhaiQuat from './thong-tin-khai-quat';
import ThongTinKhaiQuatDetail from './thong-tin-khai-quat-detail';
import ThongTinKhaiQuatUpdate from './thong-tin-khai-quat-update';
import ThongTinKhaiQuatDeleteDialog from './thong-tin-khai-quat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThongTinKhaiQuatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThongTinKhaiQuatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThongTinKhaiQuatDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThongTinKhaiQuat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ThongTinKhaiQuatDeleteDialog} />
  </>
);

export default Routes;
