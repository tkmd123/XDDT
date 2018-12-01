import { Moment } from 'moment';
import { IHaiCotLietSi } from 'app/shared/model//hai-cot-liet-si.model';
import { IDiVat } from 'app/shared/model//di-vat.model';
import { IThongTinMo } from 'app/shared/model//thong-tin-mo.model';

export interface IThongTinKhaiQuat {
  id?: number;
  maKhaiQuat?: string;
  nguoiKhaiQuat?: string;
  donViKhaiQuat?: string;
  thoiGianKhaiQuat?: Moment;
  coHaiCot?: boolean;
  soLuongHaiCot?: number;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  khaiQuatHaiCots?: IHaiCotLietSi[];
  khaiQuatDiVats?: IDiVat[];
  thongTinMo?: IThongTinMo;
}

export const defaultValue: Readonly<IThongTinKhaiQuat> = {
  coHaiCot: false,
  isDeleted: false
};
