import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TinhThanh from './tinh-thanh';
import QuanHuyen from './quan-huyen';
import PhuongXa from './phuong-xa';
import CapBac from './cap-bac';
import ChucVu from './chuc-vu';
import HoSoLietSi from './ho-so-liet-si';
import NhanDang from './nhan-dang';
import NhanDangLietSi from './nhan-dang-liet-si';
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
import VungADN from './vung-adn';
import ThongTinADN from './thong-tin-adn';
import DiemDotBien from './diem-dot-bien';
import LoaiThaoTac from './loai-thao-tac';
import ThaoTac from './thao-tac';
import PhongBan from './phong-ban';
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
      <ErrorBoundaryRoute path={`${match.url}/ho-so-liet-si`} component={HoSoLietSi} />
      <ErrorBoundaryRoute path={`${match.url}/nhan-dang`} component={NhanDang} />
      <ErrorBoundaryRoute path={`${match.url}/nhan-dang-liet-si`} component={NhanDangLietSi} />
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
      <ErrorBoundaryRoute path={`${match.url}/vung-adn`} component={VungADN} />
      <ErrorBoundaryRoute path={`${match.url}/thong-tin-adn`} component={ThongTinADN} />
      <ErrorBoundaryRoute path={`${match.url}/diem-dot-bien`} component={DiemDotBien} />
      <ErrorBoundaryRoute path={`${match.url}/loai-thao-tac`} component={LoaiThaoTac} />
      <ErrorBoundaryRoute path={`${match.url}/thao-tac`} component={ThaoTac} />
      <ErrorBoundaryRoute path={`${match.url}/phong-ban`} component={PhongBan} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
