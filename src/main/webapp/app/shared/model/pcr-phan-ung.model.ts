import { IPCR } from 'app/shared/model//pcr.model';
import { IHoaChat } from 'app/shared/model//hoa-chat.model';

export interface IPCRPhanUng {
  id?: number;
  chuKyPhanUng?: string;
  dungTich?: number;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  pcrPhanUng?: IPCR;
  hoaChatPhanUng?: IHoaChat;
}

export const defaultValue: Readonly<IPCRPhanUng> = {
  isDeleted: false
};
