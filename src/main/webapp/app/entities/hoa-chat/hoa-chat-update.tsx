import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hoa-chat.reducer';
import { IHoaChat } from 'app/shared/model/hoa-chat.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoaChatUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoaChatUpdateState {
  isNew: boolean;
}

export class HoaChatUpdate extends React.Component<IHoaChatUpdateProps, IHoaChatUpdateState> {
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
      const { hoaChatEntity } = this.props;
      const entity = {
        ...hoaChatEntity,
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
    this.props.history.push('/entity/hoa-chat');
  };

  render() {
    const { hoaChatEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoaChat.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoaChat.home.createOrEditLabel">Create or edit a HoaChat</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoaChatEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hoa-chat-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maHoaChatLabel" for="maHoaChat">
                    <Translate contentKey="xddtApp.hoaChat.maHoaChat">Ma Hoa Chat</Translate>
                  </Label>
                  <AvField id="hoa-chat-maHoaChat" type="text" name="maHoaChat" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenHoaChatLabel" for="tenHoaChat">
                    <Translate contentKey="xddtApp.hoaChat.tenHoaChat">Ten Hoa Chat</Translate>
                  </Label>
                  <AvField id="hoa-chat-tenHoaChat" type="text" name="tenHoaChat" />
                </AvGroup>
                <AvGroup>
                  <Label id="hangHoaChatLabel" for="hangHoaChat">
                    <Translate contentKey="xddtApp.hoaChat.hangHoaChat">Hang Hoa Chat</Translate>
                  </Label>
                  <AvField id="hoa-chat-hangHoaChat" type="text" name="hangHoaChat" />
                </AvGroup>
                <AvGroup>
                  <Label id="donViTinhLabel" for="donViTinh">
                    <Translate contentKey="xddtApp.hoaChat.donViTinh">Don Vi Tinh</Translate>
                  </Label>
                  <AvField id="hoa-chat-donViTinh" type="text" name="donViTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.hoaChat.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="hoa-chat-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="hoa-chat-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoaChat.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.hoaChat.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="hoa-chat-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.hoaChat.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="hoa-chat-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.hoaChat.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="hoa-chat-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hoa-chat" replace color="info">
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
  hoaChatEntity: storeState.hoaChat.entity,
  loading: storeState.hoaChat.loading,
  updating: storeState.hoaChat.updating,
  updateSuccess: storeState.hoaChat.updateSuccess
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
)(HoaChatUpdate);
