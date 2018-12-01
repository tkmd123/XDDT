import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILoaiMauXetNghiem } from 'app/shared/model/loai-mau-xet-nghiem.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './loai-mau-xet-nghiem.reducer';

export interface ILoaiMauXetNghiemDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LoaiMauXetNghiemDeleteDialog extends React.Component<ILoaiMauXetNghiemDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.loaiMauXetNghiemEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { loaiMauXetNghiemEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.loaiMauXetNghiem.delete.question">
          <Translate contentKey="xddtApp.loaiMauXetNghiem.delete.question" interpolate={{ id: loaiMauXetNghiemEntity.id }}>
            Are you sure you want to delete this LoaiMauXetNghiem?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-loaiMauXetNghiem" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ loaiMauXetNghiem }: IRootState) => ({
  loaiMauXetNghiemEntity: loaiMauXetNghiem.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LoaiMauXetNghiemDeleteDialog);
