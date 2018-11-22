import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './thao-tac.reducer';
import { IThaoTac } from 'app/shared/model/thao-tac.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThaoTacDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThaoTacDetail extends React.Component<IThaoTacDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { thaoTacEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.thaoTac.detail.title">ThaoTac</Translate> [<b>{thaoTacEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moTaKetQua">
                <Translate contentKey="xddtApp.thaoTac.moTaKetQua">Mo Ta Ket Qua</Translate>
              </span>
            </dt>
            <dd>{thaoTacEntity.moTaKetQua}</dd>
            <dt>
              <span id="trangThaiXuLy">
                <Translate contentKey="xddtApp.thaoTac.trangThaiXuLy">Trang Thai Xu Ly</Translate>
              </span>
            </dt>
            <dd>{thaoTacEntity.trangThaiXuLy ? 'true' : 'false'}</dd>
            <dt>
              <span id="isDangThucHien">
                <Translate contentKey="xddtApp.thaoTac.isDangThucHien">Is Dang Thuc Hien</Translate>
              </span>
            </dt>
            <dd>{thaoTacEntity.isDangThucHien ? 'true' : 'false'}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.thaoTac.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{thaoTacEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.thaoTac.mauXetNghiem">Mau Xet Nghiem</Translate>
            </dt>
            <dd>{thaoTacEntity.mauXetNghiem ? thaoTacEntity.mauXetNghiem.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.thaoTac.phongBan">Phong Ban</Translate>
            </dt>
            <dd>{thaoTacEntity.phongBan ? thaoTacEntity.phongBan.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.thaoTac.loaiThaoTac">Loai Thao Tac</Translate>
            </dt>
            <dd>{thaoTacEntity.loaiThaoTac ? thaoTacEntity.loaiThaoTac.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/thao-tac" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/thao-tac/${thaoTacEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ thaoTac }: IRootState) => ({
  thaoTacEntity: thaoTac.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThaoTacDetail);
