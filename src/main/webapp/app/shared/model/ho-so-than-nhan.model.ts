import { Moment } from 'moment';
import { IThanNhanLietSi } from 'app/shared/model//than-nhan-liet-si.model';
import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IPhuongXa } from 'app/shared/model//phuong-xa.model';

export interface IHoSoThanNhan {
  id?: number;
  maThanNhan?: string;
  isLayMau?: boolean;
  hoTen?: string;
  namSinh?: Moment;
  gioiTinh?: string;
  soCMT?: string;
  diaChi?: string;
  dienThoaiChinh?: string;
  dienThoaiPhu?: string;
  email?: string;
  ghiChu?: string;
  isDeleted?: boolean;
  uDF1?: string;
  uDF2?: string;
  uDF3?: string;
  thanNhans?: IThanNhanLietSi[];
  thanNhanMaus?: IMauXetNghiem[];
  phuongXa?: IPhuongXa;
}

export const defaultValue: Readonly<IHoSoThanNhan> = {
  isLayMau: false,
  isDeleted: false
};
