import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './hoa-chat-tach-chiet.reducer';
import { IHoaChatTachChiet } from 'app/shared/model/hoa-chat-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatTachChietProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoaChatTachChietState {
  search: string;
}

export class HoaChatTachChiet extends React.Component<IHoaChatTachChietProps, IHoaChatTachChietState> {
  state: IHoaChatTachChietState = {
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
    const { hoaChatTachChietList, match } = this.props;
    return (
      <div>
        <h2 id="hoa-chat-tach-chiet-heading">
          <Translate contentKey="xddtApp.hoaChatTachChiet.home.title">Hoa Chat Tach Chiets</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoaChatTachChiet.home.createLabel">Create new Hoa Chat Tach Chiet</Translate>
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
                    placeholder={translate('xddtApp.hoaChatTachChiet.home.search')}
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
                  <Translate contentKey="xddtApp.hoaChatTachChiet.soLuong">So Luong</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.hoaChat">Hoa Chat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChatTachChiet.tachChiet">Tach Chiet</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoaChatTachChietList.map((hoaChatTachChiet, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoaChatTachChiet.id}`} color="link" size="sm">
                      {hoaChatTachChiet.id}
                    </Button>
                  </td>
                  <td>{hoaChatTachChiet.soLuong}</td>
                  <td>{hoaChatTachChiet.moTa}</td>
                  <td>{hoaChatTachChiet.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoaChatTachChiet.udf1}</td>
                  <td>{hoaChatTachChiet.udf2}</td>
                  <td>{hoaChatTachChiet.udf3}</td>
                  <td>
                    {hoaChatTachChiet.hoaChat ? (
                      <Link to={`hoa-chat/${hoaChatTachChiet.hoaChat.id}`}>{hoaChatTachChiet.hoaChat.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {hoaChatTachChiet.tachChiet ? (
                      <Link to={`tach-chiet/${hoaChatTachChiet.tachChiet.id}`}>{hoaChatTachChiet.tachChiet.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoaChatTachChiet.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChatTachChiet.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChatTachChiet.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hoaChatTachChiet }: IRootState) => ({
  hoaChatTachChietList: hoaChatTachChiet.entities
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
)(HoaChatTachChiet);
