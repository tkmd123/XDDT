import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPCR } from 'app/shared/model/pcr.model';
import { getEntities as getPCrs } from 'app/entities/pcr/pcr.reducer';
import { IHoaChat } from 'app/shared/model/hoa-chat.model';
import { getEntities as getHoaChats } from 'app/entities/hoa-chat/hoa-chat.reducer';
import { getEntity, updateEntity, createEntity, reset } from './pcr-phan-ung.reducer';
import { IPCRPhanUng } from 'app/shared/model/pcr-phan-ung.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPCRPhanUngUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPCRPhanUngUpdateState {
  isNew: boolean;
  pcrPhanUngId: string;
  hoaChatPhanUngId: string;
}

export class PCRPhanUngUpdate extends React.Component<IPCRPhanUngUpdateProps, IPCRPhanUngUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      pcrPhanUngId: '0',
      hoaChatPhanUngId: '0',
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

    this.props.getPCrs();
    this.props.getHoaChats();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { pCRPhanUngEntity } = this.props;
      const entity = {
        ...pCRPhanUngEntity,
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
    this.props.history.push('/entity/pcr-phan-ung');
  };

  render() {
    const { pCRPhanUngEntity, pCRS, hoaChats, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.pCRPhanUng.home.createOrEditLabel">
              <Translate contentKey="xddtApp.pCRPhanUng.home.createOrEditLabel">Create or edit a PCRPhanUng</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : pCRPhanUngEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="pcr-phan-ung-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="chuKyPhanUngLabel" for="chuKyPhanUng">
                    <Translate contentKey="xddtApp.pCRPhanUng.chuKyPhanUng">Chu Ky Phan Ung</Translate>
                  </Label>
                  <AvField id="pcr-phan-ung-chuKyPhanUng" type="text" name="chuKyPhanUng" />
                </AvGroup>
                <AvGroup>
                  <Label id="dungTichLabel" for="dungTich">
                    <Translate contentKey="xddtApp.pCRPhanUng.dungTich">Dung Tich</Translate>
                  </Label>
                  <AvField id="pcr-phan-ung-dungTich" type="string" className="form-control" name="dungTich" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="pcr-phan-ung-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.pCRPhanUng.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.pCRPhanUng.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="pcr-phan-ung-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.pCRPhanUng.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="pcr-phan-ung-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.pCRPhanUng.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="pcr-phan-ung-udf3" type="text" name="udf3" />
                </AvGroup>
                <AvGroup>
                  <Label for="pcrPhanUng.id">
                    <Translate contentKey="xddtApp.pCRPhanUng.pcrPhanUng">Pcr Phan Ung</Translate>
                  </Label>
                  <AvInput id="pcr-phan-ung-pcrPhanUng" type="select" className="form-control" name="pcrPhanUng.id">
                    <option value="" key="0" />
                    {pCRS
                      ? pCRS.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="hoaChatPhanUng.id">
                    <Translate contentKey="xddtApp.pCRPhanUng.hoaChatPhanUng">Hoa Chat Phan Ung</Translate>
                  </Label>
                  <AvInput id="pcr-phan-ung-hoaChatPhanUng" type="select" className="form-control" name="hoaChatPhanUng.id">
                    <option value="" key="0" />
                    {hoaChats
                      ? hoaChats.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/pcr-phan-ung" replace color="info">
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
  pCRS: storeState.pCR.entities,
  hoaChats: storeState.hoaChat.entities,
  pCRPhanUngEntity: storeState.pCRPhanUng.entity,
  loading: storeState.pCRPhanUng.loading,
  updating: storeState.pCRPhanUng.updating,
  updateSuccess: storeState.pCRPhanUng.updateSuccess
});

const mapDispatchToProps = {
  getPCrs,
  getHoaChats,
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
)(PCRPhanUngUpdate);
