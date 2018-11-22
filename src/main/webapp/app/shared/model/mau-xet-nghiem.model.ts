import { Moment } from 'moment';
import { IThongTinADN } from 'app/shared/model//thong-tin-adn.model';
import { IThaoTac } from 'app/shared/model//thao-tac.model';
import { ILoaiMauXetNghiem } from 'app/shared/model//loai-mau-xet-nghiem.model';
import { IHaiCotLietSi } from 'app/shared/model//hai-cot-liet-si.model';
import { IHoSoThanNhan } from 'app/shared/model//ho-so-than-nhan.model';
import { ILoaiThaoTac } from 'app/shared/model//loai-thao-tac.model';

export interface IMauXetNghiem {
  id?: number;
  maMauXetNghiem?: string;
  nguoiTiepNhan?: string;
  ngayLayMau?: Moment;
  ngayTiepNhan?: Moment;
  trangThaiXuLy?: string;
  moTa?: string;
  ghiChu?: string;
  isDeleted?: boolean;
  mauThongTinADN?: IThongTinADN;
  mauPhanTiches?: IThaoTac[];
  loaiMauXetNghiem?: ILoaiMauXetNghiem;
  haiCotLietSi?: IHaiCotLietSi;
  thanNhan?: IHoSoThanNhan;
  loaiThaoTac?: ILoaiThaoTac;
}

export const defaultValue: Readonly<IMauXetNghiem> = {
  isDeleted: false
};
