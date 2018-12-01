import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
import { getEntities as getThongTinKhaiQuats } from 'app/entities/thong-tin-khai-quat/thong-tin-khai-quat.reducer';
import { ILoaiDiVat } from 'app/shared/model/loai-di-vat.model';
import { getEntities as getLoaiDiVats } from 'app/entities/loai-di-vat/loai-di-vat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './di-vat.reducer';
import { IDiVat } from 'app/shared/model/di-vat.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDiVatUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDiVatUpdateState {
  isNew: boolean;
  thongTinKhaiQuatId: string;
  loaiDiVatId: string;
}

export class DiVatUpdate extends React.Component<IDiVatUpdateProps, IDiVatUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      thongTinKhaiQuatId: '0',
      loaiDiVatId: '0',
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

    this.props.getThongTinKhaiQuats();
    this.props.getLoaiDiVats();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { diVatEntity } = this.props;
      const entity = {
        ...diVatEntity,
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
    this.props.history.push('/entity/di-vat');
  };

  render() {
    const { diVatEntity, thongTinKhaiQuats, loaiDiVats, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.diVat.home.createOrEditLabel">
              <Translate contentKey="xddtApp.diVat.home.createOrEditLabel">Create or edit a DiVat</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : diVatEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="di-vat-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="giaTriLabel" for="giaTri">
                    <Translate contentKey="xddtApp.diVat.giaTri">Gia Tri</Translate>
                  </Label>
                  <AvField id="di-vat-giaTri" type="text" name="giaTri" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.diVat.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="di-vat-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="di-vat-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.diVat.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.diVat.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="di-vat-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.diVat.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="di-vat-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.diVat.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="di-vat-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="thongTinKhaiQuat.id">
                    <Translate contentKey="xddtApp.diVat.thongTinKhaiQuat">Thong Tin Khai Quat</Translate>
                  </Label>
                  <AvInput id="di-vat-thongTinKhaiQuat" type="select" className="form-control" name="thongTinKhaiQuat.id">
                    <option value="" key="0" />
                    {thongTinKhaiQuats
                      ? thongTinKhaiQuats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loaiDiVat.id">
                    <Translate contentKey="xddtApp.diVat.loaiDiVat">Loai Di Vat</Translate>
                  </Label>
                  <AvInput id="di-vat-loaiDiVat" type="select" className="form-control" name="loaiDiVat.id">
                    <option value="" key="0" />
                    {loaiDiVats
                      ? loaiDiVats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/di-vat" replace color="info">
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
  thongTinKhaiQuats: storeState.thongTinKhaiQuat.entities,
  loaiDiVats: storeState.loaiDiVat.entities,
  diVatEntity: storeState.diVat.entity,
  loading: storeState.diVat.loading,
  updating: storeState.diVat.updating,
  updateSuccess: storeState.diVat.updateSuccess
});

const mapDispatchToProps = {
  getThongTinKhaiQuats,
  getLoaiDiVats,
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
)(DiVatUpdate);
