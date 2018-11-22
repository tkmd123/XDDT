import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ThongTinADN from './thong-tin-adn';
import ThongTinADNDetail from './thong-tin-adn-detail';
import ThongTinADNUpdate from './thong-tin-adn-update';
import ThongTinADNDeleteDialog from './thong-tin-adn-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ThongTinADNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ThongTinADNUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ThongTinADNDetail} />
      <ErrorBoundaryRoute path={match.url} component={ThongTinADN} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ThongTinADNDeleteDialog} />
  </>
);

export default Routes;
