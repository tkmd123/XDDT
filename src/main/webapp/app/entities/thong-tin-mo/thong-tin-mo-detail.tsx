import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './thong-tin-mo.reducer';
import { IThongTinMo } from 'app/shared/model/thong-tin-mo.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThongTinMoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThongTinMoDetail extends React.Component<IThongTinMoDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { thongTinMoEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.thongTinMo.detail.title">ThongTinMo</Translate> [<b>{thongTinMoEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="khuMo">
                <Translate contentKey="xddtApp.thongTinMo.khuMo">Khu Mo</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.khuMo}</dd>
            <dt>
              <span id="loMo">
                <Translate contentKey="xddtApp.thongTinMo.loMo">Lo Mo</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.loMo}</dd>
            <dt>
              <span id="hangMo">
                <Translate contentKey="xddtApp.thongTinMo.hangMo">Hang Mo</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.hangMo}</dd>
            <dt>
              <span id="soMo">
                <Translate contentKey="xddtApp.thongTinMo.soMo">So Mo</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.soMo}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.thongTinMo.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.thongTinMo.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{thongTinMoEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.thongTinMo.nghiaTrang">Nghia Trang</Translate>
            </dt>
            <dd>{thongTinMoEntity.nghiaTrang ? thongTinMoEntity.nghiaTrang.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/thong-tin-mo" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/thong-tin-mo/${thongTinMoEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ thongTinMo }: IRootState) => ({
  thongTinMoEntity: thongTinMo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThongTinMoDetail);
