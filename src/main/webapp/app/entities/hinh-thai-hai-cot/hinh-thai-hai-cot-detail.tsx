import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hinh-thai-hai-cot.reducer';
import { IHinhThaiHaiCot } from 'app/shared/model/hinh-thai-hai-cot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHinhThaiHaiCotDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HinhThaiHaiCotDetail extends React.Component<IHinhThaiHaiCotDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hinhThaiHaiCotEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hinhThaiHaiCot.detail.title">HinhThaiHaiCot</Translate> [<b>{hinhThaiHaiCotEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="giaTri">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.giaTri">Gia Tri</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.giaTri}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.hinhThaiHaiCot.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{hinhThaiHaiCotEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.hinhThaiHaiCot.haiCotLietSi">Hai Cot Liet Si</Translate>
            </dt>
            <dd>{hinhThaiHaiCotEntity.haiCotLietSi ? hinhThaiHaiCotEntity.haiCotLietSi.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hinhThaiHaiCot.loaiHinhThaiHaiCot">Loai Hinh Thai Hai Cot</Translate>
            </dt>
            <dd>{hinhThaiHaiCotEntity.loaiHinhThaiHaiCot ? hinhThaiHaiCotEntity.loaiHinhThaiHaiCot.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/hinh-thai-hai-cot" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hinh-thai-hai-cot/${hinhThaiHaiCotEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hinhThaiHaiCot }: IRootState) => ({
  hinhThaiHaiCotEntity: hinhThaiHaiCot.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HinhThaiHaiCotDetail);
