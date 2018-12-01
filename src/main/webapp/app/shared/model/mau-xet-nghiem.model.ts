import { Moment } from 'moment';
import { IPCRMau } from 'app/shared/model//pcr-mau.model';
import { IMauTachChiet } from 'app/shared/model//mau-tach-chiet.model';
import { IDiemDotBien } from 'app/shared/model//diem-dot-bien.model';
import { IThaoTac } from 'app/shared/model//thao-tac.model';
import { ITinhSach } from 'app/shared/model//tinh-sach.model';
import { IHoSoGiamDinh } from 'app/shared/model//ho-so-giam-dinh.model';
import { INhanVien } from 'app/shared/model//nhan-vien.model';
import { ILoaiMauXetNghiem } from 'app/shared/model//loai-mau-xet-nghiem.model';
import { IHaiCotLietSi } from 'app/shared/model//hai-cot-liet-si.model';
import { IHoSoThanNhan } from 'app/shared/model//ho-so-than-nhan.model';
import { ILoaiThaoTac } from 'app/shared/model//loai-thao-tac.model';

export const enum TrangThaiMau {
  MOI = 'MOI',
  DATACHCHIET = 'DATACHCHIET',
  PCRDAT = 'PCRDAT',
  PCRLOAI = 'PCRLOAI',
  NHANBAN = 'NHANBAN',
  GIAITRINHTU = 'GIAITRINHTU',
  HOANTHANH = 'HOANTHANH',
  KHONGDATCHUAN = 'KHONGDATCHUAN',
  HUY = 'HUY'
}

export interface IMauXetNghiem {
  id?: number;
  maMauXetNghiem?: string;
  ngayLayMau?: Moment;
  nguoiLayMau?: string;
  ngayTiepNhan?: Moment;
  soLuongMau?: number;
  trangThaiMau?: TrangThaiMau;
  moTa?: string;
  ghiChu?: string;
  fileGoc?: string;
  fileKetQua?: string;
  fileDotBien?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  udf4?: string;
  udf5?: string;
  mauPCRS?: IPCRMau[];
  mauTachChiets?: IMauTachChiet[];
  mauDiemDotBiens?: IDiemDotBien[];
  mauPhanTiches?: IThaoTac[];
  mauTinhSaches?: ITinhSach[];
  hoSoGiamDinh?: IHoSoGiamDinh;
  nhanVienNhanMau?: INhanVien;
  loaiMauXetNghiem?: ILoaiMauXetNghiem;
  haiCotLietSi?: IHaiCotLietSi;
  thanNhan?: IHoSoThanNhan;
  loaiThaoTac?: ILoaiThaoTac;
}

export const defaultValue: Readonly<IMauXetNghiem> = {
  isDeleted: false
};
