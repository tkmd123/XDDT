package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import vn.homtech.xddt.domain.enumeration.TrangThaiMau;

/**
 * A MauXetNghiem.
 */
@Entity
@Table(name = "mau_xet_nghiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "mauxetnghiem")
public class MauXetNghiem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_mau_xet_nghiem")
    private String maMauXetNghiem;

    @Column(name = "ngay_lay_mau")
    private Instant ngayLayMau;

    @Column(name = "nguoi_lay_mau")
    private String nguoiLayMau;

    @Column(name = "ngay_tiep_nhan")
    private Instant ngayTiepNhan;

    @Column(name = "so_luong_mau")
    private Integer soLuongMau;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_mau")
    private TrangThaiMau trangThaiMau;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "file_goc")
    private String fileGoc;

    @Column(name = "file_ket_qua")
    private String fileKetQua;

    @Column(name = "file_dot_bien")
    private String fileDotBien;

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

    @OneToMany(mappedBy = "mauPCR")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRMau> mauPCRS = new HashSet<>();
    @OneToMany(mappedBy = "mauTachChiet")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauTachChiet> mauTachChiets = new HashSet<>();
    @OneToMany(mappedBy = "mauDiemDotBien")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiemDotBien> mauDiemDotBiens = new HashSet<>();
    @OneToMany(mappedBy = "mauXetNghiem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThaoTac> mauPhanTiches = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "mau_xet_nghiem_mau_tinh_sach",
               joinColumns = @JoinColumn(name = "mau_xet_nghiems_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "mau_tinh_saches_id", referencedColumnName = "id"))
    private Set<TinhSach> mauTinhSaches = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("hoSoGiamDinhs")
    private HoSoGiamDinh hoSoGiamDinh;

    @ManyToOne
    @JsonIgnoreProperties("nhanVienNhanMaus")
    private NhanVien nhanVienNhanMau;

    @ManyToOne
    @JsonIgnoreProperties("loaiMauXetNghiems")
    private LoaiMauXetNghiem loaiMauXetNghiem;

    @ManyToOne
    @JsonIgnoreProperties("haiCotMaus")
    private HaiCotLietSi haiCotLietSi;

    @ManyToOne
    @JsonIgnoreProperties("thanNhanMaus")
    private HoSoThanNhan thanNhan;

    @ManyToOne
    @JsonIgnoreProperties("thaoTacHienTais")
    private LoaiThaoTac loaiThaoTac;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaMauXetNghiem() {
        return maMauXetNghiem;
    }

    public MauXetNghiem maMauXetNghiem(String maMauXetNghiem) {
        this.maMauXetNghiem = maMauXetNghiem;
        return this;
    }

    public void setMaMauXetNghiem(String maMauXetNghiem) {
        this.maMauXetNghiem = maMauXetNghiem;
    }

    public Instant getNgayLayMau() {
        return ngayLayMau;
    }

    public MauXetNghiem ngayLayMau(Instant ngayLayMau) {
        this.ngayLayMau = ngayLayMau;
        return this;
    }

    public void setNgayLayMau(Instant ngayLayMau) {
        this.ngayLayMau = ngayLayMau;
    }

    public String getNguoiLayMau() {
        return nguoiLayMau;
    }

    public MauXetNghiem nguoiLayMau(String nguoiLayMau) {
        this.nguoiLayMau = nguoiLayMau;
        return this;
    }

    public void setNguoiLayMau(String nguoiLayMau) {
        this.nguoiLayMau = nguoiLayMau;
    }

    public Instant getNgayTiepNhan() {
        return ngayTiepNhan;
    }

    public MauXetNghiem ngayTiepNhan(Instant ngayTiepNhan) {
        this.ngayTiepNhan = ngayTiepNhan;
        return this;
    }

    public void setNgayTiepNhan(Instant ngayTiepNhan) {
        this.ngayTiepNhan = ngayTiepNhan;
    }

    public Integer getSoLuongMau() {
        return soLuongMau;
    }

    public MauXetNghiem soLuongMau(Integer soLuongMau) {
        this.soLuongMau = soLuongMau;
        return this;
    }

    public void setSoLuongMau(Integer soLuongMau) {
        this.soLuongMau = soLuongMau;
    }

    public TrangThaiMau getTrangThaiMau() {
        return trangThaiMau;
    }

    public MauXetNghiem trangThaiMau(TrangThaiMau trangThaiMau) {
        this.trangThaiMau = trangThaiMau;
        return this;
    }

    public void setTrangThaiMau(TrangThaiMau trangThaiMau) {
        this.trangThaiMau = trangThaiMau;
    }

    public String getMoTa() {
        return moTa;
    }

    public MauXetNghiem moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public MauXetNghiem ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getFileGoc() {
        return fileGoc;
    }

    public MauXetNghiem fileGoc(String fileGoc) {
        this.fileGoc = fileGoc;
        return this;
    }

    public void setFileGoc(String fileGoc) {
        this.fileGoc = fileGoc;
    }

    public String getFileKetQua() {
        return fileKetQua;
    }

    public MauXetNghiem fileKetQua(String fileKetQua) {
        this.fileKetQua = fileKetQua;
        return this;
    }

    public void setFileKetQua(String fileKetQua) {
        this.fileKetQua = fileKetQua;
    }

    public String getFileDotBien() {
        return fileDotBien;
    }

    public MauXetNghiem fileDotBien(String fileDotBien) {
        this.fileDotBien = fileDotBien;
        return this;
    }

    public void setFileDotBien(String fileDotBien) {
        this.fileDotBien = fileDotBien;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public MauXetNghiem isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public MauXetNghiem udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public MauXetNghiem udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public MauXetNghiem udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public MauXetNghiem udf4(String udf4) {
        this.udf4 = udf4;
        return this;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public MauXetNghiem udf5(String udf5) {
        this.udf5 = udf5;
        return this;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public Set<PCRMau> getMauPCRS() {
        return mauPCRS;
    }

    public MauXetNghiem mauPCRS(Set<PCRMau> pCRMaus) {
        this.mauPCRS = pCRMaus;
        return this;
    }

    public MauXetNghiem addMauPCR(PCRMau pCRMau) {
        this.mauPCRS.add(pCRMau);
        pCRMau.setMauPCR(this);
        return this;
    }

    public MauXetNghiem removeMauPCR(PCRMau pCRMau) {
        this.mauPCRS.remove(pCRMau);
        pCRMau.setMauPCR(null);
        return this;
    }

    public void setMauPCRS(Set<PCRMau> pCRMaus) {
        this.mauPCRS = pCRMaus;
    }

    public Set<MauTachChiet> getMauTachChiets() {
        return mauTachChiets;
    }

    public MauXetNghiem mauTachChiets(Set<MauTachChiet> mauTachChiets) {
        this.mauTachChiets = mauTachChiets;
        return this;
    }

    public MauXetNghiem addMauTachChiet(MauTachChiet mauTachChiet) {
        this.mauTachChiets.add(mauTachChiet);
        mauTachChiet.setMauTachChiet(this);
        return this;
    }

    public MauXetNghiem removeMauTachChiet(MauTachChiet mauTachChiet) {
        this.mauTachChiets.remove(mauTachChiet);
        mauTachChiet.setMauTachChiet(null);
        return this;
    }

    public void setMauTachChiets(Set<MauTachChiet> mauTachChiets) {
        this.mauTachChiets = mauTachChiets;
    }

    public Set<DiemDotBien> getMauDiemDotBiens() {
        return mauDiemDotBiens;
    }

    public MauXetNghiem mauDiemDotBiens(Set<DiemDotBien> diemDotBiens) {
        this.mauDiemDotBiens = diemDotBiens;
        return this;
    }

    public MauXetNghiem addMauDiemDotBien(DiemDotBien diemDotBien) {
        this.mauDiemDotBiens.add(diemDotBien);
        diemDotBien.setMauDiemDotBien(this);
        return this;
    }

    public MauXetNghiem removeMauDiemDotBien(DiemDotBien diemDotBien) {
        this.mauDiemDotBiens.remove(diemDotBien);
        diemDotBien.setMauDiemDotBien(null);
        return this;
    }

    public void setMauDiemDotBiens(Set<DiemDotBien> diemDotBiens) {
        this.mauDiemDotBiens = diemDotBiens;
    }

    public Set<ThaoTac> getMauPhanTiches() {
        return mauPhanTiches;
    }

    public MauXetNghiem mauPhanTiches(Set<ThaoTac> thaoTacs) {
        this.mauPhanTiches = thaoTacs;
        return this;
    }

    public MauXetNghiem addMauPhanTich(ThaoTac thaoTac) {
        this.mauPhanTiches.add(thaoTac);
        thaoTac.setMauXetNghiem(this);
        return this;
    }

    public MauXetNghiem removeMauPhanTich(ThaoTac thaoTac) {
        this.mauPhanTiches.remove(thaoTac);
        thaoTac.setMauXetNghiem(null);
        return this;
    }

    public void setMauPhanTiches(Set<ThaoTac> thaoTacs) {
        this.mauPhanTiches = thaoTacs;
    }

    public Set<TinhSach> getMauTinhSaches() {
        return mauTinhSaches;
    }

    public MauXetNghiem mauTinhSaches(Set<TinhSach> tinhSaches) {
        this.mauTinhSaches = tinhSaches;
        return this;
    }

    public MauXetNghiem addMauTinhSach(TinhSach tinhSach) {
        this.mauTinhSaches.add(tinhSach);
        return this;
    }

    public MauXetNghiem removeMauTinhSach(TinhSach tinhSach) {
        this.mauTinhSaches.remove(tinhSach);
        return this;
    }

    public void setMauTinhSaches(Set<TinhSach> tinhSaches) {
        this.mauTinhSaches = tinhSaches;
    }

    public HoSoGiamDinh getHoSoGiamDinh() {
        return hoSoGiamDinh;
    }

    public MauXetNghiem hoSoGiamDinh(HoSoGiamDinh hoSoGiamDinh) {
        this.hoSoGiamDinh = hoSoGiamDinh;
        return this;
    }

    public void setHoSoGiamDinh(HoSoGiamDinh hoSoGiamDinh) {
        this.hoSoGiamDinh = hoSoGiamDinh;
    }

    public NhanVien getNhanVienNhanMau() {
        return nhanVienNhanMau;
    }

    public MauXetNghiem nhanVienNhanMau(NhanVien nhanVien) {
        this.nhanVienNhanMau = nhanVien;
        return this;
    }

    public void setNhanVienNhanMau(NhanVien nhanVien) {
        this.nhanVienNhanMau = nhanVien;
    }

    public LoaiMauXetNghiem getLoaiMauXetNghiem() {
        return loaiMauXetNghiem;
    }

    public MauXetNghiem loaiMauXetNghiem(LoaiMauXetNghiem loaiMauXetNghiem) {
        this.loaiMauXetNghiem = loaiMauXetNghiem;
        return this;
    }

    public void setLoaiMauXetNghiem(LoaiMauXetNghiem loaiMauXetNghiem) {
        this.loaiMauXetNghiem = loaiMauXetNghiem;
    }

    public HaiCotLietSi getHaiCotLietSi() {
        return haiCotLietSi;
    }

    public MauXetNghiem haiCotLietSi(HaiCotLietSi haiCotLietSi) {
        this.haiCotLietSi = haiCotLietSi;
        return this;
    }

    public void setHaiCotLietSi(HaiCotLietSi haiCotLietSi) {
        this.haiCotLietSi = haiCotLietSi;
    }

    public HoSoThanNhan getThanNhan() {
        return thanNhan;
    }

    public MauXetNghiem thanNhan(HoSoThanNhan hoSoThanNhan) {
        this.thanNhan = hoSoThanNhan;
        return this;
    }

    public void setThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.thanNhan = hoSoThanNhan;
    }

    public LoaiThaoTac getLoaiThaoTac() {
        return loaiThaoTac;
    }

    public MauXetNghiem loaiThaoTac(LoaiThaoTac loaiThaoTac) {
        this.loaiThaoTac = loaiThaoTac;
        return this;
    }

    public void setLoaiThaoTac(LoaiThaoTac loaiThaoTac) {
        this.loaiThaoTac = loaiThaoTac;
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
        MauXetNghiem mauXetNghiem = (MauXetNghiem) o;
        if (mauXetNghiem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mauXetNghiem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MauXetNghiem{" +
            "id=" + getId() +
            ", maMauXetNghiem='" + getMaMauXetNghiem() + "'" +
            ", ngayLayMau='" + getNgayLayMau() + "'" +
            ", nguoiLayMau='" + getNguoiLayMau() + "'" +
            ", ngayTiepNhan='" + getNgayTiepNhan() + "'" +
            ", soLuongMau=" + getSoLuongMau() +
            ", trangThaiMau='" + getTrangThaiMau() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", fileGoc='" + getFileGoc() + "'" +
            ", fileKetQua='" + getFileKetQua() + "'" +
            ", fileDotBien='" + getFileDotBien() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            ", udf4='" + getUdf4() + "'" +
            ", udf5='" + getUdf5() + "'" +
            "}";
    }
}
