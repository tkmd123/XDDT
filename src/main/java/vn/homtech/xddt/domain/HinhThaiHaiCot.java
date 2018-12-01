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
public class HinhThaiHaiCot extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

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

    public String getUdf1() {
        return udf1;
    }

    public HinhThaiHaiCot udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HinhThaiHaiCot udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HinhThaiHaiCot udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
