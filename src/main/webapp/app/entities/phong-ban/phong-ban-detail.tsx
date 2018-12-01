import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './phong-ban.reducer';
import { IPhongBan } from 'app/shared/model/phong-ban.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPhongBanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PhongBanDetail extends React.Component<IPhongBanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { phongBanEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.phongBan.detail.title">PhongBan</Translate> [<b>{phongBanEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maPhongBan">
                <Translate contentKey="xddtApp.phongBan.maPhongBan">Ma Phong Ban</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.maPhongBan}</dd>
            <dt>
              <span id="tenPhongBan">
                <Translate contentKey="xddtApp.phongBan.tenPhongBan">Ten Phong Ban</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.tenPhongBan}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.phongBan.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.moTa}</dd>
            <dt>
              <span id="isActive">
                <Translate contentKey="xddtApp.phongBan.isActive">Is Active</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.isActive ? 'true' : 'false'}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.phongBan.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.phongBan.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.phongBan.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.phongBan.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{phongBanEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.phongBan.trungtam">Trungtam</Translate>
            </dt>
            <dd>{phongBanEntity.trungtam ? phongBanEntity.trungtam.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/phong-ban" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/phong-ban/${phongBanEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ phongBan }: IRootState) => ({
  phongBanEntity: phongBan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PhongBanDetail);
