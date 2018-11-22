import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHaiCotLietSi } from 'app/shared/model/hai-cot-liet-si.model';
import { getEntities as getHaiCotLietSis } from 'app/entities/hai-cot-liet-si/hai-cot-liet-si.reducer';
import { ILoaiHinhThaiHaiCot } from 'app/shared/model/loai-hinh-thai-hai-cot.model';
import { getEntities as getLoaiHinhThaiHaiCots } from 'app/entities/loai-hinh-thai-hai-cot/loai-hinh-thai-hai-cot.reducer';
import { getEntity, updateEntity, createEntity, reset } from './hinh-thai-hai-cot.reducer';
import { IHinhThaiHaiCot } from 'app/shared/model/hinh-thai-hai-cot.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHinhThaiHaiCotUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHinhThaiHaiCotUpdateState {
  isNew: boolean;
  haiCotLietSiId: string;
  loaiHinhThaiHaiCotId: string;
}

export class HinhThaiHaiCotUpdate extends React.Component<IHinhThaiHaiCotUpdateProps, IHinhThaiHaiCotUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      haiCotLietSiId: '0',
      loaiHinhThaiHaiCotId: '0',
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

    this.props.getHaiCotLietSis();
    this.props.getLoaiHinhThaiHaiCots();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { hinhThaiHaiCotEntity } = this.props;
      const entity = {
        ...hinhThaiHaiCotEntity,
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
    this.props.history.push('/entity/hinh-thai-hai-cot');
  };

  render() {
    const { hinhThaiHaiCotEntity, haiCotLietSis, loaiHinhThaiHaiCots, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.hinhThaiHaiCot.home.createOrEditLabel">
              <Translate contentKey="xddtApp.hinhThaiHaiCot.home.createOrEditLabel">Create or edit a HinhThaiHaiCot</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : hinhThaiHaiCotEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hinh-thai-hai-cot-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="giaTriLabel" for="giaTri">
                    <Translate contentKey="xddtApp.hinhThaiHaiCot.giaTri">Gia Tri</Translate>
                  </Label>
                  <AvField id="hinh-thai-hai-cot-giaTri" type="text" name="giaTri" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.hinhThaiHaiCot.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="hinh-thai-hai-cot-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="hinh-thai-hai-cot-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.hinhThaiHaiCot.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="haiCotLietSi.id">
                    <Translate contentKey="xddtApp.hinhThaiHaiCot.haiCotLietSi">Hai Cot Liet Si</Translate>
                  </Label>
                  <AvInput id="hinh-thai-hai-cot-haiCotLietSi" type="select" className="form-control" name="haiCotLietSi.id">
                    <option value="" key="0" />
                    {haiCotLietSis
                      ? haiCotLietSis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="loaiHinhThaiHaiCot.id">
                    <Translate contentKey="xddtApp.hinhThaiHaiCot.loaiHinhThaiHaiCot">Loai Hinh Thai Hai Cot</Translate>
                  </Label>
                  <AvInput id="hinh-thai-hai-cot-loaiHinhThaiHaiCot" type="select" className="form-control" name="loaiHinhThaiHaiCot.id">
                    <option value="" key="0" />
                    {loaiHinhThaiHaiCots
                      ? loaiHinhThaiHaiCots.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hinh-thai-hai-cot" replace color="info">
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
  haiCotLietSis: storeState.haiCotLietSi.entities,
  loaiHinhThaiHaiCots: storeState.loaiHinhThaiHaiCot.entities,
  hinhThaiHaiCotEntity: storeState.hinhThaiHaiCot.entity,
  loading: storeState.hinhThaiHaiCot.loading,
  updating: storeState.hinhThaiHaiCot.updating,
  updateSuccess: storeState.hinhThaiHaiCot.updateSuccess
});

const mapDispatchToProps = {
  getHaiCotLietSis,
  getLoaiHinhThaiHaiCots,
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
)(HinhThaiHaiCotUpdate);
