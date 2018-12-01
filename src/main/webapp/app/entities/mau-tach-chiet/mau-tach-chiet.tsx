import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './mau-tach-chiet.reducer';
import { IMauTachChiet } from 'app/shared/model/mau-tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMauTachChietProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IMauTachChietState {
  search: string;
}

export class MauTachChiet extends React.Component<IMauTachChietProps, IMauTachChietState> {
  state: IMauTachChietState = {
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
    const { mauTachChietList, match } = this.props;
    return (
      <div>
        <h2 id="mau-tach-chiet-heading">
          <Translate contentKey="xddtApp.mauTachChiet.home.title">Mau Tach Chiets</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.mauTachChiet.home.createLabel">Create new Mau Tach Chiet</Translate>
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
                    placeholder={translate('xddtApp.mauTachChiet.home.search')}
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
                  <Translate contentKey="xddtApp.mauTachChiet.dacDiemMau">Dac Diem Mau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.soLuongSuDung">So Luong Su Dung</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.nhanXet">Nhan Xet</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.xuLyBeMat">Xu Ly Be Mat</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongNghien">Khoi Luong Nghien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongDeTach">Khoi Luong De Tach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauKhu">Khoi Luong Sau Khu</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongSauLoc">Khoi Luong Sau Loc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongADN">Khoi Luong ADN</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.khoiLuongChuaNghien">Khoi Luong Chua Nghien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.ghiChuTach">Ghi Chu Tach</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.ghiChuADN">Ghi Chu ADN</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.float1">Float 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.float2">Float 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.float3">Float 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.mauTachChiet">Mau Tach Chiet</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.mauTachChiet.tachChietMau">Tach Chiet Mau</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mauTachChietList.map((mauTachChiet, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mauTachChiet.id}`} color="link" size="sm">
                      {mauTachChiet.id}
                    </Button>
                  </td>
                  <td>{mauTachChiet.dacDiemMau}</td>
                  <td>{mauTachChiet.soLuongSuDung}</td>
                  <td>{mauTachChiet.nhanXet}</td>
                  <td>{mauTachChiet.xuLyBeMat}</td>
                  <td>{mauTachChiet.khoiLuongNghien}</td>
                  <td>{mauTachChiet.khoiLuongDeTach}</td>
                  <td>{mauTachChiet.khoiLuongSauKhu}</td>
                  <td>{mauTachChiet.khoiLuongSauLoc}</td>
                  <td>{mauTachChiet.khoiLuongADN}</td>
                  <td>{mauTachChiet.khoiLuongChuaNghien}</td>
                  <td>{mauTachChiet.ghiChuTach}</td>
                  <td>{mauTachChiet.ghiChuADN}</td>
                  <td>{mauTachChiet.isDeleted ? 'true' : 'false'}</td>
                  <td>{mauTachChiet.udf1}</td>
                  <td>{mauTachChiet.udf2}</td>
                  <td>{mauTachChiet.udf3}</td>
                  <td>{mauTachChiet.float1}</td>
                  <td>{mauTachChiet.float2}</td>
                  <td>{mauTachChiet.float3}</td>
                  <td>
                    {mauTachChiet.mauTachChiet ? (
                      <Link to={`mau-xet-nghiem/${mauTachChiet.mauTachChiet.id}`}>{mauTachChiet.mauTachChiet.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {mauTachChiet.tachChietMau ? (
                      <Link to={`tach-chiet/${mauTachChiet.tachChietMau.id}`}>{mauTachChiet.tachChietMau.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mauTachChiet.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mauTachChiet.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mauTachChiet.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ mauTachChiet }: IRootState) => ({
  mauTachChietList: mauTachChiet.entities
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
)(MauTachChiet);
