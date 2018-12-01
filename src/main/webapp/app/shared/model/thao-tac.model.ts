import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IPhongBan } from 'app/shared/model//phong-ban.model';
import { ILoaiThaoTac } from 'app/shared/model//loai-thao-tac.model';

export interface IThaoTac {
  id?: number;
  moTaKetQua?: string;
  trangThaiXuLy?: boolean;
  isDangThucHien?: boolean;
  isDeleted?: boolean;
  mauXetNghiem?: IMauXetNghiem;
  phongBan?: IPhongBan;
  loaiThaoTac?: ILoaiThaoTac;
}

export const defaultValue: Readonly<IThaoTac> = {
  trangThaiXuLy: false,
  isDangThucHien: false,
  isDeleted: false
};
