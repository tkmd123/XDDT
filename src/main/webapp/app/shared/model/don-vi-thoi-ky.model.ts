import { IDonVi } from 'app/shared/model//don-vi.model';

export interface IDonViThoiKy {
  id?: number;
  tuNam?: number;
  denNam?: number;
  diaDiemMoTa?: string;
  diaDiemXa?: string;
  diaDiemHuyen?: string;
  diaDiemTinh?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  donVi?: IDonVi;
}

export const defaultValue: Readonly<IDonViThoiKy> = {
  isDeleted: false
};
