import { IPhongBan } from 'app/shared/model//phong-ban.model';

export interface ITrungTam {
  id?: number;
  maTrungTam?: string;
  tenTrungTam?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  trungtams?: IPhongBan[];
}

export const defaultValue: Readonly<ITrungTam> = {
  isDeleted: false
};
