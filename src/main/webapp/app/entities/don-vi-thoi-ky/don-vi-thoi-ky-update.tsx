import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDonVi } from 'app/shared/model/don-vi.model';
import { getEntities as getDonVis } from 'app/entities/don-vi/don-vi.reducer';
import { getEntity, updateEntity, createEntity, reset } from './don-vi-thoi-ky.reducer';
import { IDonViThoiKy } from 'app/shared/model/don-vi-thoi-ky.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDonViThoiKyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDonViThoiKyUpdateState {
  isNew: boolean;
  donViId: string;
}

export class DonViThoiKyUpdate extends React.Component<IDonViThoiKyUpdateProps, IDonViThoiKyUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      donViId: '0',
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

    this.props.getDonVis();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { donViThoiKyEntity } = this.props;
      const entity = {
        ...donViThoiKyEntity,
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
    this.props.history.push('/entity/don-vi-thoi-ky');
  };

  render() {
    const { donViThoiKyEntity, donVis, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.donViThoiKy.home.createOrEditLabel">
              <Translate contentKey="xddtApp.donViThoiKy.home.createOrEditLabel">Create or edit a DonViThoiKy</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : donViThoiKyEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="don-vi-thoi-ky-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tuNamLabel" for="tuNam">
                    <Translate contentKey="xddtApp.donViThoiKy.tuNam">Tu Nam</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-tuNam" type="string" className="form-control" name="tuNam" />
                </AvGroup>
                <AvGroup>
                  <Label id="denNamLabel" for="denNam">
                    <Translate contentKey="xddtApp.donViThoiKy.denNam">Den Nam</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-denNam" type="string" className="form-control" name="denNam" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaDiemMoTaLabel" for="diaDiemMoTa">
                    <Translate contentKey="xddtApp.donViThoiKy.diaDiemMoTa">Dia Diem Mo Ta</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-diaDiemMoTa" type="text" name="diaDiemMoTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaDiemXaLabel" for="diaDiemXa">
                    <Translate contentKey="xddtApp.donViThoiKy.diaDiemXa">Dia Diem Xa</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-diaDiemXa" type="text" name="diaDiemXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaDiemHuyenLabel" for="diaDiemHuyen">
                    <Translate contentKey="xddtApp.donViThoiKy.diaDiemHuyen">Dia Diem Huyen</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-diaDiemHuyen" type="text" name="diaDiemHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="diaDiemTinhLabel" for="diaDiemTinh">
                    <Translate contentKey="xddtApp.donViThoiKy.diaDiemTinh">Dia Diem Tinh</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-diaDiemTinh" type="text" name="diaDiemTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="don-vi-thoi-ky-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.donViThoiKy.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.donViThoiKy.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="don-vi-thoi-ky-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label for="donVi.id">
                    <Translate contentKey="xddtApp.donViThoiKy.donVi">Don Vi</Translate>
                  </Label>
                  <AvInput id="don-vi-thoi-ky-donVi" type="select" className="form-control" name="donVi.id">
                    <option value="" key="0" />
                    {donVis
                      ? donVis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/don-vi-thoi-ky" replace color="info">
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
  donVis: storeState.donVi.entities,
  donViThoiKyEntity: storeState.donViThoiKy.entity,
  loading: storeState.donViThoiKy.loading,
  updating: storeState.donViThoiKy.updating,
  updateSuccess: storeState.donViThoiKy.updateSuccess
});

const mapDispatchToProps = {
  getDonVis,
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
)(DonViThoiKyUpdate);
