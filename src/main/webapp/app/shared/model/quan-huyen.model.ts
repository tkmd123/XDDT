import { ITinhThanh } from 'app/shared/model//tinh-thanh.model';
import { IPhuongXa } from 'app/shared/model//phuong-xa.model';

export interface IQuanHuyen {
  id?: number;
  maHuyen?: string;
  tenHuyen?: string;
  moTa?: string;
  tinhThanh?: ITinhThanh;
  quanHuyenPhuongXas?: IPhuongXa[];
}

export const defaultValue: Readonly<IQuanHuyen> = {};
