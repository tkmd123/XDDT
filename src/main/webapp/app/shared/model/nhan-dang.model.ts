import { INhanDangLietSi } from 'app/shared/model//nhan-dang-liet-si.model';

export interface INhanDang {
  id?: number;
  maNhanDang?: string;
  tenNhanDang?: string;
  donViTinh?: string;
  moTa?: string;
  isDeleted?: boolean;
  nhanDangLietSis?: INhanDangLietSi[];
}

export const defaultValue: Readonly<INhanDang> = {
  isDeleted: false
};
