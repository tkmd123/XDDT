import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoaChatTachChiet from './hoa-chat-tach-chiet';
import HoaChatTachChietDetail from './hoa-chat-tach-chiet-detail';
import HoaChatTachChietUpdate from './hoa-chat-tach-chiet-update';
import HoaChatTachChietDeleteDialog from './hoa-chat-tach-chiet-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoaChatTachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoaChatTachChietUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoaChatTachChietDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoaChatTachChiet} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoaChatTachChietDeleteDialog} />
  </>
);

export default Routes;
