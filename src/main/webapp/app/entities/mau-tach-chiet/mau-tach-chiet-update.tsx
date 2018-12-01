import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMauXetNghiem } from 'app/shared/model/mau-xet-nghiem.model';
import { getEntities as getMauXetNghiems } from 'app/entities/mau-xet-nghiem/mau-xet-nghiem.reducer';
import { ITachChiet } from 'app/shared/model/tach-chiet.model';
import { getEntities as getTachChiets } from 'app/entities/tach-chiet/tach-chiet.reducer';
import { getEntity, updateEntity, createEntity, reset } from './mau-tach-chiet.reducer';
import { IMauTachChiet } from 'app/shared/model/mau-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMauTachChietUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IMauTachChietUpdateState {
  isNew: boolean;
  mauTachChietId: string;
  tachChietMauId: string;
}

export class MauTachChietUpdate extends React.Component<IMauTachChietUpdateProps, IMauTachChietUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      mauTachChietId: '0',
      tachChietMauId: '0',
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

    this.props.getMauXetNghiems();
    this.props.getTachChiets();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { mauTachChietEntity } = this.props;
      const entity = {
        ...mauTachChietEntity,
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
    this.props.history.push('/entity/mau-tach-chiet');
  };

  render() {
    const { mauTachChietEntity, mauXetNghiems, tachChiets, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.mauTachChiet.home.createOrEditLabel">
              <Translate contentKey="xddtApp.mauTachChiet.home.createOrEditLabel">Create or edit a MauTachChiet</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : mauTachChietEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="mau-tach-chiet-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="dacDiemMauLabel" for="dacDiemMau">
                    <Translate contentKey="xddtApp.mauTachChiet.dacDiemMau">Dac Diem Mau</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-dacDiemMau" type="text" name="dacDiemMau" />
                </AvGroup>
                <AvGroup>
                  <Label id="soLuongSuDungLabel" for="soLuongSuDung">
                    <Translate contentKey="xddtApp.mauTachChiet.soLuongSuDung">So Luong Su Dung</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-soLuongSuDung" type="string" className="form-control" name="soLuongSuDung" />
                </AvGroup>
                <AvGroup>
                  <Label id="nhanXetLabel" for="nhanXet">
                    <Translate contentKey="xddtApp.mauTachChiet.nhanXet">Nhan Xet</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-nhanXet" type="text" name="nhanXet" />
                </AvGroup>
                <AvGroup>
                  <Label id="xuLyBeMatLabel" for="xuLyBeMat">
                    <Translate contentKey="xddtApp.mauTachChiet.xuLyBeMat">Xu Ly Be Mat</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-xuLyBeMat" type="text" name="xuLyBeMat" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongNghienLabel" for="khoiLuongNghien">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongNghien">Khoi Luong Nghien</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongNghien" type="string" className="form-control" name="khoiLuongNghien" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongDeTachLabel" for="khoiLuongDeTach">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongDeTach">Khoi Luong De Tach</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongDeTach" type="string" className="form-control" name="khoiLuongDeTach" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongSauKhuLabel" for="khoiLuongSauKhu">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauKhu">Khoi Luong Sau Khu</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongSauKhu" type="string" className="form-control" name="khoiLuongSauKhu" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongSauLocLabel" for="khoiLuongSauLoc">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauLoc">Khoi Luong Sau Loc</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongSauLoc" type="string" className="form-control" name="khoiLuongSauLoc" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongADNLabel" for="khoiLuongADN">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongADN">Khoi Luong ADN</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongADN" type="string" className="form-control" name="khoiLuongADN" />
                </AvGroup>
                <AvGroup>
                  <Label id="khoiLuongChuaNghienLabel" for="khoiLuongChuaNghien">
                    <Translate contentKey="xddtApp.mauTachChiet.khoiLuongChuaNghien">Khoi Luong Chua Nghien</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-khoiLuongChuaNghien" type="string" className="form-control" name="khoiLuongChuaNghien" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuTachLabel" for="ghiChuTach">
                    <Translate contentKey="xddtApp.mauTachChiet.ghiChuTach">Ghi Chu Tach</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-ghiChuTach" type="text" name="ghiChuTach" />
                </AvGroup>
                <AvGroup>
                  <Label id="ghiChuADNLabel" for="ghiChuADN">
                    <Translate contentKey="xddtApp.mauTachChiet.ghiChuADN">Ghi Chu ADN</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-ghiChuADN" type="text" name="ghiChuADN" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="mau-tach-chiet-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.mauTachChiet.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.mauTachChiet.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.mauTachChiet.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.mauTachChiet.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label id="float1Label" for="float1">
                    <Translate contentKey="xddtApp.mauTachChiet.float1">Float 1</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-float1" type="string" className="form-control" name="float1" />
                </AvGroup>
                <AvGroup>
                  <Label id="float2Label" for="float2">
                    <Translate contentKey="xddtApp.mauTachChiet.float2">Float 2</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-float2" type="string" className="form-control" name="float2" />
                </AvGroup>
                <AvGroup>
                  <Label id="float3Label" for="float3">
                    <Translate contentKey="xddtApp.mauTachChiet.float3">Float 3</Translate>
                  </Label>
                  <AvField id="mau-tach-chiet-float3" type="string" className="form-control" name="float3" />
                </AvGroup>
                <AvGroup>
                  <Label for="mauTachChiet.id">
                    <Translate contentKey="xddtApp.mauTachChiet.mauTachChiet">Mau Tach Chiet</Translate>
                  </Label>
                  <AvInput id="mau-tach-chiet-mauTachChiet" type="select" className="form-control" name="mauTachChiet.id">
                    <option value="" key="0" />
                    {mauXetNghiems
                      ? mauXetNghiems.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="tachChietMau.id">
                    <Translate contentKey="xddtApp.mauTachChiet.tachChietMau">Tach Chiet Mau</Translate>
                  </Label>
                  <AvInput id="mau-tach-chiet-tachChietMau" type="select" className="form-control" name="tachChietMau.id">
                    <option value="" key="0" />
                    {tachChiets
                      ? tachChiets.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/mau-tach-chiet" replace color="info">
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
  mauXetNghiems: storeState.mauXetNghiem.entities,
  tachChiets: storeState.tachChiet.entities,
  mauTachChietEntity: storeState.mauTachChiet.entity,
  loading: storeState.mauTachChiet.loading,
  updating: storeState.mauTachChiet.updating,
  updateSuccess: storeState.mauTachChiet.updateSuccess
});

const mapDispatchToProps = {
  getMauXetNghiems,
  getTachChiets,
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
)(MauTachChietUpdate);
