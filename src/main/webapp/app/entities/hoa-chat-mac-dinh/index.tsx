import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoaChatMacDinh from './hoa-chat-mac-dinh';
import HoaChatMacDinhDetail from './hoa-chat-mac-dinh-detail';
import HoaChatMacDinhUpdate from './hoa-chat-mac-dinh-update';
import HoaChatMacDinhDeleteDialog from './hoa-chat-mac-dinh-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoaChatMacDinhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoaChatMacDinhUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoaChatMacDinhDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoaChatMacDinh} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoaChatMacDinhDeleteDialog} />
  </>
);

export default Routes;
