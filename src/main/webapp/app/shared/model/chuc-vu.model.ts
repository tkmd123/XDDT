import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';

export interface IChucVu {
  id?: number;
  maChucVu?: string;
  tenChucVu?: string;
  moTa?: string;
  isDeleted?: boolean;
  chucVuLietSis?: IHoSoLietSi[];
}

export const defaultValue: Readonly<IChucVu> = {
  isDeleted: false
};
