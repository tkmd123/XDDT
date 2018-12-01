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
import { getEntity, updateEntity, createEntity, reset } from './nghia-trang.reducer';
import { INghiaTrang } from 'app/shared/model/nghia-trang.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INghiaTrangUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INghiaTrangUpdateState {
  isNew: boolean;
  phuongXaId: string;
}

export class NghiaTrangUpdate extends React.Component<INghiaTrangUpdateProps, INghiaTrangUpdateState> {
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
    if (errors.length === 0) {
      const { nghiaTrangEntity } = this.props;
      const entity = {
        ...nghiaTrangEntity,
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
    this.props.history.push('/entity/nghia-trang');
  };

  render() {
    const { nghiaTrangEntity, phuongXas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.nghiaTrang.home.createOrEditLabel">
              <Translate contentKey="xddtApp.nghiaTrang.home.createOrEditLabel">Create or edit a NghiaTrang</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nghiaTrangEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nghia-trang-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maNghiaTrangLabel" for="maNghiaTrang">
                    <Translate contentKey="xddtApp.nghiaTrang.maNghiaTrang">Ma Nghia Trang</Translate>
                  </Label>
                  <AvField id="nghia-trang-maNghiaTrang" type="text" name="maNghiaTrang" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenNghiaTrangLabel" for="tenNghiaTrang">
                    <Translate contentKey="xddtApp.nghiaTrang.tenNghiaTrang">Ten Nghia Trang</Translate>
                  </Label>
                  <AvField id="nghia-trang-tenNghiaTrang" type="text" name="tenNghiaTrang" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaChiLabel" for="diaChi">
                    <Translate contentKey="xddtApp.nghiaTrang.diaChi">Dia Chi</Translate>
                  </Label>
                  <AvField id="nghia-trang-diaChi" type="text" name="diaChi" />
                </AvGroup>
                <AvGroup>
                  <Label id="nguoiLienHeLabel" for="nguoiLienHe">
                    <Translate contentKey="xddtApp.nghiaTrang.nguoiLienHe">Nguoi Lien He</Translate>
                  </Label>
                  <AvField id="nghia-trang-nguoiLienHe" type="text" name="nguoiLienHe" />
                </AvGroup>
                <AvGroup>
                  <Label id="dienThoaiLabel" for="dienThoai">
                    <Translate contentKey="xddtApp.nghiaTrang.dienThoai">Dien Thoai</Translate>
                  </Label>
                  <AvField id="nghia-trang-dienThoai" type="text" name="dienThoai" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="xddtApp.nghiaTrang.email">Email</Translate>
                  </Label>
                  <AvField id="nghia-trang-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.nghiaTrang.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="nghia-trang-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="nghia-trang-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.nghiaTrang.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.nghiaTrang.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="nghia-trang-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.nghiaTrang.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="nghia-trang-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.nghiaTrang.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="nghia-trang-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="phuongXa.id">
                    <Translate contentKey="xddtApp.nghiaTrang.phuongXa">Phuong Xa</Translate>
                  </Label>
                  <AvInput id="nghia-trang-phuongXa" type="select" className="form-control" name="phuongXa.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/nghia-trang" replace color="info">
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
  nghiaTrangEntity: storeState.nghiaTrang.entity,
  loading: storeState.nghiaTrang.loading,
  updating: storeState.nghiaTrang.updating,
  updateSuccess: storeState.nghiaTrang.updateSuccess
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
)(NghiaTrangUpdate);
