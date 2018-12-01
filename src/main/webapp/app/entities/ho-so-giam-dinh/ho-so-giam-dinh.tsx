import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './ho-so-giam-dinh.reducer';
import { IHoSoGiamDinh } from 'app/shared/model/ho-so-giam-dinh.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHoSoGiamDinhProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IHoSoGiamDinhState {
  search: string;
}

export class HoSoGiamDinh extends React.Component<IHoSoGiamDinhProps, IHoSoGiamDinhState> {
  state: IHoSoGiamDinhState = {
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
    const { hoSoGiamDinhList, match } = this.props;
    return (
      <div>
        <h2 id="ho-so-giam-dinh-heading">
          <Translate contentKey="xddtApp.hoSoGiamDinh.home.title">Ho So Giam Dinhs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.hoSoGiamDinh.home.createLabel">Create new Ho So Giam Dinh</Translate>
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
                    placeholder={translate('xddtApp.hoSoGiamDinh.home.search')}
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
                  <Translate contentKey="xddtApp.hoSoGiamDinh.maHSGD">Ma HSGD</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhThanNhan">Giam Dinh Than Nhan</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.giamDinhLietSi">Giam Dinh Liet Si</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.hoSoGiamDinh.nhanVienHSGD">Nhan Vien HSGD</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {hoSoGiamDinhList.map((hoSoGiamDinh, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${hoSoGiamDinh.id}`} color="link" size="sm">
                      {hoSoGiamDinh.id}
                    </Button>
                  </td>
                  <td>{hoSoGiamDinh.maHSGD}</td>
                  <td>{hoSoGiamDinh.isDeleted ? 'true' : 'false'}</td>
                  <td>{hoSoGiamDinh.udf1}</td>
                  <td>{hoSoGiamDinh.udf2}</td>
                  <td>{hoSoGiamDinh.udf3}</td>
                  <td>
                    {hoSoGiamDinh.giamDinhThanNhans
                      ? hoSoGiamDinh.giamDinhThanNhans.map((val, j) => (
                          <span key={j}>
                            <Link to={`ho-so-than-nhan/${val.id}`}>{val.id}</Link>
                            {j === hoSoGiamDinh.giamDinhThanNhans.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {hoSoGiamDinh.giamDinhLietSis
                      ? hoSoGiamDinh.giamDinhLietSis.map((val, j) => (
                          <span key={j}>
                            <Link to={`ho-so-liet-si/${val.id}`}>{val.id}</Link>
                            {j === hoSoGiamDinh.giamDinhLietSis.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {hoSoGiamDinh.nhanVienHSGD ? (
                      <Link to={`nhan-vien/${hoSoGiamDinh.nhanVienHSGD.id}`}>{hoSoGiamDinh.nhanVienHSGD.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${hoSoGiamDinh.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoGiamDinh.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${hoSoGiamDinh.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ hoSoGiamDinh }: IRootState) => ({
  hoSoGiamDinhList: hoSoGiamDinh.entities
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
)(HoSoGiamDinh);
