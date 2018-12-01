package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MauTachChiet.
 */
@Entity
@Table(name = "mau_tach_chiet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "mautachchiet")
public class MauTachChiet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dac_diem_mau")
    private String dacDiemMau;

    @Column(name = "so_luong_su_dung")
    private Integer soLuongSuDung;

    @Column(name = "nhan_xet")
    private String nhanXet;

    @Column(name = "xu_ly_be_mat")
    private String xuLyBeMat;

    @Column(name = "khoi_luong_nghien")
    private Float khoiLuongNghien;

    @Column(name = "khoi_luong_de_tach")
    private Float khoiLuongDeTach;

    @Column(name = "khoi_luong_sau_khu")
    private Float khoiLuongSauKhu;

    @Column(name = "khoi_luong_sau_loc")
    private Float khoiLuongSauLoc;

    @Column(name = "khoi_luong_adn")
    private Float khoiLuongADN;

    @Column(name = "khoi_luong_chua_nghien")
    private Float khoiLuongChuaNghien;

    @Column(name = "ghi_chu_tach")
    private String ghiChuTach;

    @Column(name = "ghi_chu_adn")
    private String ghiChuADN;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @Column(name = "float_1")
    private Float float1;

    @Column(name = "float_2")
    private Float float2;

    @Column(name = "float_3")
    private Float float3;

    @ManyToOne
    @JsonIgnoreProperties("mauTachChiets")
    private MauXetNghiem mauTachChiet;

    @ManyToOne
    @JsonIgnoreProperties("tachChietMaus")
    private TachChiet tachChietMau;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDacDiemMau() {
        return dacDiemMau;
    }

    public MauTachChiet dacDiemMau(String dacDiemMau) {
        this.dacDiemMau = dacDiemMau;
        return this;
    }

    public void setDacDiemMau(String dacDiemMau) {
        this.dacDiemMau = dacDiemMau;
    }

    public Integer getSoLuongSuDung() {
        return soLuongSuDung;
    }

    public MauTachChiet soLuongSuDung(Integer soLuongSuDung) {
        this.soLuongSuDung = soLuongSuDung;
        return this;
    }

    public void setSoLuongSuDung(Integer soLuongSuDung) {
        this.soLuongSuDung = soLuongSuDung;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public MauTachChiet nhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
        return this;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public String getXuLyBeMat() {
        return xuLyBeMat;
    }

    public MauTachChiet xuLyBeMat(String xuLyBeMat) {
        this.xuLyBeMat = xuLyBeMat;
        return this;
    }

    public void setXuLyBeMat(String xuLyBeMat) {
        this.xuLyBeMat = xuLyBeMat;
    }

    public Float getKhoiLuongNghien() {
        return khoiLuongNghien;
    }

    public MauTachChiet khoiLuongNghien(Float khoiLuongNghien) {
        this.khoiLuongNghien = khoiLuongNghien;
        return this;
    }

    public void setKhoiLuongNghien(Float khoiLuongNghien) {
        this.khoiLuongNghien = khoiLuongNghien;
    }

    public Float getKhoiLuongDeTach() {
        return khoiLuongDeTach;
    }

    public MauTachChiet khoiLuongDeTach(Float khoiLuongDeTach) {
        this.khoiLuongDeTach = khoiLuongDeTach;
        return this;
    }

    public void setKhoiLuongDeTach(Float khoiLuongDeTach) {
        this.khoiLuongDeTach = khoiLuongDeTach;
    }

    public Float getKhoiLuongSauKhu() {
        return khoiLuongSauKhu;
    }

    public MauTachChiet khoiLuongSauKhu(Float khoiLuongSauKhu) {
        this.khoiLuongSauKhu = khoiLuongSauKhu;
        return this;
    }

    public void setKhoiLuongSauKhu(Float khoiLuongSauKhu) {
        this.khoiLuongSauKhu = khoiLuongSauKhu;
    }

    public Float getKhoiLuongSauLoc() {
        return khoiLuongSauLoc;
    }

    public MauTachChiet khoiLuongSauLoc(Float khoiLuongSauLoc) {
        this.khoiLuongSauLoc = khoiLuongSauLoc;
        return this;
    }

    public void setKhoiLuongSauLoc(Float khoiLuongSauLoc) {
        this.khoiLuongSauLoc = khoiLuongSauLoc;
    }

    public Float getKhoiLuongADN() {
        return khoiLuongADN;
    }

    public MauTachChiet khoiLuongADN(Float khoiLuongADN) {
        this.khoiLuongADN = khoiLuongADN;
        return this;
    }

    public void setKhoiLuongADN(Float khoiLuongADN) {
        this.khoiLuongADN = khoiLuongADN;
    }

    public Float getKhoiLuongChuaNghien() {
        return khoiLuongChuaNghien;
    }

    public MauTachChiet khoiLuongChuaNghien(Float khoiLuongChuaNghien) {
        this.khoiLuongChuaNghien = khoiLuongChuaNghien;
        return this;
    }

    public void setKhoiLuongChuaNghien(Float khoiLuongChuaNghien) {
        this.khoiLuongChuaNghien = khoiLuongChuaNghien;
    }

    public String getGhiChuTach() {
        return ghiChuTach;
    }

    public MauTachChiet ghiChuTach(String ghiChuTach) {
        this.ghiChuTach = ghiChuTach;
        return this;
    }

    public void setGhiChuTach(String ghiChuTach) {
        this.ghiChuTach = ghiChuTach;
    }

    public String getGhiChuADN() {
        return ghiChuADN;
    }

    public MauTachChiet ghiChuADN(String ghiChuADN) {
        this.ghiChuADN = ghiChuADN;
        return this;
    }

    public void setGhiChuADN(String ghiChuADN) {
        this.ghiChuADN = ghiChuADN;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public MauTachChiet isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public MauTachChiet udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public MauTachChiet udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public MauTachChiet udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Float getFloat1() {
        return float1;
    }

    public MauTachChiet float1(Float float1) {
        this.float1 = float1;
        return this;
    }

    public void setFloat1(Float float1) {
        this.float1 = float1;
    }

    public Float getFloat2() {
        return float2;
    }

    public MauTachChiet float2(Float float2) {
        this.float2 = float2;
        return this;
    }

    public void setFloat2(Float float2) {
        this.float2 = float2;
    }

    public Float getFloat3() {
        return float3;
    }

    public MauTachChiet float3(Float float3) {
        this.float3 = float3;
        return this;
    }

    public void setFloat3(Float float3) {
        this.float3 = float3;
    }

    public MauXetNghiem getMauTachChiet() {
        return mauTachChiet;
    }

    public MauTachChiet mauTachChiet(MauXetNghiem mauXetNghiem) {
        this.mauTachChiet = mauXetNghiem;
        return this;
    }

    public void setMauTachChiet(MauXetNghiem mauXetNghiem) {
        this.mauTachChiet = mauXetNghiem;
    }

    public TachChiet getTachChietMau() {
        return tachChietMau;
    }

    public MauTachChiet tachChietMau(TachChiet tachChiet) {
        this.tachChietMau = tachChiet;
        return this;
    }

    public void setTachChietMau(TachChiet tachChiet) {
        this.tachChietMau = tachChiet;
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
        MauTachChiet mauTachChiet = (MauTachChiet) o;
        if (mauTachChiet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mauTachChiet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MauTachChiet{" +
            "id=" + getId() +
            ", dacDiemMau='" + getDacDiemMau() + "'" +
            ", soLuongSuDung=" + getSoLuongSuDung() +
            ", nhanXet='" + getNhanXet() + "'" +
            ", xuLyBeMat='" + getXuLyBeMat() + "'" +
            ", khoiLuongNghien=" + getKhoiLuongNghien() +
            ", khoiLuongDeTach=" + getKhoiLuongDeTach() +
            ", khoiLuongSauKhu=" + getKhoiLuongSauKhu() +
            ", khoiLuongSauLoc=" + getKhoiLuongSauLoc() +
            ", khoiLuongADN=" + getKhoiLuongADN() +
            ", khoiLuongChuaNghien=" + getKhoiLuongChuaNghien() +
            ", ghiChuTach='" + getGhiChuTach() + "'" +
            ", ghiChuADN='" + getGhiChuADN() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            ", float1=" + getFloat1() +
            ", float2=" + getFloat2() +
            ", float3=" + getFloat3() +
            "}";
    }
}
