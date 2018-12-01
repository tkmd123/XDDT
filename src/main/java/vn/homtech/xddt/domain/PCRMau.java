package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PCRMau.
 */
@Entity
@Table(name = "pcr_mau")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pcrmau")
public class PCRMau extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nong_do_agaros")
    private Float nongDoAgaros;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @ManyToOne
    @JsonIgnoreProperties("pcrKetQuas")
    private PCRKetQua pcrKetQua;

    @ManyToOne
    @JsonIgnoreProperties("pcrMaus")
    private PCR pcrMau;

    @ManyToOne
    @JsonIgnoreProperties("mauPCRS")
    private MauXetNghiem mauPCR;

    @ManyToOne
    @JsonIgnoreProperties("moiPCRS")
    private PCRMoi moiPCR;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getNongDoAgaros() {
        return nongDoAgaros;
    }

    public PCRMau nongDoAgaros(Float nongDoAgaros) {
        this.nongDoAgaros = nongDoAgaros;
        return this;
    }

    public void setNongDoAgaros(Float nongDoAgaros) {
        this.nongDoAgaros = nongDoAgaros;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PCRMau isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PCRMau udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PCRMau udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PCRMau udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public PCRKetQua getPcrKetQua() {
        return pcrKetQua;
    }

    public PCRMau pcrKetQua(PCRKetQua pCRKetQua) {
        this.pcrKetQua = pCRKetQua;
        return this;
    }

    public void setPcrKetQua(PCRKetQua pCRKetQua) {
        this.pcrKetQua = pCRKetQua;
    }

    public PCR getPcrMau() {
        return pcrMau;
    }

    public PCRMau pcrMau(PCR pCR) {
        this.pcrMau = pCR;
        return this;
    }

    public void setPcrMau(PCR pCR) {
        this.pcrMau = pCR;
    }

    public MauXetNghiem getMauPCR() {
        return mauPCR;
    }

    public PCRMau mauPCR(MauXetNghiem mauXetNghiem) {
        this.mauPCR = mauXetNghiem;
        return this;
    }

    public void setMauPCR(MauXetNghiem mauXetNghiem) {
        this.mauPCR = mauXetNghiem;
    }

    public PCRMoi getMoiPCR() {
        return moiPCR;
    }

    public PCRMau moiPCR(PCRMoi pCRMoi) {
        this.moiPCR = pCRMoi;
        return this;
    }

    public void setMoiPCR(PCRMoi pCRMoi) {
        this.moiPCR = pCRMoi;
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
        PCRMau pCRMau = (PCRMau) o;
        if (pCRMau.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pCRMau.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PCRMau{" +
            "id=" + getId() +
            ", nongDoAgaros=" + getNongDoAgaros() +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
