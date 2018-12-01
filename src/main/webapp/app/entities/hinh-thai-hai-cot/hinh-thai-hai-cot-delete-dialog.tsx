import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IHinhThaiHaiCot } from 'app/shared/model/hinh-thai-hai-cot.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './hinh-thai-hai-cot.reducer';

export interface IHinhThaiHaiCotDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HinhThaiHaiCotDeleteDialog extends React.Component<IHinhThaiHaiCotDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.hinhThaiHaiCotEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { hinhThaiHaiCotEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="xddtApp.hinhThaiHaiCot.delete.question">
          <Translate contentKey="xddtApp.hinhThaiHaiCot.delete.question" interpolate={{ id: hinhThaiHaiCotEntity.id }}>
            Are you sure you want to delete this HinhThaiHaiCot?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-hinhThaiHaiCot" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ hinhThaiHaiCot }: IRootState) => ({
  hinhThaiHaiCotEntity: hinhThaiHaiCot.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HinhThaiHaiCotDeleteDialog);
