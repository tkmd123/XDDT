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
import { getSearchEntities, getEntities } from './mau-xet-nghiem.reducer';
import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IMauXetNghiemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IMauXetNghiemState extends IPaginationBaseState {
  search: string;
}

export class MauXetNghiem extends React.Component<IMauXetNghiemProps, IMauXetNghiemState> {
  state: IMauXetNghiemState = {
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
    const { mauXetNghiemList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="mau-xet-nghiem-heading">
          <Translate contentKey="xddtApp.mauXetNghiem.home.title">Mau Xet Nghiems</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.mauXetNghiem.home.createLabel">Create new Mau Xet Nghiem</Translate>
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
                    placeholder={translate('xddtApp.mauXetNghiem.home.search')}
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
                <th className="hand" onClick={this.sort('maMauXetNghiem')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.maMauXetNghiem">Ma Mau Xet Nghiem</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nguoiTiepNhan')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.nguoiTiepNhan">Nguoi Tiep Nhan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ngayLayMau')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ngayLayMau">Ngay Lay Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ngayTiepNhan')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ngayTiepNhan">Ngay Tiep Nhan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('trangThaiXuLy')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.trangThaiXuLy">Trang Thai Xu Ly</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('moTa')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.moTa">Mo Ta</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ghiChu')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ghiChu">Ghi Chu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.mauThongTinADN">Mau Thong Tin ADN</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.loaiMauXetNghiem">Loai Mau Xet Nghiem</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.haiCotLietSi">Hai Cot Liet Si</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.thanNhan">Than Nhan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.loaiThaoTac">Loai Thao Tac</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mauXetNghiemList.map((mauXetNghiem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mauXetNghiem.id}`} color="link" size="sm">
                      {mauXetNghiem.id}
                    </Button>
                  </td>
                  <td>{mauXetNghiem.maMauXetNghiem}</td>
                  <td>{mauXetNghiem.nguoiTiepNhan}</td>
                  <td>
                    <TextFormat type="date" value={mauXetNghiem.ngayLayMau} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={mauXetNghiem.ngayTiepNhan} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{mauXetNghiem.trangThaiXuLy}</td>
                  <td>{mauXetNghiem.moTa}</td>
                  <td>{mauXetNghiem.ghiChu}</td>
                  <td>{mauXetNghiem.isDeleted ? 'true' : 'false'}</td>
                  <td>
                    {mauXetNghiem.mauThongTinADN ? (
                      <Link to={`thong-tin-adn/${mauXetNghiem.mauThongTinADN.id}`}>{mauXetNghiem.mauThongTinADN.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauXetNghiem.loaiMauXetNghiem ? (
                      <Link to={`loai-mau-xet-nghiem/${mauXetNghiem.loaiMauXetNghiem.id}`}>{mauXetNghiem.loaiMauXetNghiem.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauXetNghiem.haiCotLietSi ? (
                      <Link to={`hai-cot-liet-si/${mauXetNghiem.haiCotLietSi.id}`}>{mauXetNghiem.haiCotLietSi.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauXetNghiem.thanNhan ? (
                      <Link to={`ho-so-than-nhan/${mauXetNghiem.thanNhan.id}`}>{mauXetNghiem.thanNhan.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauXetNghiem.loaiThaoTac ? (
                      <Link to={`loai-thao-tac/${mauXetNghiem.loaiThaoTac.id}`}>{mauXetNghiem.loaiThaoTac.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mauXetNghiem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mauXetNghiem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mauXetNghiem.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ mauXetNghiem }: IRootState) => ({
  mauXetNghiemList: mauXetNghiem.entities,
  totalItems: mauXetNghiem.totalItems
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
)(MauXetNghiem);
