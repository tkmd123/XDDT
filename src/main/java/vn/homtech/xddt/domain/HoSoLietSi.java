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
public class HoSoLietSi extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "don_vi")
    private String donVi;

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

    @Column(name = "trang_thai_xac_minh")
    private String trangThaiXacMinh;

    @Column(name = "chieu_cao")
    private Integer chieuCao;

    @Column(name = "can_nang")
    private Integer canNang;

    @Column(name = "nhom_mau")
    private String nhomMau;

    @Column(name = "dac_diem_rang")
    private String dacDiemRang;

    @Column(name = "tinh_huong_hy_sinh")
    private String tinhHuongHySinh;

    @Column(name = "tinh_huong_tim_thay")
    private String tinhHuongTimThay;

    @Column(name = "dac_diem_nhan_dang")
    private String dacDiemNhanDang;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @Column(name = "udf_4")
    private String udf4;

    @Column(name = "udf_5")
    private String udf5;

    @OneToMany(mappedBy = "lietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThanNhanLietSi> lietSis = new HashSet<>();
    @OneToMany(mappedBy = "lietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HaiCotLietSi> lietSiMos = new HashSet<>();
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

    public String getDonVi() {
        return donVi;
    }

    public HoSoLietSi donVi(String donVi) {
        this.donVi = donVi;
        return this;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
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

    public Integer getChieuCao() {
        return chieuCao;
    }

    public HoSoLietSi chieuCao(Integer chieuCao) {
        this.chieuCao = chieuCao;
        return this;
    }

    public void setChieuCao(Integer chieuCao) {
        this.chieuCao = chieuCao;
    }

    public Integer getCanNang() {
        return canNang;
    }

    public HoSoLietSi canNang(Integer canNang) {
        this.canNang = canNang;
        return this;
    }

    public void setCanNang(Integer canNang) {
        this.canNang = canNang;
    }

    public String getNhomMau() {
        return nhomMau;
    }

    public HoSoLietSi nhomMau(String nhomMau) {
        this.nhomMau = nhomMau;
        return this;
    }

    public void setNhomMau(String nhomMau) {
        this.nhomMau = nhomMau;
    }

    public String getDacDiemRang() {
        return dacDiemRang;
    }

    public HoSoLietSi dacDiemRang(String dacDiemRang) {
        this.dacDiemRang = dacDiemRang;
        return this;
    }

    public void setDacDiemRang(String dacDiemRang) {
        this.dacDiemRang = dacDiemRang;
    }

    public String getTinhHuongHySinh() {
        return tinhHuongHySinh;
    }

    public HoSoLietSi tinhHuongHySinh(String tinhHuongHySinh) {
        this.tinhHuongHySinh = tinhHuongHySinh;
        return this;
    }

    public void setTinhHuongHySinh(String tinhHuongHySinh) {
        this.tinhHuongHySinh = tinhHuongHySinh;
    }

    public String getTinhHuongTimThay() {
        return tinhHuongTimThay;
    }

    public HoSoLietSi tinhHuongTimThay(String tinhHuongTimThay) {
        this.tinhHuongTimThay = tinhHuongTimThay;
        return this;
    }

    public void setTinhHuongTimThay(String tinhHuongTimThay) {
        this.tinhHuongTimThay = tinhHuongTimThay;
    }

    public String getDacDiemNhanDang() {
        return dacDiemNhanDang;
    }

    public HoSoLietSi dacDiemNhanDang(String dacDiemNhanDang) {
        this.dacDiemNhanDang = dacDiemNhanDang;
        return this;
    }

    public void setDacDiemNhanDang(String dacDiemNhanDang) {
        this.dacDiemNhanDang = dacDiemNhanDang;
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

    public String getUdf1() {
        return udf1;
    }

    public HoSoLietSi udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HoSoLietSi udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HoSoLietSi udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public HoSoLietSi udf4(String udf4) {
        this.udf4 = udf4;
        return this;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public HoSoLietSi udf5(String udf5) {
        this.udf5 = udf5;
        return this;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
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
            ", donVi='" + getDonVi() + "'" +
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
            ", trangThaiXacMinh='" + getTrangThaiXacMinh() + "'" +
            ", chieuCao=" + getChieuCao() +
            ", canNang=" + getCanNang() +
            ", nhomMau='" + getNhomMau() + "'" +
            ", dacDiemRang='" + getDacDiemRang() + "'" +
            ", tinhHuongHySinh='" + getTinhHuongHySinh() + "'" +
            ", tinhHuongTimThay='" + getTinhHuongTimThay() + "'" +
            ", dacDiemNhanDang='" + getDacDiemNhanDang() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            ", udf4='" + getUdf4() + "'" +
            ", udf5='" + getUdf5() + "'" +
            "}";
    }
}
