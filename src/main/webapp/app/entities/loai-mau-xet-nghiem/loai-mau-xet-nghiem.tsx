import React from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities, reset } from './loai-mau-xet-nghiem.reducer';
import { ILoaiMauXetNghiem } from 'app/shared/model/loai-mau-xet-nghiem.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ILoaiMauXetNghiemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ILoaiMauXetNghiemState extends IPaginationBaseState {
  search: string;
}

export class LoaiMauXetNghiem extends React.Component<ILoaiMauXetNghiemProps, ILoaiMauXetNghiemState> {
  state: ILoaiMauXetNghiemState = {
    search: '',
    ...getSortState(this.props.location, ITEMS_PER_PAGE)
  };

  componentDidMount() {
    this.reset();
  }

  componentDidUpdate() {
    if (this.props.updateSuccess) {
      this.reset();
    }
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

  reset = () => {
    this.props.reset();
    this.setState({ activePage: 1 }, () => {
      this.getEntities();
    });
  };

  handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      this.setState({ activePage: this.state.activePage + 1 }, () => this.getEntities());
    }
  };

  sort = prop => () => {
    this.setState(
      {
        order: this.state.order === 'asc' ? 'desc' : 'asc',
        sort: prop
      },
      () => {
        this.reset();
      }
    );
  };

  getEntities = () => {
    const { activePage, itemsPerPage, sort, order } = this.state;
    this.props.getEntities(activePage - 1, itemsPerPage, `${sort},${order}`);
  };

  render() {
    const { loaiMauXetNghiemList, match } = this.props;
    return (
      <div>
        <h2 id="loai-mau-xet-nghiem-heading">
          <Translate contentKey="xddtApp.loaiMauXetNghiem.home.title">Loai Mau Xet Nghiems</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="xddtApp.loaiMauXetNghiem.home.createLabel">Create new Loai Mau Xet Nghiem</Translate>
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
                    placeholder={translate('xddtApp.loaiMauXetNghiem.home.search')}
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
          <InfiniteScroll
            pageStart={this.state.activePage}
            loadMore={this.handleLoadMore}
            hasMore={this.state.activePage - 1 < this.props.links.next}
            loader={<div className="loader">Loading ...</div>}
            threshold={0}
            initialLoad={false}
          >
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={this.sort('id')}>
                    <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('maLoaiMau')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.maLoaiMau">Ma Loai Mau</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('tenLoaiMau')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.tenLoaiMau">Ten Loai Mau</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('phanLoai')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.phanLoai">Phan Loai</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('moTa')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.moTa">Mo Ta</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('isDeleted')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.isDeleted">Is Deleted</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('udf1')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf1">Udf 1</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('udf2')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf2">Udf 2</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={this.sort('udf3')}>
                    <Translate contentKey="xddtApp.loaiMauXetNghiem.udf3">Udf 3</Translate> <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {loaiMauXetNghiemList.map((loaiMauXetNghiem, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${loaiMauXetNghiem.id}`} color="link" size="sm">
                        {loaiMauXetNghiem.id}
                      </Button>
                    </td>
                    <td>{loaiMauXetNghiem.maLoaiMau}</td>
                    <td>{loaiMauXetNghiem.tenLoaiMau}</td>
                    <td>{loaiMauXetNghiem.phanLoai}</td>
                    <td>{loaiMauXetNghiem.moTa}</td>
                    <td>{loaiMauXetNghiem.isDeleted ? 'true' : 'false'}</td>
                    <td>{loaiMauXetNghiem.udf1}</td>
                    <td>{loaiMauXetNghiem.udf2}</td>
                    <td>{loaiMauXetNghiem.udf3}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${loaiMauXetNghiem.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.view">View</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${loaiMauXetNghiem.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${loaiMauXetNghiem.id}/delete`} color="danger" size="sm">
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
          </InfiniteScroll>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ loaiMauXetNghiem }: IRootState) => ({
  loaiMauXetNghiemList: loaiMauXetNghiem.entities,
  totalItems: loaiMauXetNghiem.totalItems,
  links: loaiMauXetNghiem.links,
  entity: loaiMauXetNghiem.entity,
  updateSuccess: loaiMauXetNghiem.updateSuccess
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiMauXetNghiem);
