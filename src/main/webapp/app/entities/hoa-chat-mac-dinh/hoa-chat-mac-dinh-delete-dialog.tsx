import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IHoaChatMacDinh } from 'app/shared/model/hoa-chat-mac-dinh.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './hoa-chat-mac-dinh.reducer';

export interface IHoaChatMacDinhDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoaChatMacDinhDeleteDialog extends React.Component<IHoaChatMacDinhDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.hoaChatMacDinhEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { hoaChatMacDinhEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.hoaChatMacDinh.delete.question">
          <Translate contentKey="xddtApp.hoaChatMacDinh.delete.question" interpolate={{ id: hoaChatMacDinhEntity.id }}>
            Are you sure you want to delete this HoaChatMacDinh?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-hoaChatMacDinh" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ hoaChatMacDinh }: IRootState) => ({
  hoaChatMacDinhEntity: hoaChatMacDinh.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoaChatMacDinhDeleteDialog);
