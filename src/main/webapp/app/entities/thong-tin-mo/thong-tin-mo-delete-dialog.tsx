import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IThongTinMo } from 'app/shared/model/thong-tin-mo.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './thong-tin-mo.reducer';

export interface IThongTinMoDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ThongTinMoDeleteDialog extends React.Component<IThongTinMoDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.thongTinMoEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { thongTinMoEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.thongTinMo.delete.question">
          <Translate contentKey="xddtApp.thongTinMo.delete.question" interpolate={{ id: thongTinMoEntity.id }}>
            Are you sure you want to delete this ThongTinMo?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-thongTinMo" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ thongTinMo }: IRootState) => ({
  thongTinMoEntity: thongTinMo.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ThongTinMoDeleteDialog);
