import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './hinh-thai-hai-cot.reducer';
import { IHinhThaiHaiCot } from 'app/shared/model/hinh-thai-hai-cot.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHinhThaiHaiCotProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHinhThaiHaiCotState {
  search: string;
}

export class HinhThaiHaiCot extends React.Component<IHinhThaiHaiCotProps, IHinhThaiHaiCotState> {
  state: IHinhThaiHaiCotState = {
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
    const { hinhThaiHaiCotList, match } = this.props;
    return (
      <div>
        <h2 id="hinh-thai-hai-cot-heading">
          <Translate contentKey="xddtApp.hinhThaiHaiCot.home.title">Hinh Thai Hai Cots</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hinhThaiHaiCot.home.createLabel">Create new Hinh Thai Hai Cot</Translate>
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
                    placeholder={translate('xddtApp.hinhThaiHaiCot.home.search')}
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
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.giaTri">Gia Tri</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.haiCotLietSi">Hai Cot Liet Si</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hinhThaiHaiCot.loaiHinhThaiHaiCot">Loai Hinh Thai Hai Cot</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hinhThaiHaiCotList.map((hinhThaiHaiCot, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hinhThaiHaiCot.id}`} color="link" size="sm">
                      {hinhThaiHaiCot.id}
                    </Button>
                  </td>
                  <td>{hinhThaiHaiCot.giaTri}</td>
                  <td>{hinhThaiHaiCot.moTa}</td>
                  <td>{hinhThaiHaiCot.isDeleted ? 'true' : 'false'}</td>
                  <td>{hinhThaiHaiCot.udf1}</td>
                  <td>{hinhThaiHaiCot.udf2}</td>
                  <td>{hinhThaiHaiCot.udf3}</td>
                  <td>
                    {hinhThaiHaiCot.haiCotLietSi ? (
                      <Link to={`hai-cot-liet-si/${hinhThaiHaiCot.haiCotLietSi.id}`}>{hinhThaiHaiCot.haiCotLietSi.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {hinhThaiHaiCot.loaiHinhThaiHaiCot ? (
                      <Link to={`loai-hinh-thai-hai-cot/${hinhThaiHaiCot.loaiHinhThaiHaiCot.id}`}>
                        {hinhThaiHaiCot.loaiHinhThaiHaiCot.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hinhThaiHaiCot.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hinhThaiHaiCot.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hinhThaiHaiCot.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hinhThaiHaiCot }: IRootState) => ({
  hinhThaiHaiCotList: hinhThaiHaiCot.entities
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
)(HinhThaiHaiCot);
