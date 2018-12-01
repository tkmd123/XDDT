import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './cap-bac.reducer';
import { ICapBac } from 'app/shared/model/cap-bac.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICapBacDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CapBacDetail extends React.Component<ICapBacDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { capBacEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.capBac.detail.title">CapBac</Translate> [<b>{capBacEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maCapBac">
                <Translate contentKey="xddtApp.capBac.maCapBac">Ma Cap Bac</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.maCapBac}</dd>
            <dt>
              <span id="tenCapBac">
                <Translate contentKey="xddtApp.capBac.tenCapBac">Ten Cap Bac</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.tenCapBac}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.capBac.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.capBac.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.capBac.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.capBac.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.capBac.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{capBacEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/cap-bac" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/cap-bac/${capBacEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ capBac }: IRootState) => ({
  capBacEntity: capBac.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CapBacDetail);
