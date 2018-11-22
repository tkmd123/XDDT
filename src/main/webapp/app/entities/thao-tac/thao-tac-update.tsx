import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
import { getEntities as getMauXetNghiems } from 'app/entities/mau-xet-nghiem/mau-xet-nghiem.reducer';
import { IPhongBan } from 'app/shared/model/phong-ban.model';
import { getEntities as getPhongBans } from 'app/entities/phong-ban/phong-ban.reducer';
import { ILoaiThaoTac } from 'app/shared/model/loai-thao-tac.model';
import { getEntities as getLoaiThaoTacs } from 'app/entities/loai-thao-tac/loai-thao-tac.reducer';
import { getEntity, updateEntity, createEntity, reset } from './thao-tac.reducer';
import { IThaoTac } from 'app/shared/model/thao-tac.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IThaoTacUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IThaoTacUpdateState {
  isNew: boolean;
  mauXetNghiemId: string;
  phongBanId: string;
  loaiThaoTacId: string;
}

export class ThaoTacUpdate extends React.Component<IThaoTacUpdateProps, IThaoTacUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      mauXetNghiemId: '0',
      phongBanId: '0',
      loaiThaoTacId: '0',
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

    this.props.getMauXetNghiems();
    this.props.getPhongBans();
    this.props.getLoaiThaoTacs();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { thaoTacEntity } = this.props;
      const entity = {
        ...thaoTacEntity,
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
    this.props.history.push('/entity/thao-tac');
  };

  render() {
    const { thaoTacEntity, mauXetNghiems, phongBans, loaiThaoTacs, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.thaoTac.home.createOrEditLabel">
              <Translate contentKey="xddtApp.thaoTac.home.createOrEditLabel">Create or edit a ThaoTac</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : thaoTacEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="thao-tac-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="moTaKetQuaLabel" for="moTaKetQua">
                    <Translate contentKey="xddtApp.thaoTac.moTaKetQua">Mo Ta Ket Qua</Translate>
                  </Label>
                  <AvField id="thao-tac-moTaKetQua" type="text" name="moTaKetQua" />
                </AvGroup>
                <AvGroup>
                  <Label id="trangThaiXuLyLabel" check>
                    <AvInput id="thao-tac-trangThaiXuLy" type="checkbox" className="form-control" name="trangThaiXuLy" />
                    <Translate contentKey="xddtApp.thaoTac.trangThaiXuLy">Trang Thai Xu Ly</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="isDangThucHienLabel" check>
                    <AvInput id="thao-tac-isDangThucHien" type="checkbox" className="form-control" name="isDangThucHien" />
                    <Translate contentKey="xddtApp.thaoTac.isDangThucHien">Is Dang Thuc Hien</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="thao-tac-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.thaoTac.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="mauXetNghiem.id">
                    <Translate contentKey="xddtApp.thaoTac.mauXetNghiem">Mau Xet Nghiem</Translate>
                  </Label>
                  <AvInput id="thao-tac-mauXetNghiem" type="select" className="form-control" name="mauXetNghiem.id">
                    <option value="" key="0" />
                    {mauXetNghiems
                      ? mauXetNghiems.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="phongBan.id">
                    <Translate contentKey="xddtApp.thaoTac.phongBan">Phong Ban</Translate>
                  </Label>
                  <AvInput id="thao-tac-phongBan" type="select" className="form-control" name="phongBan.id">
                    <option value="" key="0" />
                    {phongBans
                      ? phongBans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loaiThaoTac.id">
                    <Translate contentKey="xddtApp.thaoTac.loaiThaoTac">Loai Thao Tac</Translate>
                  </Label>
                  <AvInput id="thao-tac-loaiThaoTac" type="select" className="form-control" name="loaiThaoTac.id">
                    <option value="" key="0" />
                    {loaiThaoTacs
                      ? loaiThaoTacs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/thao-tac" replace color="info">
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
  mauXetNghiems: storeState.mauXetNghiem.entities,
  phongBans: storeState.phongBan.entities,
  loaiThaoTacs: storeState.loaiThaoTac.entities,
  thaoTacEntity: storeState.thaoTac.entity,
  loading: storeState.thaoTac.loading,
  updating: storeState.thaoTac.updating,
  updateSuccess: storeState.thaoTac.updateSuccess
});

const mapDispatchToProps = {
  getMauXetNghiems,
  getPhongBans,
  getLoaiThaoTacs,
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
)(ThaoTacUpdate);
