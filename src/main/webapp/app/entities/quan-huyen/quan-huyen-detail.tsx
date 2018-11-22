import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './quan-huyen.reducer';
import { IQuanHuyen } from 'app/shared/model/quan-huyen.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IQuanHuyenDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class QuanHuyenDetail extends React.Component<IQuanHuyenDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { quanHuyenEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.quanHuyen.detail.title">QuanHuyen</Translate> [<b>{quanHuyenEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maHuyen">
                <Translate contentKey="xddtApp.quanHuyen.maHuyen">Ma Huyen</Translate>
              </span>
            </dt>
            <dd>{quanHuyenEntity.maHuyen}</dd>
            <dt>
              <span id="tenHuyen">
                <Translate contentKey="xddtApp.quanHuyen.tenHuyen">Ten Huyen</Translate>
              </span>
            </dt>
            <dd>{quanHuyenEntity.tenHuyen}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.quanHuyen.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{quanHuyenEntity.moTa}</dd>
            <dt>
              <Translate contentKey="xddtApp.quanHuyen.tinhThanh">Tinh Thanh</Translate>
            </dt>
            <dd>{quanHuyenEntity.tinhThanh ? quanHuyenEntity.tinhThanh.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/quan-huyen" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/quan-huyen/${quanHuyenEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ quanHuyen }: IRootState) => ({
  quanHuyenEntity: quanHuyen.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(QuanHuyenDetail);
