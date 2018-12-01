import { IHinhThaiHaiCot } from 'app/shared/model//hinh-thai-hai-cot.model';
import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';
import { IThongTinKhaiQuat } from 'app/shared/model//thong-tin-khai-quat.model';

export interface IHaiCotLietSi {
  id?: number;
  maHaiCot?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  thongTinHinhThaiHocs?: IHinhThaiHaiCot[];
  haiCotMaus?: IMauXetNghiem[];
  lietSi?: IHoSoLietSi;
  thongTinKhaiQuat?: IThongTinKhaiQuat;
}

export const defaultValue: Readonly<IHaiCotLietSi> = {
  isDeleted: false
};
