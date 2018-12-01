import { ITinhSach } from 'app/shared/model//tinh-sach.model';
import { IHoaChat } from 'app/shared/model//hoa-chat.model';

export interface ITinhSachPhanUng {
  id?: number;
  dungTichSuDung?: number;
  chuTrinhNhietDo?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  tinhSach?: ITinhSach;
  hoaChatTinhSach?: IHoaChat;
}

export const defaultValue: Readonly<ITinhSachPhanUng> = {
  isDeleted: false
};
