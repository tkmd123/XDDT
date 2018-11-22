import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './nhan-dang.reducer';
import { INhanDang } from 'app/shared/model/nhan-dang.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INhanDangUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INhanDangUpdateState {
  isNew: boolean;
}

export class NhanDangUpdate extends React.Component<INhanDangUpdateProps, INhanDangUpdateState> {
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
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { nhanDangEntity } = this.props;
      const entity = {
        ...nhanDangEntity,
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
    this.props.history.push('/entity/nhan-dang');
  };

  render() {
    const { nhanDangEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.nhanDang.home.createOrEditLabel">
              <Translate contentKey="xddtApp.nhanDang.home.createOrEditLabel">Create or edit a NhanDang</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nhanDangEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nhan-dang-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maNhanDangLabel" for="maNhanDang">
                    <Translate contentKey="xddtApp.nhanDang.maNhanDang">Ma Nhan Dang</Translate>
                  </Label>
                  <AvField id="nhan-dang-maNhanDang" type="text" name="maNhanDang" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenNhanDangLabel" for="tenNhanDang">
                    <Translate contentKey="xddtApp.nhanDang.tenNhanDang">Ten Nhan Dang</Translate>
                  </Label>
                  <AvField id="nhan-dang-tenNhanDang" type="text" name="tenNhanDang" />
                </AvGroup>
                <AvGroup>
                  <Label id="donViTinhLabel" for="donViTinh">
                    <Translate contentKey="xddtApp.nhanDang.donViTinh">Don Vi Tinh</Translate>
                  </Label>
                  <AvField id="nhan-dang-donViTinh" type="text" name="donViTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.nhanDang.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="nhan-dang-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="nhan-dang-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.nhanDang.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/nhan-dang" replace color="info">
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
  nhanDangEntity: storeState.nhanDang.entity,
  loading: storeState.nhanDang.loading,
  updating: storeState.nhanDang.updating,
  updateSuccess: storeState.nhanDang.updateSuccess
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
)(NhanDangUpdate);
