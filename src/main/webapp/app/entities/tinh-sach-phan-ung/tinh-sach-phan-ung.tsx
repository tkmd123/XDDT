import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './tinh-sach-phan-ung.reducer';
import { ITinhSachPhanUng } from 'app/shared/model/tinh-sach-phan-ung.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITinhSachPhanUngProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITinhSachPhanUngState {
  search: string;
}

export class TinhSachPhanUng extends React.Component<ITinhSachPhanUngProps, ITinhSachPhanUngState> {
  state: ITinhSachPhanUngState = {
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
    const { tinhSachPhanUngList, match } = this.props;
    return (
      <div>
        <h2 id="tinh-sach-phan-ung-heading">
          <Translate contentKey="xddtApp.tinhSachPhanUng.home.title">Tinh Sach Phan Ungs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.tinhSachPhanUng.home.createLabel">Create new Tinh Sach Phan Ung</Translate>
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
                    placeholder={translate('xddtApp.tinhSachPhanUng.home.search')}
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
                  <Translate contentKey="xddtApp.tinhSachPhanUng.dungTichSuDung">Dung Tich Su Dung</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.chuTrinhNhietDo">Chu Trinh Nhiet Do</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.tinhSach">Tinh Sach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tinhSachPhanUng.hoaChatTinhSach">Hoa Chat Tinh Sach</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tinhSachPhanUngList.map((tinhSachPhanUng, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tinhSachPhanUng.id}`} color="link" size="sm">
                      {tinhSachPhanUng.id}
                    </Button>
                  </td>
                  <td>{tinhSachPhanUng.dungTichSuDung}</td>
                  <td>{tinhSachPhanUng.chuTrinhNhietDo}</td>
                  <td>{tinhSachPhanUng.isDeleted ? 'true' : 'false'}</td>
                  <td>{tinhSachPhanUng.udf1}</td>
                  <td>{tinhSachPhanUng.udf2}</td>
                  <td>{tinhSachPhanUng.udf3}</td>
                  <td>
                    {tinhSachPhanUng.tinhSach ? (
                      <Link to={`tinh-sach/${tinhSachPhanUng.tinhSach.id}`}>{tinhSachPhanUng.tinhSach.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {tinhSachPhanUng.hoaChatTinhSach ? (
                      <Link to={`hoa-chat/${tinhSachPhanUng.hoaChatTinhSach.id}`}>{tinhSachPhanUng.hoaChatTinhSach.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tinhSachPhanUng.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tinhSachPhanUng.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tinhSachPhanUng.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ tinhSachPhanUng }: IRootState) => ({
  tinhSachPhanUngList: tinhSachPhanUng.entities
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
)(TinhSachPhanUng);
