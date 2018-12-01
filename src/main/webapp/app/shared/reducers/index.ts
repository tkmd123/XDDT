import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import tinhThanh, {
  TinhThanhState
} from 'app/entities/tinh-thanh/tinh-thanh.reducer';
// prettier-ignore
import quanHuyen, {
  QuanHuyenState
} from 'app/entities/quan-huyen/quan-huyen.reducer';
// prettier-ignore
import phuongXa, {
  PhuongXaState
} from 'app/entities/phuong-xa/phuong-xa.reducer';
// prettier-ignore
import capBac, {
  CapBacState
} from 'app/entities/cap-bac/cap-bac.reducer';
// prettier-ignore
import chucVu, {
  ChucVuState
} from 'app/entities/chuc-vu/chuc-vu.reducer';
// prettier-ignore
import hoSoGiamDinh, {
  HoSoGiamDinhState
} from 'app/entities/ho-so-giam-dinh/ho-so-giam-dinh.reducer';
// prettier-ignore
import hoSoLietSi, {
  HoSoLietSiState
} from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
// prettier-ignore
import donVi, {
  DonViState
} from 'app/entities/don-vi/don-vi.reducer';
// prettier-ignore
import donViThoiKy, {
  DonViThoiKyState
} from 'app/entities/don-vi-thoi-ky/don-vi-thoi-ky.reducer';
// prettier-ignore
import quanHeThanNhan, {
  QuanHeThanNhanState
} from 'app/entities/quan-he-than-nhan/quan-he-than-nhan.reducer';
// prettier-ignore
import hoSoThanNhan, {
  HoSoThanNhanState
} from 'app/entities/ho-so-than-nhan/ho-so-than-nhan.reducer';
// prettier-ignore
import thanNhanLietSi, {
  ThanNhanLietSiState
} from 'app/entities/than-nhan-liet-si/than-nhan-liet-si.reducer';
// prettier-ignore
import thongTinMo, {
  ThongTinMoState
} from 'app/entities/thong-tin-mo/thong-tin-mo.reducer';
// prettier-ignore
import nghiaTrang, {
  NghiaTrangState
} from 'app/entities/nghia-trang/nghia-trang.reducer';
// prettier-ignore
import thongTinKhaiQuat, {
  ThongTinKhaiQuatState
} from 'app/entities/thong-tin-khai-quat/thong-tin-khai-quat.reducer';
// prettier-ignore
import loaiDiVat, {
  LoaiDiVatState
} from 'app/entities/loai-di-vat/loai-di-vat.reducer';
// prettier-ignore
import diVat, {
  DiVatState
} from 'app/entities/di-vat/di-vat.reducer';
// prettier-ignore
import loaiHinhThaiHaiCot, {
  LoaiHinhThaiHaiCotState
} from 'app/entities/loai-hinh-thai-hai-cot/loai-hinh-thai-hai-cot.reducer';
// prettier-ignore
import haiCotLietSi, {
  HaiCotLietSiState
} from 'app/entities/hai-cot-liet-si/hai-cot-liet-si.reducer';
// prettier-ignore
import hinhThaiHaiCot, {
  HinhThaiHaiCotState
} from 'app/entities/hinh-thai-hai-cot/hinh-thai-hai-cot.reducer';
// prettier-ignore
import loaiMauXetNghiem, {
  LoaiMauXetNghiemState
} from 'app/entities/loai-mau-xet-nghiem/loai-mau-xet-nghiem.reducer';
// prettier-ignore
import mauXetNghiem, {
  MauXetNghiemState
} from 'app/entities/mau-xet-nghiem/mau-xet-nghiem.reducer';
// prettier-ignore
import hoaChat, {
  HoaChatState
} from 'app/entities/hoa-chat/hoa-chat.reducer';
// prettier-ignore
import hoaChatMacDinh, {
  HoaChatMacDinhState
} from 'app/entities/hoa-chat-mac-dinh/hoa-chat-mac-dinh.reducer';
// prettier-ignore
import tachChiet, {
  TachChietState
} from 'app/entities/tach-chiet/tach-chiet.reducer';
// prettier-ignore
import hoaChatTachChiet, {
  HoaChatTachChietState
} from 'app/entities/hoa-chat-tach-chiet/hoa-chat-tach-chiet.reducer';
// prettier-ignore
import mauTachChiet, {
  MauTachChietState
} from 'app/entities/mau-tach-chiet/mau-tach-chiet.reducer';
// prettier-ignore
import mayPCR, {
  MayPCRState
} from 'app/entities/may-pcr/may-pcr.reducer';
// prettier-ignore
import pCRMoi, {
  PCRMoiState
} from 'app/entities/pcr-moi/pcr-moi.reducer';
// prettier-ignore
import pCR, {
  PCRState
} from 'app/entities/pcr/pcr.reducer';
// prettier-ignore
import pCRPhanUngChuan, {
  PCRPhanUngChuanState
} from 'app/entities/pcr-phan-ung-chuan/pcr-phan-ung-chuan.reducer';
// prettier-ignore
import pCRPhanUng, {
  PCRPhanUngState
} from 'app/entities/pcr-phan-ung/pcr-phan-ung.reducer';
// prettier-ignore
import pCRMau, {
  PCRMauState
} from 'app/entities/pcr-mau/pcr-mau.reducer';
// prettier-ignore
import pCRKetQua, {
  PCRKetQuaState
} from 'app/entities/pcr-ket-qua/pcr-ket-qua.reducer';
// prettier-ignore
import tinhSach, {
  TinhSachState
} from 'app/entities/tinh-sach/tinh-sach.reducer';
// prettier-ignore
import tinhSachPhanUng, {
  TinhSachPhanUngState
} from 'app/entities/tinh-sach-phan-ung/tinh-sach-phan-ung.reducer';
// prettier-ignore
import vungADN, {
  VungADNState
} from 'app/entities/vung-adn/vung-adn.reducer';
// prettier-ignore
import mappingDotBien, {
  MappingDotBienState
} from 'app/entities/mapping-dot-bien/mapping-dot-bien.reducer';
// prettier-ignore
import diemDotBien, {
  DiemDotBienState
} from 'app/entities/diem-dot-bien/diem-dot-bien.reducer';
// prettier-ignore
import loaiThaoTac, {
  LoaiThaoTacState
} from 'app/entities/loai-thao-tac/loai-thao-tac.reducer';
// prettier-ignore
import thaoTac, {
  ThaoTacState
} from 'app/entities/thao-tac/thao-tac.reducer';
// prettier-ignore
import trungTam, {
  TrungTamState
} from 'app/entities/trung-tam/trung-tam.reducer';
// prettier-ignore
import phongBan, {
  PhongBanState
} from 'app/entities/phong-ban/phong-ban.reducer';
// prettier-ignore
import nhanVien, {
  NhanVienState
} from 'app/entities/nhan-vien/nhan-vien.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly tinhThanh: TinhThanhState;
  readonly quanHuyen: QuanHuyenState;
  readonly phuongXa: PhuongXaState;
  readonly capBac: CapBacState;
  readonly chucVu: ChucVuState;
  readonly hoSoGiamDinh: HoSoGiamDinhState;
  readonly hoSoLietSi: HoSoLietSiState;
  readonly donVi: DonViState;
  readonly donViThoiKy: DonViThoiKyState;
  readonly quanHeThanNhan: QuanHeThanNhanState;
  readonly hoSoThanNhan: HoSoThanNhanState;
  readonly thanNhanLietSi: ThanNhanLietSiState;
  readonly thongTinMo: ThongTinMoState;
  readonly nghiaTrang: NghiaTrangState;
  readonly thongTinKhaiQuat: ThongTinKhaiQuatState;
  readonly loaiDiVat: LoaiDiVatState;
  readonly diVat: DiVatState;
  readonly loaiHinhThaiHaiCot: LoaiHinhThaiHaiCotState;
  readonly haiCotLietSi: HaiCotLietSiState;
  readonly hinhThaiHaiCot: HinhThaiHaiCotState;
  readonly loaiMauXetNghiem: LoaiMauXetNghiemState;
  readonly mauXetNghiem: MauXetNghiemState;
  readonly hoaChat: HoaChatState;
  readonly hoaChatMacDinh: HoaChatMacDinhState;
  readonly tachChiet: TachChietState;
  readonly hoaChatTachChiet: HoaChatTachChietState;
  readonly mauTachChiet: MauTachChietState;
  readonly mayPCR: MayPCRState;
  readonly pCRMoi: PCRMoiState;
  readonly pCR: PCRState;
  readonly pCRPhanUngChuan: PCRPhanUngChuanState;
  readonly pCRPhanUng: PCRPhanUngState;
  readonly pCRMau: PCRMauState;
  readonly pCRKetQua: PCRKetQuaState;
  readonly tinhSach: TinhSachState;
  readonly tinhSachPhanUng: TinhSachPhanUngState;
  readonly vungADN: VungADNState;
  readonly mappingDotBien: MappingDotBienState;
  readonly diemDotBien: DiemDotBienState;
  readonly loaiThaoTac: LoaiThaoTacState;
  readonly thaoTac: ThaoTacState;
  readonly trungTam: TrungTamState;
  readonly phongBan: PhongBanState;
  readonly nhanVien: NhanVienState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  tinhThanh,
  quanHuyen,
  phuongXa,
  capBac,
  chucVu,
  hoSoGiamDinh,
  hoSoLietSi,
  donVi,
  donViThoiKy,
  quanHeThanNhan,
  hoSoThanNhan,
  thanNhanLietSi,
  thongTinMo,
  nghiaTrang,
  thongTinKhaiQuat,
  loaiDiVat,
  diVat,
  loaiHinhThaiHaiCot,
  haiCotLietSi,
  hinhThaiHaiCot,
  loaiMauXetNghiem,
  mauXetNghiem,
  hoaChat,
  hoaChatMacDinh,
  tachChiet,
  hoaChatTachChiet,
  mauTachChiet,
  mayPCR,
  pCRMoi,
  pCR,
  pCRPhanUngChuan,
  pCRPhanUng,
  pCRMau,
  pCRKetQua,
  tinhSach,
  tinhSachPhanUng,
  vungADN,
  mappingDotBien,
  diemDotBien,
  loaiThaoTac,
  thaoTac,
  trungTam,
  phongBan,
  nhanVien,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
