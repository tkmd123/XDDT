import { IHinhThaiHaiCot } from 'app/shared/model//hinh-thai-hai-cot.model';

export interface ILoaiHinhThaiHaiCot {
  id?: number;
  maHinhThai?: string;
  tenHinhThai?: string;
  moTa?: string;
  isDeleted?: boolean;
  loaiHinhThaiHaiCots?: IHinhThaiHaiCot[];
}

export const defaultValue: Readonly<ILoaiHinhThaiHaiCot> = {
  isDeleted: false
};
