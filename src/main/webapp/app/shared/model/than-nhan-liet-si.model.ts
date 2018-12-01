import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';
import { IHoSoThanNhan } from 'app/shared/model//ho-so-than-nhan.model';
import { IQuanHeThanNhan } from 'app/shared/model//quan-he-than-nhan.model';

export interface IThanNhanLietSi {
  id?: number;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  lietSi?: IHoSoLietSi;
  thanNhan?: IHoSoThanNhan;
  quanHeThanNhan?: IQuanHeThanNhan;
}

export const defaultValue: Readonly<IThanNhanLietSi> = {
  isDeleted: false
};
