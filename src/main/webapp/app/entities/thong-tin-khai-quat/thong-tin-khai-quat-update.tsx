import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IThongTinMo } from 'app/shared/model/thong-tin-mo.model';
import { getEntities as getThongTinMos } from 'app/entities/thong-tin-mo/thong-tin-mo.reducer';
import { getEntity, updateEntity, createEntity, reset } from './thong-tin-khai-quat.reducer';
import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IThongTinKhaiQuatUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IThongTinKhaiQuatUpdateState {
  isNew: boolean;
  thongTinMoId: string;
}

export class ThongTinKhaiQuatUpdate extends React.Component<IThongTinKhaiQuatUpdateProps, IThongTinKhaiQuatUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      thongTinMoId: '0',
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

    this.props.getThongTinMos();
  }

  saveEntity = (event, errors, values) => {
    values.thoiGianKhaiQuat = new Date(values.thoiGianKhaiQuat);

    if (errors.length === 0) {
      const { thongTinKhaiQuatEntity } = this.props;
      const entity = {
        ...thongTinKhaiQuatEntity,
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
    this.props.history.push('/entity/thong-tin-khai-quat');
  };

  render() {
    const { thongTinKhaiQuatEntity, thongTinMos, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.thongTinKhaiQuat.home.createOrEditLabel">
              <Translate contentKey="xddtApp.thongTinKhaiQuat.home.createOrEditLabel">Create or edit a ThongTinKhaiQuat</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : thongTinKhaiQuatEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="thong-tin-khai-quat-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maKhaiQuatLabel" for="maKhaiQuat">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.maKhaiQuat">Ma Khai Quat</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-maKhaiQuat" type="text" name="maKhaiQuat" />
                </AvGroup>
                <AvGroup>
                  <Label id="nguoiKhaiQuatLabel" for="nguoiKhaiQuat">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.nguoiKhaiQuat">Nguoi Khai Quat</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-nguoiKhaiQuat" type="text" name="nguoiKhaiQuat" />
                </AvGroup>
                <AvGroup>
                  <Label id="donViKhaiQuatLabel" for="donViKhaiQuat">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.donViKhaiQuat">Don Vi Khai Quat</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-donViKhaiQuat" type="text" name="donViKhaiQuat" />
                </AvGroup>
                <AvGroup>
                  <Label id="thoiGianKhaiQuatLabel" for="thoiGianKhaiQuat">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.thoiGianKhaiQuat">Thoi Gian Khai Quat</Translate>
                  </Label>
                  <AvInput
                    id="thong-tin-khai-quat-thoiGianKhaiQuat"
                    type="datetime-local"
                    className="form-control"
                    name="thoiGianKhaiQuat"
                    value={isNew ? null : convertDateTimeFromServer(this.props.thongTinKhaiQuatEntity.thoiGianKhaiQuat)}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="coHaiCotLabel" check>
                    <AvInput id="thong-tin-khai-quat-coHaiCot" type="checkbox" className="form-control" name="coHaiCot" />
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.coHaiCot">Co Hai Cot</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="soLuongHaiCotLabel" for="soLuongHaiCot">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.soLuongHaiCot">So Luong Hai Cot</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-soLuongHaiCot" type="string" className="form-control" name="soLuongHaiCot" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="thong-tin-khai-quat-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="thong-tin-khai-quat-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="thongTinMo.id">
                    <Translate contentKey="xddtApp.thongTinKhaiQuat.thongTinMo">Thong Tin Mo</Translate>
                  </Label>
                  <AvInput id="thong-tin-khai-quat-thongTinMo" type="select" className="form-control" name="thongTinMo.id">
                    <option value="" key="0" />
                    {thongTinMos
                      ? thongTinMos.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/thong-tin-khai-quat" replace color="info">
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
  thongTinMos: storeState.thongTinMo.entities,
  thongTinKhaiQuatEntity: storeState.thongTinKhaiQuat.entity,
  loading: storeState.thongTinKhaiQuat.loading,
  updating: storeState.thongTinKhaiQuat.updating,
  updateSuccess: storeState.thongTinKhaiQuat.updateSuccess
});

const mapDispatchToProps = {
  getThongTinMos,
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
)(ThongTinKhaiQuatUpdate);
