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

import vn.homtech.xddt.domain.enumeration.PhuongPhapLoc;

/**
 * A TachChiet.
 */
@Entity
@Table(name = "tach_chiet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tachchiet")
public class TachChiet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_tach_chiet")
    private String maTachChiet;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "thoi_gian_thuc_hien")
    private Instant thoiGianThucHien;

    @Enumerated(EnumType.STRING)
    @Column(name = "phuong_phap_loc")
    private PhuongPhapLoc phuongPhapLoc;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @OneToMany(mappedBy = "tachChietMau")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauTachChiet> tachChietMaus = new HashSet<>();
    @OneToMany(mappedBy = "tachChiet")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoaChatTachChiet> tachChietHoaChats = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("nhanVienNghienMaus")
    private NhanVien nhanVienNghienMau;

    @ManyToOne
    @JsonIgnoreProperties("nhanVienTachADNS")
    private NhanVien nhanVienTachADN;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaTachChiet() {
        return maTachChiet;
    }

    public TachChiet maTachChiet(String maTachChiet) {
        this.maTachChiet = maTachChiet;
        return this;
    }

    public void setMaTachChiet(String maTachChiet) {
        this.maTachChiet = maTachChiet;
    }

    public String getMoTa() {
        return moTa;
    }

    public TachChiet moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public TachChiet ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Instant getThoiGianThucHien() {
        return thoiGianThucHien;
    }

    public TachChiet thoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
        return this;
    }

    public void setThoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
    }

    public PhuongPhapLoc getPhuongPhapLoc() {
        return phuongPhapLoc;
    }

    public TachChiet phuongPhapLoc(PhuongPhapLoc phuongPhapLoc) {
        this.phuongPhapLoc = phuongPhapLoc;
        return this;
    }

    public void setPhuongPhapLoc(PhuongPhapLoc phuongPhapLoc) {
        this.phuongPhapLoc = phuongPhapLoc;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public TachChiet isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public TachChiet udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public TachChiet udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public TachChiet udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<MauTachChiet> getTachChietMaus() {
        return tachChietMaus;
    }

    public TachChiet tachChietMaus(Set<MauTachChiet> mauTachChiets) {
        this.tachChietMaus = mauTachChiets;
        return this;
    }

    public TachChiet addTachChietMau(MauTachChiet mauTachChiet) {
        this.tachChietMaus.add(mauTachChiet);
        mauTachChiet.setTachChietMau(this);
        return this;
    }

    public TachChiet removeTachChietMau(MauTachChiet mauTachChiet) {
        this.tachChietMaus.remove(mauTachChiet);
        mauTachChiet.setTachChietMau(null);
        return this;
    }

    public void setTachChietMaus(Set<MauTachChiet> mauTachChiets) {
        this.tachChietMaus = mauTachChiets;
    }

    public Set<HoaChatTachChiet> getTachChietHoaChats() {
        return tachChietHoaChats;
    }

    public TachChiet tachChietHoaChats(Set<HoaChatTachChiet> hoaChatTachChiets) {
        this.tachChietHoaChats = hoaChatTachChiets;
        return this;
    }

    public TachChiet addTachChietHoaChat(HoaChatTachChiet hoaChatTachChiet) {
        this.tachChietHoaChats.add(hoaChatTachChiet);
        hoaChatTachChiet.setTachChiet(this);
        return this;
    }

    public TachChiet removeTachChietHoaChat(HoaChatTachChiet hoaChatTachChiet) {
        this.tachChietHoaChats.remove(hoaChatTachChiet);
        hoaChatTachChiet.setTachChiet(null);
        return this;
    }

    public void setTachChietHoaChats(Set<HoaChatTachChiet> hoaChatTachChiets) {
        this.tachChietHoaChats = hoaChatTachChiets;
    }

    public NhanVien getNhanVienNghienMau() {
        return nhanVienNghienMau;
    }

    public TachChiet nhanVienNghienMau(NhanVien nhanVien) {
        this.nhanVienNghienMau = nhanVien;
        return this;
    }

    public void setNhanVienNghienMau(NhanVien nhanVien) {
        this.nhanVienNghienMau = nhanVien;
    }

    public NhanVien getNhanVienTachADN() {
        return nhanVienTachADN;
    }

    public TachChiet nhanVienTachADN(NhanVien nhanVien) {
        this.nhanVienTachADN = nhanVien;
        return this;
    }

    public void setNhanVienTachADN(NhanVien nhanVien) {
        this.nhanVienTachADN = nhanVien;
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
        TachChiet tachChiet = (TachChiet) o;
        if (tachChiet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tachChiet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TachChiet{" +
            "id=" + getId() +
            ", maTachChiet='" + getMaTachChiet() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", thoiGianThucHien='" + getThoiGianThucHien() + "'" +
            ", phuongPhapLoc='" + getPhuongPhapLoc() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
