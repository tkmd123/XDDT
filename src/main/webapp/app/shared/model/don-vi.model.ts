import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';
import { IDonVi } from 'app/shared/model//don-vi.model';
import { IDonViThoiKy } from 'app/shared/model//don-vi-thoi-ky.model';

export interface IDonVi {
  id?: number;
  maDonVi?: string;
  tenDonVi?: string;
  tenTat?: string;
  phanMuc?: string;
  phanCap?: string;
  phanKhoi?: string;
  moTa?: string;
  ghiChu?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  donViHySinhs?: IHoSoLietSi[];
  donViHuanLuyens?: IHoSoLietSi[];
  donViQuanLies?: IDonVi[];
  donViThoiKies?: IDonViThoiKy[];
  donViQuanLy?: IDonVi;
}

export const defaultValue: Readonly<IDonVi> = {
  isDeleted: false
};
