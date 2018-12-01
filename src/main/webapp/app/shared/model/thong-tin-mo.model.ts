import { IThongTinKhaiQuat } from 'app/shared/model//thong-tin-khai-quat.model';
import { INghiaTrang } from 'app/shared/model//nghia-trang.model';

export interface IThongTinMo {
  id?: number;
  khuMo?: string;
  loMo?: string;
  hangMo?: number;
  soMo?: number;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  moLietSis?: IThongTinKhaiQuat[];
  nghiaTrang?: INghiaTrang;
}

export const defaultValue: Readonly<IThongTinMo> = {
  isDeleted: false
};
