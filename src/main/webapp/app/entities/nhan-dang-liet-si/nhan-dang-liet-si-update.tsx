import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INhanDang } from 'app/shared/model/nhan-dang.model';
import { getEntities as getNhanDangs } from 'app/entities/nhan-dang/nhan-dang.reducer';
import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
import { getEntities as getHoSoLietSis } from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
import { getEntity, updateEntity, createEntity, reset } from './nhan-dang-liet-si.reducer';
import { INhanDangLietSi } from 'app/shared/model/nhan-dang-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INhanDangLietSiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface INhanDangLietSiUpdateState {
  isNew: boolean;
  nhanDangId: string;
  lietSiId: string;
}

export class NhanDangLietSiUpdate extends React.Component<INhanDangLietSiUpdateProps, INhanDangLietSiUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      nhanDangId: '0',
      lietSiId: '0',
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

    this.props.getNhanDangs();
    this.props.getHoSoLietSis();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { nhanDangLietSiEntity } = this.props;
      const entity = {
        ...nhanDangLietSiEntity,
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
    this.props.history.push('/entity/nhan-dang-liet-si');
  };

  render() {
    const { nhanDangLietSiEntity, nhanDangs, hoSoLietSis, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.nhanDangLietSi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.nhanDangLietSi.home.createOrEditLabel">Create or edit a NhanDangLietSi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : nhanDangLietSiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="nhan-dang-liet-si-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="giaTriLabel" for="giaTri">
                    <Translate contentKey="xddtApp.nhanDangLietSi.giaTri">Gia Tri</Translate>
                  </Label>
                  <AvField id="nhan-dang-liet-si-giaTri" type="text" name="giaTri" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.nhanDangLietSi.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="nhan-dang-liet-si-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="nhan-dang-liet-si-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.nhanDangLietSi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="nhanDang.id">
                    <Translate contentKey="xddtApp.nhanDangLietSi.nhanDang">Nhan Dang</Translate>
                  </Label>
                  <AvInput id="nhan-dang-liet-si-nhanDang" type="select" className="form-control" name="nhanDang.id">
                    <option value="" key="0" />
                    {nhanDangs
                      ? nhanDangs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="lietSi.id">
                    <Translate contentKey="xddtApp.nhanDangLietSi.lietSi">Liet Si</Translate>
                  </Label>
                  <AvInput id="nhan-dang-liet-si-lietSi" type="select" className="form-control" name="lietSi.id">
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
                <Button tag={Link} id="cancel-save" to="/entity/nhan-dang-liet-si" replace color="info">
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
  nhanDangs: storeState.nhanDang.entities,
  hoSoLietSis: storeState.hoSoLietSi.entities,
  nhanDangLietSiEntity: storeState.nhanDangLietSi.entity,
  loading: storeState.nhanDangLietSi.loading,
  updating: storeState.nhanDangLietSi.updating,
  updateSuccess: storeState.nhanDangLietSi.updateSuccess
});

const mapDispatchToProps = {
  getNhanDangs,
  getHoSoLietSis,
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
)(NhanDangLietSiUpdate);
