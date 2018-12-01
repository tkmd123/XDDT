import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './may-pcr.reducer';
import { IMayPCR } from 'app/shared/model/may-pcr.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMayPCRDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class MayPCRDetail extends React.Component<IMayPCRDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { mayPCREntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.mayPCR.detail.title">MayPCR</Translate> [<b>{mayPCREntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maMayPCR">
                <Translate contentKey="xddtApp.mayPCR.maMayPCR">Ma May PCR</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.maMayPCR}</dd>
            <dt>
              <span id="tenMayPCR">
                <Translate contentKey="xddtApp.mayPCR.tenMayPCR">Ten May PCR</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.tenMayPCR}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.mayPCR.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.mayPCR.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.mayPCR.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.mayPCR.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.mayPCR.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{mayPCREntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/may-pcr" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/may-pcr/${mayPCREntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ mayPCR }: IRootState) => ({
  mayPCREntity: mayPCR.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MayPCRDetail);
