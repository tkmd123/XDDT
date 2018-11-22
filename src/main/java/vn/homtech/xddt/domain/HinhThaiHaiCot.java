package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A HinhThaiHaiCot.
 */
@Entity
@Table(name = "hinh_thai_hai_cot")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hinhthaihaicot")
public class HinhThaiHaiCot implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gia_tri")
    private String giaTri;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("thongTinHinhThaiHocs")
    private HaiCotLietSi haiCotLietSi;

    @ManyToOne
    @JsonIgnoreProperties("loaiHinhThaiHaiCots")
    private LoaiHinhThaiHaiCot loaiHinhThaiHaiCot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public HinhThaiHaiCot giaTri(String giaTri) {
        this.giaTri = giaTri;
        return this;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public HinhThaiHaiCot moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HinhThaiHaiCot isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public HaiCotLietSi getHaiCotLietSi() {
        return haiCotLietSi;
    }

    public HinhThaiHaiCot haiCotLietSi(HaiCotLietSi haiCotLietSi) {
        this.haiCotLietSi = haiCotLietSi;
        return this;
    }

    public void setHaiCotLietSi(HaiCotLietSi haiCotLietSi) {
        this.haiCotLietSi = haiCotLietSi;
    }

    public LoaiHinhThaiHaiCot getLoaiHinhThaiHaiCot() {
        return loaiHinhThaiHaiCot;
    }

    public HinhThaiHaiCot loaiHinhThaiHaiCot(LoaiHinhThaiHaiCot loaiHinhThaiHaiCot) {
        this.loaiHinhThaiHaiCot = loaiHinhThaiHaiCot;
        return this;
    }

    public void setLoaiHinhThaiHaiCot(LoaiHinhThaiHaiCot loaiHinhThaiHaiCot) {
        this.loaiHinhThaiHaiCot = loaiHinhThaiHaiCot;
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
        HinhThaiHaiCot hinhThaiHaiCot = (HinhThaiHaiCot) o;
        if (hinhThaiHaiCot.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hinhThaiHaiCot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HinhThaiHaiCot{" +
            "id=" + getId() +
            ", giaTri='" + getGiaTri() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
