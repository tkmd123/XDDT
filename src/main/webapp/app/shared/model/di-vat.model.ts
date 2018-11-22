import { IThongTinKhaiQuat } from 'app/shared/model//thong-tin-khai-quat.model';
import { ILoaiDiVat } from 'app/shared/model//loai-di-vat.model';

export interface IDiVat {
  id?: number;
  giaTri?: string;
  moTa?: string;
  isDeleted?: boolean;
  thongTinKhaiQuat?: IThongTinKhaiQuat;
  loaiDiVat?: ILoaiDiVat;
}

export const defaultValue: Readonly<IDiVat> = {
  isDeleted: false
};
