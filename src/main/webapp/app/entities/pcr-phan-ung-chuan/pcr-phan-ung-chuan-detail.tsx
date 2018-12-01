import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr-phan-ung-chuan.reducer';
import { IPCRPhanUngChuan } from 'app/shared/model/pcr-phan-ung-chuan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRPhanUngChuanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRPhanUngChuanDetail extends React.Component<IPCRPhanUngChuanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCRPhanUngChuanEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCRPhanUngChuan.detail.title">PCRPhanUngChuan</Translate> [<b>{pCRPhanUngChuanEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="chuKyPhanUng">
                <Translate contentKey="xddtApp.pCRPhanUngChuan.chuKyPhanUng">Chu Ky Phan Ung</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngChuanEntity.chuKyPhanUng}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCRPhanUngChuan.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngChuanEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCRPhanUngChuan.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngChuanEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCRPhanUngChuan.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngChuanEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCRPhanUngChuan.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCRPhanUngChuanEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr-phan-ung-chuan" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr-phan-ung-chuan/${pCRPhanUngChuanEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCRPhanUngChuan }: IRootState) => ({
  pCRPhanUngChuanEntity: pCRPhanUngChuan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRPhanUngChuanDetail);
