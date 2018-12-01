package vn.homtech.xddt.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(vn.homtech.xddt.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TinhThanh.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TinhThanh.class.getName() + ".tinhThanhQuanHuyens", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.QuanHuyen.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.QuanHuyen.class.getName() + ".quanHuyenPhuongXas", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhuongXa.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhuongXa.class.getName() + ".phuongXaNghiaTrangs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhuongXa.class.getName() + ".phuongXaLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhuongXa.class.getName() + ".phuongXaThanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.CapBac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.CapBac.class.getName() + ".capBacLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ChucVu.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ChucVu.class.getName() + ".chucVuLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoGiamDinh.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoGiamDinh.class.getName() + ".hoSoGiamDinhs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoGiamDinh.class.getName() + ".giamDinhThanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoGiamDinh.class.getName() + ".giamDinhLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoLietSi.class.getName() + ".lietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoLietSi.class.getName() + ".lietSiMos", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonVi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonVi.class.getName() + ".donViHySinhs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonVi.class.getName() + ".donViHuanLuyens", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonVi.class.getName() + ".donViQuanLies", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonVi.class.getName() + ".donViThoiKies", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DonViThoiKy.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.QuanHeThanNhan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.QuanHeThanNhan.class.getName() + ".quanHeThanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoThanNhan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoThanNhan.class.getName() + ".thanNhans", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoSoThanNhan.class.getName() + ".thanNhanMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThanNhanLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThongTinMo.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThongTinMo.class.getName() + ".moLietSis", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NghiaTrang.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NghiaTrang.class.getName() + ".nghiaTrangMos", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThongTinKhaiQuat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThongTinKhaiQuat.class.getName() + ".khaiQuatHaiCots", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThongTinKhaiQuat.class.getName() + ".khaiQuatDiVats", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiDiVat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiDiVat.class.getName() + ".loaiDiVats", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DiVat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiHinhThaiHaiCot.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiHinhThaiHaiCot.class.getName() + ".loaiHinhThaiHaiCots", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HaiCotLietSi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HaiCotLietSi.class.getName() + ".thongTinHinhThaiHocs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HaiCotLietSi.class.getName() + ".haiCotMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HinhThaiHaiCot.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiMauXetNghiem.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiMauXetNghiem.class.getName() + ".loaiMauXetNghiems", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName() + ".mauPCRS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName() + ".mauTachChiets", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName() + ".mauDiemDotBiens", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName() + ".mauPhanTiches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauXetNghiem.class.getName() + ".mauTinhSaches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChat.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChat.class.getName() + ".hoaChatTinhSaches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChat.class.getName() + ".hoaChatPhanUngs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChat.class.getName() + ".hoaChatTachChiets", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChatMacDinh.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TachChiet.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TachChiet.class.getName() + ".tachChietMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TachChiet.class.getName() + ".tachChietHoaChats", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.HoaChatTachChiet.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MauTachChiet.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MayPCR.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MayPCR.class.getName() + ".mayTinhSaches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MayPCR.class.getName() + ".mayPCRS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRMoi.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRMoi.class.getName() + ".moiPCRS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCR.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCR.class.getName() + ".pcrMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCR.class.getName() + ".pcrPhanUngs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRPhanUngChuan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRPhanUng.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRMau.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRKetQua.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PCRKetQua.class.getName() + ".pcrKetQuas", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TinhSach.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TinhSach.class.getName() + ".tinhSaches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TinhSachPhanUng.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.VungADN.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.VungADN.class.getName() + ".vungADNS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.MappingDotBien.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.DiemDotBien.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiThaoTac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiThaoTac.class.getName() + ".thaoTacHienTais", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiThaoTac.class.getName() + ".loaiThaoTacs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.LoaiThaoTac.class.getName() + ".thaoTacTiepTheos", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.ThaoTac.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TrungTam.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.TrungTam.class.getName() + ".trungtams", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhongBan.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhongBan.class.getName() + ".phongbans", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhongBan.class.getName() + ".phongLabPhanTiches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.PhongBan.class.getName() + ".phongBanThaoTacs", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName(), jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienTinhSaches", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienPCRS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienNghienMaus", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienTachADNS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienHSGDS", jcacheConfiguration);
            cm.createCache(vn.homtech.xddt.domain.NhanVien.class.getName() + ".nhanVienNhanMaus", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
