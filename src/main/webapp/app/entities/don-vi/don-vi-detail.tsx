import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './don-vi.reducer';
import { IDonVi } from 'app/shared/model/don-vi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDonViDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DonViDetail extends React.Component<IDonViDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { donViEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.donVi.detail.title">DonVi</Translate> [<b>{donViEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maDonVi">
                <Translate contentKey="xddtApp.donVi.maDonVi">Ma Don Vi</Translate>
              </span>
            </dt>
            <dd>{donViEntity.maDonVi}</dd>
            <dt>
              <span id="tenDonVi">
                <Translate contentKey="xddtApp.donVi.tenDonVi">Ten Don Vi</Translate>
              </span>
            </dt>
            <dd>{donViEntity.tenDonVi}</dd>
            <dt>
              <span id="tenTat">
                <Translate contentKey="xddtApp.donVi.tenTat">Ten Tat</Translate>
              </span>
            </dt>
            <dd>{donViEntity.tenTat}</dd>
            <dt>
              <span id="phanMuc">
                <Translate contentKey="xddtApp.donVi.phanMuc">Phan Muc</Translate>
              </span>
            </dt>
            <dd>{donViEntity.phanMuc}</dd>
            <dt>
              <span id="phanCap">
                <Translate contentKey="xddtApp.donVi.phanCap">Phan Cap</Translate>
              </span>
            </dt>
            <dd>{donViEntity.phanCap}</dd>
            <dt>
              <span id="phanKhoi">
                <Translate contentKey="xddtApp.donVi.phanKhoi">Phan Khoi</Translate>
              </span>
            </dt>
            <dd>{donViEntity.phanKhoi}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.donVi.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{donViEntity.moTa}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.donVi.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{donViEntity.ghiChu}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.donVi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{donViEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.donVi.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{donViEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.donVi.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{donViEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.donVi.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{donViEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.donVi.donViQuanLy">Don Vi Quan Ly</Translate>
            </dt>
            <dd>{donViEntity.donViQuanLy ? donViEntity.donViQuanLy.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/don-vi" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/don-vi/${donViEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ donVi }: IRootState) => ({
  donViEntity: donVi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DonViDetail);
