import { IDiVat } from 'app/shared/model//di-vat.model';

export interface ILoaiDiVat {
  id?: number;
  maDiVat?: string;
  loaiDiVat?: string;
  moTa?: string;
  isDeleted?: boolean;
  loaiDiVats?: IDiVat[];
}

export const defaultValue: Readonly<ILoaiDiVat> = {
  isDeleted: false
};
