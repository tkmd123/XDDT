import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IHoSoGiamDinh } from 'app/shared/model/ho-so-giam-dinh.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './ho-so-giam-dinh.reducer';

export interface IHoSoGiamDinhDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HoSoGiamDinhDeleteDialog extends React.Component<IHoSoGiamDinhDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.hoSoGiamDinhEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { hoSoGiamDinhEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.hoSoGiamDinh.delete.question">
          <Translate contentKey="xddtApp.hoSoGiamDinh.delete.question" interpolate={{ id: hoSoGiamDinhEntity.id }}>
            Are you sure you want to delete this HoSoGiamDinh?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-hoSoGiamDinh" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ hoSoGiamDinh }: IRootState) => ({
  hoSoGiamDinhEntity: hoSoGiamDinh.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HoSoGiamDinhDeleteDialog);
