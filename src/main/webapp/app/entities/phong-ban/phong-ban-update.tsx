import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITrungTam } from 'app/shared/model/trung-tam.model';
import { getEntities as getTrungTams } from 'app/entities/trung-tam/trung-tam.reducer';
import { getEntity, updateEntity, createEntity, reset } from './phong-ban.reducer';
import { IPhongBan } from 'app/shared/model/phong-ban.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPhongBanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPhongBanUpdateState {
  isNew: boolean;
  trungtamId: string;
}

export class PhongBanUpdate extends React.Component<IPhongBanUpdateProps, IPhongBanUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      trungtamId: '0',
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

    this.props.getTrungTams();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { phongBanEntity } = this.props;
      const entity = {
        ...phongBanEntity,
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
    this.props.history.push('/entity/phong-ban');
  };

  render() {
    const { phongBanEntity, trungTams, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.phongBan.home.createOrEditLabel">
              <Translate contentKey="xddtApp.phongBan.home.createOrEditLabel">Create or edit a PhongBan</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : phongBanEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="phong-ban-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maPhongBanLabel" for="maPhongBan">
                    <Translate contentKey="xddtApp.phongBan.maPhongBan">Ma Phong Ban</Translate>
                  </Label>
                  <AvField id="phong-ban-maPhongBan" type="text" name="maPhongBan" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenPhongBanLabel" for="tenPhongBan">
                    <Translate contentKey="xddtApp.phongBan.tenPhongBan">Ten Phong Ban</Translate>
                  </Label>
                  <AvField id="phong-ban-tenPhongBan" type="text" name="tenPhongBan" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.phongBan.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="phong-ban-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isActiveLabel" check>
                    <AvInput id="phong-ban-isActive" type="checkbox" className="form-control" name="isActive" />
                    <Translate contentKey="xddtApp.phongBan.isActive">Is Active</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="phong-ban-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.phongBan.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.phongBan.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="phong-ban-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.phongBan.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="phong-ban-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.phongBan.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="phong-ban-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="trungtam.id">
                    <Translate contentKey="xddtApp.phongBan.trungtam">Trungtam</Translate>
                  </Label>
                  <AvInput id="phong-ban-trungtam" type="select" className="form-control" name="trungtam.id">
                    <option value="" key="0" />
                    {trungTams
                      ? trungTams.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/phong-ban" replace color="info">
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
  trungTams: storeState.trungTam.entities,
  phongBanEntity: storeState.phongBan.entity,
  loading: storeState.phongBan.loading,
  updating: storeState.phongBan.updating,
  updateSuccess: storeState.phongBan.updateSuccess
});

const mapDispatchToProps = {
  getTrungTams,
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
)(PhongBanUpdate);
