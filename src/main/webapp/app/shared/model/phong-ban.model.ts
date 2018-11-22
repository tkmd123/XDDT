import { IThaoTac } from 'app/shared/model//thao-tac.model';
import { ILoaiThaoTac } from 'app/shared/model//loai-thao-tac.model';

export interface IPhongBan {
  id?: number;
  maPhongBan?: string;
  tenPhongBan?: string;
  moTa?: string;
  isActive?: boolean;
  isDeleted?: boolean;
  phongLabPhanTiches?: IThaoTac[];
  phongBanThaoTacs?: ILoaiThaoTac[];
}

export const defaultValue: Readonly<IPhongBan> = {
  isActive: false,
  isDeleted: false
};
