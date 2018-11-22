import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loai-mau-xet-nghiem.reducer';
import { ILoaiMauXetNghiem } from 'app/shared/model/loai-mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoaiMauXetNghiemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoaiMauXetNghiemDetail extends React.Component<ILoaiMauXetNghiemDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loaiMauXetNghiemEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.loaiMauXetNghiem.detail.title">LoaiMauXetNghiem</Translate> [<b>{loaiMauXetNghiemEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maLoaiMau">
                <Translate contentKey="xddtApp.loaiMauXetNghiem.maLoaiMau">Ma Loai Mau</Translate>
              </span>
            </dt>
            <dd>{loaiMauXetNghiemEntity.maLoaiMau}</dd>
            <dt>
              <span id="tenLoaiMau">
                <Translate contentKey="xddtApp.loaiMauXetNghiem.tenLoaiMau">Ten Loai Mau</Translate>
              </span>
            </dt>
            <dd>{loaiMauXetNghiemEntity.tenLoaiMau}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.loaiMauXetNghiem.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{loaiMauXetNghiemEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.loaiMauXetNghiem.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{loaiMauXetNghiemEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/loai-mau-xet-nghiem" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/loai-mau-xet-nghiem/${loaiMauXetNghiemEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ loaiMauXetNghiem }: IRootState) => ({
  loaiMauXetNghiemEntity: loaiMauXetNghiem.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiMauXetNghiemDetail);
