import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './nhan-vien.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanVienProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface INhanVienState {
  search: string;
}

export class NhanVien extends React.Component<INhanVienProps, INhanVienState> {
  state: INhanVienState = {
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
    const { nhanVienList, match } = this.props;
    return (
      <div>
        <h2 id="nhan-vien-heading">
          <Translate contentKey="xddtApp.nhanVien.home.title">Nhan Viens</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.nhanVien.home.createLabel">Create new Nhan Vien</Translate>
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
                    placeholder={translate('xddtApp.nhanVien.home.search')}
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
                  <Translate contentKey="xddtApp.nhanVien.maNhanVien">Ma Nhan Vien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.tenNhanVien">Ten Nhan Vien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.soDienThoai">So Dien Thoai</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.ghiChu">Ghi Chu</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.userNhanVien">User Nhan Vien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.nhanVien.phongban">Phongban</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nhanVienList.map((nhanVien, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nhanVien.id}`} color="link" size="sm">
                      {nhanVien.id}
                    </Button>
                  </td>
                  <td>{nhanVien.maNhanVien}</td>
                  <td>{nhanVien.tenNhanVien}</td>
                  <td>{nhanVien.soDienThoai}</td>
                  <td>{nhanVien.email}</td>
                  <td>{nhanVien.moTa}</td>
                  <td>{nhanVien.ghiChu}</td>
                  <td>{nhanVien.isDeleted ? 'true' : 'false'}</td>
                  <td>{nhanVien.udf1}</td>
                  <td>{nhanVien.udf2}</td>
                  <td>{nhanVien.udf3}</td>
                  <td>{nhanVien.userNhanVien ? nhanVien.userNhanVien.id : ''}</td>
                  <td>{nhanVien.phongban ? <Link to={`phong-ban/${nhanVien.phongban.id}`}>{nhanVien.phongban.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nhanVien.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nhanVien.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nhanVien.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ nhanVien }: IRootState) => ({
  nhanVienList: nhanVien.entities
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
)(NhanVien);
