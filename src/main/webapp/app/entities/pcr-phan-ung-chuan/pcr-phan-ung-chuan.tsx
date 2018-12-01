import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './pcr-phan-ung-chuan.reducer';
import { IPCRPhanUngChuan } from 'app/shared/model/pcr-phan-ung-chuan.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRPhanUngChuanProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IPCRPhanUngChuanState {
  search: string;
}

export class PCRPhanUngChuan extends React.Component<IPCRPhanUngChuanProps, IPCRPhanUngChuanState> {
  state: IPCRPhanUngChuanState = {
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
    const { pCRPhanUngChuanList, match } = this.props;
    return (
      <div>
        <h2 id="pcr-phan-ung-chuan-heading">
          <Translate contentKey="xddtApp.pCRPhanUngChuan.home.title">PCR Phan Ung Chuans</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.pCRPhanUngChuan.home.createLabel">Create new PCR Phan Ung Chuan</Translate>
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
                    placeholder={translate('xddtApp.pCRPhanUngChuan.home.search')}
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
                  <Translate contentKey="xddtApp.pCRPhanUngChuan.chuKyPhanUng">Chu Ky Phan Ung</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRPhanUngChuan.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRPhanUngChuan.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRPhanUngChuan.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRPhanUngChuan.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pCRPhanUngChuanList.map((pCRPhanUngChuan, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pCRPhanUngChuan.id}`} color="link" size="sm">
                      {pCRPhanUngChuan.id}
                    </Button>
                  </td>
                  <td>{pCRPhanUngChuan.chuKyPhanUng}</td>
                  <td>{pCRPhanUngChuan.isDeleted ? 'true' : 'false'}</td>
                  <td>{pCRPhanUngChuan.udf1}</td>
                  <td>{pCRPhanUngChuan.udf2}</td>
                  <td>{pCRPhanUngChuan.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pCRPhanUngChuan.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCRPhanUngChuan.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCRPhanUngChuan.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pCRPhanUngChuan }: IRootState) => ({
  pCRPhanUngChuanList: pCRPhanUngChuan.entities
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
)(PCRPhanUngChuan);
