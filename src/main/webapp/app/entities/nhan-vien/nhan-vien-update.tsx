import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IPhongBan } from 'app/shared/model/phong-ban.model';
import { getEntities as getPhongBans } from 'app/entities/phong-ban/phong-ban.reducer';
import { getEntity, updateEntity, createEntity, reset } from './nhan-vien.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INhanVienUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INhanVienUpdateState {
  isNew: boolean;
  userNhanVienId: string;
  phongbanId: string;
}

export class NhanVienUpdate extends React.Component<INhanVienUpdateProps, INhanVienUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      userNhanVienId: '0',
      phongbanId: '0',
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

    this.props.getUsers();
    this.props.getPhongBans();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { nhanVienEntity } = this.props;
      const entity = {
        ...nhanVienEntity,
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
    this.props.history.push('/entity/nhan-vien');
  };

  render() {
    const { nhanVienEntity, users, phongBans, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.nhanVien.home.createOrEditLabel">
              <Translate contentKey="xddtApp.nhanVien.home.createOrEditLabel">Create or edit a NhanVien</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nhanVienEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nhan-vien-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maNhanVienLabel" for="maNhanVien">
                    <Translate contentKey="xddtApp.nhanVien.maNhanVien">Ma Nhan Vien</Translate>
                  </Label>
                  <AvField id="nhan-vien-maNhanVien" type="text" name="maNhanVien" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenNhanVienLabel" for="tenNhanVien">
                    <Translate contentKey="xddtApp.nhanVien.tenNhanVien">Ten Nhan Vien</Translate>
                  </Label>
                  <AvField id="nhan-vien-tenNhanVien" type="text" name="tenNhanVien" />
                </AvGroup>
                <AvGroup>
                  <Label id="soDienThoaiLabel" for="soDienThoai">
                    <Translate contentKey="xddtApp.nhanVien.soDienThoai">So Dien Thoai</Translate>
                  </Label>
                  <AvField id="nhan-vien-soDienThoai" type="text" name="soDienThoai" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="xddtApp.nhanVien.email">Email</Translate>
                  </Label>
                  <AvField id="nhan-vien-email" type="text" name="email" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.nhanVien.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="nhan-vien-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuLabel" for="ghiChu">
                    <Translate contentKey="xddtApp.nhanVien.ghiChu">Ghi Chu</Translate>
                  </Label>
                  <AvField id="nhan-vien-ghiChu" type="text" name="ghiChu" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="nhan-vien-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.nhanVien.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.nhanVien.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="nhan-vien-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.nhanVien.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="nhan-vien-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.nhanVien.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="nhan-vien-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="userNhanVien.id">
                    <Translate contentKey="xddtApp.nhanVien.userNhanVien">User Nhan Vien</Translate>
                  </Label>
                  <AvInput id="nhan-vien-userNhanVien" type="select" className="form-control" name="userNhanVien.id">
                    <option value="" key="0" />
                    {users
                      ? users.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="phongban.id">
                    <Translate contentKey="xddtApp.nhanVien.phongban">Phongban</Translate>
                  </Label>
                  <AvInput id="nhan-vien-phongban" type="select" className="form-control" name="phongban.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/nhan-vien" replace color="info">
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
  users: storeState.userManagement.users,
  phongBans: storeState.phongBan.entities,
  nhanVienEntity: storeState.nhanVien.entity,
  loading: storeState.nhanVien.loading,
  updating: storeState.nhanVien.updating,
  updateSuccess: storeState.nhanVien.updateSuccess
});

const mapDispatchToProps = {
  getUsers,
  getPhongBans,
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
)(NhanVienUpdate);
