import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tach-chiet.reducer';
import { ITachChiet } from 'app/shared/model/tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITachChietDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TachChietDetail extends React.Component<ITachChietDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tachChietEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.tachChiet.detail.title">TachChiet</Translate> [<b>{tachChietEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maTachChiet">
                <Translate contentKey="xddtApp.tachChiet.maTachChiet">Ma Tach Chiet</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.maTachChiet}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.tachChiet.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.moTa}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.tachChiet.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.ghiChu}</dd>
            <dt>
              <span id="thoiGianThucHien">
                <Translate contentKey="xddtApp.tachChiet.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={tachChietEntity.thoiGianThucHien} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="phuongPhapLoc">
                <Translate contentKey="xddtApp.tachChiet.phuongPhapLoc">Phuong Phap Loc</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.phuongPhapLoc}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.tachChiet.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.tachChiet.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.tachChiet.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.tachChiet.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{tachChietEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.tachChiet.nhanVienNghienMau">Nhan Vien Nghien Mau</Translate>
            </dt>
            <dd>{tachChietEntity.nhanVienNghienMau ? tachChietEntity.nhanVienNghienMau.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.tachChiet.nhanVienTachADN">Nhan Vien Tach ADN</Translate>
            </dt>
            <dd>{tachChietEntity.nhanVienTachADN ? tachChietEntity.nhanVienTachADN.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/tach-chiet" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/tach-chiet/${tachChietEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tachChiet }: IRootState) => ({
  tachChietEntity: tachChiet.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TachChietDetail);
