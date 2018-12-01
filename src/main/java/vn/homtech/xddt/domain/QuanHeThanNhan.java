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
 * A QuanHeThanNhan.
 */
@Entity
@Table(name = "quan_he_than_nhan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quanhethannhan")
public class QuanHeThanNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_quan_he")
    private String maQuanHe;

    @Column(name = "loai_quan_he")
    private String loaiQuanHe;

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

    @OneToMany(mappedBy = "quanHeThanNhan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThanNhanLietSi> quanHeThanNhans = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaQuanHe() {
        return maQuanHe;
    }

    public QuanHeThanNhan maQuanHe(String maQuanHe) {
        this.maQuanHe = maQuanHe;
        return this;
    }

    public void setMaQuanHe(String maQuanHe) {
        this.maQuanHe = maQuanHe;
    }

    public String getLoaiQuanHe() {
        return loaiQuanHe;
    }

    public QuanHeThanNhan loaiQuanHe(String loaiQuanHe) {
        this.loaiQuanHe = loaiQuanHe;
        return this;
    }

    public void setLoaiQuanHe(String loaiQuanHe) {
        this.loaiQuanHe = loaiQuanHe;
    }

    public String getMoTa() {
        return moTa;
    }

    public QuanHeThanNhan moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public QuanHeThanNhan isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public QuanHeThanNhan udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public QuanHeThanNhan udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public QuanHeThanNhan udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<ThanNhanLietSi> getQuanHeThanNhans() {
        return quanHeThanNhans;
    }

    public QuanHeThanNhan quanHeThanNhans(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.quanHeThanNhans = thanNhanLietSis;
        return this;
    }

    public QuanHeThanNhan addQuanHeThanNhan(ThanNhanLietSi thanNhanLietSi) {
        this.quanHeThanNhans.add(thanNhanLietSi);
        thanNhanLietSi.setQuanHeThanNhan(this);
        return this;
    }

    public QuanHeThanNhan removeQuanHeThanNhan(ThanNhanLietSi thanNhanLietSi) {
        this.quanHeThanNhans.remove(thanNhanLietSi);
        thanNhanLietSi.setQuanHeThanNhan(null);
        return this;
    }

    public void setQuanHeThanNhans(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.quanHeThanNhans = thanNhanLietSis;
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
        QuanHeThanNhan quanHeThanNhan = (QuanHeThanNhan) o;
        if (quanHeThanNhan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quanHeThanNhan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuanHeThanNhan{" +
            "id=" + getId() +
            ", maQuanHe='" + getMaQuanHe() + "'" +
            ", loaiQuanHe='" + getLoaiQuanHe() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
