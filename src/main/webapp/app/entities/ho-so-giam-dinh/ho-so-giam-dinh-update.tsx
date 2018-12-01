import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
import { getEntities as getHoSoThanNhans } from 'app/entities/ho-so-than-nhan/ho-so-than-nhan.reducer';
import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
import { getEntities as getHoSoLietSis } from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
import { getEntities as getNhanViens } from 'app/entities/nhan-vien/nhan-vien.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ho-so-giam-dinh.reducer';
import { IHoSoGiamDinh } from 'app/shared/model/ho-so-giam-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoSoGiamDinhUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoSoGiamDinhUpdateState {
  isNew: boolean;
  idsgiamDinhThanNhan: any[];
  idsgiamDinhLietSi: any[];
  nhanVienHSGDId: string;
}

export class HoSoGiamDinhUpdate extends React.Component<IHoSoGiamDinhUpdateProps, IHoSoGiamDinhUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsgiamDinhThanNhan: [],
      idsgiamDinhLietSi: [],
      nhanVienHSGDId: '0',
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

    this.props.getHoSoThanNhans();
    this.props.getHoSoLietSis();
    this.props.getNhanViens();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { hoSoGiamDinhEntity } = this.props;
      const entity = {
        ...hoSoGiamDinhEntity,
        ...values,
        giamDinhThanNhans: mapIdList(values.giamDinhThanNhans),
        giamDinhLietSis: mapIdList(values.giamDinhLietSis)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/ho-so-giam-dinh');
  };

  render() {
    const { hoSoGiamDinhEntity, hoSoThanNhans, hoSoLietSis, nhanViens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoSoGiamDinh.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoSoGiamDinh.home.createOrEditLabel">Create or edit a HoSoGiamDinh</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoSoGiamDinhEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ho-so-giam-dinh-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maHSGDLabel" for="maHSGD">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.maHSGD">Ma HSGD</Translate>
                  </Label>
                  <AvField id="ho-so-giam-dinh-maHSGD" type="text" name="maHSGD" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="ho-so-giam-dinh-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoSoGiamDinh.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="ho-so-giam-dinh-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="ho-so-giam-dinh-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="ho-so-giam-dinh-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="hoSoThanNhans">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhThanNhan">Giam Dinh Than Nhan</Translate>
                  </Label>
                  <AvInput
                    id="ho-so-giam-dinh-giamDinhThanNhan"
                    type="select"
                    multiple
                    className="form-control"
                    name="giamDinhThanNhans"
                    value={hoSoGiamDinhEntity.giamDinhThanNhans && hoSoGiamDinhEntity.giamDinhThanNhans.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {hoSoThanNhans
                      ? hoSoThanNhans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="hoSoLietSis">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhLietSi">Giam Dinh Liet Si</Translate>
                  </Label>
                  <AvInput
                    id="ho-so-giam-dinh-giamDinhLietSi"
                    type="select"
                    multiple
                    className="form-control"
                    name="giamDinhLietSis"
                    value={hoSoGiamDinhEntity.giamDinhLietSis && hoSoGiamDinhEntity.giamDinhLietSis.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {hoSoLietSis
                      ? hoSoLietSis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="nhanVienHSGD.id">
                    <Translate contentKey="xddtApp.hoSoGiamDinh.nhanVienHSGD">Nhan Vien HSGD</Translate>
                  </Label>
                  <AvInput id="ho-so-giam-dinh-nhanVienHSGD" type="select" className="form-control" name="nhanVienHSGD.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/ho-so-giam-dinh" replace color="info">
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
  hoSoThanNhans: storeState.hoSoThanNhan.entities,
  hoSoLietSis: storeState.hoSoLietSi.entities,
  nhanViens: storeState.nhanVien.entities,
  hoSoGiamDinhEntity: storeState.hoSoGiamDinh.entity,
  loading: storeState.hoSoGiamDinh.loading,
  updating: storeState.hoSoGiamDinh.updating,
  updateSuccess: storeState.hoSoGiamDinh.updateSuccess
});

const mapDispatchToProps = {
  getHoSoThanNhans,
  getHoSoLietSis,
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
)(HoSoGiamDinhUpdate);
