import { IDiemDotBien } from 'app/shared/model//diem-dot-bien.model';

export interface IVungADN {
  id?: number;
  maVungADN?: string;
  tenVungADN?: string;
  moTa?: string;
  isDeleted?: boolean;
  vungADNS?: IDiemDotBien[];
}

export const defaultValue: Readonly<IVungADN> = {
  isDeleted: false
};
