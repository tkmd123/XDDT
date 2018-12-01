import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INhanVien } from 'app/shared/model/nhan-vien.model';
import { getEntities as getNhanViens } from 'app/entities/nhan-vien/nhan-vien.reducer';
import { IMayPCR } from 'app/shared/model/may-pcr.model';
import { getEntities as getMayPcrs } from 'app/entities/may-pcr/may-pcr.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tinh-sach.reducer';
import { ITinhSach } from 'app/shared/model/tinh-sach.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITinhSachUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITinhSachUpdateState {
  isNew: boolean;
  nhanVienTinhSachId: string;
  mayTinhSachId: string;
}

export class TinhSachUpdate extends React.Component<ITinhSachUpdateProps, ITinhSachUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      nhanVienTinhSachId: '0',
      mayTinhSachId: '0',
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

    this.props.getNhanViens();
    this.props.getMayPcrs();
  }

  saveEntity = (event, errors, values) => {
    values.thoiGianThucHien = new Date(values.thoiGianThucHien);

    if (errors.length === 0) {
      const { tinhSachEntity } = this.props;
      const entity = {
        ...tinhSachEntity,
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
    this.props.history.push('/entity/tinh-sach');
  };

  render() {
    const { tinhSachEntity, nhanViens, mayPCRS, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.tinhSach.home.createOrEditLabel">
              <Translate contentKey="xddtApp.tinhSach.home.createOrEditLabel">Create or edit a TinhSach</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tinhSachEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="tinh-sach-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maTinhSachLabel" for="maTinhSach">
                    <Translate contentKey="xddtApp.tinhSach.maTinhSach">Ma Tinh Sach</Translate>
                  </Label>
                  <AvField id="tinh-sach-maTinhSach" type="text" name="maTinhSach" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianThucHienLabel" for="thoiGianThucHien">
                    <Translate contentKey="xddtApp.tinhSach.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                  </Label>
                  <AvInput
                    id="tinh-sach-thoiGianThucHien"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianThucHien"
                    value={isNew ? null : convertDateTimeFromServer(this.props.tinhSachEntity.thoiGianThucHien)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="phuongPhapTinhSachLabel">
                    <Translate contentKey="xddtApp.tinhSach.phuongPhapTinhSach">Phuong Phap Tinh Sach</Translate>
                  </Label>
                  <AvInput
                    id="tinh-sach-phuongPhapTinhSach"
                    type="select"
                    className="form-control"
                    name="phuongPhapTinhSach"
                    value={(!isNew && tinhSachEntity.phuongPhapTinhSach) || 'ENZYM'}
                  >
                    <option value="ENZYM">
                      <Translate contentKey="xddtApp.PhuongPhapTinhSach.ENZYM" />
                    </option>
                    <option value="KIT">
                      <Translate contentKey="xddtApp.PhuongPhapTinhSach.KIT" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.tinhSach.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="tinh-sach-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="tinh-sach-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.tinhSach.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.tinhSach.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="tinh-sach-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.tinhSach.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="tinh-sach-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.tinhSach.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="tinh-sach-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="nhanVienTinhSach.id">
                    <Translate contentKey="xddtApp.tinhSach.nhanVienTinhSach">Nhan Vien Tinh Sach</Translate>
                  </Label>
                  <AvInput id="tinh-sach-nhanVienTinhSach" type="select" className="form-control" name="nhanVienTinhSach.id">
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
                <AvGroup>
                  <Label for="mayTinhSach.id">
                    <Translate contentKey="xddtApp.tinhSach.mayTinhSach">May Tinh Sach</Translate>
                  </Label>
                  <AvInput id="tinh-sach-mayTinhSach" type="select" className="form-control" name="mayTinhSach.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/tinh-sach" replace color="info">
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
  nhanViens: storeState.nhanVien.entities,
  mayPCRS: storeState.mayPCR.entities,
  tinhSachEntity: storeState.tinhSach.entity,
  loading: storeState.tinhSach.loading,
  updating: storeState.tinhSach.updating,
  updateSuccess: storeState.tinhSach.updateSuccess
});

const mapDispatchToProps = {
  getNhanViens,
  getMayPcrs,
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
)(TinhSachUpdate);
