import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';

export interface ILoaiMauXetNghiem {
  id?: number;
  maLoaiMau?: string;
  tenLoaiMau?: string;
  phanLoai?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  loaiMauXetNghiems?: IMauXetNghiem[];
}

export const defaultValue: Readonly<ILoaiMauXetNghiem> = {
  isDeleted: false
};
