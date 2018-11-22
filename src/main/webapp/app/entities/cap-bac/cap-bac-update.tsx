import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './cap-bac.reducer';
import { ICapBac } from 'app/shared/model/cap-bac.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICapBacUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICapBacUpdateState {
  isNew: boolean;
}

export class CapBacUpdate extends React.Component<ICapBacUpdateProps, ICapBacUpdateState> {
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
      const { capBacEntity } = this.props;
      const entity = {
        ...capBacEntity,
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
    this.props.history.push('/entity/cap-bac');
  };

  render() {
    const { capBacEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.capBac.home.createOrEditLabel">
              <Translate contentKey="xddtApp.capBac.home.createOrEditLabel">Create or edit a CapBac</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : capBacEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="cap-bac-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maCapBacLabel" for="maCapBac">
                    <Translate contentKey="xddtApp.capBac.maCapBac">Ma Cap Bac</Translate>
                  </Label>
                  <AvField id="cap-bac-maCapBac" type="text" name="maCapBac" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenCapBacLabel" for="tenCapBac">
                    <Translate contentKey="xddtApp.capBac.tenCapBac">Ten Cap Bac</Translate>
                  </Label>
                  <AvField id="cap-bac-tenCapBac" type="text" name="tenCapBac" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.capBac.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="cap-bac-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="cap-bac-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.capBac.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/cap-bac" replace color="info">
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
  capBacEntity: storeState.capBac.entity,
  loading: storeState.capBac.loading,
  updating: storeState.capBac.updating,
  updateSuccess: storeState.capBac.updateSuccess
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
)(CapBacUpdate);
