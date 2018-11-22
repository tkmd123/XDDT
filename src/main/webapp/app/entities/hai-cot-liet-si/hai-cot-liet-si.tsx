import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './hai-cot-liet-si.reducer';
import { IHaiCotLietSi } from 'app/shared/model/hai-cot-liet-si.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHaiCotLietSiProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHaiCotLietSiState {
  search: string;
}

export class HaiCotLietSi extends React.Component<IHaiCotLietSiProps, IHaiCotLietSiState> {
  state: IHaiCotLietSiState = {
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
    const { haiCotLietSiList, match } = this.props;
    return (
      <div>
        <h2 id="hai-cot-liet-si-heading">
          <Translate contentKey="xddtApp.haiCotLietSi.home.title">Hai Cot Liet Sis</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.haiCotLietSi.home.createLabel">Create new Hai Cot Liet Si</Translate>
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
                    placeholder={translate('xddtApp.haiCotLietSi.home.search')}
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
                  <Translate contentKey="xddtApp.haiCotLietSi.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.haiCotLietSi.coHaiCot">Co Hai Cot</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.haiCotLietSi.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.haiCotLietSi.lietSi">Liet Si</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.haiCotLietSi.thongTinKhaiQuat">Thong Tin Khai Quat</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {haiCotLietSiList.map((haiCotLietSi, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${haiCotLietSi.id}`} color="link" size="sm">
                      {haiCotLietSi.id}
                    </Button>
                  </td>
                  <td>{haiCotLietSi.moTa}</td>
                  <td>{haiCotLietSi.coHaiCot ? 'true' : 'false'}</td>
                  <td>{haiCotLietSi.isDeleted ? 'true' : 'false'}</td>
                  <td>{haiCotLietSi.lietSi ? <Link to={`ho-so-liet-si/${haiCotLietSi.lietSi.id}`}>{haiCotLietSi.lietSi.id}</Link> : ''}</td>
                  <td>
                    {haiCotLietSi.thongTinKhaiQuat ? (
                      <Link to={`thong-tin-khai-quat/${haiCotLietSi.thongTinKhaiQuat.id}`}>{haiCotLietSi.thongTinKhaiQuat.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${haiCotLietSi.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${haiCotLietSi.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${haiCotLietSi.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ haiCotLietSi }: IRootState) => ({
  haiCotLietSiList: haiCotLietSi.entities
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
)(HaiCotLietSi);
