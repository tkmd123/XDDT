import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hoa-chat-tach-chiet.reducer';
import { IHoaChatTachChiet } from 'app/shared/model/hoa-chat-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatTachChietDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoaChatTachChietDetail extends React.Component<IHoaChatTachChietDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoaChatTachChietEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoaChatTachChiet.detail.title">HoaChatTachChiet</Translate> [<b>{hoaChatTachChietEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="soLuong">
                <Translate contentKey="xddtApp.hoaChatTachChiet.soLuong">So Luong</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.soLuong}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.hoaChatTachChiet.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoaChatTachChiet.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.hoaChatTachChiet.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.hoaChatTachChiet.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.hoaChatTachChiet.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{hoaChatTachChietEntity.udf3}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoaChatTachChiet.hoaChat">Hoa Chat</Translate>
            </dt>
            <dd>{hoaChatTachChietEntity.hoaChat ? hoaChatTachChietEntity.hoaChat.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoaChatTachChiet.tachChiet">Tach Chiet</Translate>
            </dt>
            <dd>{hoaChatTachChietEntity.tachChiet ? hoaChatTachChietEntity.tachChiet.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/hoa-chat-tach-chiet" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hoa-chat-tach-chiet/${hoaChatTachChietEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hoaChatTachChiet }: IRootState) => ({
  hoaChatTachChietEntity: hoaChatTachChiet.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoaChatTachChietDetail);
