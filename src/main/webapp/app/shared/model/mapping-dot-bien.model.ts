export interface IMappingDotBien {
  id?: number;
  maDotBien?: string;
  maMapping?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
}

export const defaultValue: Readonly<IMappingDotBien> = {
  isDeleted: false
};
