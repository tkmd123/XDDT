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

    @Column(name = "gia_tri_1")
    private String giaTri1;

    @Column(name = "gia_tri_2")
    private String giaTri2;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("vungADNS")
    private VungADN vungADN;

    @ManyToOne
    @JsonIgnoreProperties("thongTinADNDotBiens")
    private ThongTinADN thongTinADN;

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

    public String getGiaTri1() {
        return giaTri1;
    }

    public DiemDotBien giaTri1(String giaTri1) {
        this.giaTri1 = giaTri1;
        return this;
    }

    public void setGiaTri1(String giaTri1) {
        this.giaTri1 = giaTri1;
    }

    public String getGiaTri2() {
        return giaTri2;
    }

    public DiemDotBien giaTri2(String giaTri2) {
        this.giaTri2 = giaTri2;
        return this;
    }

    public void setGiaTri2(String giaTri2) {
        this.giaTri2 = giaTri2;
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

    public ThongTinADN getThongTinADN() {
        return thongTinADN;
    }

    public DiemDotBien thongTinADN(ThongTinADN thongTinADN) {
        this.thongTinADN = thongTinADN;
        return this;
    }

    public void setThongTinADN(ThongTinADN thongTinADN) {
        this.thongTinADN = thongTinADN;
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
            ", giaTri1='" + getGiaTri1() + "'" +
            ", giaTri2='" + getGiaTri2() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
