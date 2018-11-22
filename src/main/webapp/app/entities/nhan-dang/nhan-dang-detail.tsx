import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nhan-dang.reducer';
import { INhanDang } from 'app/shared/model/nhan-dang.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanDangDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanDangDetail extends React.Component<INhanDangDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nhanDangEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.nhanDang.detail.title">NhanDang</Translate> [<b>{nhanDangEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maNhanDang">
                <Translate contentKey="xddtApp.nhanDang.maNhanDang">Ma Nhan Dang</Translate>
              </span>
            </dt>
            <dd>{nhanDangEntity.maNhanDang}</dd>
            <dt>
              <span id="tenNhanDang">
                <Translate contentKey="xddtApp.nhanDang.tenNhanDang">Ten Nhan Dang</Translate>
              </span>
            </dt>
            <dd>{nhanDangEntity.tenNhanDang}</dd>
            <dt>
              <span id="donViTinh">
                <Translate contentKey="xddtApp.nhanDang.donViTinh">Don Vi Tinh</Translate>
              </span>
            </dt>
            <dd>{nhanDangEntity.donViTinh}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.nhanDang.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{nhanDangEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.nhanDang.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{nhanDangEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/nhan-dang" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nhan-dang/${nhanDangEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nhanDang }: IRootState) => ({
  nhanDangEntity: nhanDang.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanDangDetail);
