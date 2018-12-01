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
  getSortState,
  IPaginationBaseState,
  getPaginationItemsNumber,
  JhiPagination
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './ho-so-liet-si.reducer';
import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IHoSoLietSiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoSoLietSiState extends IPaginationBaseState {
  search: string;
}

export class HoSoLietSi extends React.Component<IHoSoLietSiProps, IHoSoLietSiState> {
  state: IHoSoLietSiState = {
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
    const { hoSoLietSiList, match, totalItems } = this.props;
    return (
      <div>
        <h2 id="ho-so-liet-si-heading">
          <Translate contentKey="xddtApp.hoSoLietSi.home.title">Ho So Liet Sis</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoSoLietSi.home.createLabel">Create new Ho So Liet Si</Translate>
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
                    placeholder={translate('xddtApp.hoSoLietSi.home.search')}
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
                <th className="hand" onClick={this.sort('maCCS')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.maCCS">Ma CCS</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('maLietSi')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.maLietSi">Ma Liet Si</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hoTen')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hoTen">Ho Ten</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tenKhac')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.tenKhac">Ten Khac</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('biDanh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.biDanh">Bi Danh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('gioiTinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.gioiTinh">Gioi Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namSinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.namSinh">Nam Sinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('queThon')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.queThon">Que Thon</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('queXa')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.queXa">Que Xa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('queHuyen')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.queHuyen">Que Huyen</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('queTinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.queTinh">Que Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('donVi')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.donVi">Don Vi</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namNhapNgu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.namNhapNgu">Nam Nhap Ngu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namXuatNgu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.namXuatNgu">Nam Xuat Ngu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namTaiNgu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.namTaiNgu">Nam Tai Ngu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('namDiB')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.namDiB">Nam Di B</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhNgay')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhNgay">Hy Sinh Ngay</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhThang')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhThang">Hy Sinh Thang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhNam')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhNam">Hy Sinh Nam</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhLyDo')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhLyDo">Hy Sinh Ly Do</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhDonVi')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhDonVi">Hy Sinh Don Vi</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhTranDanh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhTranDanh">Hy Sinh Tran Danh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhDiaDiem')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhDiaDiem">Hy Sinh Dia Diem</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhXa')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhXa">Hy Sinh Xa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhHuyen')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhHuyen">Hy Sinh Huyen</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('hySinhTinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.hySinhTinh">Hy Sinh Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('anTangDiaDiem')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.anTangDiaDiem">An Tang Dia Diem</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('anTangXa')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.anTangXa">An Tang Xa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('anTangHuyen')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.anTangHuyen">An Tang Huyen</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('anTangTinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.anTangTinh">An Tang Tinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ngayBaoTu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.ngayBaoTu">Ngay Bao Tu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('giayBaoTu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.giayBaoTu">Giay Bao Tu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('vatDungKemTheo')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.vatDungKemTheo">Vat Dung Kem Theo</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('ghiChu')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.ghiChu">Ghi Chu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('trangThaiXacMinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.trangThaiXacMinh">Trang Thai Xac Minh</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('chieuCao')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.chieuCao">Chieu Cao</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('canNang')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.canNang">Can Nang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('nhomMau')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.nhomMau">Nhom Mau</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dacDiemRang')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.dacDiemRang">Dac Diem Rang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tinhHuongHySinh')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.tinhHuongHySinh">Tinh Huong Hy Sinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('tinhHuongTimThay')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.tinhHuongTimThay">Tinh Huong Tim Thay</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('dacDiemNhanDang')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.dacDiemNhanDang">Dac Diem Nhan Dang</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('isDeleted')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf1')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.udf1">Udf 1</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf2')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.udf2">Udf 2</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf3')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.udf3">Udf 3</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf4')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.udf4">Udf 4</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={this.sort('udf5')}>
                  <Translate contentKey="xddtApp.hoSoLietSi.udf5">Udf 5</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoLietSi.phuongXa">Phuong Xa</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoLietSi.donViHiSinh">Don Vi Hi Sinh</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoLietSi.donViHuanLuyen">Don Vi Huan Luyen</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoLietSi.capBac">Cap Bac</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoLietSi.chucVu">Chuc Vu</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoSoLietSiList.map((hoSoLietSi, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoSoLietSi.id}`} color="link" size="sm">
                      {hoSoLietSi.id}
                    </Button>
                  </td>
                  <td>{hoSoLietSi.maCCS}</td>
                  <td>{hoSoLietSi.maLietSi}</td>
                  <td>{hoSoLietSi.hoTen}</td>
                  <td>{hoSoLietSi.tenKhac}</td>
                  <td>{hoSoLietSi.biDanh}</td>
                  <td>{hoSoLietSi.gioiTinh}</td>
                  <td>{hoSoLietSi.namSinh}</td>
                  <td>{hoSoLietSi.queThon}</td>
                  <td>{hoSoLietSi.queXa}</td>
                  <td>{hoSoLietSi.queHuyen}</td>
                  <td>{hoSoLietSi.queTinh}</td>
                  <td>{hoSoLietSi.donVi}</td>
                  <td>{hoSoLietSi.namNhapNgu}</td>
                  <td>{hoSoLietSi.namXuatNgu}</td>
                  <td>{hoSoLietSi.namTaiNgu}</td>
                  <td>{hoSoLietSi.namDiB}</td>
                  <td>{hoSoLietSi.hySinhNgay}</td>
                  <td>{hoSoLietSi.hySinhThang}</td>
                  <td>{hoSoLietSi.hySinhNam}</td>
                  <td>{hoSoLietSi.hySinhLyDo}</td>
                  <td>{hoSoLietSi.hySinhDonVi}</td>
                  <td>{hoSoLietSi.hySinhTranDanh}</td>
                  <td>{hoSoLietSi.hySinhDiaDiem}</td>
                  <td>{hoSoLietSi.hySinhXa}</td>
                  <td>{hoSoLietSi.hySinhHuyen}</td>
                  <td>{hoSoLietSi.hySinhTinh}</td>
                  <td>{hoSoLietSi.anTangDiaDiem}</td>
                  <td>{hoSoLietSi.anTangXa}</td>
                  <td>{hoSoLietSi.anTangHuyen}</td>
                  <td>{hoSoLietSi.anTangTinh}</td>
                  <td>{hoSoLietSi.ngayBaoTu}</td>
                  <td>{hoSoLietSi.giayBaoTu}</td>
                  <td>{hoSoLietSi.vatDungKemTheo}</td>
                  <td>{hoSoLietSi.ghiChu}</td>
                  <td>{hoSoLietSi.trangThaiXacMinh}</td>
                  <td>{hoSoLietSi.chieuCao}</td>
                  <td>{hoSoLietSi.canNang}</td>
                  <td>{hoSoLietSi.nhomMau}</td>
                  <td>{hoSoLietSi.dacDiemRang}</td>
                  <td>{hoSoLietSi.tinhHuongHySinh}</td>
                  <td>{hoSoLietSi.tinhHuongTimThay}</td>
                  <td>{hoSoLietSi.dacDiemNhanDang}</td>
                  <td>{hoSoLietSi.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoSoLietSi.udf1}</td>
                  <td>{hoSoLietSi.udf2}</td>
                  <td>{hoSoLietSi.udf3}</td>
                  <td>{hoSoLietSi.udf4}</td>
                  <td>{hoSoLietSi.udf5}</td>
                  <td>{hoSoLietSi.phuongXa ? <Link to={`phuong-xa/${hoSoLietSi.phuongXa.id}`}>{hoSoLietSi.phuongXa.id}</Link> : ''}</td>
                  <td>
                    {hoSoLietSi.donViHiSinh ? <Link to={`don-vi/${hoSoLietSi.donViHiSinh.id}`}>{hoSoLietSi.donViHiSinh.id}</Link> : ''}
                  </td>
                  <td>
                    {hoSoLietSi.donViHuanLuyen ? (
                      <Link to={`don-vi/${hoSoLietSi.donViHuanLuyen.id}`}>{hoSoLietSi.donViHuanLuyen.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{hoSoLietSi.capBac ? <Link to={`cap-bac/${hoSoLietSi.capBac.id}`}>{hoSoLietSi.capBac.id}</Link> : ''}</td>
                  <td>{hoSoLietSi.chucVu ? <Link to={`chuc-vu/${hoSoLietSi.chucVu.id}`}>{hoSoLietSi.chucVu.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoSoLietSi.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoLietSi.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoLietSi.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hoSoLietSi }: IRootState) => ({
  hoSoLietSiList: hoSoLietSi.entities,
  totalItems: hoSoLietSi.totalItems
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
)(HoSoLietSi);
