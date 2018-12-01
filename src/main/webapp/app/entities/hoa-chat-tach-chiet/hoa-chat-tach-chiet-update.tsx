import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHoaChat } from 'app/shared/model/hoa-chat.model';
import { getEntities as getHoaChats } from 'app/entities/hoa-chat/hoa-chat.reducer';
import { ITachChiet } from 'app/shared/model/tach-chiet.model';
import { getEntities as getTachChiets } from 'app/entities/tach-chiet/tach-chiet.reducer';
import { getEntity, updateEntity, createEntity, reset } from './hoa-chat-tach-chiet.reducer';
import { IHoaChatTachChiet } from 'app/shared/model/hoa-chat-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoaChatTachChietUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoaChatTachChietUpdateState {
  isNew: boolean;
  hoaChatId: string;
  tachChietId: string;
}

export class HoaChatTachChietUpdate extends React.Component<IHoaChatTachChietUpdateProps, IHoaChatTachChietUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      hoaChatId: '0',
      tachChietId: '0',
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

    this.props.getHoaChats();
    this.props.getTachChiets();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { hoaChatTachChietEntity } = this.props;
      const entity = {
        ...hoaChatTachChietEntity,
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
    this.props.history.push('/entity/hoa-chat-tach-chiet');
  };

  render() {
    const { hoaChatTachChietEntity, hoaChats, tachChiets, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoaChatTachChiet.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoaChatTachChiet.home.createOrEditLabel">Create or edit a HoaChatTachChiet</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoaChatTachChietEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hoa-chat-tach-chiet-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="soLuongLabel" for="soLuong">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.soLuong">So Luong</Translate>
                  </Label>
                  <AvField id="hoa-chat-tach-chiet-soLuong" type="string" className="form-control" name="soLuong" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="hoa-chat-tach-chiet-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="hoa-chat-tach-chiet-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoaChatTachChiet.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="hoa-chat-tach-chiet-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="hoa-chat-tach-chiet-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="hoa-chat-tach-chiet-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="hoaChat.id">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.hoaChat">Hoa Chat</Translate>
                  </Label>
                  <AvInput id="hoa-chat-tach-chiet-hoaChat" type="select" className="form-control" name="hoaChat.id">
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
                <AvGroup>
                  <Label for="tachChiet.id">
                    <Translate contentKey="xddtApp.hoaChatTachChiet.tachChiet">Tach Chiet</Translate>
                  </Label>
                  <AvInput id="hoa-chat-tach-chiet-tachChiet" type="select" className="form-control" name="tachChiet.id">
                    <option value="" key="0" />
                    {tachChiets
                      ? tachChiets.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hoa-chat-tach-chiet" replace color="info">
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
  hoaChats: storeState.hoaChat.entities,
  tachChiets: storeState.tachChiet.entities,
  hoaChatTachChietEntity: storeState.hoaChatTachChiet.entity,
  loading: storeState.hoaChatTachChiet.loading,
  updating: storeState.hoaChatTachChiet.updating,
  updateSuccess: storeState.hoaChatTachChiet.updateSuccess
});

const mapDispatchToProps = {
  getHoaChats,
  getTachChiets,
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
)(HoaChatTachChietUpdate);
