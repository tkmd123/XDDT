import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IVungADN } from 'app/shared/model/vung-adn.model';
import { getEntities as getVungAdns } from 'app/entities/vung-adn/vung-adn.reducer';
import { IThongTinADN } from 'app/shared/model/thong-tin-adn.model';
import { getEntities as getThongTinAdns } from 'app/entities/thong-tin-adn/thong-tin-adn.reducer';
import { getEntity, updateEntity, createEntity, reset } from './diem-dot-bien.reducer';
import { IDiemDotBien } from 'app/shared/model/diem-dot-bien.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDiemDotBienUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDiemDotBienUpdateState {
  isNew: boolean;
  vungADNId: string;
  thongTinADNId: string;
}

export class DiemDotBienUpdate extends React.Component<IDiemDotBienUpdateProps, IDiemDotBienUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      vungADNId: '0',
      thongTinADNId: '0',
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

    this.props.getVungAdns();
    this.props.getThongTinAdns();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { diemDotBienEntity } = this.props;
      const entity = {
        ...diemDotBienEntity,
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
    this.props.history.push('/entity/diem-dot-bien');
  };

  render() {
    const { diemDotBienEntity, vungADNS, thongTinADNS, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.diemDotBien.home.createOrEditLabel">
              <Translate contentKey="xddtApp.diemDotBien.home.createOrEditLabel">Create or edit a DiemDotBien</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : diemDotBienEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="diem-dot-bien-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="viTriLabel" for="viTri">
                    <Translate contentKey="xddtApp.diemDotBien.viTri">Vi Tri</Translate>
                  </Label>
                  <AvField id="diem-dot-bien-viTri" type="string" className="form-control" name="viTri" />
                </AvGroup>
                <AvGroup>
                  <Label id="giaTriLabel" for="giaTri">
                    <Translate contentKey="xddtApp.diemDotBien.giaTri">Gia Tri</Translate>
                  </Label>
                  <AvField id="diem-dot-bien-giaTri" type="text" name="giaTri" />
                </AvGroup>
                <AvGroup>
                  <Label id="giaTri1Label" for="giaTri1">
                    <Translate contentKey="xddtApp.diemDotBien.giaTri1">Gia Tri 1</Translate>
                  </Label>
                  <AvField id="diem-dot-bien-giaTri1" type="text" name="giaTri1" />
                </AvGroup>
                <AvGroup>
                  <Label id="giaTri2Label" for="giaTri2">
                    <Translate contentKey="xddtApp.diemDotBien.giaTri2">Gia Tri 2</Translate>
                  </Label>
                  <AvField id="diem-dot-bien-giaTri2" type="text" name="giaTri2" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="diem-dot-bien-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.diemDotBien.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="vungADN.id">
                    <Translate contentKey="xddtApp.diemDotBien.vungADN">Vung ADN</Translate>
                  </Label>
                  <AvInput id="diem-dot-bien-vungADN" type="select" className="form-control" name="vungADN.id">
                    <option value="" key="0" />
                    {vungADNS
                      ? vungADNS.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="thongTinADN.id">
                    <Translate contentKey="xddtApp.diemDotBien.thongTinADN">Thong Tin ADN</Translate>
                  </Label>
                  <AvInput id="diem-dot-bien-thongTinADN" type="select" className="form-control" name="thongTinADN.id">
                    <option value="" key="0" />
                    {thongTinADNS
                      ? thongTinADNS.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/diem-dot-bien" replace color="info">
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
  vungADNS: storeState.vungADN.entities,
  thongTinADNS: storeState.thongTinADN.entities,
  diemDotBienEntity: storeState.diemDotBien.entity,
  loading: storeState.diemDotBien.loading,
  updating: storeState.diemDotBien.updating,
  updateSuccess: storeState.diemDotBien.updateSuccess
});

const mapDispatchToProps = {
  getVungAdns,
  getThongTinAdns,
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
)(DiemDotBienUpdate);
