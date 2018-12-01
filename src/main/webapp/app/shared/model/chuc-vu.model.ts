import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';

export interface IChucVu {
  id?: number;
  maChucVu?: string;
  tenChucVu?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  chucVuLietSis?: IHoSoLietSi[];
}

export const defaultValue: Readonly<IChucVu> = {
  isDeleted: false
};
