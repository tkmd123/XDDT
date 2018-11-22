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

/**
 * A MauXetNghiem.
 */
@Entity
@Table(name = "mau_xet_nghiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "mauxetnghiem")
public class MauXetNghiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_mau_xet_nghiem")
    private String maMauXetNghiem;

    @Column(name = "nguoi_tiep_nhan")
    private String nguoiTiepNhan;

    @Column(name = "ngay_lay_mau")
    private Instant ngayLayMau;

    @Column(name = "ngay_tiep_nhan")
    private Instant ngayTiepNhan;

    @Column(name = "trang_thai_xu_ly")
    private String trangThaiXuLy;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToOne    @JoinColumn(unique = true)
    private ThongTinADN mauThongTinADN;

    @OneToMany(mappedBy = "mauXetNghiem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThaoTac> mauPhanTiches = new HashSet<>();
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

    public String getNguoiTiepNhan() {
        return nguoiTiepNhan;
    }

    public MauXetNghiem nguoiTiepNhan(String nguoiTiepNhan) {
        this.nguoiTiepNhan = nguoiTiepNhan;
        return this;
    }

    public void setNguoiTiepNhan(String nguoiTiepNhan) {
        this.nguoiTiepNhan = nguoiTiepNhan;
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

    public String getTrangThaiXuLy() {
        return trangThaiXuLy;
    }

    public MauXetNghiem trangThaiXuLy(String trangThaiXuLy) {
        this.trangThaiXuLy = trangThaiXuLy;
        return this;
    }

    public void setTrangThaiXuLy(String trangThaiXuLy) {
        this.trangThaiXuLy = trangThaiXuLy;
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

    public ThongTinADN getMauThongTinADN() {
        return mauThongTinADN;
    }

    public MauXetNghiem mauThongTinADN(ThongTinADN thongTinADN) {
        this.mauThongTinADN = thongTinADN;
        return this;
    }

    public void setMauThongTinADN(ThongTinADN thongTinADN) {
        this.mauThongTinADN = thongTinADN;
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
            ", nguoiTiepNhan='" + getNguoiTiepNhan() + "'" +
            ", ngayLayMau='" + getNgayLayMau() + "'" +
            ", ngayTiepNhan='" + getNgayTiepNhan() + "'" +
            ", trangThaiXuLy='" + getTrangThaiXuLy() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
