import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import VungADN from './vung-adn';
import VungADNDetail from './vung-adn-detail';
import VungADNUpdate from './vung-adn-update';
import VungADNDeleteDialog from './vung-adn-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VungADNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VungADNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VungADNDetail} />
      <ErrorBoundaryRoute path={match.url} component={VungADN} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={VungADNDeleteDialog} />
  </>
);

export default Routes;
