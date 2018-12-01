import { IVungADN } from 'app/shared/model//vung-adn.model';
import { IMauXetNghiem } from 'app/shared/model//mau-xet-nghiem.model';

export interface IDiemDotBien {
  id?: number;
  viTri?: number;
  giaTri?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  vungADN?: IVungADN;
  mauDiemDotBien?: IMauXetNghiem;
}

export const defaultValue: Readonly<IDiemDotBien> = {
  isDeleted: false
};
