import { IHoSoLietSi } from 'app/shared/model//ho-so-liet-si.model';

export interface ICapBac {
  id?: number;
  maCapBac?: string;
  tenCapBac?: string;
  moTa?: string;
  isDeleted?: boolean;
  capBacLietSis?: IHoSoLietSi[];
}

export const defaultValue: Readonly<ICapBac> = {
  isDeleted: false
};
