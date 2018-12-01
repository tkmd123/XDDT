import { Moment } from 'moment';
import { ITinhSachPhanUng } from 'app/shared/model//tinh-sach-phan-ung.model';
import { INhanVien } from 'app/shared/model//nhan-vien.model';
import { IMayPCR } from 'app/shared/model//may-pcr.model';

export const enum PhuongPhapTinhSach {
  ENZYM = 'ENZYM',
  KIT = 'KIT'
}

export interface ITinhSach {
  id?: number;
  maTinhSach?: string;
  thoiGianThucHien?: Moment;
  phuongPhapTinhSach?: PhuongPhapTinhSach;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  tinhSaches?: ITinhSachPhanUng[];
  nhanVienTinhSach?: INhanVien;
  mayTinhSach?: IMayPCR;
}

export const defaultValue: Readonly<ITinhSach> = {
  isDeleted: false
};
