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
 * A TrungTam.
 */
@Entity
@Table(name = "trung_tam")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "trungtam")
public class TrungTam extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_trung_tam")
    private String maTrungTam;

    @Column(name = "ten_trung_tam")
    private String tenTrungTam;

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

    @OneToMany(mappedBy = "trungtam")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PhongBan> trungtams = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaTrungTam() {
        return maTrungTam;
    }

    public TrungTam maTrungTam(String maTrungTam) {
        this.maTrungTam = maTrungTam;
        return this;
    }

    public void setMaTrungTam(String maTrungTam) {
        this.maTrungTam = maTrungTam;
    }

    public String getTenTrungTam() {
        return tenTrungTam;
    }

    public TrungTam tenTrungTam(String tenTrungTam) {
        this.tenTrungTam = tenTrungTam;
        return this;
    }

    public void setTenTrungTam(String tenTrungTam) {
        this.tenTrungTam = tenTrungTam;
    }

    public String getMoTa() {
        return moTa;
    }

    public TrungTam moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public TrungTam isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public TrungTam udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public TrungTam udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public TrungTam udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<PhongBan> getTrungtams() {
        return trungtams;
    }

    public TrungTam trungtams(Set<PhongBan> phongBans) {
        this.trungtams = phongBans;
        return this;
    }

    public TrungTam addTrungtam(PhongBan phongBan) {
        this.trungtams.add(phongBan);
        phongBan.setTrungtam(this);
        return this;
    }

    public TrungTam removeTrungtam(PhongBan phongBan) {
        this.trungtams.remove(phongBan);
        phongBan.setTrungtam(null);
        return this;
    }

    public void setTrungtams(Set<PhongBan> phongBans) {
        this.trungtams = phongBans;
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
        TrungTam trungTam = (TrungTam) o;
        if (trungTam.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trungTam.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrungTam{" +
            "id=" + getId() +
            ", maTrungTam='" + getMaTrungTam() + "'" +
            ", tenTrungTam='" + getTenTrungTam() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
