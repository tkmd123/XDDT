import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
import { getEntities as getHoSoLietSis } from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
import { getEntities as getHoSoThanNhans } from 'app/entities/ho-so-than-nhan/ho-so-than-nhan.reducer';
import { IQuanHeThanNhan } from 'app/shared/model/quan-he-than-nhan.model';
import { getEntities as getQuanHeThanNhans } from 'app/entities/quan-he-than-nhan/quan-he-than-nhan.reducer';
import { getEntity, updateEntity, createEntity, reset } from './than-nhan-liet-si.reducer';
import { IThanNhanLietSi } from 'app/shared/model/than-nhan-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IThanNhanLietSiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IThanNhanLietSiUpdateState {
  isNew: boolean;
  lietSiId: string;
  thanNhanId: string;
  quanHeThanNhanId: string;
}

export class ThanNhanLietSiUpdate extends React.Component<IThanNhanLietSiUpdateProps, IThanNhanLietSiUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      lietSiId: '0',
      thanNhanId: '0',
      quanHeThanNhanId: '0',
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

    this.props.getHoSoLietSis();
    this.props.getHoSoThanNhans();
    this.props.getQuanHeThanNhans();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { thanNhanLietSiEntity } = this.props;
      const entity = {
        ...thanNhanLietSiEntity,
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
    this.props.history.push('/entity/than-nhan-liet-si');
  };

  render() {
    const { thanNhanLietSiEntity, hoSoLietSis, hoSoThanNhans, quanHeThanNhans, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.thanNhanLietSi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.thanNhanLietSi.home.createOrEditLabel">Create or edit a ThanNhanLietSi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : thanNhanLietSiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="than-nhan-liet-si-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.thanNhanLietSi.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="than-nhan-liet-si-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="than-nhan-liet-si-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.thanNhanLietSi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.thanNhanLietSi.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="than-nhan-liet-si-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.thanNhanLietSi.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="than-nhan-liet-si-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.thanNhanLietSi.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="than-nhan-liet-si-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="lietSi.id">
                    <Translate contentKey="xddtApp.thanNhanLietSi.lietSi">Liet Si</Translate>
                  </Label>
                  <AvInput id="than-nhan-liet-si-lietSi" type="select" className="form-control" name="lietSi.id">
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
                <AvGroup>
                  <Label for="thanNhan.id">
                    <Translate contentKey="xddtApp.thanNhanLietSi.thanNhan">Than Nhan</Translate>
                  </Label>
                  <AvInput id="than-nhan-liet-si-thanNhan" type="select" className="form-control" name="thanNhan.id">
                    <option value="" key="0" />
                    {hoSoThanNhans
                      ? hoSoThanNhans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="quanHeThanNhan.id">
                    <Translate contentKey="xddtApp.thanNhanLietSi.quanHeThanNhan">Quan He Than Nhan</Translate>
                  </Label>
                  <AvInput id="than-nhan-liet-si-quanHeThanNhan" type="select" className="form-control" name="quanHeThanNhan.id">
                    <option value="" key="0" />
                    {quanHeThanNhans
                      ? quanHeThanNhans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/than-nhan-liet-si" replace color="info">
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
  hoSoLietSis: storeState.hoSoLietSi.entities,
  hoSoThanNhans: storeState.hoSoThanNhan.entities,
  quanHeThanNhans: storeState.quanHeThanNhan.entities,
  thanNhanLietSiEntity: storeState.thanNhanLietSi.entity,
  loading: storeState.thanNhanLietSi.loading,
  updating: storeState.thanNhanLietSi.updating,
  updateSuccess: storeState.thanNhanLietSi.updateSuccess
});

const mapDispatchToProps = {
  getHoSoLietSis,
  getHoSoThanNhans,
  getQuanHeThanNhans,
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
)(ThanNhanLietSiUpdate);
