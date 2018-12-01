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
 * A PCRKetQua.
 */
@Entity
@Table(name = "pcr_ket_qua")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "pcrketqua")
public class PCRKetQua implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_ket_qua")
    private String maKetQua;

    @Column(name = "ten_ket_qua")
    private String tenKetQua;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @OneToMany(mappedBy = "pcrKetQua")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRMau> pcrKetQuas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKetQua() {
        return maKetQua;
    }

    public PCRKetQua maKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
        return this;
    }

    public void setMaKetQua(String maKetQua) {
        this.maKetQua = maKetQua;
    }

    public String getTenKetQua() {
        return tenKetQua;
    }

    public PCRKetQua tenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
        return this;
    }

    public void setTenKetQua(String tenKetQua) {
        this.tenKetQua = tenKetQua;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PCRKetQua isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PCRKetQua udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PCRKetQua udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PCRKetQua udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<PCRMau> getPcrKetQuas() {
        return pcrKetQuas;
    }

    public PCRKetQua pcrKetQuas(Set<PCRMau> pCRMaus) {
        this.pcrKetQuas = pCRMaus;
        return this;
    }

    public PCRKetQua addPcrKetQua(PCRMau pCRMau) {
        this.pcrKetQuas.add(pCRMau);
        pCRMau.setPcrKetQua(this);
        return this;
    }

    public PCRKetQua removePcrKetQua(PCRMau pCRMau) {
        this.pcrKetQuas.remove(pCRMau);
        pCRMau.setPcrKetQua(null);
        return this;
    }

    public void setPcrKetQuas(Set<PCRMau> pCRMaus) {
        this.pcrKetQuas = pCRMaus;
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
        PCRKetQua pCRKetQua = (PCRKetQua) o;
        if (pCRKetQua.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pCRKetQua.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PCRKetQua{" +
            "id=" + getId() +
            ", maKetQua='" + getMaKetQua() + "'" +
            ", tenKetQua='" + getTenKetQua() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
