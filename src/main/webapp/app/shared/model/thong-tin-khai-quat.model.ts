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
  isDeleted?: boolean;
  khaiQuatHaiCots?: IHaiCotLietSi[];
  khaiQuatDiVats?: IDiVat[];
  thongTinMo?: IThongTinMo;
}

export const defaultValue: Readonly<IThongTinKhaiQuat> = {
  isDeleted: false
};
