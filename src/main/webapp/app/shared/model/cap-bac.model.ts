import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';

export interface ICapBac {
  id?: number;
  maCapBac?: string;
  tenCapBac?: string;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  capBacLietSis?: IHoSoLietSi[];
}

export const defaultValue: Readonly<ICapBac> = {
  isDeleted: false
};
