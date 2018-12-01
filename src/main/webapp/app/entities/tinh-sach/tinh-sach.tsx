import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './tinh-sach.reducer';
import { ITinhSach } from 'app/shared/model/tinh-sach.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITinhSachProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITinhSachState {
  search: string;
}

export class TinhSach extends React.Component<ITinhSachProps, ITinhSachState> {
  state: ITinhSachState = {
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
    const { tinhSachList, match } = this.props;
    return (
      <div>
        <h2 id="tinh-sach-heading">
          <Translate contentKey="xddtApp.tinhSach.home.title">Tinh Saches</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.tinhSach.home.createLabel">Create new Tinh Sach</Translate>
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
                    placeholder={translate('xddtApp.tinhSach.home.search')}
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
                  <Translate contentKey="xddtApp.tinhSach.maTinhSach">Ma Tinh Sach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.phuongPhapTinhSach">Phuong Phap Tinh Sach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.nhanVienTinhSach">Nhan Vien Tinh Sach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSach.mayTinhSach">May Tinh Sach</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tinhSachList.map((tinhSach, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tinhSach.id}`} color="link" size="sm">
                      {tinhSach.id}
                    </Button>
                  </td>
                  <td>{tinhSach.maTinhSach}</td>
                  <td>
                    <TextFormat type="date" value={tinhSach.thoiGianThucHien} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`xddtApp.PhuongPhapTinhSach.${tinhSach.phuongPhapTinhSach}`} />
                  </td>
                  <td>{tinhSach.moTa}</td>
                  <td>{tinhSach.isDeleted ? 'true' : 'false'}</td>
                  <td>{tinhSach.udf1}</td>
                  <td>{tinhSach.udf2}</td>
                  <td>{tinhSach.udf3}</td>
                  <td>
                    {tinhSach.nhanVienTinhSach ? (
                      <Link to={`nhan-vien/${tinhSach.nhanVienTinhSach.id}`}>{tinhSach.nhanVienTinhSach.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{tinhSach.mayTinhSach ? <Link to={`may-pcr/${tinhSach.mayTinhSach.id}`}>{tinhSach.mayTinhSach.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tinhSach.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tinhSach.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tinhSach.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ tinhSach }: IRootState) => ({
  tinhSachList: tinhSach.entities
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
)(TinhSach);
