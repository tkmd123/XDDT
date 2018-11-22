import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './quan-he-than-nhan.reducer';
import { IQuanHeThanNhan } from 'app/shared/model/quan-he-than-nhan.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IQuanHeThanNhanUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IQuanHeThanNhanUpdateState {
  isNew: boolean;
}

export class QuanHeThanNhanUpdate extends React.Component<IQuanHeThanNhanUpdateProps, IQuanHeThanNhanUpdateState> {
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
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { quanHeThanNhanEntity } = this.props;
      const entity = {
        ...quanHeThanNhanEntity,
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
    this.props.history.push('/entity/quan-he-than-nhan');
  };

  render() {
    const { quanHeThanNhanEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.quanHeThanNhan.home.createOrEditLabel">
              <Translate contentKey="xddtApp.quanHeThanNhan.home.createOrEditLabel">Create or edit a QuanHeThanNhan</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : quanHeThanNhanEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="quan-he-than-nhan-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maQuanHeLabel" for="maQuanHe">
                    <Translate contentKey="xddtApp.quanHeThanNhan.maQuanHe">Ma Quan He</Translate>
                  </Label>
                  <AvField id="quan-he-than-nhan-maQuanHe" type="text" name="maQuanHe" />
                </AvGroup>
                <AvGroup>
                  <Label id="loaiQuanHeLabel" for="loaiQuanHe">
                    <Translate contentKey="xddtApp.quanHeThanNhan.loaiQuanHe">Loai Quan He</Translate>
                  </Label>
                  <AvField id="quan-he-than-nhan-loaiQuanHe" type="text" name="loaiQuanHe" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.quanHeThanNhan.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="quan-he-than-nhan-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="quan-he-than-nhan-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.quanHeThanNhan.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/quan-he-than-nhan" replace color="info">
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
  quanHeThanNhanEntity: storeState.quanHeThanNhan.entity,
  loading: storeState.quanHeThanNhan.loading,
  updating: storeState.quanHeThanNhan.updating,
  updateSuccess: storeState.quanHeThanNhan.updateSuccess
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
)(QuanHeThanNhanUpdate);
