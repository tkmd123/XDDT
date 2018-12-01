import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getDonVis } from 'app/entities/don-vi/don-vi.reducer';
import { getEntity, updateEntity, createEntity, reset } from './don-vi.reducer';
import { IDonVi } from 'app/shared/model/don-vi.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDonViUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDonViUpdateState {
  isNew: boolean;
  donViQuanLyId: string;
  donViQuanLyId: string;
}

export class DonViUpdate extends React.Component<IDonViUpdateProps, IDonViUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      donViQuanLyId: '0',
      donViQuanLyId: '0',
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
      const { donViEntity } = this.props;
      const entity = {
        ...donViEntity,
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
    this.props.history.push('/entity/don-vi');
  };

  render() {
    const { donViEntity, donVis, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.donVi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.donVi.home.createOrEditLabel">Create or edit a DonVi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : donViEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="don-vi-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maDonViLabel" for="maDonVi">
                    <Translate contentKey="xddtApp.donVi.maDonVi">Ma Don Vi</Translate>
                  </Label>
                  <AvField id="don-vi-maDonVi" type="text" name="maDonVi" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenDonViLabel" for="tenDonVi">
                    <Translate contentKey="xddtApp.donVi.tenDonVi">Ten Don Vi</Translate>
                  </Label>
                  <AvField id="don-vi-tenDonVi" type="text" name="tenDonVi" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenTatLabel" for="tenTat">
                    <Translate contentKey="xddtApp.donVi.tenTat">Ten Tat</Translate>
                  </Label>
                  <AvField id="don-vi-tenTat" type="text" name="tenTat" />
                </AvGroup>
                <AvGroup>
                  <Label id="phanMucLabel" for="phanMuc">
                    <Translate contentKey="xddtApp.donVi.phanMuc">Phan Muc</Translate>
                  </Label>
                  <AvField id="don-vi-phanMuc" type="text" name="phanMuc" />
                </AvGroup>
                <AvGroup>
                  <Label id="phanCapLabel" for="phanCap">
                    <Translate contentKey="xddtApp.donVi.phanCap">Phan Cap</Translate>
                  </Label>
                  <AvField id="don-vi-phanCap" type="text" name="phanCap" />
                </AvGroup>
                <AvGroup>
                  <Label id="phanKhoiLabel" for="phanKhoi">
                    <Translate contentKey="xddtApp.donVi.phanKhoi">Phan Khoi</Translate>
                  </Label>
                  <AvField id="don-vi-phanKhoi" type="text" name="phanKhoi" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.donVi.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="don-vi-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.donVi.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="don-vi-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="don-vi-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.donVi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.donVi.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="don-vi-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.donVi.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="don-vi-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.donVi.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="don-vi-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="donViQuanLy.id">
                    <Translate contentKey="xddtApp.donVi.donViQuanLy">Don Vi Quan Ly</Translate>
                  </Label>
                  <AvInput id="don-vi-donViQuanLy" type="select" className="form-control" name="donViQuanLy.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/don-vi" replace color="info">
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
  donViEntity: storeState.donVi.entity,
  loading: storeState.donVi.loading,
  updating: storeState.donVi.updating,
  updateSuccess: storeState.donVi.updateSuccess
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
)(DonViUpdate);
