import { IPCRKetQua } from 'app/shared/model//pcr-ket-qua.model';
import { IPCR } from 'app/shared/model//pcr.model';
import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';
import { IPCRMoi } from 'app/shared/model//pcr-moi.model';

export interface IPCRMau {
  id?: number;
  nongDoAgaros?: number;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  pcrKetQua?: IPCRKetQua;
  pcrMau?: IPCR;
  mauPCR?: IMauXetNghiem;
  moiPCR?: IPCRMoi;
}

export const defaultValue: Readonly<IPCRMau> = {
  isDeleted: false
};
