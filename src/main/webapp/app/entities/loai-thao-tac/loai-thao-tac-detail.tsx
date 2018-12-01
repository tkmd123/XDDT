import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loai-thao-tac.reducer';
import { ILoaiThaoTac } from 'app/shared/model/loai-thao-tac.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoaiThaoTacDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoaiThaoTacDetail extends React.Component<ILoaiThaoTacDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loaiThaoTacEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.loaiThaoTac.detail.title">LoaiThaoTac</Translate> [<b>{loaiThaoTacEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maLoaiThaoTac">
                <Translate contentKey="xddtApp.loaiThaoTac.maLoaiThaoTac">Ma Loai Thao Tac</Translate>
              </span>
            </dt>
            <dd>{loaiThaoTacEntity.maLoaiThaoTac}</dd>
            <dt>
              <span id="tenLoaiThaoTac">
                <Translate contentKey="xddtApp.loaiThaoTac.tenLoaiThaoTac">Ten Loai Thao Tac</Translate>
              </span>
            </dt>
            <dd>{loaiThaoTacEntity.tenLoaiThaoTac}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.loaiThaoTac.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{loaiThaoTacEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.loaiThaoTac.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{loaiThaoTacEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.loaiThaoTac.thaoTacTiepTheo">Thao Tac Tiep Theo</Translate>
            </dt>
            <dd>{loaiThaoTacEntity.thaoTacTiepTheo ? loaiThaoTacEntity.thaoTacTiepTheo.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.loaiThaoTac.phongBan">Phong Ban</Translate>
            </dt>
            <dd>{loaiThaoTacEntity.phongBan ? loaiThaoTacEntity.phongBan.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/loai-thao-tac" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/loai-thao-tac/${loaiThaoTacEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ loaiThaoTac }: IRootState) => ({
  loaiThaoTacEntity: loaiThaoTac.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiThaoTacDetail);
