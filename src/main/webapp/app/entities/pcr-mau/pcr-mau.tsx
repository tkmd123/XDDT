import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './pcr-mau.reducer';
import { IPCRMau } from 'app/shared/model/pcr-mau.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPCRMauProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IPCRMauState {
  search: string;
}

export class PCRMau extends React.Component<IPCRMauProps, IPCRMauState> {
  state: IPCRMauState = {
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
    const { pCRMauList, match } = this.props;
    return (
      <div>
        <h2 id="pcr-mau-heading">
          <Translate contentKey="xddtApp.pCRMau.home.title">PCR Maus</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.pCRMau.home.createLabel">Create new PCR Mau</Translate>
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
                    placeholder={translate('xddtApp.pCRMau.home.search')}
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
                  <Translate contentKey="xddtApp.pCRMau.nongDoAgaros">Nong Do Agaros</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.pcrKetQua">Pcr Ket Qua</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.pcrMau">Pcr Mau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.mauPCR">Mau PCR</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.pCRMau.moiPCR">Moi PCR</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {pCRMauList.map((pCRMau, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${pCRMau.id}`} color="link" size="sm">
                      {pCRMau.id}
                    </Button>
                  </td>
                  <td>{pCRMau.nongDoAgaros}</td>
                  <td>{pCRMau.isDeleted ? 'true' : 'false'}</td>
                  <td>{pCRMau.udf1}</td>
                  <td>{pCRMau.udf2}</td>
                  <td>{pCRMau.udf3}</td>
                  <td>{pCRMau.pcrKetQua ? <Link to={`pcr-ket-qua/${pCRMau.pcrKetQua.id}`}>{pCRMau.pcrKetQua.id}</Link> : ''}</td>
                  <td>{pCRMau.pcrMau ? <Link to={`pcr/${pCRMau.pcrMau.id}`}>{pCRMau.pcrMau.id}</Link> : ''}</td>
                  <td>{pCRMau.mauPCR ? <Link to={`mau-xet-nghiem/${pCRMau.mauPCR.id}`}>{pCRMau.mauPCR.id}</Link> : ''}</td>
                  <td>{pCRMau.moiPCR ? <Link to={`pcr-moi/${pCRMau.moiPCR.id}`}>{pCRMau.moiPCR.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${pCRMau.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCRMau.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${pCRMau.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ pCRMau }: IRootState) => ({
  pCRMauList: pCRMau.entities
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
)(PCRMau);
