import { IHoaChat } from 'app/shared/model//hoa-chat.model';
import { ITachChiet } from 'app/shared/model//tach-chiet.model';

export interface IHoaChatTachChiet {
  id?: number;
  soLuong?: number;
  moTa?: string;
  isDeleted?: boolean;
  udf1?: string;
  udf2?: string;
  udf3?: string;
  hoaChat?: IHoaChat;
  tachChiet?: ITachChiet;
}

export const defaultValue: Readonly<IHoaChatTachChiet> = {
  isDeleted: false
};
