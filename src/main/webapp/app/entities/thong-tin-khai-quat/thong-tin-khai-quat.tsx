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
import { getSearchEntities, getEntities } from './thong-tin-khai-quat.reducer';
import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IThongTinKhaiQuatProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IThongTinKhaiQuatState extends IPaginationBaseState {
  search: string;
}

export class ThongTinKhaiQuat extends React.Component<IThongTinKhaiQuatProps, IThongTinKhaiQuatState> {
  state: IThongTinKhaiQuatState = {
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
    const { thongTinKhaiQuatList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="thong-tin-khai-quat-heading">
          <Translate contentKey="xddtApp.thongTinKhaiQuat.home.title">Thong Tin Khai Quats</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.thongTinKhaiQuat.home.createLabel">Create new Thong Tin Khai Quat</Translate>
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
                    placeholder={translate('xddtApp.thongTinKhaiQuat.home.search')}
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
                <th className="hand" onClick={this.sort('maKhaiQuat')}>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.maKhaiQuat">Ma Khai Quat</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nguoiKhaiQuat')}>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.nguoiKhaiQuat">Nguoi Khai Quat</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('donViKhaiQuat')}>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.donViKhaiQuat">Don Vi Khai Quat</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('thoiGianKhaiQuat')}>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.thoiGianKhaiQuat">Thoi Gian Khai Quat</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.thongTinKhaiQuat.thongTinMo">Thong Tin Mo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {thongTinKhaiQuatList.map((thongTinKhaiQuat, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${thongTinKhaiQuat.id}`} color="link" size="sm">
                      {thongTinKhaiQuat.id}
                    </Button>
                  </td>
                  <td>{thongTinKhaiQuat.maKhaiQuat}</td>
                  <td>{thongTinKhaiQuat.nguoiKhaiQuat}</td>
                  <td>{thongTinKhaiQuat.donViKhaiQuat}</td>
                  <td>
                    <TextFormat type="date" value={thongTinKhaiQuat.thoiGianKhaiQuat} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{thongTinKhaiQuat.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {thongTinKhaiQuat.thongTinMo ? (
                      <Link to={`thong-tin-mo/${thongTinKhaiQuat.thongTinMo.id}`}>{thongTinKhaiQuat.thongTinMo.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${thongTinKhaiQuat.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${thongTinKhaiQuat.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${thongTinKhaiQuat.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ thongTinKhaiQuat }: IRootState) => ({
  thongTinKhaiQuatList: thongTinKhaiQuat.entities,
  totalItems: thongTinKhaiQuat.totalItems
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
)(ThongTinKhaiQuat);
