import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INghiaTrang } from 'app/shared/model/nghia-trang.model';
import { getEntities as getNghiaTrangs } from 'app/entities/nghia-trang/nghia-trang.reducer';
import { getEntity, updateEntity, createEntity, reset } from './thong-tin-mo.reducer';
import { IThongTinMo } from 'app/shared/model/thong-tin-mo.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IThongTinMoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IThongTinMoUpdateState {
  isNew: boolean;
  nghiaTrangId: string;
}

export class ThongTinMoUpdate extends React.Component<IThongTinMoUpdateProps, IThongTinMoUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      nghiaTrangId: '0',
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

    this.props.getNghiaTrangs();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { thongTinMoEntity } = this.props;
      const entity = {
        ...thongTinMoEntity,
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
    this.props.history.push('/entity/thong-tin-mo');
  };

  render() {
    const { thongTinMoEntity, nghiaTrangs, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.thongTinMo.home.createOrEditLabel">
              <Translate contentKey="xddtApp.thongTinMo.home.createOrEditLabel">Create or edit a ThongTinMo</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : thongTinMoEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="thong-tin-mo-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="khuMoLabel" for="khuMo">
                    <Translate contentKey="xddtApp.thongTinMo.khuMo">Khu Mo</Translate>
                  </Label>
                  <AvField id="thong-tin-mo-khuMo" type="text" name="khuMo" />
                </AvGroup>
                <AvGroup>
                  <Label id="loMoLabel" for="loMo">
                    <Translate contentKey="xddtApp.thongTinMo.loMo">Lo Mo</Translate>
                  </Label>
                  <AvField id="thong-tin-mo-loMo" type="text" name="loMo" />
                </AvGroup>
                <AvGroup>
                  <Label id="hangMoLabel" for="hangMo">
                    <Translate contentKey="xddtApp.thongTinMo.hangMo">Hang Mo</Translate>
                  </Label>
                  <AvField id="thong-tin-mo-hangMo" type="string" className="form-control" name="hangMo" />
                </AvGroup>
                <AvGroup>
                  <Label id="soMoLabel" for="soMo">
                    <Translate contentKey="xddtApp.thongTinMo.soMo">So Mo</Translate>
                  </Label>
                  <AvField id="thong-tin-mo-soMo" type="string" className="form-control" name="soMo" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.thongTinMo.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="thong-tin-mo-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="thong-tin-mo-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.thongTinMo.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="nghiaTrang.id">
                    <Translate contentKey="xddtApp.thongTinMo.nghiaTrang">Nghia Trang</Translate>
                  </Label>
                  <AvInput id="thong-tin-mo-nghiaTrang" type="select" className="form-control" name="nghiaTrang.id">
                    <option value="" key="0" />
                    {nghiaTrangs
                      ? nghiaTrangs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/thong-tin-mo" replace color="info">
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
  nghiaTrangs: storeState.nghiaTrang.entities,
  thongTinMoEntity: storeState.thongTinMo.entity,
  loading: storeState.thongTinMo.loading,
  updating: storeState.thongTinMo.updating,
  updateSuccess: storeState.thongTinMo.updateSuccess
});

const mapDispatchToProps = {
  getNghiaTrangs,
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
)(ThongTinMoUpdate);
