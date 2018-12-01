import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr-mau.reducer';
import { IPCRMau } from 'app/shared/model/pcr-mau.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRMauDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRMauDetail extends React.Component<IPCRMauDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCRMauEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCRMau.detail.title">PCRMau</Translate> [<b>{pCRMauEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nongDoAgaros">
                <Translate contentKey="xddtApp.pCRMau.nongDoAgaros">Nong Do Agaros</Translate>
              </span>
            </dt>
            <dd>{pCRMauEntity.nongDoAgaros}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCRMau.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCRMauEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCRMau.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCRMauEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCRMau.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCRMauEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCRMau.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCRMauEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRMau.pcrKetQua">Pcr Ket Qua</Translate>
            </dt>
            <dd>{pCRMauEntity.pcrKetQua ? pCRMauEntity.pcrKetQua.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRMau.pcrMau">Pcr Mau</Translate>
            </dt>
            <dd>{pCRMauEntity.pcrMau ? pCRMauEntity.pcrMau.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRMau.mauPCR">Mau PCR</Translate>
            </dt>
            <dd>{pCRMauEntity.mauPCR ? pCRMauEntity.mauPCR.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRMau.moiPCR">Moi PCR</Translate>
            </dt>
            <dd>{pCRMauEntity.moiPCR ? pCRMauEntity.moiPCR.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr-mau" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr-mau/${pCRMauEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCRMau }: IRootState) => ({
  pCRMauEntity: pCRMau.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRMauDetail);
