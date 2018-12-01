import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pcr-moi.reducer';
import { IPCRMoi } from 'app/shared/model/pcr-moi.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPCRMoiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPCRMoiUpdateState {
  isNew: boolean;
}

export class PCRMoiUpdate extends React.Component<IPCRMoiUpdateProps, IPCRMoiUpdateState> {
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
      const { pCRMoiEntity } = this.props;
      const entity = {
        ...pCRMoiEntity,
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
    this.props.history.push('/entity/pcr-moi');
  };

  render() {
    const { pCRMoiEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.pCRMoi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.pCRMoi.home.createOrEditLabel">Create or edit a PCRMoi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pCRMoiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pcr-moi-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maMoiLabel" for="maMoi">
                    <Translate contentKey="xddtApp.pCRMoi.maMoi">Ma Moi</Translate>
                  </Label>
                  <AvField id="pcr-moi-maMoi" type="text" name="maMoi" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenMoiLabel" for="tenMoi">
                    <Translate contentKey="xddtApp.pCRMoi.tenMoi">Ten Moi</Translate>
                  </Label>
                  <AvField id="pcr-moi-tenMoi" type="text" name="tenMoi" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.pCRMoi.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="pcr-moi-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pcr-moi-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.pCRMoi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.pCRMoi.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="pcr-moi-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.pCRMoi.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="pcr-moi-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.pCRMoi.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="pcr-moi-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pcr-moi" replace color="info">
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
  pCRMoiEntity: storeState.pCRMoi.entity,
  loading: storeState.pCRMoi.loading,
  updating: storeState.pCRMoi.updating,
  updateSuccess: storeState.pCRMoi.updateSuccess
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
)(PCRMoiUpdate);
