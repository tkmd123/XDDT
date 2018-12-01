import { IThongTinMo } from 'app/shared/model//thong-tin-mo.model';
import { IPhuongXa } from 'app/shared/model//phuong-xa.model';

export interface INghiaTrang {
  id?: number;
  maNghiaTrang?: string;
  tenNghiaTrang?: string;
  diaChi?: string;
  nguoiLienHe?: string;
  dienThoai?: string;
  email?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  nghiaTrangMos?: IThongTinMo[];
  phuongXa?: IPhuongXa;
}

export const defaultValue: Readonly<INghiaTrang> = {
  isDeleted: false
};
