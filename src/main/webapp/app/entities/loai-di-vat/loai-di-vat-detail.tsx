import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './loai-di-vat.reducer';
import { ILoaiDiVat } from 'app/shared/model/loai-di-vat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILoaiDiVatDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoaiDiVatDetail extends React.Component<ILoaiDiVatDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { loaiDiVatEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="xddtApp.loaiDiVat.detail.title">LoaiDiVat</Translate> [<b>{loaiDiVatEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="maDiVat">
                <Translate contentKey="xddtApp.loaiDiVat.maDiVat">Ma Di Vat</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.maDiVat}</dd>
            <dt>
              <span id="loaiDiVat">
                <Translate contentKey="xddtApp.loaiDiVat.loaiDiVat">Loai Di Vat</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.loaiDiVat}</dd>
            <dt>
              <span id="moTa">
                <Translate contentKey="xddtApp.loaiDiVat.moTa">Mo Ta</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.moTa}</dd>
            <dt>
              <span id="isDeleted">
                <Translate contentKey="xddtApp.loaiDiVat.isDeleted">Is Deleted</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.isDeleted ? 'true' : 'false'}</dd>
            <dt>
              <span id="udf1">
                <Translate contentKey="xddtApp.loaiDiVat.udf1">Udf 1</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.udf1}</dd>
            <dt>
              <span id="udf2">
                <Translate contentKey="xddtApp.loaiDiVat.udf2">Udf 2</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.udf2}</dd>
            <dt>
              <span id="udf3">
                <Translate contentKey="xddtApp.loaiDiVat.udf3">Udf 3</Translate>
              </span>
            </dt>
            <dd>{loaiDiVatEntity.udf3}</dd>
          </dl>
          <Button tag={Link} to="/entity/loai-di-vat" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/loai-di-vat/${loaiDiVatEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ loaiDiVat }: IRootState) => ({
  loaiDiVatEntity: loaiDiVat.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiDiVatDetail);
