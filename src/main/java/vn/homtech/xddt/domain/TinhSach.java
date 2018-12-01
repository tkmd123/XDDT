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

import vn.homtech.xddt.domain.enumeration.PhuongPhapTinhSach;

/**
 * A TinhSach.
 */
@Entity
@Table(name = "tinh_sach")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tinhsach")
public class TinhSach extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_tinh_sach")
    private String maTinhSach;

    @Column(name = "thoi_gian_thuc_hien")
    private Instant thoiGianThucHien;

    @Enumerated(EnumType.STRING)
    @Column(name = "phuong_phap_tinh_sach")
    private PhuongPhapTinhSach phuongPhapTinhSach;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @OneToMany(mappedBy = "tinhSach")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TinhSachPhanUng> tinhSaches = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("nhanVienTinhSaches")
    private NhanVien nhanVienTinhSach;

    @ManyToOne
    @JsonIgnoreProperties("mayTinhSaches")
    private MayPCR mayTinhSach;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaTinhSach() {
        return maTinhSach;
    }

    public TinhSach maTinhSach(String maTinhSach) {
        this.maTinhSach = maTinhSach;
        return this;
    }

    public void setMaTinhSach(String maTinhSach) {
        this.maTinhSach = maTinhSach;
    }

    public Instant getThoiGianThucHien() {
        return thoiGianThucHien;
    }

    public TinhSach thoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
        return this;
    }

    public void setThoiGianThucHien(Instant thoiGianThucHien) {
        this.thoiGianThucHien = thoiGianThucHien;
    }

    public PhuongPhapTinhSach getPhuongPhapTinhSach() {
        return phuongPhapTinhSach;
    }

    public TinhSach phuongPhapTinhSach(PhuongPhapTinhSach phuongPhapTinhSach) {
        this.phuongPhapTinhSach = phuongPhapTinhSach;
        return this;
    }

    public void setPhuongPhapTinhSach(PhuongPhapTinhSach phuongPhapTinhSach) {
        this.phuongPhapTinhSach = phuongPhapTinhSach;
    }

    public String getMoTa() {
        return moTa;
    }

    public TinhSach moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public TinhSach isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public TinhSach udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public TinhSach udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public TinhSach udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<TinhSachPhanUng> getTinhSaches() {
        return tinhSaches;
    }

    public TinhSach tinhSaches(Set<TinhSachPhanUng> tinhSachPhanUngs) {
        this.tinhSaches = tinhSachPhanUngs;
        return this;
    }

    public TinhSach addTinhSach(TinhSachPhanUng tinhSachPhanUng) {
        this.tinhSaches.add(tinhSachPhanUng);
        tinhSachPhanUng.setTinhSach(this);
        return this;
    }

    public TinhSach removeTinhSach(TinhSachPhanUng tinhSachPhanUng) {
        this.tinhSaches.remove(tinhSachPhanUng);
        tinhSachPhanUng.setTinhSach(null);
        return this;
    }

    public void setTinhSaches(Set<TinhSachPhanUng> tinhSachPhanUngs) {
        this.tinhSaches = tinhSachPhanUngs;
    }

    public NhanVien getNhanVienTinhSach() {
        return nhanVienTinhSach;
    }

    public TinhSach nhanVienTinhSach(NhanVien nhanVien) {
        this.nhanVienTinhSach = nhanVien;
        return this;
    }

    public void setNhanVienTinhSach(NhanVien nhanVien) {
        this.nhanVienTinhSach = nhanVien;
    }

    public MayPCR getMayTinhSach() {
        return mayTinhSach;
    }

    public TinhSach mayTinhSach(MayPCR mayPCR) {
        this.mayTinhSach = mayPCR;
        return this;
    }

    public void setMayTinhSach(MayPCR mayPCR) {
        this.mayTinhSach = mayPCR;
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
        TinhSach tinhSach = (TinhSach) o;
        if (tinhSach.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tinhSach.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TinhSach{" +
            "id=" + getId() +
            ", maTinhSach='" + getMaTinhSach() + "'" +
            ", thoiGianThucHien='" + getThoiGianThucHien() + "'" +
            ", phuongPhapTinhSach='" + getPhuongPhapTinhSach() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
