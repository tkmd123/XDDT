import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './mapping-dot-bien.reducer';
import { IMappingDotBien } from 'app/shared/model/mapping-dot-bien.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMappingDotBienUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IMappingDotBienUpdateState {
  isNew: boolean;
}

export class MappingDotBienUpdate extends React.Component<IMappingDotBienUpdateProps, IMappingDotBienUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { mappingDotBienEntity } = this.props;
      const entity = {
        ...mappingDotBienEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/mapping-dot-bien');
  };

  render() {
    const { mappingDotBienEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.mappingDotBien.home.createOrEditLabel">
              <Translate contentKey="xddtApp.mappingDotBien.home.createOrEditLabel">Create or edit a MappingDotBien</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : mappingDotBienEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="mapping-dot-bien-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maDotBienLabel" for="maDotBien">
                    <Translate contentKey="xddtApp.mappingDotBien.maDotBien">Ma Dot Bien</Translate>
                  </Label>
                  <AvField id="mapping-dot-bien-maDotBien" type="text" name="maDotBien" />
                </AvGroup>
                <AvGroup>
                  <Label id="maMappingLabel" for="maMapping">
                    <Translate contentKey="xddtApp.mappingDotBien.maMapping">Ma Mapping</Translate>
                  </Label>
                  <AvField id="mapping-dot-bien-maMapping" type="text" name="maMapping" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="mapping-dot-bien-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.mappingDotBien.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.mappingDotBien.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="mapping-dot-bien-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.mappingDotBien.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="mapping-dot-bien-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.mappingDotBien.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="mapping-dot-bien-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/mapping-dot-bien" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  mappingDotBienEntity: storeState.mappingDotBien.entity,
  loading: storeState.mappingDotBien.loading,
  updating: storeState.mappingDotBien.updating,
  updateSuccess: storeState.mappingDotBien.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(MappingDotBienUpdate);
