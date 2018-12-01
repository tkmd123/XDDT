import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './chuc-vu.reducer';
import { IChucVu } from 'app/shared/model/chuc-vu.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChucVuDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ChucVuDetail extends React.Component<IChucVuDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { chucVuEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.chucVu.detail.title">ChucVu</Translate> [<b>{chucVuEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maChucVu">
                <Translate contentKey="xddtApp.chucVu.maChucVu">Ma Chuc Vu</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.maChucVu}</dd>
            <dt>
              <span id="tenChucVu">
                <Translate contentKey="xddtApp.chucVu.tenChucVu">Ten Chuc Vu</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.tenChucVu}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.chucVu.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.chucVu.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.chucVu.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.chucVu.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.chucVu.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{chucVuEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/chuc-vu" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/chuc-vu/${chucVuEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ chucVu }: IRootState) => ({
  chucVuEntity: chucVu.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ChucVuDetail);
