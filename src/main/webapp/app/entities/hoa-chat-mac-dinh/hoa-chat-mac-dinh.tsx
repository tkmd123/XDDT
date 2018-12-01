import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './hoa-chat-mac-dinh.reducer';
import { IHoaChatMacDinh } from 'app/shared/model/hoa-chat-mac-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatMacDinhProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoaChatMacDinhState {
  search: string;
}

export class HoaChatMacDinh extends React.Component<IHoaChatMacDinhProps, IHoaChatMacDinhState> {
  state: IHoaChatMacDinhState = {
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
    const { hoaChatMacDinhList, match } = this.props;
    return (
      <div>
        <h2 id="hoa-chat-mac-dinh-heading">
          <Translate contentKey="xddtApp.hoaChatMacDinh.home.title">Hoa Chat Mac Dinhs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoaChatMacDinh.home.createLabel">Create new Hoa Chat Mac Dinh</Translate>
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
                    placeholder={translate('xddtApp.hoaChatMacDinh.home.search')}
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
                  <Translate contentKey="xddtApp.hoaChatMacDinh.loaiThaoTac">Loai Thao Tac</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatMacDinh.isDefault">Is Default</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatMacDinh.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatMacDinh.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatMacDinh.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatMacDinh.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoaChatMacDinhList.map((hoaChatMacDinh, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoaChatMacDinh.id}`} color="link" size="sm">
                      {hoaChatMacDinh.id}
                    </Button>
                  </td>
                  <td>{hoaChatMacDinh.loaiThaoTac}</td>
                  <td>{hoaChatMacDinh.isDefault ? 'true' : 'false'}</td>
                  <td>{hoaChatMacDinh.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoaChatMacDinh.udf1}</td>
                  <td>{hoaChatMacDinh.udf2}</td>
                  <td>{hoaChatMacDinh.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoaChatMacDinh.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChatMacDinh.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChatMacDinh.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hoaChatMacDinh }: IRootState) => ({
  hoaChatMacDinhList: hoaChatMacDinh.entities
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
)(HoaChatMacDinh);
