import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './diem-dot-bien.reducer';
import { IDiemDotBien } from 'app/shared/model/diem-dot-bien.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiemDotBienDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DiemDotBienDetail extends React.Component<IDiemDotBienDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { diemDotBienEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.diemDotBien.detail.title">DiemDotBien</Translate> [<b>{diemDotBienEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="viTri">
                <Translate contentKey="xddtApp.diemDotBien.viTri">Vi Tri</Translate>
              </span>
            </dt>
            <dd>{diemDotBienEntity.viTri}</dd>
            <dt>
              <span id="giaTri">
                <Translate contentKey="xddtApp.diemDotBien.giaTri">Gia Tri</Translate>
              </span>
            </dt>
            <dd>{diemDotBienEntity.giaTri}</dd>
            <dt>
              <span id="giaTri1">
                <Translate contentKey="xddtApp.diemDotBien.giaTri1">Gia Tri 1</Translate>
              </span>
            </dt>
            <dd>{diemDotBienEntity.giaTri1}</dd>
            <dt>
              <span id="giaTri2">
                <Translate contentKey="xddtApp.diemDotBien.giaTri2">Gia Tri 2</Translate>
              </span>
            </dt>
            <dd>{diemDotBienEntity.giaTri2}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.diemDotBien.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{diemDotBienEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <Translate contentKey="xddtApp.diemDotBien.vungADN">Vung ADN</Translate>
            </dt>
            <dd>{diemDotBienEntity.vungADN ? diemDotBienEntity.vungADN.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.diemDotBien.thongTinADN">Thong Tin ADN</Translate>
            </dt>
            <dd>{diemDotBienEntity.thongTinADN ? diemDotBienEntity.thongTinADN.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/diem-dot-bien" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/diem-dot-bien/${diemDotBienEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ diemDotBien }: IRootState) => ({
  diemDotBienEntity: diemDotBien.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DiemDotBienDetail);
