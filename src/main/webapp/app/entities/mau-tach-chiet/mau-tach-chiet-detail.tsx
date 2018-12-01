import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mau-tach-chiet.reducer';
import { IMauTachChiet } from 'app/shared/model/mau-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMauTachChietDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class MauTachChietDetail extends React.Component<IMauTachChietDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { mauTachChietEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.mauTachChiet.detail.title">MauTachChiet</Translate> [<b>{mauTachChietEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dacDiemMau">
                <Translate contentKey="xddtApp.mauTachChiet.dacDiemMau">Dac Diem Mau</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.dacDiemMau}</dd>
            <dt>
              <span id="soLuongSuDung">
                <Translate contentKey="xddtApp.mauTachChiet.soLuongSuDung">So Luong Su Dung</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.soLuongSuDung}</dd>
            <dt>
              <span id="nhanXet">
                <Translate contentKey="xddtApp.mauTachChiet.nhanXet">Nhan Xet</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.nhanXet}</dd>
            <dt>
              <span id="xuLyBeMat">
                <Translate contentKey="xddtApp.mauTachChiet.xuLyBeMat">Xu Ly Be Mat</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.xuLyBeMat}</dd>
            <dt>
              <span id="khoiLuongNghien">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongNghien">Khoi Luong Nghien</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongNghien}</dd>
            <dt>
              <span id="khoiLuongDeTach">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongDeTach">Khoi Luong De Tach</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongDeTach}</dd>
            <dt>
              <span id="khoiLuongSauKhu">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauKhu">Khoi Luong Sau Khu</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongSauKhu}</dd>
            <dt>
              <span id="khoiLuongSauLoc">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauLoc">Khoi Luong Sau Loc</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongSauLoc}</dd>
            <dt>
              <span id="khoiLuongADN">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongADN">Khoi Luong ADN</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongADN}</dd>
            <dt>
              <span id="khoiLuongChuaNghien">
                <Translate contentKey="xddtApp.mauTachChiet.khoiLuongChuaNghien">Khoi Luong Chua Nghien</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.khoiLuongChuaNghien}</dd>
            <dt>
              <span id="ghiChuTach">
                <Translate contentKey="xddtApp.mauTachChiet.ghiChuTach">Ghi Chu Tach</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.ghiChuTach}</dd>
            <dt>
              <span id="ghiChuADN">
                <Translate contentKey="xddtApp.mauTachChiet.ghiChuADN">Ghi Chu ADN</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.ghiChuADN}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.mauTachChiet.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.mauTachChiet.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.mauTachChiet.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.mauTachChiet.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.udf3}</dd>
            <dt>
              <span id="float1">
                <Translate contentKey="xddtApp.mauTachChiet.float1">Float 1</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.float1}</dd>
            <dt>
              <span id="float2">
                <Translate contentKey="xddtApp.mauTachChiet.float2">Float 2</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.float2}</dd>
            <dt>
              <span id="float3">
                <Translate contentKey="xddtApp.mauTachChiet.float3">Float 3</Translate>
              </span>
            </dt>
            <dd>{mauTachChietEntity.float3}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauTachChiet.mauTachChiet">Mau Tach Chiet</Translate>
            </dt>
            <dd>{mauTachChietEntity.mauTachChiet ? mauTachChietEntity.mauTachChiet.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.mauTachChiet.tachChietMau">Tach Chiet Mau</Translate>
            </dt>
            <dd>{mauTachChietEntity.tachChietMau ? mauTachChietEntity.tachChietMau.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/mau-tach-chiet" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/mau-tach-chiet/${mauTachChietEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ mauTachChiet }: IRootState) => ({
  mauTachChietEntity: mauTachChiet.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MauTachChietDetail);
