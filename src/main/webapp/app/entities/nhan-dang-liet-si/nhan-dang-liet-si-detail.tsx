import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nhan-dang-liet-si.reducer';
import { INhanDangLietSi } from 'app/shared/model/nhan-dang-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanDangLietSiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanDangLietSiDetail extends React.Component<INhanDangLietSiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nhanDangLietSiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.nhanDangLietSi.detail.title">NhanDangLietSi</Translate> [<b>{nhanDangLietSiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="giaTri">
                <Translate contentKey="xddtApp.nhanDangLietSi.giaTri">Gia Tri</Translate>
              </span>
            </dt>
            <dd>{nhanDangLietSiEntity.giaTri}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.nhanDangLietSi.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{nhanDangLietSiEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.nhanDangLietSi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{nhanDangLietSiEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.nhanDangLietSi.nhanDang">Nhan Dang</Translate>
            </dt>
            <dd>{nhanDangLietSiEntity.nhanDang ? nhanDangLietSiEntity.nhanDang.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.nhanDangLietSi.lietSi">Liet Si</Translate>
            </dt>
            <dd>{nhanDangLietSiEntity.lietSi ? nhanDangLietSiEntity.lietSi.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/nhan-dang-liet-si" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nhan-dang-liet-si/${nhanDangLietSiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nhanDangLietSi }: IRootState) => ({
  nhanDangLietSiEntity: nhanDangLietSi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanDangLietSiDetail);
