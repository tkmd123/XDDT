import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './tinh-thanh.reducer';
import { ITinhThanh } from 'app/shared/model/tinh-thanh.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITinhThanhUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITinhThanhUpdateState {
  isNew: boolean;
}

export class TinhThanhUpdate extends React.Component<ITinhThanhUpdateProps, ITinhThanhUpdateState> {
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
      const { tinhThanhEntity } = this.props;
      const entity = {
        ...tinhThanhEntity,
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
    this.props.history.push('/entity/tinh-thanh');
  };

  render() {
    const { tinhThanhEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.tinhThanh.home.createOrEditLabel">
              <Translate contentKey="xddtApp.tinhThanh.home.createOrEditLabel">Create or edit a TinhThanh</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : tinhThanhEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="tinh-thanh-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maTinhLabel" for="maTinh">
                    <Translate contentKey="xddtApp.tinhThanh.maTinh">Ma Tinh</Translate>
                  </Label>
                  <AvField id="tinh-thanh-maTinh" type="text" name="maTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenTinhLabel" for="tenTinh">
                    <Translate contentKey="xddtApp.tinhThanh.tenTinh">Ten Tinh</Translate>
                  </Label>
                  <AvField id="tinh-thanh-tenTinh" type="text" name="tenTinh" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.tinhThanh.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="tinh-thanh-moTa" type="text" name="moTa" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/tinh-thanh" replace color="info">
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
  tinhThanhEntity: storeState.tinhThanh.entity,
  loading: storeState.tinhThanh.loading,
  updating: storeState.tinhThanh.updating,
  updateSuccess: storeState.tinhThanh.updateSuccess
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
)(TinhThanhUpdate);
