import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './may-pcr.reducer';
import { IMayPCR } from 'app/shared/model/may-pcr.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMayPCRProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IMayPCRState {
  search: string;
}

export class MayPCR extends React.Component<IMayPCRProps, IMayPCRState> {
  state: IMayPCRState = {
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
    const { mayPCRList, match } = this.props;
    return (
      <div>
        <h2 id="may-pcr-heading">
          <Translate contentKey="xddtApp.mayPCR.home.title">May PCRS</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.mayPCR.home.createLabel">Create new May PCR</Translate>
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
                    placeholder={translate('xddtApp.mayPCR.home.search')}
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
                  <Translate contentKey="xddtApp.mayPCR.maMayPCR">Ma May PCR</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.tenMayPCR">Ten May PCR</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mayPCR.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mayPCRList.map((mayPCR, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mayPCR.id}`} color="link" size="sm">
                      {mayPCR.id}
                    </Button>
                  </td>
                  <td>{mayPCR.maMayPCR}</td>
                  <td>{mayPCR.tenMayPCR}</td>
                  <td>{mayPCR.moTa}</td>
                  <td>{mayPCR.isDeleted ? 'true' : 'false'}</td>
                  <td>{mayPCR.udf1}</td>
                  <td>{mayPCR.udf2}</td>
                  <td>{mayPCR.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mayPCR.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mayPCR.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mayPCR.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ mayPCR }: IRootState) => ({
  mayPCRList: mayPCR.entities
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
)(MayPCR);
