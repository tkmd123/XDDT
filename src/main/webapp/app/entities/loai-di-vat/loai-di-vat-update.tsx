import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './loai-di-vat.reducer';
import { ILoaiDiVat } from 'app/shared/model/loai-di-vat.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoaiDiVatUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoaiDiVatUpdateState {
  isNew: boolean;
}

export class LoaiDiVatUpdate extends React.Component<ILoaiDiVatUpdateProps, ILoaiDiVatUpdateState> {
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
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { loaiDiVatEntity } = this.props;
      const entity = {
        ...loaiDiVatEntity,
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
    this.props.history.push('/entity/loai-di-vat');
  };

  render() {
    const { loaiDiVatEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.loaiDiVat.home.createOrEditLabel">
              <Translate contentKey="xddtApp.loaiDiVat.home.createOrEditLabel">Create or edit a LoaiDiVat</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loaiDiVatEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loai-di-vat-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maDiVatLabel" for="maDiVat">
                    <Translate contentKey="xddtApp.loaiDiVat.maDiVat">Ma Di Vat</Translate>
                  </Label>
                  <AvField id="loai-di-vat-maDiVat" type="text" name="maDiVat" />
                </AvGroup>
                <AvGroup>
                  <Label id="loaiDiVatLabel" for="loaiDiVat">
                    <Translate contentKey="xddtApp.loaiDiVat.loaiDiVat">Loai Di Vat</Translate>
                  </Label>
                  <AvField id="loai-di-vat-loaiDiVat" type="text" name="loaiDiVat" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.loaiDiVat.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="loai-di-vat-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="loai-di-vat-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.loaiDiVat.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.loaiDiVat.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="loai-di-vat-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.loaiDiVat.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="loai-di-vat-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.loaiDiVat.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="loai-di-vat-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loai-di-vat" replace color="info">
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
  loaiDiVatEntity: storeState.loaiDiVat.entity,
  loading: storeState.loaiDiVat.loading,
  updating: storeState.loaiDiVat.updating,
  updateSuccess: storeState.loaiDiVat.updateSuccess
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
)(LoaiDiVatUpdate);
