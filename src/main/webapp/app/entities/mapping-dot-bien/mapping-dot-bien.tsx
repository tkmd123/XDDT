import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './mapping-dot-bien.reducer';
import { IMappingDotBien } from 'app/shared/model/mapping-dot-bien.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMappingDotBienProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IMappingDotBienState {
  search: string;
}

export class MappingDotBien extends React.Component<IMappingDotBienProps, IMappingDotBienState> {
  state: IMappingDotBienState = {
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
    const { mappingDotBienList, match } = this.props;
    return (
      <div>
        <h2 id="mapping-dot-bien-heading">
          <Translate contentKey="xddtApp.mappingDotBien.home.title">Mapping Dot Biens</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.mappingDotBien.home.createLabel">Create new Mapping Dot Bien</Translate>
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
                    placeholder={translate('xddtApp.mappingDotBien.home.search')}
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
                  <Translate contentKey="xddtApp.mappingDotBien.maDotBien">Ma Dot Bien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mappingDotBien.maMapping">Ma Mapping</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mappingDotBien.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mappingDotBien.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mappingDotBien.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mappingDotBien.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mappingDotBienList.map((mappingDotBien, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mappingDotBien.id}`} color="link" size="sm">
                      {mappingDotBien.id}
                    </Button>
                  </td>
                  <td>{mappingDotBien.maDotBien}</td>
                  <td>{mappingDotBien.maMapping}</td>
                  <td>{mappingDotBien.isDeleted ? 'true' : 'false'}</td>
                  <td>{mappingDotBien.udf1}</td>
                  <td>{mappingDotBien.udf2}</td>
                  <td>{mappingDotBien.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mappingDotBien.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mappingDotBien.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mappingDotBien.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ mappingDotBien }: IRootState) => ({
  mappingDotBienList: mappingDotBien.entities
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
)(MappingDotBien);
