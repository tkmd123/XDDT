import { IUser } from 'app/shared/model/user.model';
import { ITinhSach } from 'app/shared/model//tinh-sach.model';
import { IPCR } from 'app/shared/model//pcr.model';
import { ITachChiet } from 'app/shared/model//tach-chiet.model';
import { IHoSoGiamDinh } from 'app/shared/model//ho-so-giam-dinh.model';
import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IPhongBan } from 'app/shared/model//phong-ban.model';

export interface INhanVien {
  id?: number;
  maNhanVien?: string;
  tenNhanVien?: string;
  soDienThoai?: string;
  email?: string;
  moTa?: string;
  ghiChu?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  userNhanVien?: IUser;
  nhanVienTinhSaches?: ITinhSach[];
  nhanVienPCRS?: IPCR[];
  nhanVienNghienMaus?: ITachChiet[];
  nhanVienTachADNS?: ITachChiet[];
  nhanVienHSGDS?: IHoSoGiamDinh[];
  nhanVienNhanMaus?: IMauXetNghiem[];
  phongban?: IPhongBan;
}

export const defaultValue: Readonly<INhanVien> = {
  isDeleted: false
};
