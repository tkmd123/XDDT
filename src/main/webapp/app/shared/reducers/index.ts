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
import hoSoLietSi, {
  HoSoLietSiState
} from 'app/entities/ho-so-liet-si/ho-so-liet-si.reducer';
// prettier-ignore
import nhanDang, {
  NhanDangState
} from 'app/entities/nhan-dang/nhan-dang.reducer';
// prettier-ignore
import nhanDangLietSi, {
  NhanDangLietSiState
} from 'app/entities/nhan-dang-liet-si/nhan-dang-liet-si.reducer';
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
import vungADN, {
  VungADNState
} from 'app/entities/vung-adn/vung-adn.reducer';
// prettier-ignore
import thongTinADN, {
  ThongTinADNState
} from 'app/entities/thong-tin-adn/thong-tin-adn.reducer';
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
import phongBan, {
  PhongBanState
} from 'app/entities/phong-ban/phong-ban.reducer';
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
  readonly hoSoLietSi: HoSoLietSiState;
  readonly nhanDang: NhanDangState;
  readonly nhanDangLietSi: NhanDangLietSiState;
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
  readonly vungADN: VungADNState;
  readonly thongTinADN: ThongTinADNState;
  readonly diemDotBien: DiemDotBienState;
  readonly loaiThaoTac: LoaiThaoTacState;
  readonly thaoTac: ThaoTacState;
  readonly phongBan: PhongBanState;
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
  hoSoLietSi,
  nhanDang,
  nhanDangLietSi,
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
  vungADN,
  thongTinADN,
  diemDotBien,
  loaiThaoTac,
  thaoTac,
  phongBan,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
