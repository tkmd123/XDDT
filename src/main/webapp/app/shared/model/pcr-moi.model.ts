import { IPCRMau } from 'app/shared/model//pcr-mau.model';

export interface IPCRMoi {
  id?: number;
  maMoi?: string;
  tenMoi?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  moiPCRS?: IPCRMau[];
}

export const defaultValue: Readonly<IPCRMoi> = {
  isDeleted: false
};
