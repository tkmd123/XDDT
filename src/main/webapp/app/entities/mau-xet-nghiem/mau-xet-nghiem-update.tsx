import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITinhSach } from 'app/shared/model/tinh-sach.model';
import { getEntities as getTinhSaches } from 'app/entities/tinh-sach/tinh-sach.reducer';
import { IHoSoGiamDinh } from 'app/shared/model/ho-so-giam-dinh.model';
import { getEntities as getHoSoGiamDinhs } from 'app/entities/ho-so-giam-dinh/ho-so-giam-dinh.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
import { getEntities as getNhanViens } from 'app/entities/nhan-vien/nhan-vien.reducer';
import { ILoaiMauXetNghiem } from 'app/shared/model/loai-mau-xet-nghiem.model';
import { getEntities as getLoaiMauXetNghiems } from 'app/entities/loai-mau-xet-nghiem/loai-mau-xet-nghiem.reducer';
import { IHaiCotLietSi } from 'app/shared/model/hai-cot-liet-si.model';
import { getEntities as getHaiCotLietSis } from 'app/entities/hai-cot-liet-si/hai-cot-liet-si.reducer';
import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
import { getEntities as getHoSoThanNhans } from 'app/entities/ho-so-than-nhan/ho-so-than-nhan.reducer';
import { ILoaiThaoTac } from 'app/shared/model/loai-thao-tac.model';
import { getEntities as getLoaiThaoTacs } from 'app/entities/loai-thao-tac/loai-thao-tac.reducer';
import { getEntity, updateEntity, createEntity, reset } from './mau-xet-nghiem.reducer';
import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMauXetNghiemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IMauXetNghiemUpdateState {
  isNew: boolean;
  idsmauTinhSach: any[];
  hoSoGiamDinhId: string;
  nhanVienNhanMauId: string;
  loaiMauXetNghiemId: string;
  haiCotLietSiId: string;
  thanNhanId: string;
  loaiThaoTacId: string;
}

