package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A HoSoLietSi.
 */
@Entity
@Table(name = "ho_so_liet_si")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hosolietsi")
public class HoSoLietSi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_ccs")
    private String maCCS;

    @Column(name = "ma_liet_si")
    private String maLietSi;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ten_khac")
    private String tenKhac;

    @Column(name = "bi_danh")
    private String biDanh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "nam_sinh")
    private String namSinh;

    @Column(name = "que_thon")
    private String queThon;

    @Column(name = "que_xa")
    private String queXa;

    @Column(name = "que_huyen")
    private String queHuyen;

    @Column(name = "que_tinh")
    private String queTinh;

    @Column(name = "nam_nhap_ngu")
    private Integer namNhapNgu;

    @Column(name = "nam_xuat_ngu")
    private Integer namXuatNgu;

    @Column(name = "nam_tai_ngu")
    private Integer namTaiNgu;

    @Column(name = "nam_di_b")
    private String namDiB;

    @Column(name = "hy_sinh_ngay")
    private Integer hySinhNgay;

    @Column(name = "hy_sinh_thang")
    private Integer hySinhThang;

    @Column(name = "hy_sinh_nam")
    private Integer hySinhNam;

    @Column(name = "hy_sinh_ly_do")
    private String hySinhLyDo;

    @Column(name = "hy_sinh_don_vi")
    private String hySinhDonVi;

    @Column(name = "hy_sinh_tran_danh")
    private String hySinhTranDanh;

    @Column(name = "hy_sinh_dia_diem")
    private String hySinhDiaDiem;

    @Column(name = "hy_sinh_xa")
    private String hySinhXa;

    @Column(name = "hy_sinh_huyen")
    private String hySinhHuyen;

    @Column(name = "hy_sinh_tinh")
    private String hySinhTinh;

    @Column(name = "an_tang_dia_diem")
    private String anTangDiaDiem;

    @Column(name = "an_tang_xa")
    private String anTangXa;

    @Column(name = "an_tang_huyen")
    private String anTangHuyen;

    @Column(name = "an_tang_tinh")
    private String anTangTinh;

    @Column(name = "ngay_bao_tu")
    private String ngayBaoTu;

    @Column(name = "giay_bao_tu")
    private String giayBaoTu;

    @Column(name = "vat_dung_kem_theo")
    private String vatDungKemTheo;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "trang_thai_xac_minh")
    private String trangThaiXacMinh;

    @Column(name = "u_df_1")
    private String uDF1;

    @Column(name = "u_df_2")
    private String uDF2;

    @Column(name = "u_df_3")
    private String uDF3;

    @OneToMany(mappedBy = "lietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThanNhanLietSi> lietSis = new HashSet<>();
    @OneToMany(mappedBy = "lietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HaiCotLietSi> lietSiMos = new HashSet<>();
    @OneToMany(mappedBy = "lietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhanDangLietSi> lietSiNhanDangs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("phuongXaLietSis")
    private PhuongXa phuongXa;

    @ManyToOne
    @JsonIgnoreProperties("donViHySinhs")
    private DonVi donViHiSinh;

    @ManyToOne
    @JsonIgnoreProperties("donViHuanLuyens")
    private DonVi donViHuanLuyen;

    @ManyToOne
    @JsonIgnoreProperties("capBacLietSis")
    private CapBac capBac;

    @ManyToOne
    @JsonIgnoreProperties("chucVuLietSis")
    private ChucVu chucVu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCCS() {
        return maCCS;
    }

    public HoSoLietSi maCCS(String maCCS) {
        this.maCCS = maCCS;
        return this;
    }

    public void setMaCCS(String maCCS) {
        this.maCCS = maCCS;
    }

    public String getMaLietSi() {
        return maLietSi;
    }

    public HoSoLietSi maLietSi(String maLietSi) {
        this.maLietSi = maLietSi;
        return this;
    }

    public void setMaLietSi(String maLietSi) {
        this.maLietSi = maLietSi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public HoSoLietSi hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenKhac() {
        return tenKhac;
    }

    public HoSoLietSi tenKhac(String tenKhac) {
        this.tenKhac = tenKhac;
        return this;
    }

    public void setTenKhac(String tenKhac) {
        this.tenKhac = tenKhac;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public HoSoLietSi biDanh(String biDanh) {
        this.biDanh = biDanh;
        return this;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public HoSoLietSi gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public HoSoLietSi namSinh(String namSinh) {
        this.namSinh = namSinh;
        return this;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getQueThon() {
        return queThon;
    }

    public HoSoLietSi queThon(String queThon) {
        this.queThon = queThon;
        return this;
    }

    public void setQueThon(String queThon) {
        this.queThon = queThon;
    }

    public String getQueXa() {
        return queXa;
    }

    public HoSoLietSi queXa(String queXa) {
        this.queXa = queXa;
        return this;
    }

    public void setQueXa(String queXa) {
        this.queXa = queXa;
    }

    public String getQueHuyen() {
        return queHuyen;
    }

    public HoSoLietSi queHuyen(String queHuyen) {
        this.queHuyen = queHuyen;
        return this;
    }

    public void setQueHuyen(String queHuyen) {
        this.queHuyen = queHuyen;
    }

    public String getQueTinh() {
        return queTinh;
    }

    public HoSoLietSi queTinh(String queTinh) {
        this.queTinh = queTinh;
        return this;
    }

    public void setQueTinh(String queTinh) {
        this.queTinh = queTinh;
    }

    public Integer getNamNhapNgu() {
        return namNhapNgu;
    }

    public HoSoLietSi namNhapNgu(Integer namNhapNgu) {
        this.namNhapNgu = namNhapNgu;
        return this;
    }

    public void setNamNhapNgu(Integer namNhapNgu) {
        this.namNhapNgu = namNhapNgu;
    }

    public Integer getNamXuatNgu() {
        return namXuatNgu;
    }

    public HoSoLietSi namXuatNgu(Integer namXuatNgu) {
        this.namXuatNgu = namXuatNgu;
        return this;
    }

    public void setNamXuatNgu(Integer namXuatNgu) {
        this.namXuatNgu = namXuatNgu;
    }

    public Integer getNamTaiNgu() {
        return namTaiNgu;
    }

    public HoSoLietSi namTaiNgu(Integer namTaiNgu) {
        this.namTaiNgu = namTaiNgu;
        return this;
    }

    public void setNamTaiNgu(Integer namTaiNgu) {
        this.namTaiNgu = namTaiNgu;
    }

    public String getNamDiB() {
        return namDiB;
    }

    public HoSoLietSi namDiB(String namDiB) {
        this.namDiB = namDiB;
        return this;
    }

    public void setNamDiB(String namDiB) {
        this.namDiB = namDiB;
    }

    public Integer getHySinhNgay() {
        return hySinhNgay;
    }

    public HoSoLietSi hySinhNgay(Integer hySinhNgay) {
        this.hySinhNgay = hySinhNgay;
        return this;
    }

    public void setHySinhNgay(Integer hySinhNgay) {
        this.hySinhNgay = hySinhNgay;
    }

    public Integer getHySinhThang() {
        return hySinhThang;
    }

    public HoSoLietSi hySinhThang(Integer hySinhThang) {
        this.hySinhThang = hySinhThang;
        return this;
    }

    public void setHySinhThang(Integer hySinhThang) {
        this.hySinhThang = hySinhThang;
    }

    public Integer getHySinhNam() {
        return hySinhNam;
    }

    public HoSoLietSi hySinhNam(Integer hySinhNam) {
        this.hySinhNam = hySinhNam;
        return this;
    }

    public void setHySinhNam(Integer hySinhNam) {
        this.hySinhNam = hySinhNam;
    }

    public String getHySinhLyDo() {
        return hySinhLyDo;
    }

    public HoSoLietSi hySinhLyDo(String hySinhLyDo) {
        this.hySinhLyDo = hySinhLyDo;
        return this;
    }

    public void setHySinhLyDo(String hySinhLyDo) {
        this.hySinhLyDo = hySinhLyDo;
    }

    public String getHySinhDonVi() {
        return hySinhDonVi;
    }

    public HoSoLietSi hySinhDonVi(String hySinhDonVi) {
        this.hySinhDonVi = hySinhDonVi;
        return this;
    }

    public void setHySinhDonVi(String hySinhDonVi) {
        this.hySinhDonVi = hySinhDonVi;
    }

    public String getHySinhTranDanh() {
        return hySinhTranDanh;
    }

    public HoSoLietSi hySinhTranDanh(String hySinhTranDanh) {
        this.hySinhTranDanh = hySinhTranDanh;
        return this;
    }

    public void setHySinhTranDanh(String hySinhTranDanh) {
        this.hySinhTranDanh = hySinhTranDanh;
    }

    public String getHySinhDiaDiem() {
        return hySinhDiaDiem;
    }

    public HoSoLietSi hySinhDiaDiem(String hySinhDiaDiem) {
        this.hySinhDiaDiem = hySinhDiaDiem;
        return this;
    }

    public void setHySinhDiaDiem(String hySinhDiaDiem) {
        this.hySinhDiaDiem = hySinhDiaDiem;
    }

    public String getHySinhXa() {
        return hySinhXa;
    }

    public HoSoLietSi hySinhXa(String hySinhXa) {
        this.hySinhXa = hySinhXa;
        return this;
    }

    public void setHySinhXa(String hySinhXa) {
        this.hySinhXa = hySinhXa;
    }

    public String getHySinhHuyen() {
        return hySinhHuyen;
    }

    public HoSoLietSi hySinhHuyen(String hySinhHuyen) {
        this.hySinhHuyen = hySinhHuyen;
        return this;
    }

    public void setHySinhHuyen(String hySinhHuyen) {
        this.hySinhHuyen = hySinhHuyen;
    }

    public String getHySinhTinh() {
        return hySinhTinh;
    }

    public HoSoLietSi hySinhTinh(String hySinhTinh) {
        this.hySinhTinh = hySinhTinh;
        return this;
    }

    public void setHySinhTinh(String hySinhTinh) {
        this.hySinhTinh = hySinhTinh;
    }

    public String getAnTangDiaDiem() {
        return anTangDiaDiem;
    }

    public HoSoLietSi anTangDiaDiem(String anTangDiaDiem) {
        this.anTangDiaDiem = anTangDiaDiem;
        return this;
    }

    public void setAnTangDiaDiem(String anTangDiaDiem) {
        this.anTangDiaDiem = anTangDiaDiem;
    }

    public String getAnTangXa() {
        return anTangXa;
    }

    public HoSoLietSi anTangXa(String anTangXa) {
        this.anTangXa = anTangXa;
        return this;
    }

    public void setAnTangXa(String anTangXa) {
        this.anTangXa = anTangXa;
    }

    public String getAnTangHuyen() {
        return anTangHuyen;
    }

    public HoSoLietSi anTangHuyen(String anTangHuyen) {
        this.anTangHuyen = anTangHuyen;
        return this;
    }

    public void setAnTangHuyen(String anTangHuyen) {
        this.anTangHuyen = anTangHuyen;
    }

    public String getAnTangTinh() {
        return anTangTinh;
    }

    public HoSoLietSi anTangTinh(String anTangTinh) {
        this.anTangTinh = anTangTinh;
        return this;
    }

    public void setAnTangTinh(String anTangTinh) {
        this.anTangTinh = anTangTinh;
    }

    public String getNgayBaoTu() {
        return ngayBaoTu;
    }

    public HoSoLietSi ngayBaoTu(String ngayBaoTu) {
        this.ngayBaoTu = ngayBaoTu;
        return this;
    }

    public void setNgayBaoTu(String ngayBaoTu) {
        this.ngayBaoTu = ngayBaoTu;
    }

    public String getGiayBaoTu() {
        return giayBaoTu;
    }

    public HoSoLietSi giayBaoTu(String giayBaoTu) {
        this.giayBaoTu = giayBaoTu;
        return this;
    }

    public void setGiayBaoTu(String giayBaoTu) {
        this.giayBaoTu = giayBaoTu;
    }

    public String getVatDungKemTheo() {
        return vatDungKemTheo;
    }

    public HoSoLietSi vatDungKemTheo(String vatDungKemTheo) {
        this.vatDungKemTheo = vatDungKemTheo;
        return this;
    }

    public void setVatDungKemTheo(String vatDungKemTheo) {
        this.vatDungKemTheo = vatDungKemTheo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public HoSoLietSi ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HoSoLietSi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getTrangThaiXacMinh() {
        return trangThaiXacMinh;
    }

    public HoSoLietSi trangThaiXacMinh(String trangThaiXacMinh) {
        this.trangThaiXacMinh = trangThaiXacMinh;
        return this;
    }

    public void setTrangThaiXacMinh(String trangThaiXacMinh) {
        this.trangThaiXacMinh = trangThaiXacMinh;
    }

    public String getuDF1() {
        return uDF1;
    }

    public HoSoLietSi uDF1(String uDF1) {
        this.uDF1 = uDF1;
        return this;
    }

    public void setuDF1(String uDF1) {
        this.uDF1 = uDF1;
    }

    public String getuDF2() {
        return uDF2;
    }

    public HoSoLietSi uDF2(String uDF2) {
        this.uDF2 = uDF2;
        return this;
    }

    public void setuDF2(String uDF2) {
        this.uDF2 = uDF2;
    }

    public String getuDF3() {
        return uDF3;
    }

    public HoSoLietSi uDF3(String uDF3) {
        this.uDF3 = uDF3;
        return this;
    }

    public void setuDF3(String uDF3) {
        this.uDF3 = uDF3;
    }

    public Set<ThanNhanLietSi> getLietSis() {
        return lietSis;
    }

    public HoSoLietSi lietSis(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.lietSis = thanNhanLietSis;
        return this;
    }

    public HoSoLietSi addLietSi(ThanNhanLietSi thanNhanLietSi) {
        this.lietSis.add(thanNhanLietSi);
        thanNhanLietSi.setLietSi(this);
        return this;
    }

    public HoSoLietSi removeLietSi(ThanNhanLietSi thanNhanLietSi) {
        this.lietSis.remove(thanNhanLietSi);
        thanNhanLietSi.setLietSi(null);
        return this;
    }

    public void setLietSis(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.lietSis = thanNhanLietSis;
    }

    public Set<HaiCotLietSi> getLietSiMos() {
        return lietSiMos;
    }

    public HoSoLietSi lietSiMos(Set<HaiCotLietSi> haiCotLietSis) {
        this.lietSiMos = haiCotLietSis;
        return this;
    }

    public HoSoLietSi addLietSiMo(HaiCotLietSi haiCotLietSi) {
        this.lietSiMos.add(haiCotLietSi);
        haiCotLietSi.setLietSi(this);
        return this;
    }

    public HoSoLietSi removeLietSiMo(HaiCotLietSi haiCotLietSi) {
        this.lietSiMos.remove(haiCotLietSi);
        haiCotLietSi.setLietSi(null);
        return this;
    }

    public void setLietSiMos(Set<HaiCotLietSi> haiCotLietSis) {
        this.lietSiMos = haiCotLietSis;
    }

    public Set<NhanDangLietSi> getLietSiNhanDangs() {
        return lietSiNhanDangs;
    }

    public HoSoLietSi lietSiNhanDangs(Set<NhanDangLietSi> nhanDangLietSis) {
        this.lietSiNhanDangs = nhanDangLietSis;
        return this;
    }

    public HoSoLietSi addLietSiNhanDang(NhanDangLietSi nhanDangLietSi) {
        this.lietSiNhanDangs.add(nhanDangLietSi);
        nhanDangLietSi.setLietSi(this);
        return this;
    }

    public HoSoLietSi removeLietSiNhanDang(NhanDangLietSi nhanDangLietSi) {
        this.lietSiNhanDangs.remove(nhanDangLietSi);
        nhanDangLietSi.setLietSi(null);
        return this;
    }

    public void setLietSiNhanDangs(Set<NhanDangLietSi> nhanDangLietSis) {
        this.lietSiNhanDangs = nhanDangLietSis;
    }

    public PhuongXa getPhuongXa() {
        return phuongXa;
    }

    public HoSoLietSi phuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
        return this;
    }

    public void setPhuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
    }

    public DonVi getDonViHiSinh() {
        return donViHiSinh;
    }

    public HoSoLietSi donViHiSinh(DonVi donVi) {
        this.donViHiSinh = donVi;
        return this;
    }

    public void setDonViHiSinh(DonVi donVi) {
        this.donViHiSinh = donVi;
    }

    public DonVi getDonViHuanLuyen() {
        return donViHuanLuyen;
    }

    public HoSoLietSi donViHuanLuyen(DonVi donVi) {
        this.donViHuanLuyen = donVi;
        return this;
    }

    public void setDonViHuanLuyen(DonVi donVi) {
        this.donViHuanLuyen = donVi;
    }

    public CapBac getCapBac() {
        return capBac;
    }

    public HoSoLietSi capBac(CapBac capBac) {
        this.capBac = capBac;
        return this;
    }

    public void setCapBac(CapBac capBac) {
        this.capBac = capBac;
    }

    public ChucVu getChucVu() {
        return chucVu;
    }

    public HoSoLietSi chucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
        return this;
    }

    public void setChucVu(ChucVu chucVu) {
        this.chucVu = chucVu;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HoSoLietSi hoSoLietSi = (HoSoLietSi) o;
        if (hoSoLietSi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoSoLietSi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoSoLietSi{" +
            "id=" + getId() +
            ", maCCS='" + getMaCCS() + "'" +
            ", maLietSi='" + getMaLietSi() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", tenKhac='" + getTenKhac() + "'" +
            ", biDanh='" + getBiDanh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", namSinh='" + getNamSinh() + "'" +
            ", queThon='" + getQueThon() + "'" +
            ", queXa='" + getQueXa() + "'" +
            ", queHuyen='" + getQueHuyen() + "'" +
            ", queTinh='" + getQueTinh() + "'" +
            ", namNhapNgu=" + getNamNhapNgu() +
            ", namXuatNgu=" + getNamXuatNgu() +
            ", namTaiNgu=" + getNamTaiNgu() +
            ", namDiB='" + getNamDiB() + "'" +
            ", hySinhNgay=" + getHySinhNgay() +
            ", hySinhThang=" + getHySinhThang() +
            ", hySinhNam=" + getHySinhNam() +
            ", hySinhLyDo='" + getHySinhLyDo() + "'" +
            ", hySinhDonVi='" + getHySinhDonVi() + "'" +
            ", hySinhTranDanh='" + getHySinhTranDanh() + "'" +
            ", hySinhDiaDiem='" + getHySinhDiaDiem() + "'" +
            ", hySinhXa='" + getHySinhXa() + "'" +
            ", hySinhHuyen='" + getHySinhHuyen() + "'" +
            ", hySinhTinh='" + getHySinhTinh() + "'" +
            ", anTangDiaDiem='" + getAnTangDiaDiem() + "'" +
            ", anTangXa='" + getAnTangXa() + "'" +
            ", anTangHuyen='" + getAnTangHuyen() + "'" +
            ", anTangTinh='" + getAnTangTinh() + "'" +
            ", ngayBaoTu='" + getNgayBaoTu() + "'" +
            ", giayBaoTu='" + getGiayBaoTu() + "'" +
            ", vatDungKemTheo='" + getVatDungKemTheo() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", trangThaiXacMinh='" + getTrangThaiXacMinh() + "'" +
            ", uDF1='" + getuDF1() + "'" +
            ", uDF2='" + getuDF2() + "'" +
            ", uDF3='" + getuDF3() + "'" +
            "}";
    }
}
