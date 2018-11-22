import { IThanNhanLietSi } from 'app/shared/model//than-nhan-liet-si.model';
import { IHaiCotLietSi } from 'app/shared/model//hai-cot-liet-si.model';
import { INhanDangLietSi } from 'app/shared/model//nhan-dang-liet-si.model';
import { IPhuongXa } from 'app/shared/model//phuong-xa.model';
import { IDonVi } from 'app/shared/model//don-vi.model';
import { ICapBac } from 'app/shared/model//cap-bac.model';
import { IChucVu } from 'app/shared/model//chuc-vu.model';

export interface IHoSoLietSi {
  id?: number;
  maCCS?: string;
  maLietSi?: string;
  hoTen?: string;
  tenKhac?: string;
  biDanh?: string;
  gioiTinh?: string;
  namSinh?: string;
  queThon?: string;
  queXa?: string;
  queHuyen?: string;
  queTinh?: string;
  namNhapNgu?: number;
  namXuatNgu?: number;
  namTaiNgu?: number;
  namDiB?: string;
  hySinhNgay?: number;
  hySinhThang?: number;
  hySinhNam?: number;
  hySinhLyDo?: string;
  hySinhDonVi?: string;
  hySinhTranDanh?: string;
  hySinhDiaDiem?: string;
  hySinhXa?: string;
  hySinhHuyen?: string;
  hySinhTinh?: string;
  anTangDiaDiem?: string;
  anTangXa?: string;
  anTangHuyen?: string;
  anTangTinh?: string;
  ngayBaoTu?: string;
  giayBaoTu?: string;
  vatDungKemTheo?: string;
  ghiChu?: string;
  isDeleted?: boolean;
  trangThaiXacMinh?: string;
  uDF1?: string;
  uDF2?: string;
  uDF3?: string;
  lietSis?: IThanNhanLietSi[];
  lietSiMos?: IHaiCotLietSi[];
  lietSiNhanDangs?: INhanDangLietSi[];
  phuongXa?: IPhuongXa;
  donViHiSinh?: IDonVi;
  donViHuanLuyen?: IDonVi;
  capBac?: ICapBac;
  chucVu?: IChucVu;
}

export const defaultValue: Readonly<IHoSoLietSi> = {
  isDeleted: false
};
