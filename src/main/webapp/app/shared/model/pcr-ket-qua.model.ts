import { IPCRMau } from 'app/shared/model//pcr-mau.model';

export interface IPCRKetQua {
  id?: number;
  maKetQua?: string;
  tenKetQua?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  pcrKetQuas?: IPCRMau[];
}

export const defaultValue: Readonly<IPCRKetQua> = {
  isDeleted: false
};
