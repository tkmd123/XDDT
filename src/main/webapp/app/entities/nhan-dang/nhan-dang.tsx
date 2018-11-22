import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './nhan-dang.reducer';
import { INhanDang } from 'app/shared/model/nhan-dang.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanDangProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface INhanDangState {
  search: string;
}

export class NhanDang extends React.Component<INhanDangProps, INhanDangState> {
  state: INhanDangState = {
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
    const { nhanDangList, match } = this.props;
    return (
      <div>
        <h2 id="nhan-dang-heading">
          <Translate contentKey="xddtApp.nhanDang.home.title">Nhan Dangs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.nhanDang.home.createLabel">Create new Nhan Dang</Translate>
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
                    placeholder={translate('xddtApp.nhanDang.home.search')}
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
                  <Translate contentKey="xddtApp.nhanDang.maNhanDang">Ma Nhan Dang</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanDang.tenNhanDang">Ten Nhan Dang</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanDang.donViTinh">Don Vi Tinh</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanDang.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanDang.isDeleted">Is Deleted</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nhanDangList.map((nhanDang, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nhanDang.id}`} color="link" size="sm">
                      {nhanDang.id}
                    </Button>
                  </td>
                  <td>{nhanDang.maNhanDang}</td>
                  <td>{nhanDang.tenNhanDang}</td>
                  <td>{nhanDang.donViTinh}</td>
                  <td>{nhanDang.moTa}</td>
                  <td>{nhanDang.isDeleted ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nhanDang.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nhanDang.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nhanDang.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ nhanDang }: IRootState) => ({
  nhanDangList: nhanDang.entities
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
)(NhanDang);
