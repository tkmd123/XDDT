import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './chuc-vu.reducer';
import { IChucVu } from 'app/shared/model/chuc-vu.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IChucVuUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IChucVuUpdateState {
  isNew: boolean;
}

export class ChucVuUpdate extends React.Component<IChucVuUpdateProps, IChucVuUpdateState> {
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
      const { chucVuEntity } = this.props;
      const entity = {
        ...chucVuEntity,
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
    this.props.history.push('/entity/chuc-vu');
  };

  render() {
    const { chucVuEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="xddtApp.chucVu.home.createOrEditLabel">
              <Translate contentKey="xddtApp.chucVu.home.createOrEditLabel">Create or edit a ChucVu</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : chucVuEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="chuc-vu-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="maChucVuLabel" for="maChucVu">
                    <Translate contentKey="xddtApp.chucVu.maChucVu">Ma Chuc Vu</Translate>
                  </Label>
                  <AvField id="chuc-vu-maChucVu" type="text" name="maChucVu" />
                </AvGroup>
                <AvGroup>
                  <Label id="tenChucVuLabel" for="tenChucVu">
                    <Translate contentKey="xddtApp.chucVu.tenChucVu">Ten Chuc Vu</Translate>
                  </Label>
                  <AvField id="chuc-vu-tenChucVu" type="text" name="tenChucVu" />
                </AvGroup>
                <AvGroup>
                  <Label id="moTaLabel" for="moTa">
                    <Translate contentKey="xddtApp.chucVu.moTa">Mo Ta</Translate>
                  </Label>
                  <AvField id="chuc-vu-moTa" type="text" name="moTa" />
                </AvGroup>
                <AvGroup>
                  <Label id="isDeletedLabel" check>
                    <AvInput id="chuc-vu-isDeleted" type="checkbox" className="form-control" name="isDeleted" />
                    <Translate contentKey="xddtApp.chucVu.isDeleted">Is Deleted</Translate>
                  </Label>
                </AvGroup>
                <AvGroup>
                  <Label id="udf1Label" for="udf1">
                    <Translate contentKey="xddtApp.chucVu.udf1">Udf 1</Translate>
                  </Label>
                  <AvField id="chuc-vu-udf1" type="text" name="udf1" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf2Label" for="udf2">
                    <Translate contentKey="xddtApp.chucVu.udf2">Udf 2</Translate>
                  </Label>
                  <AvField id="chuc-vu-udf2" type="text" name="udf2" />
                </AvGroup>
                <AvGroup>
                  <Label id="udf3Label" for="udf3">
                    <Translate contentKey="xddtApp.chucVu.udf3">Udf 3</Translate>
                  </Label>
                  <AvField id="chuc-vu-udf3" type="text" name="udf3" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/chuc-vu" replace color="info">
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
  chucVuEntity: storeState.chucVu.entity,
  loading: storeState.chucVu.loading,
  updating: storeState.chucVu.updating,
  updateSuccess: storeState.chucVu.updateSuccess
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
)(ChucVuUpdate);
