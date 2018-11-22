import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './don-vi-thoi-ky.reducer';
import { IDonViThoiKy } from 'app/shared/model/don-vi-thoi-ky.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDonViThoiKyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IDonViThoiKyState {
  search: string;
}

export class DonViThoiKy extends React.Component<IDonViThoiKyProps, IDonViThoiKyState> {
  state: IDonViThoiKyState = {
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
    const { donViThoiKyList, match } = this.props;
    return (
      <div>
        <h2 id="don-vi-thoi-ky-heading">
          <Translate contentKey="xddtApp.donViThoiKy.home.title">Don Vi Thoi Kies</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.donViThoiKy.home.createLabel">Create new Don Vi Thoi Ky</Translate>
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
                    placeholder={translate('xddtApp.donViThoiKy.home.search')}
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
                  <Translate contentKey="xddtApp.donViThoiKy.tuNam">Tu Nam</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.denNam">Den Nam</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.diaDiemMoTa">Dia Diem Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.diaDiemXa">Dia Diem Xa</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.diaDiemHuyen">Dia Diem Huyen</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.diaDiemTinh">Dia Diem Tinh</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.ghiChu">Ghi Chu</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donViThoiKy.donVi">Don Vi</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {donViThoiKyList.map((donViThoiKy, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${donViThoiKy.id}`} color="link" size="sm">
                      {donViThoiKy.id}
                    </Button>
                  </td>
                  <td>{donViThoiKy.tuNam}</td>
                  <td>{donViThoiKy.denNam}</td>
                  <td>{donViThoiKy.diaDiemMoTa}</td>
                  <td>{donViThoiKy.diaDiemXa}</td>
                  <td>{donViThoiKy.diaDiemHuyen}</td>
                  <td>{donViThoiKy.diaDiemTinh}</td>
                  <td>{donViThoiKy.isDeleted ? 'true' : 'false'}</td>
                  <td>{donViThoiKy.ghiChu}</td>
                  <td>{donViThoiKy.donVi ? <Link to={`don-vi/${donViThoiKy.donVi.id}`}>{donViThoiKy.donVi.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${donViThoiKy.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${donViThoiKy.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${donViThoiKy.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ donViThoiKy }: IRootState) => ({
  donViThoiKyList: donViThoiKy.entities
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
)(DonViThoiKy);
