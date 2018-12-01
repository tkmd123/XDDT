import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hoa-chat-mac-dinh.reducer';
import { IHoaChatMacDinh } from 'app/shared/model/hoa-chat-mac-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHoaChatMacDinhUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHoaChatMacDinhUpdateState {
  isNew: boolean;
}

export class HoaChatMacDinhUpdate extends React.Component<IHoaChatMacDinhUpdateProps, IHoaChatMacDinhUpdateState> {
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
      const { hoaChatMacDinhEntity } = this.props;
      const entity = {
        ...hoaChatMacDinhEntity,
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
    this.props.history.push('/entity/hoa-chat-mac-dinh');
  };

  render() {
    const { hoaChatMacDinhEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hoaChatMacDinh.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hoaChatMacDinh.home.createOrEditLabel">Create or edit a HoaChatMacDinh</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hoaChatMacDinhEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hoa-chat-mac-dinh-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="loaiThaoTacLabel" for="loaiThaoTac">
                    <Translate contentKey="xddtApp.hoaChatMacDinh.loaiThaoTac">Loai Thao Tac</Translate>
                  </Label>
                  <AvField id="hoa-chat-mac-dinh-loaiThaoTac" type="text" name="loaiThaoTac" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDefaultLabel" check>
                    <AvInput id="hoa-chat-mac-dinh-isDefault" type="checkbox" className="form-control" name="isDefault" />
                    <Translate contentKey="xddtApp.hoaChatMacDinh.isDefault">Is Default</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="hoa-chat-mac-dinh-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hoaChatMacDinh.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.hoaChatMacDinh.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="hoa-chat-mac-dinh-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.hoaChatMacDinh.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="hoa-chat-mac-dinh-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.hoaChatMacDinh.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="hoa-chat-mac-dinh-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hoa-chat-mac-dinh" replace color="info">
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
  hoaChatMacDinhEntity: storeState.hoaChatMacDinh.entity,
  loading: storeState.hoaChatMacDinh.loading,
  updating: storeState.hoaChatMacDinh.updating,
  updateSuccess: storeState.hoaChatMacDinh.updateSuccess
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
)(HoaChatMacDinhUpdate);
