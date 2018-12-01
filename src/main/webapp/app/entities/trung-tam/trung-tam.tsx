import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './trung-tam.reducer';
import { ITrungTam } from 'app/shared/model/trung-tam.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrungTamProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITrungTamState {
  search: string;
}

export class TrungTam extends React.Component<ITrungTamProps, ITrungTamState> {
  state: ITrungTamState = {
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
    const { trungTamList, match } = this.props;
    return (
      <div>
        <h2 id="trung-tam-heading">
          <Translate contentKey="xddtApp.trungTam.home.title">Trung Tams</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.trungTam.home.createLabel">Create new Trung Tam</Translate>
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
                    placeholder={translate('xddtApp.trungTam.home.search')}
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
                  <Translate contentKey="xddtApp.trungTam.maTrungTam">Ma Trung Tam</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.tenTrungTam">Ten Trung Tam</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.trungTam.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {trungTamList.map((trungTam, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${trungTam.id}`} color="link" size="sm">
                      {trungTam.id}
                    </Button>
                  </td>
                  <td>{trungTam.maTrungTam}</td>
                  <td>{trungTam.tenTrungTam}</td>
                  <td>{trungTam.moTa}</td>
                  <td>{trungTam.isDeleted ? 'true' : 'false'}</td>
                  <td>{trungTam.udf1}</td>
                  <td>{trungTam.udf2}</td>
                  <td>{trungTam.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${trungTam.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trungTam.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trungTam.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ trungTam }: IRootState) => ({
  trungTamList: trungTam.entities
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
)(TrungTam);
