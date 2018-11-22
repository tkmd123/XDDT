import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './than-nhan-liet-si.reducer';
import { IThanNhanLietSi } from 'app/shared/model/than-nhan-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThanNhanLietSiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IThanNhanLietSiState {
  search: string;
}

export class ThanNhanLietSi extends React.Component<IThanNhanLietSiProps, IThanNhanLietSiState> {
  state: IThanNhanLietSiState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.props.getEntities();
    this.setState({
      search: ''
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { thanNhanLietSiList, match } = this.props;
    return (
      <div>
        <h2 id="than-nhan-liet-si-heading">
          <Translate contentKey="xddtApp.thanNhanLietSi.home.title">Than Nhan Liet Sis</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.thanNhanLietSi.home.createLabel">Create new Than Nhan Liet Si</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('xddtApp.thanNhanLietSi.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.thanNhanLietSi.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.thanNhanLietSi.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.thanNhanLietSi.lietSi">Liet Si</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.thanNhanLietSi.thanNhan">Than Nhan</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.thanNhanLietSi.quanHeThanNhan">Quan He Than Nhan</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {thanNhanLietSiList.map((thanNhanLietSi, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${thanNhanLietSi.id}`} color="link" size="sm">
                      {thanNhanLietSi.id}
                    </Button>
                  </td>
                  <td>{thanNhanLietSi.moTa}</td>
                  <td>{thanNhanLietSi.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {thanNhanLietSi.lietSi ? <Link to={`ho-so-liet-si/${thanNhanLietSi.lietSi.id}`}>{thanNhanLietSi.lietSi.id}</Link> : ''}
                  </td>
                  <td>
                    {thanNhanLietSi.thanNhan ? (
                      <Link to={`ho-so-than-nhan/${thanNhanLietSi.thanNhan.id}`}>{thanNhanLietSi.thanNhan.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {thanNhanLietSi.quanHeThanNhan ? (
                      <Link to={`quan-he-than-nhan/${thanNhanLietSi.quanHeThanNhan.id}`}>{thanNhanLietSi.quanHeThanNhan.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${thanNhanLietSi.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${thanNhanLietSi.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${thanNhanLietSi.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ thanNhanLietSi }: IRootState) => ({
  thanNhanLietSiList: thanNhanLietSi.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThanNhanLietSi);
