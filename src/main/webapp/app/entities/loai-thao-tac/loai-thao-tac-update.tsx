import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getLoaiThaoTacs } from 'app/entities/loai-thao-tac/loai-thao-tac.reducer';
import { IPhongBan } from 'app/shared/model/phong-ban.model';
import { getEntities as getPhongBans } from 'app/entities/phong-ban/phong-ban.reducer';
import { getEntity, updateEntity, createEntity, reset } from './loai-thao-tac.reducer';
import { ILoaiThaoTac } from 'app/shared/model/loai-thao-tac.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoaiThaoTacUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoaiThaoTacUpdateState {
  isNew: boolean;
  thaoTacTiepTheoId: string;
  thaoTacTiepTheoId: string;
  phongBanId: string;
}

export class LoaiThaoTacUpdate extends React.Component<ILoaiThaoTacUpdateProps, ILoaiThaoTacUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      thaoTacTiepTheoId: '0',
      thaoTacTiepTheoId: '0',
      phongBanId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (!this.state.isNew) {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getLoaiThaoTacs();
    this.props.getPhongBans();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { loaiThaoTacEntity } = this.props;
      const entity = {
        ...loaiThaoTacEntity,
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
    this.props.history.push('/entity/loai-thao-tac');
  };

  render() {
    const { loaiThaoTacEntity, loaiThaoTacs, phongBans, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.loaiThaoTac.home.createOrEditLabel">
              <Translate contentKey="xddtApp.loaiThaoTac.home.createOrEditLabel">Create or edit a LoaiThaoTac</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loaiThaoTacEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loai-thao-tac-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maLoaiThaoTacLabel" for="maLoaiThaoTac">
                    <Translate contentKey="xddtApp.loaiThaoTac.maLoaiThaoTac">Ma Loai Thao Tac</Translate>
                  </Label>
                  <AvField id="loai-thao-tac-maLoaiThaoTac" type="text" name="maLoaiThaoTac" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenLoaiThaoTacLabel" for="tenLoaiThaoTac">
                    <Translate contentKey="xddtApp.loaiThaoTac.tenLoaiThaoTac">Ten Loai Thao Tac</Translate>
                  </Label>
                  <AvField id="loai-thao-tac-tenLoaiThaoTac" type="text" name="tenLoaiThaoTac" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.loaiThaoTac.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="loai-thao-tac-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="loai-thao-tac-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.loaiThaoTac.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label for="thaoTacTiepTheo.id">
                    <Translate contentKey="xddtApp.loaiThaoTac.thaoTacTiepTheo">Thao Tac Tiep Theo</Translate>
                  </Label>
                  <AvInput id="loai-thao-tac-thaoTacTiepTheo" type="select" className="form-control" name="thaoTacTiepTheo.id">
                    <option value="" key="0" />
                    {loaiThaoTacs
                      ? loaiThaoTacs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="phongBan.id">
                    <Translate contentKey="xddtApp.loaiThaoTac.phongBan">Phong Ban</Translate>
                  </Label>
                  <AvInput id="loai-thao-tac-phongBan" type="select" className="form-control" name="phongBan.id">
                    <option value="" key="0" />
                    {phongBans
                      ? phongBans.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loai-thao-tac" replace color="info">
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
  loaiThaoTacs: storeState.loaiThaoTac.entities,
  phongBans: storeState.phongBan.entities,
  loaiThaoTacEntity: storeState.loaiThaoTac.entity,
  loading: storeState.loaiThaoTac.loading,
  updating: storeState.loaiThaoTac.updating,
  updateSuccess: storeState.loaiThaoTac.updateSuccess
});

const mapDispatchToProps = {
  getLoaiThaoTacs,
  getPhongBans,
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
)(LoaiThaoTacUpdate);
