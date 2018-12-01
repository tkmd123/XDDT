import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tinh-sach.reducer';
import { ITinhSach } from 'app/shared/model/tinh-sach.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITinhSachDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TinhSachDetail extends React.Component<ITinhSachDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tinhSachEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.tinhSach.detail.title">TinhSach</Translate> [<b>{tinhSachEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maTinhSach">
                <Translate contentKey="xddtApp.tinhSach.maTinhSach">Ma Tinh Sach</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.maTinhSach}</dd>
            <dt>
              <span id="thoiGianThucHien">
                <Translate contentKey="xddtApp.tinhSach.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={tinhSachEntity.thoiGianThucHien} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="phuongPhapTinhSach">
                <Translate contentKey="xddtApp.tinhSach.phuongPhapTinhSach">Phuong Phap Tinh Sach</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.phuongPhapTinhSach}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.tinhSach.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.tinhSach.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.tinhSach.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.tinhSach.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.tinhSach.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{tinhSachEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.tinhSach.nhanVienTinhSach">Nhan Vien Tinh Sach</Translate>
            </dt>
            <dd>{tinhSachEntity.nhanVienTinhSach ? tinhSachEntity.nhanVienTinhSach.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.tinhSach.mayTinhSach">May Tinh Sach</Translate>
            </dt>
            <dd>{tinhSachEntity.mayTinhSach ? tinhSachEntity.mayTinhSach.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/tinh-sach" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/tinh-sach/${tinhSachEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tinhSach }: IRootState) => ({
  tinhSachEntity: tinhSach.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TinhSachDetail);
