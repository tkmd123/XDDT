import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hai-cot-liet-si.reducer';
import { IHaiCotLietSi } from 'app/shared/model/hai-cot-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHaiCotLietSiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HaiCotLietSiDetail extends React.Component<IHaiCotLietSiDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { haiCotLietSiEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.haiCotLietSi.detail.title">HaiCotLietSi</Translate> [<b>{haiCotLietSiEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.haiCotLietSi.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{haiCotLietSiEntity.moTa}</dd>
            <dt>
              <span id="coHaiCot">
                <Translate contentKey="xddtApp.haiCotLietSi.coHaiCot">Co Hai Cot</Translate>
              </span>
            </dt>
            <dd>{haiCotLietSiEntity.coHaiCot ? 'true' : 'false'}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.haiCotLietSi.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{haiCotLietSiEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.haiCotLietSi.lietSi">Liet Si</Translate>
            </dt>
            <dd>{haiCotLietSiEntity.lietSi ? haiCotLietSiEntity.lietSi.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.haiCotLietSi.thongTinKhaiQuat">Thong Tin Khai Quat</Translate>
            </dt>
            <dd>{haiCotLietSiEntity.thongTinKhaiQuat ? haiCotLietSiEntity.thongTinKhaiQuat.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/hai-cot-liet-si" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hai-cot-liet-si/${haiCotLietSiEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ haiCotLietSi }: IRootState) => ({
  haiCotLietSiEntity: haiCotLietSi.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HaiCotLietSiDetail);
