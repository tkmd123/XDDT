import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './tach-chiet.reducer';
import { ITachChiet } from 'app/shared/model/tach-chiet.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITachChietProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITachChietState {
  search: string;
}

export class TachChiet extends React.Component<ITachChietProps, ITachChietState> {
  state: ITachChietState = {
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
    const { tachChietList, match } = this.props;
    return (
      <div>
        <h2 id="tach-chiet-heading">
          <Translate contentKey="xddtApp.tachChiet.home.title">Tach Chiets</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.tachChiet.home.createLabel">Create new Tach Chiet</Translate>
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
                    placeholder={translate('xddtApp.tachChiet.home.search')}
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
                  <Translate contentKey="xddtApp.tachChiet.maTachChiet">Ma Tach Chiet</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.moTa">Mo Ta</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.ghiChu">Ghi Chu</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.thoiGianThucHien">Thoi Gian Thuc Hien</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.phuongPhapLoc">Phuong Phap Loc</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.isDeleted">Is Deleted</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.udf1">Udf 1</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.udf2">Udf 2</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.udf3">Udf 3</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.nhanVienNghienMau">Nhan Vien Nghien Mau</Translate>
                </th>
                <th>
                  <Translate contentKey="xddtApp.tachChiet.nhanVienTachADN">Nhan Vien Tach ADN</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tachChietList.map((tachChiet, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tachChiet.id}`} color="link" size="sm">
                      {tachChiet.id}
                    </Button>
                  </td>
                  <td>{tachChiet.maTachChiet}</td>
                  <td>{tachChiet.moTa}</td>
                  <td>{tachChiet.ghiChu}</td>
                  <td>
                    <TextFormat type="date" value={tachChiet.thoiGianThucHien} format={APP_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`xddtApp.PhuongPhapLoc.${tachChiet.phuongPhapLoc}`} />
                  </td>
                  <td>{tachChiet.isDeleted ? 'true' : 'false'}</td>
                  <td>{tachChiet.udf1}</td>
                  <td>{tachChiet.udf2}</td>
                  <td>{tachChiet.udf3}</td>
                  <td>
                    {tachChiet.nhanVienNghienMau ? (
                      <Link to={`nhan-vien/${tachChiet.nhanVienNghienMau.id}`}>{tachChiet.nhanVienNghienMau.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {tachChiet.nhanVienTachADN ? (
                      <Link to={`nhan-vien/${tachChiet.nhanVienTachADN.id}`}>{tachChiet.nhanVienTachADN.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tachChiet.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tachChiet.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tachChiet.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ tachChiet }: IRootState) => ({
  tachChietList: tachChiet.entities
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
)(TachChiet);
