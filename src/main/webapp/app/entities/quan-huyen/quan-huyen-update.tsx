import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITinhThanh } from 'app/shared/model/tinh-thanh.model';
import { getEntities as getTinhThanhs } from 'app/entities/tinh-thanh/tinh-thanh.reducer';
import { getEntity, updateEntity, createEntity, reset } from './quan-huyen.reducer';
import { IQuanHuyen } from 'app/shared/model/quan-huyen.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IQuanHuyenUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IQuanHuyenUpdateState {
  isNew: boolean;
  tinhThanhId: string;
}

export class QuanHuyenUpdate extends React.Component<IQuanHuyenUpdateProps, IQuanHuyenUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      tinhThanhId: '0',
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

    this.props.getTinhThanhs();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { quanHuyenEntity } = this.props;
      const entity = {
        ...quanHuyenEntity,
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
    this.props.history.push('/entity/quan-huyen');
  };

  render() {
    const { quanHuyenEntity, tinhThanhs, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.quanHuyen.home.createOrEditLabel">
              <Translate contentKey="xddtApp.quanHuyen.home.createOrEditLabel">Create or edit a QuanHuyen</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : quanHuyenEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="quan-huyen-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maHuyenLabel" for="maHuyen">
                    <Translate contentKey="xddtApp.quanHuyen.maHuyen">Ma Huyen</Translate>
                  </Label>
                  <AvField id="quan-huyen-maHuyen" type="text" name="maHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenHuyenLabel" for="tenHuyen">
                    <Translate contentKey="xddtApp.quanHuyen.tenHuyen">Ten Huyen</Translate>
                  </Label>
                  <AvField id="quan-huyen-tenHuyen" type="text" name="tenHuyen" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.quanHuyen.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="quan-huyen-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label for="tinhThanh.id">
                    <Translate contentKey="xddtApp.quanHuyen.tinhThanh">Tinh Thanh</Translate>
                  </Label>
                  <AvInput id="quan-huyen-tinhThanh" type="select" className="form-control" name="tinhThanh.id">
                    <option value="" key="0" />
                    {tinhThanhs
                      ? tinhThanhs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/quan-huyen" replace color="info">
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
  tinhThanhs: storeState.tinhThanh.entities,
  quanHuyenEntity: storeState.quanHuyen.entity,
  loading: storeState.quanHuyen.loading,
  updating: storeState.quanHuyen.updating,
  updateSuccess: storeState.quanHuyen.updateSuccess
});

const mapDispatchToProps = {
  getTinhThanhs,
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
)(QuanHuyenUpdate);
