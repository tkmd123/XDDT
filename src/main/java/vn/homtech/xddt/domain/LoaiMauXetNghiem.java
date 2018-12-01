package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A LoaiMauXetNghiem.
 */
@Entity
@Table(name = "loai_mau_xet_nghiem")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "loaimauxetnghiem")
public class LoaiMauXetNghiem extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_loai_mau")
    private String maLoaiMau;

    @Column(name = "ten_loai_mau")
    private String tenLoaiMau;

    @Column(name = "phan_loai")
    private String phanLoai;

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

    @OneToMany(mappedBy = "loaiMauXetNghiem")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> loaiMauXetNghiems = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoaiMau() {
        return maLoaiMau;
    }

    public LoaiMauXetNghiem maLoaiMau(String maLoaiMau) {
        this.maLoaiMau = maLoaiMau;
        return this;
    }

    public void setMaLoaiMau(String maLoaiMau) {
        this.maLoaiMau = maLoaiMau;
    }

    public String getTenLoaiMau() {
        return tenLoaiMau;
    }

    public LoaiMauXetNghiem tenLoaiMau(String tenLoaiMau) {
        this.tenLoaiMau = tenLoaiMau;
        return this;
    }

    public void setTenLoaiMau(String tenLoaiMau) {
        this.tenLoaiMau = tenLoaiMau;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public LoaiMauXetNghiem phanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
        return this;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public LoaiMauXetNghiem moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public LoaiMauXetNghiem isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public LoaiMauXetNghiem udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public LoaiMauXetNghiem udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public LoaiMauXetNghiem udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<MauXetNghiem> getLoaiMauXetNghiems() {
        return loaiMauXetNghiems;
    }

    public LoaiMauXetNghiem loaiMauXetNghiems(Set<MauXetNghiem> mauXetNghiems) {
        this.loaiMauXetNghiems = mauXetNghiems;
        return this;
    }

    public LoaiMauXetNghiem addLoaiMauXetNghiem(MauXetNghiem mauXetNghiem) {
        this.loaiMauXetNghiems.add(mauXetNghiem);
        mauXetNghiem.setLoaiMauXetNghiem(this);
        return this;
    }

    public LoaiMauXetNghiem removeLoaiMauXetNghiem(MauXetNghiem mauXetNghiem) {
        this.loaiMauXetNghiems.remove(mauXetNghiem);
        mauXetNghiem.setLoaiMauXetNghiem(null);
        return this;
    }

    public void setLoaiMauXetNghiems(Set<MauXetNghiem> mauXetNghiems) {
        this.loaiMauXetNghiems = mauXetNghiems;
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
        LoaiMauXetNghiem loaiMauXetNghiem = (LoaiMauXetNghiem) o;
        if (loaiMauXetNghiem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiMauXetNghiem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiMauXetNghiem{" +
            "id=" + getId() +
            ", maLoaiMau='" + getMaLoaiMau() + "'" +
            ", tenLoaiMau='" + getTenLoaiMau() + "'" +
            ", phanLoai='" + getPhanLoai() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
