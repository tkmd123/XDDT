import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IHoSoThanNhan } from 'app/shared/model//ho-so-than-nhan.model';
import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';
import { INhanVien } from 'app/shared/model//nhan-vien.model';

export interface IHoSoGiamDinh {
  id?: number;
  maHSGD?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  hoSoGiamDinhs?: IMauXetNghiem[];
  giamDinhThanNhans?: IHoSoThanNhan[];
  giamDinhLietSis?: IHoSoLietSi[];
  nhanVienHSGD?: INhanVien;
}

export const defaultValue: Readonly<IHoSoGiamDinh> = {
  isDeleted: false
};
