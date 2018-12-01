import { ITinhSach } from 'app/shared/model//tinh-sach.model';
import { IPCR } from 'app/shared/model//pcr.model';

export interface IMayPCR {
  id?: number;
  maMayPCR?: string;
  tenMayPCR?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  mayTinhSaches?: ITinhSach[];
  mayPCRS?: IPCR[];
}

export const defaultValue: Readonly<IMayPCR> = {
  isDeleted: false
};
