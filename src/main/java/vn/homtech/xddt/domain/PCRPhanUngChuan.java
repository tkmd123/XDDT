package vn.homtech.xddt.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PCRPhanUngChuan.
 */
@Entity
@Table(name = "pcr_phan_ung_chuan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pcrphanungchuan")
public class PCRPhanUngChuan extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chu_ky_phan_ung")
    private String chuKyPhanUng;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChuKyPhanUng() {
        return chuKyPhanUng;
    }

    public PCRPhanUngChuan chuKyPhanUng(String chuKyPhanUng) {
        this.chuKyPhanUng = chuKyPhanUng;
        return this;
    }

    public void setChuKyPhanUng(String chuKyPhanUng) {
        this.chuKyPhanUng = chuKyPhanUng;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PCRPhanUngChuan isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PCRPhanUngChuan udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PCRPhanUngChuan udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PCRPhanUngChuan udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
        PCRPhanUngChuan pCRPhanUngChuan = (PCRPhanUngChuan) o;
        if (pCRPhanUngChuan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pCRPhanUngChuan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PCRPhanUngChuan{" +
            "id=" + getId() +
            ", chuKyPhanUng='" + getChuKyPhanUng() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
