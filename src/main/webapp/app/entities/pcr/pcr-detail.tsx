import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './pcr.reducer';
import { IPCR } from 'app/shared/model/pcr.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRDetail extends React.Component<IPCRDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { pCREntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.pCR.detail.title">PCR</Translate> [<b>{pCREntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maPCR">
                <Translate contentKey="xddtApp.pCR.maPCR">Ma PCR</Translate>
              </span>
            </dt>
            <dd>{pCREntity.maPCR}</dd>
            <dt>
              <span id="thoiGianThucHien">
                <Translate contentKey="xddtApp.pCR.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={pCREntity.thoiGianThucHien} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="nhanXet">
                <Translate contentKey="xddtApp.pCR.nhanXet">Nhan Xet</Translate>
              </span>
            </dt>
            <dd>{pCREntity.nhanXet}</dd>
            <dt>
              <span id="thoiGianBatDau">
                <Translate contentKey="xddtApp.pCR.thoiGianBatDau">Thoi Gian Bat Dau</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={pCREntity.thoiGianBatDau} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="congSuatBatDau">
                <Translate contentKey="xddtApp.pCR.congSuatBatDau">Cong Suat Bat Dau</Translate>
              </span>
            </dt>
            <dd>{pCREntity.congSuatBatDau}</dd>
            <dt>
              <span id="cuongDoBatDau">
                <Translate contentKey="xddtApp.pCR.cuongDoBatDau">Cuong Do Bat Dau</Translate>
              </span>
            </dt>
            <dd>{pCREntity.cuongDoBatDau}</dd>
            <dt>
              <span id="dienTheBatDau">
                <Translate contentKey="xddtApp.pCR.dienTheBatDau">Dien The Bat Dau</Translate>
              </span>
            </dt>
            <dd>{pCREntity.dienTheBatDau}</dd>
            <dt>
              <span id="thoiGianKetThuc">
                <Translate contentKey="xddtApp.pCR.thoiGianKetThuc">Thoi Gian Ket Thuc</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={pCREntity.thoiGianKetThuc} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="congSuatKetThuc">
                <Translate contentKey="xddtApp.pCR.congSuatKetThuc">Cong Suat Ket Thuc</Translate>
              </span>
            </dt>
            <dd>{pCREntity.congSuatKetThuc}</dd>
            <dt>
              <span id="cuongDoKetThuc">
                <Translate contentKey="xddtApp.pCR.cuongDoKetThuc">Cuong Do Ket Thuc</Translate>
              </span>
            </dt>
            <dd>{pCREntity.cuongDoKetThuc}</dd>
            <dt>
              <span id="dienTheKetThuc">
                <Translate contentKey="xddtApp.pCR.dienTheKetThuc">Dien The Ket Thuc</Translate>
              </span>
            </dt>
            <dd>{pCREntity.dienTheKetThuc}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.pCR.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{pCREntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.pCR.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{pCREntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.pCR.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{pCREntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.pCR.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{pCREntity.udf3}</dd>
            <dt>
              <span id="udf4">
                <Translate contentKey="xddtApp.pCR.udf4">Udf 4</Translate>
              </span>
            </dt>
            <dd>{pCREntity.udf4}</dd>
            <dt>
              <span id="udf5">
                <Translate contentKey="xddtApp.pCR.udf5">Udf 5</Translate>
              </span>
            </dt>
            <dd>{pCREntity.udf5}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCR.mayPCR">May PCR</Translate>
            </dt>
            <dd>{pCREntity.mayPCR ? pCREntity.mayPCR.id : ''}</dd>
            <dt>
              <Translate contentKey="xddtApp.pCR.nhanVienPCR">Nhan Vien PCR</Translate>
            </dt>
            <dd>{pCREntity.nhanVienPCR ? pCREntity.nhanVienPCR.id : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/pcr" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/pcr/${pCREntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ pCR }: IRootState) => ({
  pCREntity: pCR.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRDetail);
