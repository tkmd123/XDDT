import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ho-so-giam-dinh.reducer';
import { IHoSoGiamDinh } from 'app/shared/model/ho-so-giam-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoSoGiamDinhDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoSoGiamDinhDetail extends React.Component<IHoSoGiamDinhDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoSoGiamDinhEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoSoGiamDinh.detail.title">HoSoGiamDinh</Translate> [<b>{hoSoGiamDinhEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maHSGD">
                <Translate contentKey="xddtApp.hoSoGiamDinh.maHSGD">Ma HSGD</Translate>
              </span>
            </dt>
            <dd>{hoSoGiamDinhEntity.maHSGD}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoSoGiamDinh.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoSoGiamDinhEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.hoSoGiamDinh.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{hoSoGiamDinhEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.hoSoGiamDinh.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{hoSoGiamDinhEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.hoSoGiamDinh.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{hoSoGiamDinhEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhThanNhan">Giam Dinh Than Nhan</Translate>
            </dt>
            <dd>
              {hoSoGiamDinhEntity.giamDinhThanNhans
                ? hoSoGiamDinhEntity.giamDinhThanNhans.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === hoSoGiamDinhEntity.giamDinhThanNhans.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhLietSi">Giam Dinh Liet Si</Translate>
            </dt>
            <dd>
              {hoSoGiamDinhEntity.giamDinhLietSis
                ? hoSoGiamDinhEntity.giamDinhLietSis.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === hoSoGiamDinhEntity.giamDinhLietSis.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoGiamDinh.nhanVienHSGD">Nhan Vien HSGD</Translate>
            </dt>
            <dd>{hoSoGiamDinhEntity.nhanVienHSGD ? hoSoGiamDinhEntity.nhanVienHSGD.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ho-so-giam-dinh" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ho-so-giam-dinh/${hoSoGiamDinhEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hoSoGiamDinh }: IRootState) => ({
  hoSoGiamDinhEntity: hoSoGiamDinh.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoSoGiamDinhDetail);
