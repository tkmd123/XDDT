import { ITinhThanh } from 'app/shared/model//tinh-thanh.model';
import { IPhuongXa } from 'app/shared/model//phuong-xa.model';

export interface IQuanHuyen {
  id?: number;
  maHuyen?: string;
  tenHuyen?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  tinhThanh?: ITinhThanh;
  quanHuyenPhuongXas?: IPhuongXa[];
}

export const defaultValue: Readonly<IQuanHuyen> = {
  isDeleted: false
};
