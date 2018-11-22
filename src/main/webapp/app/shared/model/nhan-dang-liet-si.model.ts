import { INhanDang } from 'app/shared/model//nhan-dang.model';
import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';

export interface INhanDangLietSi {
  id?: number;
  giaTri?: string;
  moTa?: string;
  isDeleted?: boolean;
  nhanDang?: INhanDang;
  lietSi?: IHoSoLietSi;
}

export const defaultValue: Readonly<INhanDangLietSi> = {
  isDeleted: false
};
