import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IHoSoLietSi } from 'app/shared/model/ho-so-liet-si.model';
import { getEntities as getHoSoLietSis } from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
import { getEntities as getThongTinKhaiQuats } from 'app/entities/thong-tin-khai-quat/thong-tin-khai-quat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './hai-cot-liet-si.reducer';
import { IHaiCotLietSi } from 'app/shared/model/hai-cot-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHaiCotLietSiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHaiCotLietSiUpdateState {
  isNew: boolean;
  lietSiId: string;
  thongTinKhaiQuatId: string;
}

export class HaiCotLietSiUpdate extends React.Component<IHaiCotLietSiUpdateProps, IHaiCotLietSiUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      lietSiId: '0',
      thongTinKhaiQuatId: '0',
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

    this.props.getHoSoLietSis();
    this.props.getThongTinKhaiQuats();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { haiCotLietSiEntity } = this.props;
      const entity = {
        ...haiCotLietSiEntity,
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
    this.props.history.push('/entity/hai-cot-liet-si');
  };

  render() {
    const { haiCotLietSiEntity, hoSoLietSis, thongTinKhaiQuats, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.haiCotLietSi.home.createOrEditLabel">
              <Translate contentKey="xddtApp.haiCotLietSi.home.createOrEditLabel">Create or edit a HaiCotLietSi</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : haiCotLietSiEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="hai-cot-liet-si-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maHaiCotLabel" for="maHaiCot">
                    <Translate contentKey="xddtApp.haiCotLietSi.maHaiCot">Ma Hai Cot</Translate>
                  </Label>
                  <AvField id="hai-cot-liet-si-maHaiCot" type="text" name="maHaiCot" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.haiCotLietSi.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="hai-cot-liet-si-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="hai-cot-liet-si-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.haiCotLietSi.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.haiCotLietSi.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="hai-cot-liet-si-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.haiCotLietSi.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="hai-cot-liet-si-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.haiCotLietSi.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="hai-cot-liet-si-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="lietSi.id">
                    <Translate contentKey="xddtApp.haiCotLietSi.lietSi">Liet Si</Translate>
                  </Label>
                  <AvInput id="hai-cot-liet-si-lietSi" type="select" className="form-control" name="lietSi.id">
                    <option value="" key="0" />
                    {hoSoLietSis
                      ? hoSoLietSis.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="thongTinKhaiQuat.id">
                    <Translate contentKey="xddtApp.haiCotLietSi.thongTinKhaiQuat">Thong Tin Khai Quat</Translate>
                  </Label>
                  <AvInput id="hai-cot-liet-si-thongTinKhaiQuat" type="select" className="form-control" name="thongTinKhaiQuat.id">
                    <option value="" key="0" />
                    {thongTinKhaiQuats
                      ? thongTinKhaiQuats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/hai-cot-liet-si" replace color="info">
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
  hoSoLietSis: storeState.hoSoLietSi.entities,
  thongTinKhaiQuats: storeState.thongTinKhaiQuat.entities,
  haiCotLietSiEntity: storeState.haiCotLietSi.entity,
  loading: storeState.haiCotLietSi.loading,
  updating: storeState.haiCotLietSi.updating,
  updateSuccess: storeState.haiCotLietSi.updateSuccess
});

const mapDispatchToProps = {
  getHoSoLietSis,
  getThongTinKhaiQuats,
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
)(HaiCotLietSiUpdate);
