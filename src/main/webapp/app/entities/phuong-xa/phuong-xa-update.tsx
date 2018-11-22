import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IQuanHuyen } from 'app/shared/model/quan-huyen.model';
import { getEntities as getQuanHuyens } from 'app/entities/quan-huyen/quan-huyen.reducer';
import { getEntity, updateEntity, createEntity, reset } from './phuong-xa.reducer';
import { IPhuongXa } from 'app/shared/model/phuong-xa.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPhuongXaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPhuongXaUpdateState {
  isNew: boolean;
  quanHuyenId: string;
}

export class PhuongXaUpdate extends React.Component<IPhuongXaUpdateProps, IPhuongXaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      quanHuyenId: '0',
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

    this.props.getQuanHuyens();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { phuongXaEntity } = this.props;
      const entity = {
        ...phuongXaEntity,
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
    this.props.history.push('/entity/phuong-xa');
  };

  render() {
    const { phuongXaEntity, quanHuyens, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.phuongXa.home.createOrEditLabel">
              <Translate contentKey="xddtApp.phuongXa.home.createOrEditLabel">Create or edit a PhuongXa</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : phuongXaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="phuong-xa-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maXaLabel" for="maXa">
                    <Translate contentKey="xddtApp.phuongXa.maXa">Ma Xa</Translate>
                  </Label>
                  <AvField id="phuong-xa-maXa" type="text" name="maXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenXaLabel" for="tenXa">
                    <Translate contentKey="xddtApp.phuongXa.tenXa">Ten Xa</Translate>
                  </Label>
                  <AvField id="phuong-xa-tenXa" type="text" name="tenXa" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.phuongXa.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="phuong-xa-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label for="quanHuyen.id">
                    <Translate contentKey="xddtApp.phuongXa.quanHuyen">Quan Huyen</Translate>
                  </Label>
                  <AvInput id="phuong-xa-quanHuyen" type="select" className="form-control" name="quanHuyen.id">
                    <option value="" key="0" />
                    {quanHuyens
                      ? quanHuyens.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/phuong-xa" replace color="info">
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
  quanHuyens: storeState.quanHuyen.entities,
  phuongXaEntity: storeState.phuongXa.entity,
  loading: storeState.phuongXa.loading,
  updating: storeState.phuongXa.updating,
  updateSuccess: storeState.phuongXa.updateSuccess
});

const mapDispatchToProps = {
  getQuanHuyens,
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
)(PhuongXaUpdate);
