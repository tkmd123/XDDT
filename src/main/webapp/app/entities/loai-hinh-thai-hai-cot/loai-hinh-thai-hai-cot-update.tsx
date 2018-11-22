import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './loai-hinh-thai-hai-cot.reducer';
import { ILoaiHinhThaiHaiCot } from 'app/shared/model/loai-hinh-thai-hai-cot.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILoaiHinhThaiHaiCotUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILoaiHinhThaiHaiCotUpdateState {
  isNew: boolean;
}

export class LoaiHinhThaiHaiCotUpdate extends React.Component<ILoaiHinhThaiHaiCotUpdateProps, ILoaiHinhThaiHaiCotUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { loaiHinhThaiHaiCotEntity } = this.props;
      const entity = {
        ...loaiHinhThaiHaiCotEntity,
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
    this.props.history.push('/entity/loai-hinh-thai-hai-cot');
  };

  render() {
    const { loaiHinhThaiHaiCotEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.loaiHinhThaiHaiCot.home.createOrEditLabel">
              <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.home.createOrEditLabel">Create or edit a LoaiHinhThaiHaiCot</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : loaiHinhThaiHaiCotEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="loai-hinh-thai-hai-cot-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maHinhThaiLabel" for="maHinhThai">
                    <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.maHinhThai">Ma Hinh Thai</Translate>
                  </Label>
                  <AvField id="loai-hinh-thai-hai-cot-maHinhThai" type="text" name="maHinhThai" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenHinhThaiLabel" for="tenHinhThai">
                    <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.tenHinhThai">Ten Hinh Thai</Translate>
                  </Label>
                  <AvField id="loai-hinh-thai-hai-cot-tenHinhThai" type="text" name="tenHinhThai" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="loai-hinh-thai-hai-cot-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="loai-hinh-thai-hai-cot-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/loai-hinh-thai-hai-cot" replace color="info">
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
  loaiHinhThaiHaiCotEntity: storeState.loaiHinhThaiHaiCot.entity,
  loading: storeState.loaiHinhThaiHaiCot.loading,
  updating: storeState.loaiHinhThaiHaiCot.updating,
  updateSuccess: storeState.loaiHinhThaiHaiCot.updateSuccess
});

const mapDispatchToProps = {
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
)(LoaiHinhThaiHaiCotUpdate);
