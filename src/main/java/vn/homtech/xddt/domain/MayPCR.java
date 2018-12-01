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
 * A MayPCR.
 */
@Entity
@Table(name = "may_pcr")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "maypcr")
public class MayPCR implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_may_pcr")
    private String maMayPCR;

    @Column(name = "ten_may_pcr")
    private String tenMayPCR;

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

    @OneToMany(mappedBy = "mayTinhSach")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TinhSach> mayTinhSaches = new HashSet<>();
    @OneToMany(mappedBy = "mayPCR")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCR> mayPCRS = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaMayPCR() {
        return maMayPCR;
    }

    public MayPCR maMayPCR(String maMayPCR) {
        this.maMayPCR = maMayPCR;
        return this;
    }

    public void setMaMayPCR(String maMayPCR) {
        this.maMayPCR = maMayPCR;
    }

    public String getTenMayPCR() {
        return tenMayPCR;
    }

    public MayPCR tenMayPCR(String tenMayPCR) {
        this.tenMayPCR = tenMayPCR;
        return this;
    }

    public void setTenMayPCR(String tenMayPCR) {
        this.tenMayPCR = tenMayPCR;
    }

    public String getMoTa() {
        return moTa;
    }

    public MayPCR moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public MayPCR isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public MayPCR udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public MayPCR udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public MayPCR udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<TinhSach> getMayTinhSaches() {
        return mayTinhSaches;
    }

    public MayPCR mayTinhSaches(Set<TinhSach> tinhSaches) {
        this.mayTinhSaches = tinhSaches;
        return this;
    }

    public MayPCR addMayTinhSach(TinhSach tinhSach) {
        this.mayTinhSaches.add(tinhSach);
        tinhSach.setMayTinhSach(this);
        return this;
    }

    public MayPCR removeMayTinhSach(TinhSach tinhSach) {
        this.mayTinhSaches.remove(tinhSach);
        tinhSach.setMayTinhSach(null);
        return this;
    }

    public void setMayTinhSaches(Set<TinhSach> tinhSaches) {
        this.mayTinhSaches = tinhSaches;
    }

    public Set<PCR> getMayPCRS() {
        return mayPCRS;
    }

    public MayPCR mayPCRS(Set<PCR> pCRS) {
        this.mayPCRS = pCRS;
        return this;
    }

    public MayPCR addMayPCR(PCR pCR) {
        this.mayPCRS.add(pCR);
        pCR.setMayPCR(this);
        return this;
    }

    public MayPCR removeMayPCR(PCR pCR) {
        this.mayPCRS.remove(pCR);
        pCR.setMayPCR(null);
        return this;
    }

    public void setMayPCRS(Set<PCR> pCRS) {
        this.mayPCRS = pCRS;
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
        MayPCR mayPCR = (MayPCR) o;
        if (mayPCR.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mayPCR.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MayPCR{" +
            "id=" + getId() +
            ", maMayPCR='" + getMaMayPCR() + "'" +
            ", tenMayPCR='" + getTenMayPCR() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
