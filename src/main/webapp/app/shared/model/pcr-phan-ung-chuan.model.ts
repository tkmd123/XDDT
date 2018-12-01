export interface IPCRPhanUngChuan {
  id?: number;
  chuKyPhanUng?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
}

export const defaultValue: Readonly<IPCRPhanUngChuan> = {
  isDeleted: false
};
