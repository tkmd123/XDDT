import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './don-vi.reducer';
import { IDonVi } from 'app/shared/model/don-vi.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDonViProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IDonViState {
  search: string;
}

export class DonVi extends React.Component<IDonViProps, IDonViState> {
  state: IDonViState = {
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
    const { donViList, match } = this.props;
    return (
      <div>
        <h2 id="don-vi-heading">
          <Translate contentKey="xddtApp.donVi.home.title">Don Vis</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.donVi.home.createLabel">Create new Don Vi</Translate>
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
                    placeholder={translate('xddtApp.donVi.home.search')}
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
                  <Translate contentKey="xddtApp.donVi.maDonVi">Ma Don Vi</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.tenDonVi">Ten Don Vi</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.tenTat">Ten Tat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.phanMuc">Phan Muc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.phanCap">Phan Cap</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.phanKhoi">Phan Khoi</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.ghiChu">Ghi Chu</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.donVi.donViQuanLy">Don Vi Quan Ly</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {donViList.map((donVi, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${donVi.id}`} color="link" size="sm">
                      {donVi.id}
                    </Button>
                  </td>
                  <td>{donVi.maDonVi}</td>
                  <td>{donVi.tenDonVi}</td>
                  <td>{donVi.tenTat}</td>
                  <td>{donVi.phanMuc}</td>
                  <td>{donVi.phanCap}</td>
                  <td>{donVi.phanKhoi}</td>
                  <td>{donVi.moTa}</td>
                  <td>{donVi.ghiChu}</td>
                  <td>{donVi.isDeleted ? 'true' : 'false'}</td>
                  <td>{donVi.donViQuanLy ? <Link to={`don-vi/${donVi.donViQuanLy.id}`}>{donVi.donViQuanLy.id}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${donVi.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${donVi.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${donVi.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ donVi }: IRootState) => ({
  donViList: donVi.entities
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
)(DonVi);
