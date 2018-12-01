import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TinhThanh from './tinh-thanh';
import QuanHuyen from './quan-huyen';
import PhuongXa from './phuong-xa';
import CapBac from './cap-bac';
import ChucVu from './chuc-vu';
import HoSoGiamDinh from './ho-so-giam-dinh';
import HoSoLietSi from './ho-so-liet-si';
import DonVi from './don-vi';
import DonViThoiKy from './don-vi-thoi-ky';
import QuanHeThanNhan from './quan-he-than-nhan';
import HoSoThanNhan from './ho-so-than-nhan';
import ThanNhanLietSi from './than-nhan-liet-si';
import ThongTinMo from './thong-tin-mo';
import NghiaTrang from './nghia-trang';
import ThongTinKhaiQuat from './thong-tin-khai-quat';
import LoaiDiVat from './loai-di-vat';
import DiVat from './di-vat';
import LoaiHinhThaiHaiCot from './loai-hinh-thai-hai-cot';
import HaiCotLietSi from './hai-cot-liet-si';
import HinhThaiHaiCot from './hinh-thai-hai-cot';
import LoaiMauXetNghiem from './loai-mau-xet-nghiem';
import MauXetNghiem from './mau-xet-nghiem';
import HoaChat from './hoa-chat';
import HoaChatMacDinh from './hoa-chat-mac-dinh';
import TachChiet from './tach-chiet';
import HoaChatTachChiet from './hoa-chat-tach-chiet';
import MauTachChiet from './mau-tach-chiet';
import MayPCR from './may-pcr';
import PCRMoi from './pcr-moi';
import PCR from './pcr';
import PCRPhanUngChuan from './pcr-phan-ung-chuan';
import PCRPhanUng from './pcr-phan-ung';
import PCRMau from './pcr-mau';
import PCRKetQua from './pcr-ket-qua';
import TinhSach from './tinh-sach';
import TinhSachPhanUng from './tinh-sach-phan-ung';
import VungADN from './vung-adn';
import MappingDotBien from './mapping-dot-bien';
import DiemDotBien from './diem-dot-bien';
import LoaiThaoTac from './loai-thao-tac';
import ThaoTac from './thao-tac';
import TrungTam from './trung-tam';
import PhongBan from './phong-ban';
import NhanVien from './nhan-vien';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/tinh-thanh`} component={TinhThanh} />
      <ErrorBoundaryRoute path={`${match.url}/quan-huyen`} component={QuanHuyen} />
      <ErrorBoundaryRoute path={`${match.url}/phuong-xa`} component={PhuongXa} />
      <ErrorBoundaryRoute path={`${match.url}/cap-bac`} component={CapBac} />
      <ErrorBoundaryRoute path={`${match.url}/chuc-vu`} component={ChucVu} />
      <ErrorBoundaryRoute path={`${match.url}/ho-so-giam-dinh`} component={HoSoGiamDinh} />
      <ErrorBoundaryRoute path={`${match.url}/ho-so-liet-si`} component={HoSoLietSi} />
      <ErrorBoundaryRoute path={`${match.url}/don-vi`} component={DonVi} />
      <ErrorBoundaryRoute path={`${match.url}/don-vi-thoi-ky`} component={DonViThoiKy} />
      <ErrorBoundaryRoute path={`${match.url}/quan-he-than-nhan`} component={QuanHeThanNhan} />
      <ErrorBoundaryRoute path={`${match.url}/ho-so-than-nhan`} component={HoSoThanNhan} />
      <ErrorBoundaryRoute path={`${match.url}/than-nhan-liet-si`} component={ThanNhanLietSi} />
      <ErrorBoundaryRoute path={`${match.url}/thong-tin-mo`} component={ThongTinMo} />
      <ErrorBoundaryRoute path={`${match.url}/nghia-trang`} component={NghiaTrang} />
      <ErrorBoundaryRoute path={`${match.url}/thong-tin-khai-quat`} component={ThongTinKhaiQuat} />
      <ErrorBoundaryRoute path={`${match.url}/loai-di-vat`} component={LoaiDiVat} />
      <ErrorBoundaryRoute path={`${match.url}/di-vat`} component={DiVat} />
      <ErrorBoundaryRoute path={`${match.url}/loai-hinh-thai-hai-cot`} component={LoaiHinhThaiHaiCot} />
      <ErrorBoundaryRoute path={`${match.url}/hai-cot-liet-si`} component={HaiCotLietSi} />
      <ErrorBoundaryRoute path={`${match.url}/hinh-thai-hai-cot`} component={HinhThaiHaiCot} />
      <ErrorBoundaryRoute path={`${match.url}/loai-mau-xet-nghiem`} component={LoaiMauXetNghiem} />
      <ErrorBoundaryRoute path={`${match.url}/mau-xet-nghiem`} component={MauXetNghiem} />
      <ErrorBoundaryRoute path={`${match.url}/hoa-chat`} component={HoaChat} />
      <ErrorBoundaryRoute path={`${match.url}/hoa-chat-mac-dinh`} component={HoaChatMacDinh} />
      <ErrorBoundaryRoute path={`${match.url}/tach-chiet`} component={TachChiet} />
      <ErrorBoundaryRoute path={`${match.url}/hoa-chat-tach-chiet`} component={HoaChatTachChiet} />
      <ErrorBoundaryRoute path={`${match.url}/mau-tach-chiet`} component={MauTachChiet} />
      <ErrorBoundaryRoute path={`${match.url}/may-pcr`} component={MayPCR} />
      <ErrorBoundaryRoute path={`${match.url}/pcr-moi`} component={PCRMoi} />
      <ErrorBoundaryRoute path={`${match.url}/pcr`} component={PCR} />
      <ErrorBoundaryRoute path={`${match.url}/pcr-phan-ung-chuan`} component={PCRPhanUngChuan} />
      <ErrorBoundaryRoute path={`${match.url}/pcr-phan-ung`} component={PCRPhanUng} />
      <ErrorBoundaryRoute path={`${match.url}/pcr-mau`} component={PCRMau} />
      <ErrorBoundaryRoute path={`${match.url}/pcr-ket-qua`} component={PCRKetQua} />
      <ErrorBoundaryRoute path={`${match.url}/tinh-sach`} component={TinhSach} />
      <ErrorBoundaryRoute path={`${match.url}/tinh-sach-phan-ung`} component={TinhSachPhanUng} />
      <ErrorBoundaryRoute path={`${match.url}/vung-adn`} component={VungADN} />
      <ErrorBoundaryRoute path={`${match.url}/mapping-dot-bien`} component={MappingDotBien} />
      <ErrorBoundaryRoute path={`${match.url}/diem-dot-bien`} component={DiemDotBien} />
      <ErrorBoundaryRoute path={`${match.url}/loai-thao-tac`} component={LoaiThaoTac} />
      <ErrorBoundaryRoute path={`${match.url}/thao-tac`} component={ThaoTac} />
      <ErrorBoundaryRoute path={`${match.url}/trung-tam`} component={TrungTam} />
      <ErrorBoundaryRoute path={`${match.url}/phong-ban`} component={PhongBan} />
      <ErrorBoundaryRoute path={`${match.url}/nhan-vien`} component={NhanVien} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
