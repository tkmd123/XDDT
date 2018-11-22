import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ho-so-than-nhan.reducer';
import { IHoSoThanNhan } from 'app/shared/model/ho-so-than-nhan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoSoThanNhanDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoSoThanNhanDetail extends React.Component<IHoSoThanNhanDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { hoSoThanNhanEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.hoSoThanNhan.detail.title">HoSoThanNhan</Translate> [<b>{hoSoThanNhanEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maThanNhan">
                <Translate contentKey="xddtApp.hoSoThanNhan.maThanNhan">Ma Than Nhan</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.maThanNhan}</dd>
            <dt>
              <span id="isLayMau">
                <Translate contentKey="xddtApp.hoSoThanNhan.isLayMau">Is Lay Mau</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.isLayMau ? 'true' : 'false'}</dd>
            <dt>
              <span id="hoTen">
                <Translate contentKey="xddtApp.hoSoThanNhan.hoTen">Ho Ten</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.hoTen}</dd>
            <dt>
              <span id="namSinh">
                <Translate contentKey="xddtApp.hoSoThanNhan.namSinh">Nam Sinh</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={hoSoThanNhanEntity.namSinh} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="gioiTinh">
                <Translate contentKey="xddtApp.hoSoThanNhan.gioiTinh">Gioi Tinh</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.gioiTinh}</dd>
            <dt>
              <span id="soCMT">
                <Translate contentKey="xddtApp.hoSoThanNhan.soCMT">So CMT</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.soCMT}</dd>
            <dt>
              <span id="diaChi">
                <Translate contentKey="xddtApp.hoSoThanNhan.diaChi">Dia Chi</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.diaChi}</dd>
            <dt>
              <span id="dienThoaiChinh">
                <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiChinh">Dien Thoai Chinh</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.dienThoaiChinh}</dd>
            <dt>
              <span id="dienThoaiPhu">
                <Translate contentKey="xddtApp.hoSoThanNhan.dienThoaiPhu">Dien Thoai Phu</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.dienThoaiPhu}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="xddtApp.hoSoThanNhan.email">Email</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.email}</dd>
            <dt>
              <span id="ghiChu">
                <Translate contentKey="xddtApp.hoSoThanNhan.ghiChu">Ghi Chu</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.ghiChu}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.hoSoThanNhan.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="uDF1">
                <Translate contentKey="xddtApp.hoSoThanNhan.uDF1">U DF 1</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.uDF1}</dd>
            <dt>
              <span id="uDF2">
                <Translate contentKey="xddtApp.hoSoThanNhan.uDF2">U DF 2</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.uDF2}</dd>
            <dt>
              <span id="uDF3">
                <Translate contentKey="xddtApp.hoSoThanNhan.uDF3">U DF 3</Translate>
              </span>
            </dt>
            <dd>{hoSoThanNhanEntity.uDF3}</dd>
            <dt>
              <Translate contentKey="xddtApp.hoSoThanNhan.phuongXa">Phuong Xa</Translate>
            </dt>
            <dd>{hoSoThanNhanEntity.phuongXa ? hoSoThanNhanEntity.phuongXa.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ho-so-than-nhan" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ho-so-than-nhan/${hoSoThanNhanEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ hoSoThanNhan }: IRootState) => ({
  hoSoThanNhanEntity: hoSoThanNhan.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoSoThanNhanDetail);
