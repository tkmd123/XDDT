import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr-moi.reducer';
import { IPCRMoi } from 'app/shared/model/pcr-moi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRMoiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRMoiDetail extends React.Component<IPCRMoiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCRMoiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCRMoi.detail.title">PCRMoi</Translate> [<b>{pCRMoiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maMoi">
                <Translate contentKey="xddtApp.pCRMoi.maMoi">Ma Moi</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.maMoi}</dd>
            <dt>
              <span id="tenMoi">
                <Translate contentKey="xddtApp.pCRMoi.tenMoi">Ten Moi</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.tenMoi}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.pCRMoi.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCRMoi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCRMoi.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCRMoi.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCRMoi.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCRMoiEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr-moi" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr-moi/${pCRMoiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCRMoi }: IRootState) => ({
  pCRMoiEntity: pCRMoi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRMoiDetail);
