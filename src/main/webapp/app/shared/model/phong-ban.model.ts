import { INhanVien } from 'app/shared/model//nhan-vien.model';
import { IThaoTac } from 'app/shared/model//thao-tac.model';
import { ILoaiThaoTac } from 'app/shared/model//loai-thao-tac.model';
import { ITrungTam } from 'app/shared/model//trung-tam.model';

export interface IPhongBan {
  id?: number;
  maPhongBan?: string;
  tenPhongBan?: string;
  moTa?: string;
  isActive?: boolean;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  phongbans?: INhanVien[];
  phongLabPhanTiches?: IThaoTac[];
  phongBanThaoTacs?: ILoaiThaoTac[];
  trungtam?: ITrungTam;
}

export const defaultValue: Readonly<IPhongBan> = {
  isActive: false,
  isDeleted: false
};
