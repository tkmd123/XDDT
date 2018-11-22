import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tinh-thanh.reducer';
import { ITinhThanh } from 'app/shared/model/tinh-thanh.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITinhThanhDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TinhThanhDetail extends React.Component<ITinhThanhDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tinhThanhEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.tinhThanh.detail.title">TinhThanh</Translate> [<b>{tinhThanhEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maTinh">
                <Translate contentKey="xddtApp.tinhThanh.maTinh">Ma Tinh</Translate>
              </span>
            </dt>
            <dd>{tinhThanhEntity.maTinh}</dd>
            <dt>
              <span id="tenTinh">
                <Translate contentKey="xddtApp.tinhThanh.tenTinh">Ten Tinh</Translate>
              </span>
            </dt>
            <dd>{tinhThanhEntity.tenTinh}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.tinhThanh.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{tinhThanhEntity.moTa}</dd>
          </dl>
          <Button tag={Link} to="/entity/tinh-thanh" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/tinh-thanh/${tinhThanhEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tinhThanh }: IRootState) => ({
  tinhThanhEntity: tinhThanh.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TinhThanhDetail);
