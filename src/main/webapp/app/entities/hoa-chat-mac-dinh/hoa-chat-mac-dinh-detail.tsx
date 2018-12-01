import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hoa-chat-mac-dinh.reducer';
import { IHoaChatMacDinh } from 'app/shared/model/hoa-chat-mac-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatMacDinhDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoaChatMacDinhDetail extends React.Component<IHoaChatMacDinhDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoaChatMacDinhEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoaChatMacDinh.detail.title">HoaChatMacDinh</Translate> [<b>{hoaChatMacDinhEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="loaiThaoTac">
                <Translate contentKey="xddtApp.hoaChatMacDinh.loaiThaoTac">Loai Thao Tac</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.loaiThaoTac}</dd>
            <dt>
              <span id="isDefault">
                <Translate contentKey="xddtApp.hoaChatMacDinh.isDefault">Is Default</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.isDefault ? 'true' : 'false'}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoaChatMacDinh.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.hoaChatMacDinh.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.hoaChatMacDinh.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.hoaChatMacDinh.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{hoaChatMacDinhEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/hoa-chat-mac-dinh" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/hoa-chat-mac-dinh/${hoaChatMacDinhEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hoaChatMacDinh }: IRootState) => ({
  hoaChatMacDinhEntity: hoaChatMacDinh.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoaChatMacDinhDetail);
