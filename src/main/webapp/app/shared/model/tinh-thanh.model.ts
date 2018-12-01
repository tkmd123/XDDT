import { IQuanHuyen } from 'app/shared/model//quan-huyen.model';

export interface ITinhThanh {
  id?: number;
  maTinh?: string;
  tenTinh?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  tinhThanhQuanHuyens?: IQuanHuyen[];
}

export const defaultValue: Readonly<ITinhThanh> = {
  isDeleted: false
};
