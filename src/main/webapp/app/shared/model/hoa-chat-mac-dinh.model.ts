export interface IHoaChatMacDinh {
  id?: number;
  loaiThaoTac?: string;
  isDefault?: boolean;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
}

export const defaultValue: Readonly<IHoaChatMacDinh> = {
  isDefault: false,
  isDeleted: false
};
