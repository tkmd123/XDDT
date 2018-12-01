import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './thong-tin-khai-quat.reducer';
import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThongTinKhaiQuatDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThongTinKhaiQuatDetail extends React.Component<IThongTinKhaiQuatDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { thongTinKhaiQuatEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.thongTinKhaiQuat.detail.title">ThongTinKhaiQuat</Translate> [<b>{thongTinKhaiQuatEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maKhaiQuat">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.maKhaiQuat">Ma Khai Quat</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.maKhaiQuat}</dd>
            <dt>
              <span id="nguoiKhaiQuat">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.nguoiKhaiQuat">Nguoi Khai Quat</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.nguoiKhaiQuat}</dd>
            <dt>
              <span id="donViKhaiQuat">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.donViKhaiQuat">Don Vi Khai Quat</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.donViKhaiQuat}</dd>
            <dt>
              <span id="thoiGianKhaiQuat">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.thoiGianKhaiQuat">Thoi Gian Khai Quat</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={thongTinKhaiQuatEntity.thoiGianKhaiQuat} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="coHaiCot">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.coHaiCot">Co Hai Cot</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.coHaiCot ? 'true' : 'false'}</dd>
            <dt>
              <span id="soLuongHaiCot">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.soLuongHaiCot">So Luong Hai Cot</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.soLuongHaiCot}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.thongTinKhaiQuat.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{thongTinKhaiQuatEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.thongTinKhaiQuat.thongTinMo">Thong Tin Mo</Translate>
            </dt>
            <dd>{thongTinKhaiQuatEntity.thongTinMo ? thongTinKhaiQuatEntity.thongTinMo.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/thong-tin-khai-quat" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/thong-tin-khai-quat/${thongTinKhaiQuatEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ thongTinKhaiQuat }: IRootState) => ({
  thongTinKhaiQuatEntity: thongTinKhaiQuat.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThongTinKhaiQuatDetail);
