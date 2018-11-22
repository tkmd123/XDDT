import { IThanNhanLietSi } from 'app/shared/model//than-nhan-liet-si.model';

export interface IQuanHeThanNhan {
  id?: number;
  maQuanHe?: string;
  loaiQuanHe?: string;
  moTa?: string;
  isDeleted?: boolean;
  quanHeThanNhans?: IThanNhanLietSi[];
}

export const defaultValue: Readonly<IQuanHeThanNhan> = {
  isDeleted: false
};
