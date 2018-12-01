import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPCRKetQua } from 'app/shared/model/pcr-ket-qua.model';
import { getEntities as getPCrKetQuas } from 'app/entities/pcr-ket-qua/pcr-ket-qua.reducer';
import { IPCR } from 'app/shared/model/pcr.model';
import { getEntities as getPCrs } from 'app/entities/pcr/pcr.reducer';
import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
import { getEntities as getMauXetNghiems } from 'app/entities/mau-xet-nghiem/mau-xet-nghiem.reducer';
import { IPCRMoi } from 'app/shared/model/pcr-moi.model';
import { getEntities as getPCrMois } from 'app/entities/pcr-moi/pcr-moi.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pcr-mau.reducer';
import { IPCRMau } from 'app/shared/model/pcr-mau.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPCRMauUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPCRMauUpdateState {
  isNew: boolean;
  pcrKetQuaId: string;
  pcrMauId: string;
  mauPCRId: string;
  moiPCRId: string;
}

export class PCRMauUpdate extends React.Component<IPCRMauUpdateProps, IPCRMauUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      pcrKetQuaId: '0',
      pcrMauId: '0',
      mauPCRId: '0',
      moiPCRId: '0',
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

    this.props.getPCrKetQuas();
    this.props.getPCrs();
    this.props.getMauXetNghiems();
    this.props.getPCrMois();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { pCRMauEntity } = this.props;
      const entity = {
        ...pCRMauEntity,
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
    this.props.history.push('/entity/pcr-mau');
  };

  render() {
    const { pCRMauEntity, pCRKetQuas, pCRS, mauXetNghiems, pCRMois, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.pCRMau.home.createOrEditLabel">
              <Translate contentKey="xddtApp.pCRMau.home.createOrEditLabel">Create or edit a PCRMau</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pCRMauEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pcr-mau-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nongDoAgarosLabel" for="nongDoAgaros">
                    <Translate contentKey="xddtApp.pCRMau.nongDoAgaros">Nong Do Agaros</Translate>
                  </Label>
                  <AvField id="pcr-mau-nongDoAgaros" type="string" className="form-control" name="nongDoAgaros" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pcr-mau-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.pCRMau.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.pCRMau.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="pcr-mau-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.pCRMau.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="pcr-mau-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.pCRMau.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="pcr-mau-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="pcrKetQua.id">
                    <Translate contentKey="xddtApp.pCRMau.pcrKetQua">Pcr Ket Qua</Translate>
                  </Label>
                  <AvInput id="pcr-mau-pcrKetQua" type="select" className="form-control" name="pcrKetQua.id">
                    <option value="" key="0" />
                    {pCRKetQuas
                      ? pCRKetQuas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="pcrMau.id">
                    <Translate contentKey="xddtApp.pCRMau.pcrMau">Pcr Mau</Translate>
                  </Label>
                  <AvInput id="pcr-mau-pcrMau" type="select" className="form-control" name="pcrMau.id">
                    <option value="" key="0" />
                    {pCRS
                      ? pCRS.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="mauPCR.id">
                    <Translate contentKey="xddtApp.pCRMau.mauPCR">Mau PCR</Translate>
                  </Label>
                  <AvInput id="pcr-mau-mauPCR" type="select" className="form-control" name="mauPCR.id">
                    <option value="" key="0" />
                    {mauXetNghiems
                      ? mauXetNghiems.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="moiPCR.id">
                    <Translate contentKey="xddtApp.pCRMau.moiPCR">Moi PCR</Translate>
                  </Label>
                  <AvInput id="pcr-mau-moiPCR" type="select" className="form-control" name="moiPCR.id">
                    <option value="" key="0" />
                    {pCRMois
                      ? pCRMois.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pcr-mau" replace color="info">
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
  pCRKetQuas: storeState.pCRKetQua.entities,
  pCRS: storeState.pCR.entities,
  mauXetNghiems: storeState.mauXetNghiem.entities,
  pCRMois: storeState.pCRMoi.entities,
  pCRMauEntity: storeState.pCRMau.entity,
  loading: storeState.pCRMau.loading,
  updating: storeState.pCRMau.updating,
  updateSuccess: storeState.pCRMau.updateSuccess
});

const mapDispatchToProps = {
  getPCrKetQuas,
  getPCrs,
  getMauXetNghiems,
  getPCrMois,
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
)(PCRMauUpdate);
