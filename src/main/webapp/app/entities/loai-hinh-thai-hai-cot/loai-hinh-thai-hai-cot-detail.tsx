import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loai-hinh-thai-hai-cot.reducer';
import { ILoaiHinhThaiHaiCot } from 'app/shared/model/loai-hinh-thai-hai-cot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoaiHinhThaiHaiCotDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoaiHinhThaiHaiCotDetail extends React.Component<ILoaiHinhThaiHaiCotDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loaiHinhThaiHaiCotEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.detail.title">LoaiHinhThaiHaiCot</Translate> [<b>
              {loaiHinhThaiHaiCotEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maHinhThai">
                <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.maHinhThai">Ma Hinh Thai</Translate>
              </span>
            </dt>
            <dd>{loaiHinhThaiHaiCotEntity.maHinhThai}</dd>
            <dt>
              <span id="tenHinhThai">
                <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.tenHinhThai">Ten Hinh Thai</Translate>
              </span>
            </dt>
            <dd>{loaiHinhThaiHaiCotEntity.tenHinhThai}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{loaiHinhThaiHaiCotEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.loaiHinhThaiHaiCot.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{loaiHinhThaiHaiCotEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/loai-hinh-thai-hai-cot" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/loai-hinh-thai-hai-cot/${loaiHinhThaiHaiCotEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ loaiHinhThaiHaiCot }: IRootState) => ({
  loaiHinhThaiHaiCotEntity: loaiHinhThaiHaiCot.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiHinhThaiHaiCotDetail);
