import { IVungADN } from 'app/shared/model//vung-adn.model';
import { IThongTinADN } from 'app/shared/model//thong-tin-adn.model';

export interface IDiemDotBien {
  id?: number;
  viTri?: number;
  giaTri?: string;
  giaTri1?: string;
  giaTri2?: string;
  isDeleted?: boolean;
  vungADN?: IVungADN;
  thongTinADN?: IThongTinADN;
}

export const defaultValue: Readonly<IDiemDotBien> = {
  isDeleted: false
};
