import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './don-vi-thoi-ky.reducer';
import { IDonViThoiKy } from 'app/shared/model/don-vi-thoi-ky.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDonViThoiKyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DonViThoiKyDetail extends React.Component<IDonViThoiKyDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { donViThoiKyEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.donViThoiKy.detail.title">DonViThoiKy</Translate> [<b>{donViThoiKyEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tuNam">
                <Translate contentKey="xddtApp.donViThoiKy.tuNam">Tu Nam</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.tuNam}</dd>
            <dt>
              <span id="denNam">
                <Translate contentKey="xddtApp.donViThoiKy.denNam">Den Nam</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.denNam}</dd>
            <dt>
              <span id="diaDiemMoTa">
                <Translate contentKey="xddtApp.donViThoiKy.diaDiemMoTa">Dia Diem Mo Ta</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.diaDiemMoTa}</dd>
            <dt>
              <span id="diaDiemXa">
                <Translate contentKey="xddtApp.donViThoiKy.diaDiemXa">Dia Diem Xa</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.diaDiemXa}</dd>
            <dt>
              <span id="diaDiemHuyen">
                <Translate contentKey="xddtApp.donViThoiKy.diaDiemHuyen">Dia Diem Huyen</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.diaDiemHuyen}</dd>
            <dt>
              <span id="diaDiemTinh">
                <Translate contentKey="xddtApp.donViThoiKy.diaDiemTinh">Dia Diem Tinh</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.diaDiemTinh}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.donViThoiKy.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.donViThoiKy.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.donViThoiKy.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.donViThoiKy.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{donViThoiKyEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.donViThoiKy.donVi">Don Vi</Translate>
            </dt>
            <dd>{donViThoiKyEntity.donVi ? donViThoiKyEntity.donVi.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/don-vi-thoi-ky" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/don-vi-thoi-ky/${donViThoiKyEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ donViThoiKy }: IRootState) => ({
  donViThoiKyEntity: donViThoiKy.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DonViThoiKyDetail);
