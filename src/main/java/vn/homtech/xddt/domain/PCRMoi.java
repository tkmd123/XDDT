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
 * A PCRMoi.
 */
@Entity
@Table(name = "pcr_moi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pcrmoi")
public class PCRMoi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_moi")
    private String maMoi;

    @Column(name = "ten_moi")
    private String tenMoi;

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

    @OneToMany(mappedBy = "moiPCR")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRMau> moiPCRS = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaMoi() {
        return maMoi;
    }

    public PCRMoi maMoi(String maMoi) {
        this.maMoi = maMoi;
        return this;
    }

    public void setMaMoi(String maMoi) {
        this.maMoi = maMoi;
    }

    public String getTenMoi() {
        return tenMoi;
    }

    public PCRMoi tenMoi(String tenMoi) {
        this.tenMoi = tenMoi;
        return this;
    }

    public void setTenMoi(String tenMoi) {
        this.tenMoi = tenMoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public PCRMoi moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PCRMoi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PCRMoi udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PCRMoi udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PCRMoi udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<PCRMau> getMoiPCRS() {
        return moiPCRS;
    }

    public PCRMoi moiPCRS(Set<PCRMau> pCRMaus) {
        this.moiPCRS = pCRMaus;
        return this;
    }

    public PCRMoi addMoiPCR(PCRMau pCRMau) {
        this.moiPCRS.add(pCRMau);
        pCRMau.setMoiPCR(this);
        return this;
    }

    public PCRMoi removeMoiPCR(PCRMau pCRMau) {
        this.moiPCRS.remove(pCRMau);
        pCRMau.setMoiPCR(null);
        return this;
    }

    public void setMoiPCRS(Set<PCRMau> pCRMaus) {
        this.moiPCRS = pCRMaus;
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
        PCRMoi pCRMoi = (PCRMoi) o;
        if (pCRMoi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pCRMoi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PCRMoi{" +
            "id=" + getId() +
            ", maMoi='" + getMaMoi() + "'" +
            ", tenMoi='" + getTenMoi() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
