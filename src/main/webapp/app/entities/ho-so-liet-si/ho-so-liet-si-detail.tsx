import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ho-so-liet-si.reducer';
import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoSoLietSiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoSoLietSiDetail extends React.Component<IHoSoLietSiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoSoLietSiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoSoLietSi.detail.title">HoSoLietSi</Translate> [<b>{hoSoLietSiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maCCS">
                <Translate contentKey="xddtApp.hoSoLietSi.maCCS">Ma CCS</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.maCCS}</dd>
            <dt>
              <span id="maLietSi">
                <Translate contentKey="xddtApp.hoSoLietSi.maLietSi">Ma Liet Si</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.maLietSi}</dd>
            <dt>
              <span id="hoTen">
                <Translate contentKey="xddtApp.hoSoLietSi.hoTen">Ho Ten</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hoTen}</dd>
            <dt>
              <span id="tenKhac">
                <Translate contentKey="xddtApp.hoSoLietSi.tenKhac">Ten Khac</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.tenKhac}</dd>
            <dt>
              <span id="biDanh">
                <Translate contentKey="xddtApp.hoSoLietSi.biDanh">Bi Danh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.biDanh}</dd>
            <dt>
              <span id="gioiTinh">
                <Translate contentKey="xddtApp.hoSoLietSi.gioiTinh">Gioi Tinh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.gioiTinh}</dd>
            <dt>
              <span id="namSinh">
                <Translate contentKey="xddtApp.hoSoLietSi.namSinh">Nam Sinh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.namSinh}</dd>
            <dt>
              <span id="queThon">
                <Translate contentKey="xddtApp.hoSoLietSi.queThon">Que Thon</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.queThon}</dd>
            <dt>
              <span id="queXa">
                <Translate contentKey="xddtApp.hoSoLietSi.queXa">Que Xa</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.queXa}</dd>
            <dt>
              <span id="queHuyen">
                <Translate contentKey="xddtApp.hoSoLietSi.queHuyen">Que Huyen</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.queHuyen}</dd>
            <dt>
              <span id="queTinh">
                <Translate contentKey="xddtApp.hoSoLietSi.queTinh">Que Tinh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.queTinh}</dd>
            <dt>
              <span id="namNhapNgu">
                <Translate contentKey="xddtApp.hoSoLietSi.namNhapNgu">Nam Nhap Ngu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.namNhapNgu}</dd>
            <dt>
              <span id="namXuatNgu">
                <Translate contentKey="xddtApp.hoSoLietSi.namXuatNgu">Nam Xuat Ngu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.namXuatNgu}</dd>
            <dt>
              <span id="namTaiNgu">
                <Translate contentKey="xddtApp.hoSoLietSi.namTaiNgu">Nam Tai Ngu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.namTaiNgu}</dd>
            <dt>
              <span id="namDiB">
                <Translate contentKey="xddtApp.hoSoLietSi.namDiB">Nam Di B</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.namDiB}</dd>
            <dt>
              <span id="hySinhNgay">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhNgay">Hy Sinh Ngay</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhNgay}</dd>
            <dt>
              <span id="hySinhThang">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhThang">Hy Sinh Thang</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhThang}</dd>
            <dt>
              <span id="hySinhNam">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhNam">Hy Sinh Nam</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhNam}</dd>
            <dt>
              <span id="hySinhLyDo">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhLyDo">Hy Sinh Ly Do</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhLyDo}</dd>
            <dt>
              <span id="hySinhDonVi">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhDonVi">Hy Sinh Don Vi</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhDonVi}</dd>
            <dt>
              <span id="hySinhTranDanh">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhTranDanh">Hy Sinh Tran Danh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhTranDanh}</dd>
            <dt>
              <span id="hySinhDiaDiem">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhDiaDiem">Hy Sinh Dia Diem</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhDiaDiem}</dd>
            <dt>
              <span id="hySinhXa">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhXa">Hy Sinh Xa</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhXa}</dd>
            <dt>
              <span id="hySinhHuyen">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhHuyen">Hy Sinh Huyen</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhHuyen}</dd>
            <dt>
              <span id="hySinhTinh">
                <Translate contentKey="xddtApp.hoSoLietSi.hySinhTinh">Hy Sinh Tinh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.hySinhTinh}</dd>
            <dt>
              <span id="anTangDiaDiem">
                <Translate contentKey="xddtApp.hoSoLietSi.anTangDiaDiem">An Tang Dia Diem</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.anTangDiaDiem}</dd>
            <dt>
              <span id="anTangXa">
                <Translate contentKey="xddtApp.hoSoLietSi.anTangXa">An Tang Xa</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.anTangXa}</dd>
            <dt>
              <span id="anTangHuyen">
                <Translate contentKey="xddtApp.hoSoLietSi.anTangHuyen">An Tang Huyen</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.anTangHuyen}</dd>
            <dt>
              <span id="anTangTinh">
                <Translate contentKey="xddtApp.hoSoLietSi.anTangTinh">An Tang Tinh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.anTangTinh}</dd>
            <dt>
              <span id="ngayBaoTu">
                <Translate contentKey="xddtApp.hoSoLietSi.ngayBaoTu">Ngay Bao Tu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.ngayBaoTu}</dd>
            <dt>
              <span id="giayBaoTu">
                <Translate contentKey="xddtApp.hoSoLietSi.giayBaoTu">Giay Bao Tu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.giayBaoTu}</dd>
            <dt>
              <span id="vatDungKemTheo">
                <Translate contentKey="xddtApp.hoSoLietSi.vatDungKemTheo">Vat Dung Kem Theo</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.vatDungKemTheo}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.hoSoLietSi.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.ghiChu}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoSoLietSi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="trangThaiXacMinh">
                <Translate contentKey="xddtApp.hoSoLietSi.trangThaiXacMinh">Trang Thai Xac Minh</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.trangThaiXacMinh}</dd>
            <dt>
              <span id="uDF1">
                <Translate contentKey="xddtApp.hoSoLietSi.uDF1">U DF 1</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.uDF1}</dd>
            <dt>
              <span id="uDF2">
                <Translate contentKey="xddtApp.hoSoLietSi.uDF2">U DF 2</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.uDF2}</dd>
            <dt>
              <span id="uDF3">
                <Translate contentKey="xddtApp.hoSoLietSi.uDF3">U DF 3</Translate>
              </span>
            </dt>
            <dd>{hoSoLietSiEntity.uDF3}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoLietSi.phuongXa">Phuong Xa</Translate>
            </dt>
            <dd>{hoSoLietSiEntity.phuongXa ? hoSoLietSiEntity.phuongXa.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoLietSi.donViHiSinh">Don Vi Hi Sinh</Translate>
            </dt>
            <dd>{hoSoLietSiEntity.donViHiSinh ? hoSoLietSiEntity.donViHiSinh.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoLietSi.donViHuanLuyen">Don Vi Huan Luyen</Translate>
            </dt>
            <dd>{hoSoLietSiEntity.donViHuanLuyen ? hoSoLietSiEntity.donViHuanLuyen.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoLietSi.capBac">Cap Bac</Translate>
            </dt>
            <dd>{hoSoLietSiEntity.capBac ? hoSoLietSiEntity.capBac.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoLietSi.chucVu">Chuc Vu</Translate>
            </dt>
            <dd>{hoSoLietSiEntity.chucVu ? hoSoLietSiEntity.chucVu.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ho-so-liet-si" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ho-so-liet-si/${hoSoLietSiEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ hoSoLietSi }: IRootState) => ({
  hoSoLietSiEntity: hoSoLietSi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoSoLietSiDetail);
