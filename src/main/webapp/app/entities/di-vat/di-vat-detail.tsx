import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './di-vat.reducer';
import { IDiVat } from 'app/shared/model/di-vat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiVatDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DiVatDetail extends React.Component<IDiVatDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { diVatEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.diVat.detail.title">DiVat</Translate> [<b>{diVatEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="giaTri">
                <Translate contentKey="xddtApp.diVat.giaTri">Gia Tri</Translate>
              </span>
            </dt>
            <dd>{diVatEntity.giaTri}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.diVat.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{diVatEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.diVat.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{diVatEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.diVat.thongTinKhaiQuat">Thong Tin Khai Quat</Translate>
            </dt>
            <dd>{diVatEntity.thongTinKhaiQuat ? diVatEntity.thongTinKhaiQuat.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.diVat.loaiDiVat">Loai Di Vat</Translate>
            </dt>
            <dd>{diVatEntity.loaiDiVat ? diVatEntity.loaiDiVat.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/di-vat" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/di-vat/${diVatEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ diVat }: IRootState) => ({
  diVatEntity: diVat.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DiVatDetail);
