import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './pcr.reducer';
import { IPCR } from 'app/shared/model/pcr.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IPCRState {
  search: string;
}

export class PCR extends React.Component<IPCRProps, IPCRState> {
  state: IPCRState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.props.getEntities();
    this.setState({
      search: ''
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { pCRList, match } = this.props;
    return (
      <div>
        <h2 id="pcr-heading">
          <Translate contentKey="xddtApp.pCR.home.title">PCRS</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.pCR.home.createLabel">Create new PCR</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('xddtApp.pCR.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.maPCR">Ma PCR</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.nhanXet">Nhan Xet</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.thoiGianBatDau">Thoi Gian Bat Dau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.congSuatBatDau">Cong Suat Bat Dau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.cuongDoBatDau">Cuong Do Bat Dau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.dienTheBatDau">Dien The Bat Dau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.thoiGianKetThuc">Thoi Gian Ket Thuc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.congSuatKetThuc">Cong Suat Ket Thuc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.cuongDoKetThuc">Cuong Do Ket Thuc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.dienTheKetThuc">Dien The Ket Thuc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.udf4">Udf 4</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.udf5">Udf 5</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.mayPCR">May PCR</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCR.nhanVienPCR">Nhan Vien PCR</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pCRList.map((pCR, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pCR.id}`} color="link" size="sm">
                      {pCR.id}
                    </Button>
                  </td>
                  <td>{pCR.maPCR}</td>
                  <td>
                    <TextFormat type="date" value={pCR.thoiGianThucHien} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pCR.nhanXet}</td>
                  <td>
                    <TextFormat type="date" value={pCR.thoiGianBatDau} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pCR.congSuatBatDau}</td>
                  <td>{pCR.cuongDoBatDau}</td>
                  <td>{pCR.dienTheBatDau}</td>
                  <td>
                    <TextFormat type="date" value={pCR.thoiGianKetThuc} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{pCR.congSuatKetThuc}</td>
                  <td>{pCR.cuongDoKetThuc}</td>
                  <td>{pCR.dienTheKetThuc}</td>
                  <td>{pCR.isDeleted ? 'true' : 'false'}</td>
                  <td>{pCR.udf1}</td>
                  <td>{pCR.udf2}</td>
                  <td>{pCR.udf3}</td>
                  <td>{pCR.udf4}</td>
                  <td>{pCR.udf5}</td>
                  <td>{pCR.mayPCR ? <Link to={`may-pcr/${pCR.mayPCR.id}`}>{pCR.mayPCR.id}</Link> : ''}</td>
                  <td>{pCR.nhanVienPCR ? <Link to={`nhan-vien/${pCR.nhanVienPCR.id}`}>{pCR.nhanVienPCR.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pCR.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCR.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCR.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ pCR }: IRootState) => ({
  pCRList: pCR.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCR);
