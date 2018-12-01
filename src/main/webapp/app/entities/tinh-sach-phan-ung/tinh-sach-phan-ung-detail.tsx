import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tinh-sach-phan-ung.reducer';
import { ITinhSachPhanUng } from 'app/shared/model/tinh-sach-phan-ung.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITinhSachPhanUngDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TinhSachPhanUngDetail extends React.Component<ITinhSachPhanUngDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tinhSachPhanUngEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.tinhSachPhanUng.detail.title">TinhSachPhanUng</Translate> [<b>{tinhSachPhanUngEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="dungTichSuDung">
                <Translate contentKey="xddtApp.tinhSachPhanUng.dungTichSuDung">Dung Tich Su Dung</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.dungTichSuDung}</dd>
            <dt>
              <span id="chuTrinhNhietDo">
                <Translate contentKey="xddtApp.tinhSachPhanUng.chuTrinhNhietDo">Chu Trinh Nhiet Do</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.chuTrinhNhietDo}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.tinhSachPhanUng.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.tinhSachPhanUng.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.tinhSachPhanUng.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.tinhSachPhanUng.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{tinhSachPhanUngEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.tinhSachPhanUng.tinhSach">Tinh Sach</Translate>
            </dt>
            <dd>{tinhSachPhanUngEntity.tinhSach ? tinhSachPhanUngEntity.tinhSach.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.tinhSachPhanUng.hoaChatTinhSach">Hoa Chat Tinh Sach</Translate>
            </dt>
            <dd>{tinhSachPhanUngEntity.hoaChatTinhSach ? tinhSachPhanUngEntity.hoaChatTinhSach.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/tinh-sach-phan-ung" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/tinh-sach-phan-ung/${tinhSachPhanUngEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tinhSachPhanUng }: IRootState) => ({
  tinhSachPhanUngEntity: tinhSachPhanUng.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TinhSachPhanUngDetail);
