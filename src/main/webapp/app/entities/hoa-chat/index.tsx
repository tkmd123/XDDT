import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import HoaChat from './hoa-chat';
import HoaChatDetail from './hoa-chat-detail';
import HoaChatUpdate from './hoa-chat-update';
import HoaChatDeleteDialog from './hoa-chat-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HoaChatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HoaChatUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HoaChatDetail} />
      <ErrorBoundaryRoute path={match.url} component={HoaChat} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HoaChatDeleteDialog} />
  </>
);

export default Routes;
