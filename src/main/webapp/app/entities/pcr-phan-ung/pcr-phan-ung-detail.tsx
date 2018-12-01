import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr-phan-ung.reducer';
import { IPCRPhanUng } from 'app/shared/model/pcr-phan-ung.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRPhanUngDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRPhanUngDetail extends React.Component<IPCRPhanUngDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCRPhanUngEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCRPhanUng.detail.title">PCRPhanUng</Translate> [<b>{pCRPhanUngEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="chuKyPhanUng">
                <Translate contentKey="xddtApp.pCRPhanUng.chuKyPhanUng">Chu Ky Phan Ung</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.chuKyPhanUng}</dd>
            <dt>
              <span id="dungTich">
                <Translate contentKey="xddtApp.pCRPhanUng.dungTich">Dung Tich</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.dungTich}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCRPhanUng.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCRPhanUng.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCRPhanUng.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCRPhanUng.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRPhanUng.pcrPhanUng">Pcr Phan Ung</Translate>
            </dt>
            <dd>{pCRPhanUngEntity.pcrPhanUng ? pCRPhanUngEntity.pcrPhanUng.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCRPhanUng.hoaChatPhanUng">Hoa Chat Phan Ung</Translate>
            </dt>
            <dd>{pCRPhanUngEntity.hoaChatPhanUng ? pCRPhanUngEntity.hoaChatPhanUng.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr-phan-ung" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr-phan-ung/${pCRPhanUngEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCRPhanUng }: IRootState) => ({
  pCRPhanUngEntity: pCRPhanUng.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRPhanUngDetail);
