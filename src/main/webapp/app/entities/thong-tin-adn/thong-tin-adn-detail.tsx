import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './thong-tin-adn.reducer';
import { IThongTinADN } from 'app/shared/model/thong-tin-adn.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IThongTinADNDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThongTinADNDetail extends React.Component<IThongTinADNDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { thongTinADNEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.thongTinADN.detail.title">ThongTinADN</Translate> [<b>{thongTinADNEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.thongTinADN.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{thongTinADNEntity.moTa}</dd>
            <dt>
              <span id="fileDuLieu">
                <Translate contentKey="xddtApp.thongTinADN.fileDuLieu">File Du Lieu</Translate>
              </span>
            </dt>
            <dd>{thongTinADNEntity.fileDuLieu}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.thongTinADN.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{thongTinADNEntity.isDeleted ? 'true' : 'false'}</dd>
          </dl>
          <Button tag={Link} to="/entity/thong-tin-adn" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/thong-tin-adn/${thongTinADNEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ thongTinADN }: IRootState) => ({
  thongTinADNEntity: thongTinADN.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThongTinADNDetail);
