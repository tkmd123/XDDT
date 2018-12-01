import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { ITachChiet } from 'app/shared/model//tach-chiet.model';

export interface IMauTachChiet {
  id?: number;
  dacDiemMau?: string;
  soLuongSuDung?: number;
  nhanXet?: string;
  xuLyBeMat?: string;
  khoiLuongNghien?: number;
  khoiLuongDeTach?: number;
  khoiLuongSauKhu?: number;
  khoiLuongSauLoc?: number;
  khoiLuongADN?: number;
  khoiLuongChuaNghien?: number;
  ghiChuTach?: string;
  ghiChuADN?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  float1?: number;
  float2?: number;
  float3?: number;
  mauTachChiet?: IMauXetNghiem;
  tachChietMau?: ITachChiet;
}

export const defaultValue: Readonly<IMauTachChiet> = {
  isDeleted: false
};
