import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nghia-trang.reducer';
import { INghiaTrang } from 'app/shared/model/nghia-trang.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INghiaTrangDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NghiaTrangDetail extends React.Component<INghiaTrangDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nghiaTrangEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.nghiaTrang.detail.title">NghiaTrang</Translate> [<b>{nghiaTrangEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maNghiaTrang">
                <Translate contentKey="xddtApp.nghiaTrang.maNghiaTrang">Ma Nghia Trang</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.maNghiaTrang}</dd>
            <dt>
              <span id="tenNghiaTrang">
                <Translate contentKey="xddtApp.nghiaTrang.tenNghiaTrang">Ten Nghia Trang</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.tenNghiaTrang}</dd>
            <dt>
              <span id="diaChi">
                <Translate contentKey="xddtApp.nghiaTrang.diaChi">Dia Chi</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.diaChi}</dd>
            <dt>
              <span id="nguoiLienHe">
                <Translate contentKey="xddtApp.nghiaTrang.nguoiLienHe">Nguoi Lien He</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.nguoiLienHe}</dd>
            <dt>
              <span id="dienThoai">
                <Translate contentKey="xddtApp.nghiaTrang.dienThoai">Dien Thoai</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.dienThoai}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="xddtApp.nghiaTrang.email">Email</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.email}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.nghiaTrang.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.nghiaTrang.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{nghiaTrangEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.nghiaTrang.phuongXa">Phuong Xa</Translate>
            </dt>
            <dd>{nghiaTrangEntity.phuongXa ? nghiaTrangEntity.phuongXa.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/nghia-trang" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nghia-trang/${nghiaTrangEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nghiaTrang }: IRootState) => ({
  nghiaTrangEntity: nghiaTrang.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NghiaTrangDetail);
