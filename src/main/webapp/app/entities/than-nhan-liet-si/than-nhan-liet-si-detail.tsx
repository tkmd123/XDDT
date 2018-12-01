import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './than-nhan-liet-si.reducer';
import { IThanNhanLietSi } from 'app/shared/model/than-nhan-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThanNhanLietSiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThanNhanLietSiDetail extends React.Component<IThanNhanLietSiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { thanNhanLietSiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.thanNhanLietSi.detail.title">ThanNhanLietSi</Translate> [<b>{thanNhanLietSiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.thanNhanLietSi.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{thanNhanLietSiEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.thanNhanLietSi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{thanNhanLietSiEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.thanNhanLietSi.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{thanNhanLietSiEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.thanNhanLietSi.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{thanNhanLietSiEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.thanNhanLietSi.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{thanNhanLietSiEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.thanNhanLietSi.lietSi">Liet Si</Translate>
            </dt>
            <dd>{thanNhanLietSiEntity.lietSi ? thanNhanLietSiEntity.lietSi.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.thanNhanLietSi.thanNhan">Than Nhan</Translate>
            </dt>
            <dd>{thanNhanLietSiEntity.thanNhan ? thanNhanLietSiEntity.thanNhan.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.thanNhanLietSi.quanHeThanNhan">Quan He Than Nhan</Translate>
            </dt>
            <dd>{thanNhanLietSiEntity.quanHeThanNhan ? thanNhanLietSiEntity.quanHeThanNhan.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/than-nhan-liet-si" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/than-nhan-liet-si/${thanNhanLietSiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ thanNhanLietSi }: IRootState) => ({
  thanNhanLietSiEntity: thanNhanLietSi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThanNhanLietSiDetail);
