import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trung-tam.reducer';
import { ITrungTam } from 'app/shared/model/trung-tam.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrungTamDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TrungTamDetail extends React.Component<ITrungTamDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { trungTamEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.trungTam.detail.title">TrungTam</Translate> [<b>{trungTamEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maTrungTam">
                <Translate contentKey="xddtApp.trungTam.maTrungTam">Ma Trung Tam</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.maTrungTam}</dd>
            <dt>
              <span id="tenTrungTam">
                <Translate contentKey="xddtApp.trungTam.tenTrungTam">Ten Trung Tam</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.tenTrungTam}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.trungTam.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.trungTam.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.trungTam.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.trungTam.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.trungTam.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{trungTamEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/trung-tam" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/trung-tam/${trungTamEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ trungTam }: IRootState) => ({
  trungTamEntity: trungTam.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrungTamDetail);
