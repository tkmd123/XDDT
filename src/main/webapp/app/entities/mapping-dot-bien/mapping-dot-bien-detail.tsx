import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './mapping-dot-bien.reducer';
import { IMappingDotBien } from 'app/shared/model/mapping-dot-bien.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMappingDotBienDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class MappingDotBienDetail extends React.Component<IMappingDotBienDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { mappingDotBienEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.mappingDotBien.detail.title">MappingDotBien</Translate> [<b>{mappingDotBienEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maDotBien">
                <Translate contentKey="xddtApp.mappingDotBien.maDotBien">Ma Dot Bien</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.maDotBien}</dd>
            <dt>
              <span id="maMapping">
                <Translate contentKey="xddtApp.mappingDotBien.maMapping">Ma Mapping</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.maMapping}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.mappingDotBien.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.mappingDotBien.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.mappingDotBien.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.mappingDotBien.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{mappingDotBienEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/mapping-dot-bien" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/mapping-dot-bien/${mappingDotBienEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ mappingDotBien }: IRootState) => ({
  mappingDotBienEntity: mappingDotBien.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MappingDotBienDetail);
