import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import {
  Translate,
  translate,
  ICrudSearchAction,
  ICrudGetAllAction,
  TextFormat,
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './ho-so-than-nhan.reducer';
import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IHoSoThanNhanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoSoThanNhanState extends IPaginationBaseState {
  search: string;
}

export class HoSoThanNhan extends React.Component<IHoSoThanNhanProps, IHoSoThanNhanState> {
  state: IHoSoThanNhanState = {
    search: '',
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.getEntities();
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

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => this.sortEntities()
    );
  };

  sortEntities() {
    this.getEntities();
    this.props.history.push(`${this.props.location.pathname}?page=${this.state.activePage}&sort=${this.state.sort},${this.state.order}`);
  }

  handlePagination = activePage => this.setState({ activePage }, () => this.sortEntities());

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { hoSoThanNhanList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="ho-so-than-nhan-heading">
          <Translate contentKey="xddtApp.hoSoThanNhan.home.title">Ho So Than Nhans</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoSoThanNhan.home.createLabel">Create new Ho So Than Nhan</Translate>
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
                    placeholder={translate('xddtApp.hoSoThanNhan.home.search')}
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
                <th className="hand" onClick={this.sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('maThanNhan')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.maThanNhan">Ma Than Nhan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isLayMau')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.isLayMau">Is Lay Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hoTen')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.hoTen">Ho Ten</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namSinh')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.namSinh">Nam Sinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gioiTinh')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.gioiTinh">Gioi Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('soCMT')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.soCMT">So CMT</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('diaChi')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.diaChi">Dia Chi</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dienThoaiChinh')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiChinh">Dien Thoai Chinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dienThoaiPhu')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiPhu">Dien Thoai Phu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('email')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.email">Email</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ghiChu')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.ghiChu">Ghi Chu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf1')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.udf1">Udf 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf2')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.udf2">Udf 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf3')}>
                  <Translate contentKey="xddtApp.hoSoThanNhan.udf3">Udf 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoThanNhan.phuongXa">Phuong Xa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoSoThanNhanList.map((hoSoThanNhan, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoSoThanNhan.id}`} color="link" size="sm">
                      {hoSoThanNhan.id}
                    </Button>
                  </td>
                  <td>{hoSoThanNhan.maThanNhan}</td>
                  <td>{hoSoThanNhan.isLayMau ? 'true' : 'false'}</td>
                  <td>{hoSoThanNhan.hoTen}</td>
                  <td>
                    <TextFormat type="date" value={hoSoThanNhan.namSinh} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{hoSoThanNhan.gioiTinh}</td>
                  <td>{hoSoThanNhan.soCMT}</td>
                  <td>{hoSoThanNhan.diaChi}</td>
                  <td>{hoSoThanNhan.dienThoaiChinh}</td>
                  <td>{hoSoThanNhan.dienThoaiPhu}</td>
                  <td>{hoSoThanNhan.email}</td>
                  <td>{hoSoThanNhan.ghiChu}</td>
                  <td>{hoSoThanNhan.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoSoThanNhan.udf1}</td>
                  <td>{hoSoThanNhan.udf2}</td>
                  <td>{hoSoThanNhan.udf3}</td>
                  <td>
                    {hoSoThanNhan.phuongXa ? <Link to={`phuong-xa/${hoSoThanNhan.phuongXa.id}`}>{hoSoThanNhan.phuongXa.id}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoSoThanNhan.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoThanNhan.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoThanNhan.id}/delete`} color="danger" size="sm">
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
        <Row className="justify-content-center">
          <JhiPagination
            items={getPaginationItemsNumber(totalItems, this.state.itemsPerPage)}
            activePage={this.state.activePage}
            onSelect={this.handlePagination}
            maxButtons={5}
          />
        </Row>
      </div>
    );
  }
}

const mapStateToProps = ({ hoSoThanNhan }: IRootState) => ({
  hoSoThanNhanList: hoSoThanNhan.entities,
  totalItems: hoSoThanNhan.totalItems
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
)(HoSoThanNhan);
