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
public class LoaiMauXetNghiem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_loai_mau")
    private String maLoaiMau;

    @Column(name = "ten_loai_mau")
    private String tenLoaiMau;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
