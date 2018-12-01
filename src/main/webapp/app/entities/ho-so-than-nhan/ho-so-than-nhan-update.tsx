import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPhuongXa } from 'app/shared/model/phuong-xa.model';
import { getEntities as getPhuongXas } from 'app/entities/phuong-xa/phuong-xa.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ho-so-than-nhan.reducer';
import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoSoThanNhanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoSoThanNhanUpdateState {
  isNew: boolean;
  phuongXaId: string;
}

export class HoSoThanNhanUpdate extends React.Component<IHoSoThanNhanUpdateProps, IHoSoThanNhanUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      phuongXaId: '0',
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

    this.props.getPhuongXas();
  }

  saveEntity = (event, errors, values) => {
    values.namSinh = new Date(values.namSinh);

    if (errors.length === 0) {
      const { hoSoThanNhanEntity } = this.props;
      const entity = {
        ...hoSoThanNhanEntity,
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
    this.props.history.push('/entity/ho-so-than-nhan');
  };

  render() {
    const { hoSoThanNhanEntity, phuongXas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoSoThanNhan.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoSoThanNhan.home.createOrEditLabel">Create or edit a HoSoThanNhan</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoSoThanNhanEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ho-so-than-nhan-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maThanNhanLabel" for="maThanNhan">
                    <Translate contentKey="xddtApp.hoSoThanNhan.maThanNhan">Ma Than Nhan</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-maThanNhan" type="text" name="maThanNhan" />
                </AvGroup>
                <AvGroup>
                  <Label id="isLayMauLabel" check>
                    <AvInput id="ho-so-than-nhan-isLayMau" type="checkbox" className="form-control" name="isLayMau" />
                    <Translate contentKey="xddtApp.hoSoThanNhan.isLayMau">Is Lay Mau</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="hoTenLabel" for="hoTen">
                    <Translate contentKey="xddtApp.hoSoThanNhan.hoTen">Ho Ten</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-hoTen" type="text" name="hoTen" />
                </AvGroup>
                <AvGroup>
                  <Label id="namSinhLabel" for="namSinh">
                    <Translate contentKey="xddtApp.hoSoThanNhan.namSinh">Nam Sinh</Translate>
                  </Label>
                  <AvInput
                    id="ho-so-than-nhan-namSinh"
                    type="datetime-local"
                    className="form-control"
                    name="namSinh"
                    value={isNew ? null : convertDateTimeFromServer(this.props.hoSoThanNhanEntity.namSinh)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="gioiTinhLabel" for="gioiTinh">
                    <Translate contentKey="xddtApp.hoSoThanNhan.gioiTinh">Gioi Tinh</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-gioiTinh" type="text" name="gioiTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="soCMTLabel" for="soCMT">
                    <Translate contentKey="xddtApp.hoSoThanNhan.soCMT">So CMT</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-soCMT" type="text" name="soCMT" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaChiLabel" for="diaChi">
                    <Translate contentKey="xddtApp.hoSoThanNhan.diaChi">Dia Chi</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-diaChi" type="text" name="diaChi" />
                </AvGroup>
                <AvGroup>
                  <Label id="dienThoaiChinhLabel" for="dienThoaiChinh">
                    <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiChinh">Dien Thoai Chinh</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-dienThoaiChinh" type="text" name="dienThoaiChinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="dienThoaiPhuLabel" for="dienThoaiPhu">
                    <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiPhu">Dien Thoai Phu</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-dienThoaiPhu" type="text" name="dienThoaiPhu" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="xddtApp.hoSoThanNhan.email">Email</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.hoSoThanNhan.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="ho-so-than-nhan-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoSoThanNhan.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.hoSoThanNhan.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.hoSoThanNhan.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.hoSoThanNhan.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="ho-so-than-nhan-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="phuongXa.id">
                    <Translate contentKey="xddtApp.hoSoThanNhan.phuongXa">Phuong Xa</Translate>
                  </Label>
                  <AvInput id="ho-so-than-nhan-phuongXa" type="select" className="form-control" name="phuongXa.id">
                    <option value="" key="0" />
                    {phuongXas
                      ? phuongXas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ho-so-than-nhan" replace color="info">
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
  phuongXas: storeState.phuongXa.entities,
  hoSoThanNhanEntity: storeState.hoSoThanNhan.entity,
  loading: storeState.hoSoThanNhan.loading,
  updating: storeState.hoSoThanNhan.updating,
  updateSuccess: storeState.hoSoThanNhan.updateSuccess
});

const mapDispatchToProps = {
  getPhuongXas,
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
)(HoSoThanNhanUpdate);
