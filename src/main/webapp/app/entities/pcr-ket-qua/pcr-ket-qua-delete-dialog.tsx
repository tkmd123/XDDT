import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IPCRKetQua } from 'app/shared/model/pcr-ket-qua.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './pcr-ket-qua.reducer';

export interface IPCRKetQuaDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PCRKetQuaDeleteDialog extends React.Component<IPCRKetQuaDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.pCRKetQuaEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { pCRKetQuaEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.pCRKetQua.delete.question">
          <Translate contentKey="xddtApp.pCRKetQua.delete.question" interpolate={{ id: pCRKetQuaEntity.id }}>
            Are you sure you want to delete this PCRKetQua?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-pCRKetQua" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ pCRKetQua }: IRootState) => ({
  pCRKetQuaEntity: pCRKetQua.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PCRKetQuaDeleteDialog);
