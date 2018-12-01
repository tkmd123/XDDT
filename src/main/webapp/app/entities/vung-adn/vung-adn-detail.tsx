import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vung-adn.reducer';
import { IVungADN } from 'app/shared/model/vung-adn.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVungADNDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class VungADNDetail extends React.Component<IVungADNDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { vungADNEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.vungADN.detail.title">VungADN</Translate> [<b>{vungADNEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maVungADN">
                <Translate contentKey="xddtApp.vungADN.maVungADN">Ma Vung ADN</Translate>
              </span>
            </dt>
            <dd>{vungADNEntity.maVungADN}</dd>
            <dt>
              <span id="tenVungADN">
                <Translate contentKey="xddtApp.vungADN.tenVungADN">Ten Vung ADN</Translate>
              </span>
            </dt>
            <dd>{vungADNEntity.tenVungADN}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.vungADN.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{vungADNEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.vungADN.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{vungADNEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/vung-adn" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/vung-adn/${vungADNEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ vungADN }: IRootState) => ({
  vungADNEntity: vungADN.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VungADNDetail);
