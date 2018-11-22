import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/tinh-thanh">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.tinhThanh" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/quan-huyen">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.quanHuyen" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/phuong-xa">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.phuongXa" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/cap-bac">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.capBac" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/chuc-vu">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.chucVu" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ho-so-liet-si">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.hoSoLietSi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/nhan-dang">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.nhanDang" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/nhan-dang-liet-si">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.nhanDangLietSi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/don-vi">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.donVi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/don-vi-thoi-ky">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.donViThoiKy" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/quan-he-than-nhan">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.quanHeThanNhan" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/ho-so-than-nhan">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.hoSoThanNhan" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/than-nhan-liet-si">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.thanNhanLietSi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/thong-tin-mo">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.thongTinMo" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/nghia-trang">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.nghiaTrang" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/thong-tin-khai-quat">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.thongTinKhaiQuat" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/loai-di-vat">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.loaiDiVat" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/di-vat">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.diVat" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/loai-hinh-thai-hai-cot">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.loaiHinhThaiHaiCot" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/hai-cot-liet-si">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.haiCotLietSi" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/hinh-thai-hai-cot">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.hinhThaiHaiCot" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/loai-mau-xet-nghiem">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.loaiMauXetNghiem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/mau-xet-nghiem">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.mauXetNghiem" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/vung-adn">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.vungAdn" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/thong-tin-adn">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.thongTinAdn" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/diem-dot-bien">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.diemDotBien" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/loai-thao-tac">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.loaiThaoTac" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/thao-tac">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.thaoTac" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/phong-ban">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.phongBan" />
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
