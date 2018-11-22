import { IDiemDotBien } from 'app/shared/model//diem-dot-bien.model';

export interface IThongTinADN {
  id?: number;
  moTa?: string;
  fileDuLieu?: string;
  isDeleted?: boolean;
  thongTinADNDotBiens?: IDiemDotBien[];
}

export const defaultValue: Readonly<IThongTinADN> = {
  isDeleted: false
};
