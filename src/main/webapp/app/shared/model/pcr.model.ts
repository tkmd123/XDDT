import { Moment } from 'moment';
import { IPCRMau } from 'app/shared/model//pcr-mau.model';
import { IPCRPhanUng } from 'app/shared/model//pcr-phan-ung.model';
import { IMayPCR } from 'app/shared/model//may-pcr.model';
import { INhanVien } from 'app/shared/model//nhan-vien.model';

export interface IPCR {
  id?: number;
  maPCR?: string;
  thoiGianThucHien?: Moment;
  nhanXet?: string;
  thoiGianBatDau?: Moment;
  congSuatBatDau?: number;
  cuongDoBatDau?: number;
  dienTheBatDau?: number;
  thoiGianKetThuc?: Moment;
  congSuatKetThuc?: number;
  cuongDoKetThuc?: number;
  dienTheKetThuc?: number;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  udf4?: string;
  udf5?: string;
  pcrMaus?: IPCRMau[];
  pcrPhanUngs?: IPCRPhanUng[];
  mayPCR?: IMayPCR;
  nhanVienPCR?: INhanVien;
}

export const defaultValue: Readonly<IPCR> = {
  isDeleted: false
};
