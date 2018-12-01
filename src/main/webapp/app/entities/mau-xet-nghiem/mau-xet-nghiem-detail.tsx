import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mau-xet-nghiem.reducer';
import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMauXetNghiemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class MauXetNghiemDetail extends React.Component<IMauXetNghiemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { mauXetNghiemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.mauXetNghiem.detail.title">MauXetNghiem</Translate> [<b>{mauXetNghiemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maMauXetNghiem">
                <Translate contentKey="xddtApp.mauXetNghiem.maMauXetNghiem">Ma Mau Xet Nghiem</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.maMauXetNghiem}</dd>
            <dt>
              <span id="ngayLayMau">
                <Translate contentKey="xddtApp.mauXetNghiem.ngayLayMau">Ngay Lay Mau</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={mauXetNghiemEntity.ngayLayMau} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="nguoiLayMau">
                <Translate contentKey="xddtApp.mauXetNghiem.nguoiLayMau">Nguoi Lay Mau</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.nguoiLayMau}</dd>
            <dt>
              <span id="ngayTiepNhan">
                <Translate contentKey="xddtApp.mauXetNghiem.ngayTiepNhan">Ngay Tiep Nhan</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={mauXetNghiemEntity.ngayTiepNhan} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="soLuongMau">
                <Translate contentKey="xddtApp.mauXetNghiem.soLuongMau">So Luong Mau</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.soLuongMau}</dd>
            <dt>
              <span id="trangThaiMau">
                <Translate contentKey="xddtApp.mauXetNghiem.trangThaiMau">Trang Thai Mau</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.trangThaiMau}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.mauXetNghiem.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.moTa}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.mauXetNghiem.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.ghiChu}</dd>
            <dt>
              <span id="fileGoc">
                <Translate contentKey="xddtApp.mauXetNghiem.fileGoc">File Goc</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.fileGoc}</dd>
            <dt>
              <span id="fileKetQua">
                <Translate contentKey="xddtApp.mauXetNghiem.fileKetQua">File Ket Qua</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.fileKetQua}</dd>
            <dt>
              <span id="fileDotBien">
                <Translate contentKey="xddtApp.mauXetNghiem.fileDotBien">File Dot Bien</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.fileDotBien}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.mauXetNghiem.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.mauXetNghiem.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.mauXetNghiem.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.mauXetNghiem.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.udf3}</dd>
            <dt>
              <span id="udf4">
                <Translate contentKey="xddtApp.mauXetNghiem.udf4">Udf 4</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.udf4}</dd>
            <dt>
              <span id="udf5">
                <Translate contentKey="xddtApp.mauXetNghiem.udf5">Udf 5</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.udf5}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.mauTinhSach">Mau Tinh Sach</Translate>
            </dt>
            <dd>
              {mauXetNghiemEntity.mauTinhSaches
                ? mauXetNghiemEntity.mauTinhSaches.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === mauXetNghiemEntity.mauTinhSaches.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.hoSoGiamDinh">Ho So Giam Dinh</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.hoSoGiamDinh ? mauXetNghiemEntity.hoSoGiamDinh.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.nhanVienNhanMau">Nhan Vien Nhan Mau</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.nhanVienNhanMau ? mauXetNghiemEntity.nhanVienNhanMau.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.loaiMauXetNghiem">Loai Mau Xet Nghiem</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.loaiMauXetNghiem ? mauXetNghiemEntity.loaiMauXetNghiem.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.haiCotLietSi">Hai Cot Liet Si</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.haiCotLietSi ? mauXetNghiemEntity.haiCotLietSi.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.thanNhan">Than Nhan</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.thanNhan ? mauXetNghiemEntity.thanNhan.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.loaiThaoTac">Loai Thao Tac</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.loaiThaoTac ? mauXetNghiemEntity.loaiThaoTac.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/mau-xet-nghiem" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/mau-xet-nghiem/${mauXetNghiemEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ mauXetNghiem }: IRootState) => ({
  mauXetNghiemEntity: mauXetNghiem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MauXetNghiemDetail);
