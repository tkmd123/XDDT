import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMayPCR } from 'app/shared/model/may-pcr.model';
import { getEntities as getMayPcrs } from 'app/entities/may-pcr/may-pcr.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
import { getEntities as getNhanViens } from 'app/entities/nhan-vien/nhan-vien.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pcr.reducer';
import { IPCR } from 'app/shared/model/pcr.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPCRUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPCRUpdateState {
  isNew: boolean;
  mayPCRId: string;
  nhanVienPCRId: string;
}

export class PCRUpdate extends React.Component<IPCRUpdateProps, IPCRUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      mayPCRId: '0',
      nhanVienPCRId: '0',
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

    this.props.getMayPcrs();
    this.props.getNhanViens();
  }

  saveEntity = (event, errors, values) => {
    values.thoiGianThucHien = new Date(values.thoiGianThucHien);
    values.thoiGianBatDau = new Date(values.thoiGianBatDau);
    values.thoiGianKetThuc = new Date(values.thoiGianKetThuc);

    if (errors.length === 0) {
      const { pCREntity } = this.props;
      const entity = {
        ...pCREntity,
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
    this.props.history.push('/entity/pcr');
  };

  render() {
    const { pCREntity, mayPCRS, nhanViens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.pCR.home.createOrEditLabel">
              <Translate contentKey="xddtApp.pCR.home.createOrEditLabel">Create or edit a PCR</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pCREntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pcr-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maPCRLabel" for="maPCR">
                    <Translate contentKey="xddtApp.pCR.maPCR">Ma PCR</Translate>
                  </Label>
                  <AvField id="pcr-maPCR" type="text" name="maPCR" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianThucHienLabel" for="thoiGianThucHien">
                    <Translate contentKey="xddtApp.pCR.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                  </Label>
                  <AvInput
                    id="pcr-thoiGianThucHien"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianThucHien"
                    value={isNew ? null : convertDateTimeFromServer(this.props.pCREntity.thoiGianThucHien)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nhanXetLabel" for="nhanXet">
                    <Translate contentKey="xddtApp.pCR.nhanXet">Nhan Xet</Translate>
                  </Label>
                  <AvField id="pcr-nhanXet" type="text" name="nhanXet" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianBatDauLabel" for="thoiGianBatDau">
                    <Translate contentKey="xddtApp.pCR.thoiGianBatDau">Thoi Gian Bat Dau</Translate>
                  </Label>
                  <AvInput
                    id="pcr-thoiGianBatDau"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianBatDau"
                    value={isNew ? null : convertDateTimeFromServer(this.props.pCREntity.thoiGianBatDau)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="congSuatBatDauLabel" for="congSuatBatDau">
                    <Translate contentKey="xddtApp.pCR.congSuatBatDau">Cong Suat Bat Dau</Translate>
                  </Label>
                  <AvField id="pcr-congSuatBatDau" type="string" className="form-control" name="congSuatBatDau" />
                </AvGroup>
                <AvGroup>
                  <Label id="cuongDoBatDauLabel" for="cuongDoBatDau">
                    <Translate contentKey="xddtApp.pCR.cuongDoBatDau">Cuong Do Bat Dau</Translate>
                  </Label>
                  <AvField id="pcr-cuongDoBatDau" type="string" className="form-control" name="cuongDoBatDau" />
                </AvGroup>
                <AvGroup>
                  <Label id="dienTheBatDauLabel" for="dienTheBatDau">
                    <Translate contentKey="xddtApp.pCR.dienTheBatDau">Dien The Bat Dau</Translate>
                  </Label>
                  <AvField id="pcr-dienTheBatDau" type="string" className="form-control" name="dienTheBatDau" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianKetThucLabel" for="thoiGianKetThuc">
                    <Translate contentKey="xddtApp.pCR.thoiGianKetThuc">Thoi Gian Ket Thuc</Translate>
                  </Label>
                  <AvInput
                    id="pcr-thoiGianKetThuc"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianKetThuc"
                    value={isNew ? null : convertDateTimeFromServer(this.props.pCREntity.thoiGianKetThuc)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="congSuatKetThucLabel" for="congSuatKetThuc">
                    <Translate contentKey="xddtApp.pCR.congSuatKetThuc">Cong Suat Ket Thuc</Translate>
                  </Label>
                  <AvField id="pcr-congSuatKetThuc" type="string" className="form-control" name="congSuatKetThuc" />
                </AvGroup>
                <AvGroup>
                  <Label id="cuongDoKetThucLabel" for="cuongDoKetThuc">
                    <Translate contentKey="xddtApp.pCR.cuongDoKetThuc">Cuong Do Ket Thuc</Translate>
                  </Label>
                  <AvField id="pcr-cuongDoKetThuc" type="string" className="form-control" name="cuongDoKetThuc" />
                </AvGroup>
                <AvGroup>
                  <Label id="dienTheKetThucLabel" for="dienTheKetThuc">
                    <Translate contentKey="xddtApp.pCR.dienTheKetThuc">Dien The Ket Thuc</Translate>
                  </Label>
                  <AvField id="pcr-dienTheKetThuc" type="string" className="form-control" name="dienTheKetThuc" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pcr-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.pCR.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.pCR.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="pcr-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.pCR.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="pcr-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.pCR.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="pcr-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf4Label" for="udf4">
                    <Translate contentKey="xddtApp.pCR.udf4">Udf 4</Translate>
                  </Label>
                  <AvField id="pcr-udf4" type="text" name="udf4" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf5Label" for="udf5">
                    <Translate contentKey="xddtApp.pCR.udf5">Udf 5</Translate>
                  </Label>
                  <AvField id="pcr-udf5" type="text" name="udf5" />
                </AvGroup>
                <AvGroup>
                  <Label for="mayPCR.id">
                    <Translate contentKey="xddtApp.pCR.mayPCR">May PCR</Translate>
                  </Label>
                  <AvInput id="pcr-mayPCR" type="select" className="form-control" name="mayPCR.id">
                    <option value="" key="0" />
                    {mayPCRS
                      ? mayPCRS.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="nhanVienPCR.id">
                    <Translate contentKey="xddtApp.pCR.nhanVienPCR">Nhan Vien PCR</Translate>
                  </Label>
                  <AvInput id="pcr-nhanVienPCR" type="select" className="form-control" name="nhanVienPCR.id">
                    <option value="" key="0" />
                    {nhanViens
                      ? nhanViens.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pcr" replace color="info">
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
  mayPCRS: storeState.mayPCR.entities,
  nhanViens: storeState.nhanVien.entities,
  pCREntity: storeState.pCR.entity,
  loading: storeState.pCR.loading,
  updating: storeState.pCR.updating,
  updateSuccess: storeState.pCR.updateSuccess
});

const mapDispatchToProps = {
  getMayPcrs,
  getNhanViens,
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
)(PCRUpdate);
