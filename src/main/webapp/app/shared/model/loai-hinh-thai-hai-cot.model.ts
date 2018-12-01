import { IHinhThaiHaiCot } from 'app/shared/model//hinh-thai-hai-cot.model';

export interface ILoaiHinhThaiHaiCot {
  id?: number;
  maHinhThai?: string;
  tenHinhThai?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  loaiHinhThaiHaiCots?: IHinhThaiHaiCot[];
}

export const defaultValue: Readonly<ILoaiHinhThaiHaiCot> = {
  isDeleted: false
};
