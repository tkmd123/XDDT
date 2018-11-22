import { IQuanHuyen } from 'app/shared/model//quan-huyen.model';

export interface ITinhThanh {
  id?: number;
  maTinh?: string;
  tenTinh?: string;
  moTa?: string;
  tinhThanhQuanHuyens?: IQuanHuyen[];
}

export const defaultValue: Readonly<ITinhThanh> = {};
