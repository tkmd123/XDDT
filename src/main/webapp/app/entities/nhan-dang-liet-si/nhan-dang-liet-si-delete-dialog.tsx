import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { INhanDangLietSi } from 'app/shared/model/nhan-dang-liet-si.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './nhan-dang-liet-si.reducer';

export interface INhanDangLietSiDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NhanDangLietSiDeleteDialog extends React.Component<INhanDangLietSiDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.nhanDangLietSiEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { nhanDangLietSiEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.nhanDangLietSi.delete.question">
          <Translate contentKey="xddtApp.nhanDangLietSi.delete.question" interpolate={{ id: nhanDangLietSiEntity.id }}>
            Are you sure you want to delete this NhanDangLietSi?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-nhanDangLietSi" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ nhanDangLietSi }: IRootState) => ({
  nhanDangLietSiEntity: nhanDangLietSi.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NhanDangLietSiDeleteDialog);
