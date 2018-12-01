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
 * A LoaiHinhThaiHaiCot.
 */
@Entity
@Table(name = "loai_hinh_thai_hai_cot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "loaihinhthaihaicot")
public class LoaiHinhThaiHaiCot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hinh_thai")
    private String maHinhThai;

    @Column(name = "ten_hinh_thai")
    private String tenHinhThai;

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

    @OneToMany(mappedBy = "loaiHinhThaiHaiCot")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HinhThaiHaiCot> loaiHinhThaiHaiCots = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHinhThai() {
        return maHinhThai;
    }

    public LoaiHinhThaiHaiCot maHinhThai(String maHinhThai) {
        this.maHinhThai = maHinhThai;
        return this;
    }

    public void setMaHinhThai(String maHinhThai) {
        this.maHinhThai = maHinhThai;
    }

    public String getTenHinhThai() {
        return tenHinhThai;
    }

    public LoaiHinhThaiHaiCot tenHinhThai(String tenHinhThai) {
        this.tenHinhThai = tenHinhThai;
        return this;
    }

    public void setTenHinhThai(String tenHinhThai) {
        this.tenHinhThai = tenHinhThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public LoaiHinhThaiHaiCot moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public LoaiHinhThaiHaiCot isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public LoaiHinhThaiHaiCot udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public LoaiHinhThaiHaiCot udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public LoaiHinhThaiHaiCot udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<HinhThaiHaiCot> getLoaiHinhThaiHaiCots() {
        return loaiHinhThaiHaiCots;
    }

    public LoaiHinhThaiHaiCot loaiHinhThaiHaiCots(Set<HinhThaiHaiCot> hinhThaiHaiCots) {
        this.loaiHinhThaiHaiCots = hinhThaiHaiCots;
        return this;
    }

    public LoaiHinhThaiHaiCot addLoaiHinhThaiHaiCot(HinhThaiHaiCot hinhThaiHaiCot) {
        this.loaiHinhThaiHaiCots.add(hinhThaiHaiCot);
        hinhThaiHaiCot.setLoaiHinhThaiHaiCot(this);
        return this;
    }

    public LoaiHinhThaiHaiCot removeLoaiHinhThaiHaiCot(HinhThaiHaiCot hinhThaiHaiCot) {
        this.loaiHinhThaiHaiCots.remove(hinhThaiHaiCot);
        hinhThaiHaiCot.setLoaiHinhThaiHaiCot(null);
        return this;
    }

    public void setLoaiHinhThaiHaiCots(Set<HinhThaiHaiCot> hinhThaiHaiCots) {
        this.loaiHinhThaiHaiCots = hinhThaiHaiCots;
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
        LoaiHinhThaiHaiCot loaiHinhThaiHaiCot = (LoaiHinhThaiHaiCot) o;
        if (loaiHinhThaiHaiCot.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiHinhThaiHaiCot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiHinhThaiHaiCot{" +
            "id=" + getId() +
            ", maHinhThai='" + getMaHinhThai() + "'" +
            ", tenHinhThai='" + getTenHinhThai() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
