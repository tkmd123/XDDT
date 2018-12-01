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
                <th className="hand" onClick={this.sort('ngayLayMau')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ngayLayMau">Ngay Lay Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nguoiLayMau')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.nguoiLayMau">Nguoi Lay Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ngayTiepNhan')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ngayTiepNhan">Ngay Tiep Nhan</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('soLuongMau')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.soLuongMau">So Luong Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('trangThaiMau')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.trangThaiMau">Trang Thai Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('moTa')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.moTa">Mo Ta</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ghiChu')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.ghiChu">Ghi Chu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fileGoc')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.fileGoc">File Goc</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fileKetQua')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.fileKetQua">File Ket Qua</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('fileDotBien')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.fileDotBien">File Dot Bien</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf1')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.udf1">Udf 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf2')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.udf2">Udf 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf3')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.udf3">Udf 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf4')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.udf4">Udf 4</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf5')}>
                  <Translate contentKey="xddtApp.mauXetNghiem.udf5">Udf 5</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.hoSoGiamDinh">Ho So Giam Dinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauXetNghiem.nhanVienNhanMau">Nhan Vien Nhan Mau</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
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
                  <td>
                    <TextFormat type="date" value={mauXetNghiem.ngayLayMau} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{mauXetNghiem.nguoiLayMau}</td>
                  <td>
                    <TextFormat type="date" value={mauXetNghiem.ngayTiepNhan} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{mauXetNghiem.soLuongMau}</td>
                  <td>
                    <Translate contentKey={`xddtApp.TrangThaiMau.${mauXetNghiem.trangThaiMau}`} />
                  </td>
                  <td>{mauXetNghiem.moTa}</td>
                  <td>{mauXetNghiem.ghiChu}</td>
                  <td>{mauXetNghiem.fileGoc}</td>
                  <td>{mauXetNghiem.fileKetQua}</td>
                  <td>{mauXetNghiem.fileDotBien}</td>
                  <td>{mauXetNghiem.isDeleted ? 'true' : 'false'}</td>
                  <td>{mauXetNghiem.udf1}</td>
                  <td>{mauXetNghiem.udf2}</td>
                  <td>{mauXetNghiem.udf3}</td>
                  <td>{mauXetNghiem.udf4}</td>
                  <td>{mauXetNghiem.udf5}</td>
                  <td>
                    {mauXetNghiem.hoSoGiamDinh ? (
                      <Link to={`ho-so-giam-dinh/${mauXetNghiem.hoSoGiamDinh.id}`}>{mauXetNghiem.hoSoGiamDinh.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauXetNghiem.nhanVienNhanMau ? (
                      <Link to={`nhan-vien/${mauXetNghiem.nhanVienNhanMau.id}`}>{mauXetNghiem.nhanVienNhanMau.id}</Link>
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
