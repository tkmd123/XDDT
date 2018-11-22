package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DiVat.
 */
@Entity
@Table(name = "di_vat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "divat")
public class DiVat implements Serializable {

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
    @JsonIgnoreProperties("khaiQuatDiVats")
    private ThongTinKhaiQuat thongTinKhaiQuat;

    @ManyToOne
    @JsonIgnoreProperties("loaiDiVats")
    private LoaiDiVat loaiDiVat;

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

    public DiVat giaTri(String giaTri) {
        this.giaTri = giaTri;
        return this;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public DiVat moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public DiVat isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ThongTinKhaiQuat getThongTinKhaiQuat() {
        return thongTinKhaiQuat;
    }

    public DiVat thongTinKhaiQuat(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.thongTinKhaiQuat = thongTinKhaiQuat;
        return this;
    }

    public void setThongTinKhaiQuat(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.thongTinKhaiQuat = thongTinKhaiQuat;
    }

    public LoaiDiVat getLoaiDiVat() {
        return loaiDiVat;
    }

    public DiVat loaiDiVat(LoaiDiVat loaiDiVat) {
        this.loaiDiVat = loaiDiVat;
        return this;
    }

    public void setLoaiDiVat(LoaiDiVat loaiDiVat) {
        this.loaiDiVat = loaiDiVat;
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
        DiVat diVat = (DiVat) o;
        if (diVat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diVat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiVat{" +
            "id=" + getId() +
            ", giaTri='" + getGiaTri() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
