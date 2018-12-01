import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './quan-he-than-nhan.reducer';
import { IQuanHeThanNhan } from 'app/shared/model/quan-he-than-nhan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQuanHeThanNhanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class QuanHeThanNhanDetail extends React.Component<IQuanHeThanNhanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { quanHeThanNhanEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.quanHeThanNhan.detail.title">QuanHeThanNhan</Translate> [<b>{quanHeThanNhanEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maQuanHe">
                <Translate contentKey="xddtApp.quanHeThanNhan.maQuanHe">Ma Quan He</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.maQuanHe}</dd>
            <dt>
              <span id="loaiQuanHe">
                <Translate contentKey="xddtApp.quanHeThanNhan.loaiQuanHe">Loai Quan He</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.loaiQuanHe}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.quanHeThanNhan.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.quanHeThanNhan.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.quanHeThanNhan.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.quanHeThanNhan.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.quanHeThanNhan.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{quanHeThanNhanEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/quan-he-than-nhan" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/quan-he-than-nhan/${quanHeThanNhanEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ quanHeThanNhan }: IRootState) => ({
  quanHeThanNhanEntity: quanHeThanNhan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QuanHeThanNhanDetail);
