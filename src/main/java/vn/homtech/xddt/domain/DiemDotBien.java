package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DiemDotBien.
 */
@Entity
@Table(name = "diem_dot_bien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "diemdotbien")
public class DiemDotBien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vi_tri")
    private Integer viTri;

    @Column(name = "gia_tri")
    private String giaTri;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @ManyToOne
    @JsonIgnoreProperties("vungADNS")
    private VungADN vungADN;

    @ManyToOne
    @JsonIgnoreProperties("mauDiemDotBiens")
    private MauXetNghiem mauDiemDotBien;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getViTri() {
        return viTri;
    }

    public DiemDotBien viTri(Integer viTri) {
        this.viTri = viTri;
        return this;
    }

    public void setViTri(Integer viTri) {
        this.viTri = viTri;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public DiemDotBien giaTri(String giaTri) {
        this.giaTri = giaTri;
        return this;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public DiemDotBien isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public DiemDotBien udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public DiemDotBien udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public DiemDotBien udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public VungADN getVungADN() {
        return vungADN;
    }

    public DiemDotBien vungADN(VungADN vungADN) {
        this.vungADN = vungADN;
        return this;
    }

    public void setVungADN(VungADN vungADN) {
        this.vungADN = vungADN;
    }

    public MauXetNghiem getMauDiemDotBien() {
        return mauDiemDotBien;
    }

    public DiemDotBien mauDiemDotBien(MauXetNghiem mauXetNghiem) {
        this.mauDiemDotBien = mauXetNghiem;
        return this;
    }

    public void setMauDiemDotBien(MauXetNghiem mauXetNghiem) {
        this.mauDiemDotBien = mauXetNghiem;
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
        DiemDotBien diemDotBien = (DiemDotBien) o;
        if (diemDotBien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diemDotBien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiemDotBien{" +
            "id=" + getId() +
            ", viTri=" + getViTri() +
            ", giaTri='" + getGiaTri() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}