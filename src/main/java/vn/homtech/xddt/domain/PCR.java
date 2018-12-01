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
 * A PCR.
 */
@Entity
@Table(name = "pcr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pcr")
public class PCR extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_pcr")
    private String maPCR;

    @Column(name = "thoi_gian_thuc_hien")
    private Instant thoiGianThucHien;

    @Column(name = "nhan_xet")
    private String nhanXet;

    @Column(name = "thoi_gian_bat_dau")
    private Instant thoiGianBatDau;

    @Column(name = "cong_suat_bat_dau")
    private Float congSuatBatDau;

    @Column(name = "cuong_do_bat_dau")
    private Float cuongDoBatDau;

    @Column(name = "dien_the_bat_dau")
    private Float dienTheBatDau;

    @Column(name = "thoi_gian_ket_thuc")
    private Instant thoiGianKetThuc;

    @Column(name = "cong_suat_ket_thuc")
    private Float congSuatKetThuc;

    @Column(name = "cuong_do_ket_thuc")
    private Float cuongDoKetThuc;

    @Column(name = "dien_the_ket_thuc")
    private Float dienTheKetThuc;

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

    @OneToMany(mappedBy = "pcrMau")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRMau> pcrMaus = new HashSet<>();
    @OneToMany(mappedBy = "pcrPhanUng")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRPhanUng> pcrPhanUngs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("mayPCRS")
    private MayPCR mayPCR;

    @ManyToOne
    @JsonIgnoreProperties("nhanVienPCRS")
    private NhanVien nhanVienPCR;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaPCR() {
        return maPCR;
    }

    public PCR maPCR(String maPCR) {
        this.maPCR = maPCR;
        return this;
    }

    public void setMaPCR(String maPCR) {
        this.maPCR = maPCR;
    }

    public Instant getThoiGianThucHien() {
        return thoiGianThucHien;
    }

    public PCR thoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
        return this;
    }

    public void setThoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public PCR nhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
        return this;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public Instant getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public PCR thoiGianBatDau(Instant thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
        return this;
    }

    public void setThoiGianBatDau(Instant thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Float getCongSuatBatDau() {
        return congSuatBatDau;
    }

    public PCR congSuatBatDau(Float congSuatBatDau) {
        this.congSuatBatDau = congSuatBatDau;
        return this;
    }

    public void setCongSuatBatDau(Float congSuatBatDau) {
        this.congSuatBatDau = congSuatBatDau;
    }

    public Float getCuongDoBatDau() {
        return cuongDoBatDau;
    }

    public PCR cuongDoBatDau(Float cuongDoBatDau) {
        this.cuongDoBatDau = cuongDoBatDau;
        return this;
    }

    public void setCuongDoBatDau(Float cuongDoBatDau) {
        this.cuongDoBatDau = cuongDoBatDau;
    }

    public Float getDienTheBatDau() {
        return dienTheBatDau;
    }

    public PCR dienTheBatDau(Float dienTheBatDau) {
        this.dienTheBatDau = dienTheBatDau;
        return this;
    }

    public void setDienTheBatDau(Float dienTheBatDau) {
        this.dienTheBatDau = dienTheBatDau;
    }

    public Instant getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public PCR thoiGianKetThuc(Instant thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
        return this;
    }

    public void setThoiGianKetThuc(Instant thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public Float getCongSuatKetThuc() {
        return congSuatKetThuc;
    }

    public PCR congSuatKetThuc(Float congSuatKetThuc) {
        this.congSuatKetThuc = congSuatKetThuc;
        return this;
    }

    public void setCongSuatKetThuc(Float congSuatKetThuc) {
        this.congSuatKetThuc = congSuatKetThuc;
    }

    public Float getCuongDoKetThuc() {
        return cuongDoKetThuc;
    }

    public PCR cuongDoKetThuc(Float cuongDoKetThuc) {
        this.cuongDoKetThuc = cuongDoKetThuc;
        return this;
    }

    public void setCuongDoKetThuc(Float cuongDoKetThuc) {
        this.cuongDoKetThuc = cuongDoKetThuc;
    }

    public Float getDienTheKetThuc() {
        return dienTheKetThuc;
    }

    public PCR dienTheKetThuc(Float dienTheKetThuc) {
        this.dienTheKetThuc = dienTheKetThuc;
        return this;
    }

    public void setDienTheKetThuc(Float dienTheKetThuc) {
        this.dienTheKetThuc = dienTheKetThuc;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PCR isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PCR udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PCR udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PCR udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public String getUdf4() {
        return udf4;
    }

    public PCR udf4(String udf4) {
        this.udf4 = udf4;
        return this;
    }

    public void setUdf4(String udf4) {
        this.udf4 = udf4;
    }

    public String getUdf5() {
        return udf5;
    }

    public PCR udf5(String udf5) {
        this.udf5 = udf5;
        return this;
    }

    public void setUdf5(String udf5) {
        this.udf5 = udf5;
    }

    public Set<PCRMau> getPcrMaus() {
        return pcrMaus;
    }

    public PCR pcrMaus(Set<PCRMau> pCRMaus) {
        this.pcrMaus = pCRMaus;
        return this;
    }

    public PCR addPcrMau(PCRMau pCRMau) {
        this.pcrMaus.add(pCRMau);
        pCRMau.setPcrMau(this);
        return this;
    }

    public PCR removePcrMau(PCRMau pCRMau) {
        this.pcrMaus.remove(pCRMau);
        pCRMau.setPcrMau(null);
        return this;
    }

    public void setPcrMaus(Set<PCRMau> pCRMaus) {
        this.pcrMaus = pCRMaus;
    }

    public Set<PCRPhanUng> getPcrPhanUngs() {
        return pcrPhanUngs;
    }

    public PCR pcrPhanUngs(Set<PCRPhanUng> pCRPhanUngs) {
        this.pcrPhanUngs = pCRPhanUngs;
        return this;
    }

    public PCR addPcrPhanUng(PCRPhanUng pCRPhanUng) {
        this.pcrPhanUngs.add(pCRPhanUng);
        pCRPhanUng.setPcrPhanUng(this);
        return this;
    }

    public PCR removePcrPhanUng(PCRPhanUng pCRPhanUng) {
        this.pcrPhanUngs.remove(pCRPhanUng);
        pCRPhanUng.setPcrPhanUng(null);
        return this;
    }

    public void setPcrPhanUngs(Set<PCRPhanUng> pCRPhanUngs) {
        this.pcrPhanUngs = pCRPhanUngs;
    }

    public MayPCR getMayPCR() {
        return mayPCR;
    }

    public PCR mayPCR(MayPCR mayPCR) {
        this.mayPCR = mayPCR;
        return this;
    }

    public void setMayPCR(MayPCR mayPCR) {
        this.mayPCR = mayPCR;
    }

    public NhanVien getNhanVienPCR() {
        return nhanVienPCR;
    }

    public PCR nhanVienPCR(NhanVien nhanVien) {
        this.nhanVienPCR = nhanVien;
        return this;
    }

    public void setNhanVienPCR(NhanVien nhanVien) {
        this.nhanVienPCR = nhanVien;
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
        PCR pCR = (PCR) o;
        if (pCR.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pCR.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PCR{" +
            "id=" + getId() +
            ", maPCR='" + getMaPCR() + "'" +
            ", thoiGianThucHien='" + getThoiGianThucHien() + "'" +
            ", nhanXet='" + getNhanXet() + "'" +
            ", thoiGianBatDau='" + getThoiGianBatDau() + "'" +
            ", congSuatBatDau=" + getCongSuatBatDau() +
            ", cuongDoBatDau=" + getCuongDoBatDau() +
            ", dienTheBatDau=" + getDienTheBatDau() +
            ", thoiGianKetThuc='" + getThoiGianKetThuc() + "'" +
            ", congSuatKetThuc=" + getCongSuatKetThuc() +
            ", cuongDoKetThuc=" + getCuongDoKetThuc() +
            ", dienTheKetThuc=" + getDienTheKetThuc() +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            ", udf4='" + getUdf4() + "'" +
            ", udf5='" + getUdf5() + "'" +
            "}";
    }
}
