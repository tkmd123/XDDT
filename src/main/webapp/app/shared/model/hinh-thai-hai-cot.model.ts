import { IHaiCotLietSi } from 'app/shared/model//hai-cot-liet-si.model';
import { ILoaiHinhThaiHaiCot } from 'app/shared/model//loai-hinh-thai-hai-cot.model';

export interface IHinhThaiHaiCot {
  id?: number;
  giaTri?: string;
  moTa?: string;
  isDeleted?: boolean;
  haiCotLietSi?: IHaiCotLietSi;
  loaiHinhThaiHaiCot?: ILoaiHinhThaiHaiCot;
}

export const defaultValue: Readonly<IHinhThaiHaiCot> = {
  isDeleted: false
};
