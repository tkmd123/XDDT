import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';

export interface ILoaiMauXetNghiem {
  id?: number;
  maLoaiMau?: string;
  tenLoaiMau?: string;
  moTa?: string;
  isDeleted?: boolean;
  loaiMauXetNghiems?: IMauXetNghiem[];
}

export const defaultValue: Readonly<ILoaiMauXetNghiem> = {
  isDeleted: false
};
