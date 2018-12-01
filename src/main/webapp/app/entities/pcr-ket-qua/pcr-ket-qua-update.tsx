import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './pcr-ket-qua.reducer';
import { IPCRKetQua } from 'app/shared/model/pcr-ket-qua.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPCRKetQuaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPCRKetQuaUpdateState {
  isNew: boolean;
}

export class PCRKetQuaUpdate extends React.Component<IPCRKetQuaUpdateProps, IPCRKetQuaUpdateState> {
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
      const { pCRKetQuaEntity } = this.props;
      const entity = {
        ...pCRKetQuaEntity,
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
    this.props.history.push('/entity/pcr-ket-qua');
  };

  render() {
    const { pCRKetQuaEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.pCRKetQua.home.createOrEditLabel">
              <Translate contentKey="xddtApp.pCRKetQua.home.createOrEditLabel">Create or edit a PCRKetQua</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pCRKetQuaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pcr-ket-qua-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maKetQuaLabel" for="maKetQua">
                    <Translate contentKey="xddtApp.pCRKetQua.maKetQua">Ma Ket Qua</Translate>
                  </Label>
                  <AvField id="pcr-ket-qua-maKetQua" type="text" name="maKetQua" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenKetQuaLabel" for="tenKetQua">
                    <Translate contentKey="xddtApp.pCRKetQua.tenKetQua">Ten Ket Qua</Translate>
                  </Label>
                  <AvField id="pcr-ket-qua-tenKetQua" type="text" name="tenKetQua" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pcr-ket-qua-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.pCRKetQua.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.pCRKetQua.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="pcr-ket-qua-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.pCRKetQua.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="pcr-ket-qua-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.pCRKetQua.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="pcr-ket-qua-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pcr-ket-qua" replace color="info">
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
  pCRKetQuaEntity: storeState.pCRKetQua.entity,
  loading: storeState.pCRKetQua.loading,
  updating: storeState.pCRKetQua.updating,
  updateSuccess: storeState.pCRKetQua.updateSuccess
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
)(PCRKetQuaUpdate);
