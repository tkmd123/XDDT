import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './loai-mau-xet-nghiem.reducer';
import { ILoaiMauXetNghiem } from 'app/shared/model/loai-mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoaiMauXetNghiemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoaiMauXetNghiemUpdateState {
  isNew: boolean;
}

export class LoaiMauXetNghiemUpdate extends React.Component<ILoaiMauXetNghiemUpdateProps, ILoaiMauXetNghiemUpdateState> {
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
      const { loaiMauXetNghiemEntity } = this.props;
      const entity = {
        ...loaiMauXetNghiemEntity,
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
    this.props.history.push('/entity/loai-mau-xet-nghiem');
  };

  render() {
    const { loaiMauXetNghiemEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.loaiMauXetNghiem.home.createOrEditLabel">
              <Translate contentKey="xddtApp.loaiMauXetNghiem.home.createOrEditLabel">Create or edit a LoaiMauXetNghiem</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loaiMauXetNghiemEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loai-mau-xet-nghiem-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maLoaiMauLabel" for="maLoaiMau">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.maLoaiMau">Ma Loai Mau</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-maLoaiMau" type="text" name="maLoaiMau" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenLoaiMauLabel" for="tenLoaiMau">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.tenLoaiMau">Ten Loai Mau</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-tenLoaiMau" type="text" name="tenLoaiMau" />
                </AvGroup>
                <AvGroup>
                  <Label id="phanLoaiLabel" for="phanLoai">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.phanLoai">Phan Loai</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-phanLoai" type="text" name="phanLoai" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="loai-mau-xet-nghiem-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="loai-mau-xet-nghiem-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loai-mau-xet-nghiem" replace color="info">
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
  loaiMauXetNghiemEntity: storeState.loaiMauXetNghiem.entity,
  loading: storeState.loaiMauXetNghiem.loading,
  updating: storeState.loaiMauXetNghiem.updating,
  updateSuccess: storeState.loaiMauXetNghiem.updateSuccess
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
)(LoaiMauXetNghiemUpdate);
