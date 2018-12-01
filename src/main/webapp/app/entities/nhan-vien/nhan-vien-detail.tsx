import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nhan-vien.reducer';
import { INhanVien } from 'app/shared/model/nhan-vien.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INhanVienDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanVienDetail extends React.Component<INhanVienDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nhanVienEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.nhanVien.detail.title">NhanVien</Translate> [<b>{nhanVienEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maNhanVien">
                <Translate contentKey="xddtApp.nhanVien.maNhanVien">Ma Nhan Vien</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.maNhanVien}</dd>
            <dt>
              <span id="tenNhanVien">
                <Translate contentKey="xddtApp.nhanVien.tenNhanVien">Ten Nhan Vien</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.tenNhanVien}</dd>
            <dt>
              <span id="soDienThoai">
                <Translate contentKey="xddtApp.nhanVien.soDienThoai">So Dien Thoai</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.soDienThoai}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="xddtApp.nhanVien.email">Email</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.email}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.nhanVien.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.moTa}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.nhanVien.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.ghiChu}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.nhanVien.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.nhanVien.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.nhanVien.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.nhanVien.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{nhanVienEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.nhanVien.userNhanVien">User Nhan Vien</Translate>
            </dt>
            <dd>{nhanVienEntity.userNhanVien ? nhanVienEntity.userNhanVien.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.nhanVien.phongban">Phongban</Translate>
            </dt>
            <dd>{nhanVienEntity.phongban ? nhanVienEntity.phongban.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/nhan-vien" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nhan-vien/${nhanVienEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nhanVien }: IRootState) => ({
  nhanVienEntity: nhanVien.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanVienDetail);
