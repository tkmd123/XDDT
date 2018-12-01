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
import { getEntity, updateEntity, createEntity, reset } from './tach-chiet.reducer';
import { ITachChiet } from 'app/shared/model/tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITachChietUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITachChietUpdateState {
  isNew: boolean;
  nhanVienNghienMauId: string;
  nhanVienTachADNId: string;
}

export class TachChietUpdate extends React.Component<ITachChietUpdateProps, ITachChietUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      nhanVienNghienMauId: '0',
      nhanVienTachADNId: '0',
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
  }

  saveEntity = (event, errors, values) => {
    values.thoiGianThucHien = new Date(values.thoiGianThucHien);

    if (errors.length === 0) {
      const { tachChietEntity } = this.props;
      const entity = {
        ...tachChietEntity,
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
    this.props.history.push('/entity/tach-chiet');
  };

  render() {
    const { tachChietEntity, nhanViens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.tachChiet.home.createOrEditLabel">
              <Translate contentKey="xddtApp.tachChiet.home.createOrEditLabel">Create or edit a TachChiet</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tachChietEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="tach-chiet-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maTachChietLabel" for="maTachChiet">
                    <Translate contentKey="xddtApp.tachChiet.maTachChiet">Ma Tach Chiet</Translate>
                  </Label>
                  <AvField id="tach-chiet-maTachChiet" type="text" name="maTachChiet" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.tachChiet.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="tach-chiet-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.tachChiet.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="tach-chiet-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianThucHienLabel" for="thoiGianThucHien">
                    <Translate contentKey="xddtApp.tachChiet.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                  </Label>
                  <AvInput
                    id="tach-chiet-thoiGianThucHien"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianThucHien"
                    value={isNew ? null : convertDateTimeFromServer(this.props.tachChietEntity.thoiGianThucHien)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="phuongPhapLocLabel">
                    <Translate contentKey="xddtApp.tachChiet.phuongPhapLoc">Phuong Phap Loc</Translate>
                  </Label>
                  <AvInput
                    id="tach-chiet-phuongPhapLoc"
                    type="select"
                    className="form-control"
                    name="phuongPhapLoc"
                    value={(!isNew && tachChietEntity.phuongPhapLoc) || 'KIT'}
                  >
                    <option value="KIT">
                      <Translate contentKey="xddtApp.PhuongPhapLoc.KIT" />
                    </option>
                    <option value="PHENOL">
                      <Translate contentKey="xddtApp.PhuongPhapLoc.PHENOL" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="tach-chiet-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.tachChiet.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.tachChiet.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="tach-chiet-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.tachChiet.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="tach-chiet-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.tachChiet.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="tach-chiet-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="nhanVienNghienMau.id">
                    <Translate contentKey="xddtApp.tachChiet.nhanVienNghienMau">Nhan Vien Nghien Mau</Translate>
                  </Label>
                  <AvInput id="tach-chiet-nhanVienNghienMau" type="select" className="form-control" name="nhanVienNghienMau.id">
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
                  <Label for="nhanVienTachADN.id">
                    <Translate contentKey="xddtApp.tachChiet.nhanVienTachADN">Nhan Vien Tach ADN</Translate>
                  </Label>
                  <AvInput id="tach-chiet-nhanVienTachADN" type="select" className="form-control" name="nhanVienTachADN.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/tach-chiet" replace color="info">
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
  tachChietEntity: storeState.tachChiet.entity,
  loading: storeState.tachChiet.loading,
  updating: storeState.tachChiet.updating,
  updateSuccess: storeState.tachChiet.updateSuccess
});

const mapDispatchToProps = {
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
)(TachChietUpdate);
