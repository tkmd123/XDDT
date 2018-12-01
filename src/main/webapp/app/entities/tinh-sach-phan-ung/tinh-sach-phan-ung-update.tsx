import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITinhSach } from 'app/shared/model/tinh-sach.model';
import { getEntities as getTinhSaches } from 'app/entities/tinh-sach/tinh-sach.reducer';
import { IHoaChat } from 'app/shared/model/hoa-chat.model';
import { getEntities as getHoaChats } from 'app/entities/hoa-chat/hoa-chat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './tinh-sach-phan-ung.reducer';
import { ITinhSachPhanUng } from 'app/shared/model/tinh-sach-phan-ung.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITinhSachPhanUngUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITinhSachPhanUngUpdateState {
  isNew: boolean;
  tinhSachId: string;
  hoaChatTinhSachId: string;
}

export class TinhSachPhanUngUpdate extends React.Component<ITinhSachPhanUngUpdateProps, ITinhSachPhanUngUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      tinhSachId: '0',
      hoaChatTinhSachId: '0',
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

    this.props.getTinhSaches();
    this.props.getHoaChats();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { tinhSachPhanUngEntity } = this.props;
      const entity = {
        ...tinhSachPhanUngEntity,
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
    this.props.history.push('/entity/tinh-sach-phan-ung');
  };

  render() {
    const { tinhSachPhanUngEntity, tinhSaches, hoaChats, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.tinhSachPhanUng.home.createOrEditLabel">
              <Translate contentKey="xddtApp.tinhSachPhanUng.home.createOrEditLabel">Create or edit a TinhSachPhanUng</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tinhSachPhanUngEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="tinh-sach-phan-ung-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dungTichSuDungLabel" for="dungTichSuDung">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.dungTichSuDung">Dung Tich Su Dung</Translate>
                  </Label>
                  <AvField id="tinh-sach-phan-ung-dungTichSuDung" type="string" className="form-control" name="dungTichSuDung" />
                </AvGroup>
                <AvGroup>
                  <Label id="chuTrinhNhietDoLabel" for="chuTrinhNhietDo">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.chuTrinhNhietDo">Chu Trinh Nhiet Do</Translate>
                  </Label>
                  <AvField id="tinh-sach-phan-ung-chuTrinhNhietDo" type="text" name="chuTrinhNhietDo" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="tinh-sach-phan-ung-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.tinhSachPhanUng.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="tinh-sach-phan-ung-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="tinh-sach-phan-ung-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="tinh-sach-phan-ung-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="tinhSach.id">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.tinhSach">Tinh Sach</Translate>
                  </Label>
                  <AvInput id="tinh-sach-phan-ung-tinhSach" type="select" className="form-control" name="tinhSach.id">
                    <option value="" key="0" />
                    {tinhSaches
                      ? tinhSaches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="hoaChatTinhSach.id">
                    <Translate contentKey="xddtApp.tinhSachPhanUng.hoaChatTinhSach">Hoa Chat Tinh Sach</Translate>
                  </Label>
                  <AvInput id="tinh-sach-phan-ung-hoaChatTinhSach" type="select" className="form-control" name="hoaChatTinhSach.id">
                    <option value="" key="0" />
                    {hoaChats
                      ? hoaChats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/tinh-sach-phan-ung" replace color="info">
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
  tinhSaches: storeState.tinhSach.entities,
  hoaChats: storeState.hoaChat.entities,
  tinhSachPhanUngEntity: storeState.tinhSachPhanUng.entity,
  loading: storeState.tinhSachPhanUng.loading,
  updating: storeState.tinhSachPhanUng.updating,
  updateSuccess: storeState.tinhSachPhanUng.updateSuccess
});

const mapDispatchToProps = {
  getTinhSaches,
  getHoaChats,
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
)(TinhSachPhanUngUpdate);
