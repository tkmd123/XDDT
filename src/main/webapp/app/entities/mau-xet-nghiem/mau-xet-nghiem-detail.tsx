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
              <span id="nguoiTiepNhan">
                <Translate contentKey="xddtApp.mauXetNghiem.nguoiTiepNhan">Nguoi Tiep Nhan</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.nguoiTiepNhan}</dd>
            <dt>
              <span id="ngayLayMau">
                <Translate contentKey="xddtApp.mauXetNghiem.ngayLayMau">Ngay Lay Mau</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={mauXetNghiemEntity.ngayLayMau} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ngayTiepNhan">
                <Translate contentKey="xddtApp.mauXetNghiem.ngayTiepNhan">Ngay Tiep Nhan</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={mauXetNghiemEntity.ngayTiepNhan} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="trangThaiXuLy">
                <Translate contentKey="xddtApp.mauXetNghiem.trangThaiXuLy">Trang Thai Xu Ly</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.trangThaiXuLy}</dd>
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
              <span id="isDeleted">
                <Translate contentKey="xddtApp.mauXetNghiem.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{mauXetNghiemEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauXetNghiem.mauThongTinADN">Mau Thong Tin ADN</Translate>
            </dt>
            <dd>{mauXetNghiemEntity.mauThongTinADN ? mauXetNghiemEntity.mauThongTinADN.id : ''}</dd>
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
