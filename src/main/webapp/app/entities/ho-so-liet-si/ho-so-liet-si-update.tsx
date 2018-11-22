import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPhuongXa } from 'app/shared/model/phuong-xa.model';
import { getEntities as getPhuongXas } from 'app/entities/phuong-xa/phuong-xa.reducer';
import { IDonVi } from 'app/shared/model/don-vi.model';
import { getEntities as getDonVis } from 'app/entities/don-vi/don-vi.reducer';
import { ICapBac } from 'app/shared/model/cap-bac.model';
import { getEntities as getCapBacs } from 'app/entities/cap-bac/cap-bac.reducer';
import { IChucVu } from 'app/shared/model/chuc-vu.model';
import { getEntities as getChucVus } from 'app/entities/chuc-vu/chuc-vu.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ho-so-liet-si.reducer';
import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoSoLietSiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoSoLietSiUpdateState {
  isNew: boolean;
  phuongXaId: string;
  donViHiSinhId: string;
  donViHuanLuyenId: string;
  capBacId: string;
  chucVuId: string;
}

export class HoSoLietSiUpdate extends React.Component<IHoSoLietSiUpdateProps, IHoSoLietSiUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      phuongXaId: '0',
      donViHiSinhId: '0',
      donViHuanLuyenId: '0',
      capBacId: '0',
      chucVuId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getPhuongXas();
    this.props.getDonVis();
    this.props.getCapBacs();
    this.props.getChucVus();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { hoSoLietSiEntity } = this.props;
      const entity = {
        ...hoSoLietSiEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/ho-so-liet-si');
  };

  render() {
    const { hoSoLietSiEntity, phuongXas, donVis, capBacs, chucVus, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoSoLietSi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoSoLietSi.home.createOrEditLabel">Create or edit a HoSoLietSi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoSoLietSiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ho-so-liet-si-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maCCSLabel" for="maCCS">
                    <Translate contentKey="xddtApp.hoSoLietSi.maCCS">Ma CCS</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-maCCS" type="text" name="maCCS" />
                </AvGroup>
                <AvGroup>
                  <Label id="maLietSiLabel" for="maLietSi">
                    <Translate contentKey="xddtApp.hoSoLietSi.maLietSi">Ma Liet Si</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-maLietSi" type="text" name="maLietSi" />
                </AvGroup>
                <AvGroup>
                  <Label id="hoTenLabel" for="hoTen">
                    <Translate contentKey="xddtApp.hoSoLietSi.hoTen">Ho Ten</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hoTen" type="text" name="hoTen" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenKhacLabel" for="tenKhac">
                    <Translate contentKey="xddtApp.hoSoLietSi.tenKhac">Ten Khac</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-tenKhac" type="text" name="tenKhac" />
                </AvGroup>
                <AvGroup>
                  <Label id="biDanhLabel" for="biDanh">
                    <Translate contentKey="xddtApp.hoSoLietSi.biDanh">Bi Danh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-biDanh" type="text" name="biDanh" />
                </AvGroup>
                <AvGroup>
                  <Label id="gioiTinhLabel" for="gioiTinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.gioiTinh">Gioi Tinh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-gioiTinh" type="text" name="gioiTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="namSinhLabel" for="namSinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.namSinh">Nam Sinh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-namSinh" type="text" name="namSinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="queThonLabel" for="queThon">
                    <Translate contentKey="xddtApp.hoSoLietSi.queThon">Que Thon</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-queThon" type="text" name="queThon" />
                </AvGroup>
                <AvGroup>
                  <Label id="queXaLabel" for="queXa">
                    <Translate contentKey="xddtApp.hoSoLietSi.queXa">Que Xa</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-queXa" type="text" name="queXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="queHuyenLabel" for="queHuyen">
                    <Translate contentKey="xddtApp.hoSoLietSi.queHuyen">Que Huyen</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-queHuyen" type="text" name="queHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="queTinhLabel" for="queTinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.queTinh">Que Tinh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-queTinh" type="text" name="queTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="namNhapNguLabel" for="namNhapNgu">
                    <Translate contentKey="xddtApp.hoSoLietSi.namNhapNgu">Nam Nhap Ngu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-namNhapNgu" type="string" className="form-control" name="namNhapNgu" />
                </AvGroup>
                <AvGroup>
                  <Label id="namXuatNguLabel" for="namXuatNgu">
                    <Translate contentKey="xddtApp.hoSoLietSi.namXuatNgu">Nam Xuat Ngu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-namXuatNgu" type="string" className="form-control" name="namXuatNgu" />
                </AvGroup>
                <AvGroup>
                  <Label id="namTaiNguLabel" for="namTaiNgu">
                    <Translate contentKey="xddtApp.hoSoLietSi.namTaiNgu">Nam Tai Ngu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-namTaiNgu" type="string" className="form-control" name="namTaiNgu" />
                </AvGroup>
                <AvGroup>
                  <Label id="namDiBLabel" for="namDiB">
                    <Translate contentKey="xddtApp.hoSoLietSi.namDiB">Nam Di B</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-namDiB" type="text" name="namDiB" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhNgayLabel" for="hySinhNgay">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhNgay">Hy Sinh Ngay</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhNgay" type="string" className="form-control" name="hySinhNgay" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhThangLabel" for="hySinhThang">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhThang">Hy Sinh Thang</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhThang" type="string" className="form-control" name="hySinhThang" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhNamLabel" for="hySinhNam">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhNam">Hy Sinh Nam</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhNam" type="string" className="form-control" name="hySinhNam" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhLyDoLabel" for="hySinhLyDo">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhLyDo">Hy Sinh Ly Do</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhLyDo" type="text" name="hySinhLyDo" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhDonViLabel" for="hySinhDonVi">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhDonVi">Hy Sinh Don Vi</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhDonVi" type="text" name="hySinhDonVi" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhTranDanhLabel" for="hySinhTranDanh">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhTranDanh">Hy Sinh Tran Danh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhTranDanh" type="text" name="hySinhTranDanh" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhDiaDiemLabel" for="hySinhDiaDiem">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhDiaDiem">Hy Sinh Dia Diem</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhDiaDiem" type="text" name="hySinhDiaDiem" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhXaLabel" for="hySinhXa">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhXa">Hy Sinh Xa</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhXa" type="text" name="hySinhXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhHuyenLabel" for="hySinhHuyen">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhHuyen">Hy Sinh Huyen</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhHuyen" type="text" name="hySinhHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="hySinhTinhLabel" for="hySinhTinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.hySinhTinh">Hy Sinh Tinh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-hySinhTinh" type="text" name="hySinhTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="anTangDiaDiemLabel" for="anTangDiaDiem">
                    <Translate contentKey="xddtApp.hoSoLietSi.anTangDiaDiem">An Tang Dia Diem</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-anTangDiaDiem" type="text" name="anTangDiaDiem" />
                </AvGroup>
                <AvGroup>
                  <Label id="anTangXaLabel" for="anTangXa">
                    <Translate contentKey="xddtApp.hoSoLietSi.anTangXa">An Tang Xa</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-anTangXa" type="text" name="anTangXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="anTangHuyenLabel" for="anTangHuyen">
                    <Translate contentKey="xddtApp.hoSoLietSi.anTangHuyen">An Tang Huyen</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-anTangHuyen" type="text" name="anTangHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="anTangTinhLabel" for="anTangTinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.anTangTinh">An Tang Tinh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-anTangTinh" type="text" name="anTangTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayBaoTuLabel" for="ngayBaoTu">
                    <Translate contentKey="xddtApp.hoSoLietSi.ngayBaoTu">Ngay Bao Tu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-ngayBaoTu" type="text" name="ngayBaoTu" />
                </AvGroup>
                <AvGroup>
                  <Label id="giayBaoTuLabel" for="giayBaoTu">
                    <Translate contentKey="xddtApp.hoSoLietSi.giayBaoTu">Giay Bao Tu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-giayBaoTu" type="text" name="giayBaoTu" />
                </AvGroup>
                <AvGroup>
                  <Label id="vatDungKemTheoLabel" for="vatDungKemTheo">
                    <Translate contentKey="xddtApp.hoSoLietSi.vatDungKemTheo">Vat Dung Kem Theo</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-vatDungKemTheo" type="text" name="vatDungKemTheo" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.hoSoLietSi.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="ho-so-liet-si-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoSoLietSi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="trangThaiXacMinhLabel" for="trangThaiXacMinh">
                    <Translate contentKey="xddtApp.hoSoLietSi.trangThaiXacMinh">Trang Thai Xac Minh</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-trangThaiXacMinh" type="text" name="trangThaiXacMinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="uDF1Label" for="uDF1">
                    <Translate contentKey="xddtApp.hoSoLietSi.uDF1">U DF 1</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-uDF1" type="text" name="uDF1" />
                </AvGroup>
                <AvGroup>
                  <Label id="uDF2Label" for="uDF2">
                    <Translate contentKey="xddtApp.hoSoLietSi.uDF2">U DF 2</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-uDF2" type="text" name="uDF2" />
                </AvGroup>
                <AvGroup>
                  <Label id="uDF3Label" for="uDF3">
                    <Translate contentKey="xddtApp.hoSoLietSi.uDF3">U DF 3</Translate>
                  </Label>
                  <AvField id="ho-so-liet-si-uDF3" type="text" name="uDF3" />
                </AvGroup>
                <AvGroup>
                  <Label for="phuongXa.id">
                    <Translate contentKey="xddtApp.hoSoLietSi.phuongXa">Phuong Xa</Translate>
                  </Label>
                  <AvInput id="ho-so-liet-si-phuongXa" type="select" className="form-control" name="phuongXa.id">
                    <option value="" key="0" />
                    {phuongXas
                      ? phuongXas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="donViHiSinh.id">
                    <Translate contentKey="xddtApp.hoSoLietSi.donViHiSinh">Don Vi Hi Sinh</Translate>
                  </Label>
                  <AvInput id="ho-so-liet-si-donViHiSinh" type="select" className="form-control" name="donViHiSinh.id">
                    <option value="" key="0" />
                    {donVis
                      ? donVis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="donViHuanLuyen.id">
                    <Translate contentKey="xddtApp.hoSoLietSi.donViHuanLuyen">Don Vi Huan Luyen</Translate>
                  </Label>
                  <AvInput id="ho-so-liet-si-donViHuanLuyen" type="select" className="form-control" name="donViHuanLuyen.id">
                    <option value="" key="0" />
                    {donVis
                      ? donVis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="capBac.id">
                    <Translate contentKey="xddtApp.hoSoLietSi.capBac">Cap Bac</Translate>
                  </Label>
                  <AvInput id="ho-so-liet-si-capBac" type="select" className="form-control" name="capBac.id">
                    <option value="" key="0" />
                    {capBacs
                      ? capBacs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="chucVu.id">
                    <Translate contentKey="xddtApp.hoSoLietSi.chucVu">Chuc Vu</Translate>
                  </Label>
                  <AvInput id="ho-so-liet-si-chucVu" type="select" className="form-control" name="chucVu.id">
                    <option value="" key="0" />
                    {chucVus
                      ? chucVus.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ho-so-liet-si" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  phuongXas: storeState.phuongXa.entities,
  donVis: storeState.donVi.entities,
  capBacs: storeState.capBac.entities,
  chucVus: storeState.chucVu.entities,
  hoSoLietSiEntity: storeState.hoSoLietSi.entity,
  loading: storeState.hoSoLietSi.loading,
  updating: storeState.hoSoLietSi.updating,
  updateSuccess: storeState.hoSoLietSi.updateSuccess
});

const mapDispatchToProps = {
  getPhuongXas,
  getDonVis,
  getCapBacs,
  getChucVus,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoSoLietSiUpdate);
