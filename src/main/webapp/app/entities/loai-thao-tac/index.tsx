import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LoaiThaoTac from './loai-thao-tac';
import LoaiThaoTacDetail from './loai-thao-tac-detail';
import LoaiThaoTacUpdate from './loai-thao-tac-update';
import LoaiThaoTacDeleteDialog from './loai-thao-tac-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LoaiThaoTacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LoaiThaoTacUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LoaiThaoTacDetail} />
      <ErrorBoundaryRoute path={match.url} component={LoaiThaoTac} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LoaiThaoTacDeleteDialog} />
  </>
);

export default Routes;