export class MauXetNghiemUpdate extends React.Component<IMauXetNghiemUpdateProps, IMauXetNghiemUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsmauTinhSach: [],
      hoSoGiamDinhId: '0',
      nhanVienNhanMauId: '0',
      loaiMauXetNghiemId: '0',
      haiCotLietSiId: '0',
      thanNhanId: '0',
      loaiThaoTacId: '0',
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

    this.props.getTinhSaches();
    this.props.getHoSoGiamDinhs();
    this.props.getNhanViens();
    this.props.getLoaiMauXetNghiems();
    this.props.getHaiCotLietSis();
    this.props.getHoSoThanNhans();
    this.props.getLoaiThaoTacs();
  }

  saveEntity = (event, errors, values) => {
    values.ngayLayMau = new Date(values.ngayLayMau);
    values.ngayTiepNhan = new Date(values.ngayTiepNhan);

    if (errors.length === 0) {
      const { mauXetNghiemEntity } = this.props;
      const entity = {
        ...mauXetNghiemEntity,
        ...values,
        mauTinhSaches: mapIdList(values.mauTinhSaches)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/mau-xet-nghiem');
  };

  render() {
    const {
      mauXetNghiemEntity,
      tinhSaches,
      hoSoGiamDinhs,
      nhanViens,
      loaiMauXetNghiems,
      haiCotLietSis,
      hoSoThanNhans,
      loaiThaoTacs,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.mauXetNghiem.home.createOrEditLabel">
              <Translate contentKey="xddtApp.mauXetNghiem.home.createOrEditLabel">Create or edit a MauXetNghiem</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : mauXetNghiemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="mau-xet-nghiem-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maMauXetNghiemLabel" for="maMauXetNghiem">
                    <Translate contentKey="xddtApp.mauXetNghiem.maMauXetNghiem">Ma Mau Xet Nghiem</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-maMauXetNghiem" type="text" name="maMauXetNghiem" />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayLayMauLabel" for="ngayLayMau">
                    <Translate contentKey="xddtApp.mauXetNghiem.ngayLayMau">Ngay Lay Mau</Translate>
                  </Label>
                  <AvInput
                    id="mau-xet-nghiem-ngayLayMau"
                    type="datetime-local"
                    className="form-control"
                    name="ngayLayMau"
                    value={isNew ? null : convertDateTimeFromServer(this.props.mauXetNghiemEntity.ngayLayMau)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nguoiLayMauLabel" for="nguoiLayMau">
                    <Translate contentKey="xddtApp.mauXetNghiem.nguoiLayMau">Nguoi Lay Mau</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-nguoiLayMau" type="text" name="nguoiLayMau" />
                </AvGroup>
                <AvGroup>
                  <Label id="ngayTiepNhanLabel" for="ngayTiepNhan">
                    <Translate contentKey="xddtApp.mauXetNghiem.ngayTiepNhan">Ngay Tiep Nhan</Translate>
                  </Label>
                  <AvInput
                    id="mau-xet-nghiem-ngayTiepNhan"
                    type="datetime-local"
                    className="form-control"
                    name="ngayTiepNhan"
                    value={isNew ? null : convertDateTimeFromServer(this.props.mauXetNghiemEntity.ngayTiepNhan)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="soLuongMauLabel" for="soLuongMau">
                    <Translate contentKey="xddtApp.mauXetNghiem.soLuongMau">So Luong Mau</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-soLuongMau" type="string" className="form-control" name="soLuongMau" />
                </AvGroup>
                <AvGroup>
                  <Label id="trangThaiMauLabel">
                    <Translate contentKey="xddtApp.mauXetNghiem.trangThaiMau">Trang Thai Mau</Translate>
                  </Label>
                  <AvInput
                    id="mau-xet-nghiem-trangThaiMau"
                    type="select"
                    className="form-control"
                    name="trangThaiMau"
                    value={(!isNew && mauXetNghiemEntity.trangThaiMau) || 'MOI'}
                  >
                    <option value="MOI">
                      <Translate contentKey="xddtApp.TrangThaiMau.MOI" />
                    </option>
                    <option value="DATACHCHIET">
                      <Translate contentKey="xddtApp.TrangThaiMau.DATACHCHIET" />
                    </option>
                    <option value="PCRDAT">
                      <Translate contentKey="xddtApp.TrangThaiMau.PCRDAT" />
                    </option>
                    <option value="PCRLOAI">
                      <Translate contentKey="xddtApp.TrangThaiMau.PCRLOAI" />
                    </option>
                    <option value="NHANBAN">
                      <Translate contentKey="xddtApp.TrangThaiMau.NHANBAN" />
                    </option>
                    <option value="GIAITRINHTU">
                      <Translate contentKey="xddtApp.TrangThaiMau.GIAITRINHTU" />
                    </option>
                    <option value="HOANTHANH">
                      <Translate contentKey="xddtApp.TrangThaiMau.HOANTHANH" />
                    </option>
                    <option value="KHONGDATCHUAN">
                      <Translate contentKey="xddtApp.TrangThaiMau.KHONGDATCHUAN" />
                    </option>
                    <option value="HUY">
                      <Translate contentKey="xddtApp.TrangThaiMau.HUY" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.mauXetNghiem.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.mauXetNghiem.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="fileGocLabel" for="fileGoc">
                    <Translate contentKey="xddtApp.mauXetNghiem.fileGoc">File Goc</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-fileGoc" type="text" name="fileGoc" />
                </AvGroup>
                <AvGroup>
                  <Label id="fileKetQuaLabel" for="fileKetQua">
                    <Translate contentKey="xddtApp.mauXetNghiem.fileKetQua">File Ket Qua</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-fileKetQua" type="text" name="fileKetQua" />
                </AvGroup>
                <AvGroup>
                  <Label id="fileDotBienLabel" for="fileDotBien">
                    <Translate contentKey="xddtApp.mauXetNghiem.fileDotBien">File Dot Bien</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-fileDotBien" type="text" name="fileDotBien" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="mau-xet-nghiem-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.mauXetNghiem.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.mauXetNghiem.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.mauXetNghiem.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.mauXetNghiem.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf4Label" for="udf4">
                    <Translate contentKey="xddtApp.mauXetNghiem.udf4">Udf 4</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-udf4" type="text" name="udf4" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf5Label" for="udf5">
                    <Translate contentKey="xddtApp.mauXetNghiem.udf5">Udf 5</Translate>
                  </Label>
                  <AvField id="mau-xet-nghiem-udf5" type="text" name="udf5" />
                </AvGroup>
                <AvGroup>
                  <Label for="tinhSaches">
                    <Translate contentKey="xddtApp.mauXetNghiem.mauTinhSach">Mau Tinh Sach</Translate>
                  </Label>
                  <AvInput
                    id="mau-xet-nghiem-mauTinhSach"
                    type="select"
                    multiple
                    className="form-control"
                    name="mauTinhSaches"
                    value={mauXetNghiemEntity.mauTinhSaches && mauXetNghiemEntity.mauTinhSaches.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {tinhSaches
                      ? tinhSaches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="hoSoGiamDinh.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.hoSoGiamDinh">Ho So Giam Dinh</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-hoSoGiamDinh" type="select" className="form-control" name="hoSoGiamDinh.id">
                    <option value="" key="0" />
                    {hoSoGiamDinhs
                      ? hoSoGiamDinhs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="nhanVienNhanMau.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.nhanVienNhanMau">Nhan Vien Nhan Mau</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-nhanVienNhanMau" type="select" className="form-control" name="nhanVienNhanMau.id">
                    <option value="" key="0" />
                    {nhanViens
                      ? nhanViens.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loaiMauXetNghiem.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.loaiMauXetNghiem">Loai Mau Xet Nghiem</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-loaiMauXetNghiem" type="select" className="form-control" name="loaiMauXetNghiem.id">
                    <option value="" key="0" />
                    {loaiMauXetNghiems
                      ? loaiMauXetNghiems.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="haiCotLietSi.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.haiCotLietSi">Hai Cot Liet Si</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-haiCotLietSi" type="select" className="form-control" name="haiCotLietSi.id">
                    <option value="" key="0" />
                    {haiCotLietSis
                      ? haiCotLietSis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="thanNhan.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.thanNhan">Than Nhan</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-thanNhan" type="select" className="form-control" name="thanNhan.id">
                    <option value="" key="0" />
                    {hoSoThanNhans
                      ? hoSoThanNhans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loaiThaoTac.id">
                    <Translate contentKey="xddtApp.mauXetNghiem.loaiThaoTac">Loai Thao Tac</Translate>
                  </Label>
                  <AvInput id="mau-xet-nghiem-loaiThaoTac" type="select" className="form-control" name="loaiThaoTac.id">
                    <option value="" key="0" />
                    {loaiThaoTacs
                      ? loaiThaoTacs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/mau-xet-nghiem" replace color="info">
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
  tinhSaches: storeState.tinhSach.entities,
  hoSoGiamDinhs: storeState.hoSoGiamDinh.entities,
  nhanViens: storeState.nhanVien.entities,
  loaiMauXetNghiems: storeState.loaiMauXetNghiem.entities,
  haiCotLietSis: storeState.haiCotLietSi.entities,
  hoSoThanNhans: storeState.hoSoThanNhan.entities,
  loaiThaoTacs: storeState.loaiThaoTac.entities,
  mauXetNghiemEntity: storeState.mauXetNghiem.entity,
  loading: storeState.mauXetNghiem.loading,
  updating: storeState.mauXetNghiem.updating,
  updateSuccess: storeState.mauXetNghiem.updateSuccess
});

const mapDispatchToProps = {
  getTinhSaches,
  getHoSoGiamDinhs,
  getNhanViens,
  getLoaiMauXetNghiems,
  getHaiCotLietSis,
  getHoSoThanNhans,
  getLoaiThaoTacs,
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
)(MauXetNghiemUpdate);
