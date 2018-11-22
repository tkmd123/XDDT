import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IThongTinKhaiQuat } from 'app/shared/model/thong-tin-khai-quat.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './thong-tin-khai-quat.reducer';

export interface IThongTinKhaiQuatDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThongTinKhaiQuatDeleteDialog extends React.Component<IThongTinKhaiQuatDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.thongTinKhaiQuatEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { thongTinKhaiQuatEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.thongTinKhaiQuat.delete.question">
          <Translate contentKey="xddtApp.thongTinKhaiQuat.delete.question" interpolate={{ id: thongTinKhaiQuatEntity.id }}>
            Are you sure you want to delete this ThongTinKhaiQuat?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-thongTinKhaiQuat" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ thongTinKhaiQuat }: IRootState) => ({
  thongTinKhaiQuatEntity: thongTinKhaiQuat.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThongTinKhaiQuatDeleteDialog);
