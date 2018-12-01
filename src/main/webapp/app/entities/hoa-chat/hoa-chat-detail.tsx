import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hoa-chat.reducer';
import { IHoaChat } from 'app/shared/model/hoa-chat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoaChatDetail extends React.Component<IHoaChatDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoaChatEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoaChat.detail.title">HoaChat</Translate> [<b>{hoaChatEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maHoaChat">
                <Translate contentKey="xddtApp.hoaChat.maHoaChat">Ma Hoa Chat</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.maHoaChat}</dd>
            <dt>
              <span id="tenHoaChat">
                <Translate contentKey="xddtApp.hoaChat.tenHoaChat">Ten Hoa Chat</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.tenHoaChat}</dd>
            <dt>
              <span id="hangHoaChat">
                <Translate contentKey="xddtApp.hoaChat.hangHoaChat">Hang Hoa Chat</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.hangHoaChat}</dd>
            <dt>
              <span id="donViTinh">
                <Translate contentKey="xddtApp.hoaChat.donViTinh">Don Vi Tinh</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.donViTinh}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.hoaChat.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoaChat.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.hoaChat.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.hoaChat.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.hoaChat.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{hoaChatEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/hoa-chat" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hoa-chat/${hoaChatEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hoaChat }: IRootState) => ({
  hoaChatEntity: hoaChat.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoaChatDetail);
