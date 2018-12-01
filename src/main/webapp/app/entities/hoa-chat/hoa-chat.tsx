import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './hoa-chat.reducer';
import { IHoaChat } from 'app/shared/model/hoa-chat.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoaChatProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoaChatState {
  search: string;
}

export class HoaChat extends React.Component<IHoaChatProps, IHoaChatState> {
  state: IHoaChatState = {
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
    const { hoaChatList, match } = this.props;
    return (
      <div>
        <h2 id="hoa-chat-heading">
          <Translate contentKey="xddtApp.hoaChat.home.title">Hoa Chats</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoaChat.home.createLabel">Create new Hoa Chat</Translate>
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
                    placeholder={translate('xddtApp.hoaChat.home.search')}
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
                  <Translate contentKey="xddtApp.hoaChat.maHoaChat">Ma Hoa Chat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.tenHoaChat">Ten Hoa Chat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.hangHoaChat">Hang Hoa Chat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.donViTinh">Don Vi Tinh</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoaChat.udf3">Udf 3</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoaChatList.map((hoaChat, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoaChat.id}`} color="link" size="sm">
                      {hoaChat.id}
                    </Button>
                  </td>
                  <td>{hoaChat.maHoaChat}</td>
                  <td>{hoaChat.tenHoaChat}</td>
                  <td>{hoaChat.hangHoaChat}</td>
                  <td>{hoaChat.donViTinh}</td>
                  <td>{hoaChat.moTa}</td>
                  <td>{hoaChat.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoaChat.udf1}</td>
                  <td>{hoaChat.udf2}</td>
                  <td>{hoaChat.udf3}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoaChat.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChat.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoaChat.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hoaChat }: IRootState) => ({
  hoaChatList: hoaChat.entities
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
)(HoaChat);
