import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr-ket-qua.reducer';
import { IPCRKetQua } from 'app/shared/model/pcr-ket-qua.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRKetQuaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRKetQuaDetail extends React.Component<IPCRKetQuaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCRKetQuaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCRKetQua.detail.title">PCRKetQua</Translate> [<b>{pCRKetQuaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maKetQua">
                <Translate contentKey="xddtApp.pCRKetQua.maKetQua">Ma Ket Qua</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.maKetQua}</dd>
            <dt>
              <span id="tenKetQua">
                <Translate contentKey="xddtApp.pCRKetQua.tenKetQua">Ten Ket Qua</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.tenKetQua}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCRKetQua.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCRKetQua.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCRKetQua.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCRKetQua.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCRKetQuaEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr-ket-qua" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr-ket-qua/${pCRKetQuaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCRKetQua }: IRootState) => ({
  pCRKetQuaEntity: pCRKetQua.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRKetQuaDetail);
