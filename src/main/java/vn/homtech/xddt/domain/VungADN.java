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
 * A VungADN.
 */
@Entity
@Table(name = "vung_adn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "vungadn")
public class VungADN extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_vung_adn")
    private String maVungADN;

    @Column(name = "ten_vung_adn")
    private String tenVungADN;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "vungADN")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiemDotBien> vungADNS = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaVungADN() {
        return maVungADN;
    }

    public VungADN maVungADN(String maVungADN) {
        this.maVungADN = maVungADN;
        return this;
    }

    public void setMaVungADN(String maVungADN) {
        this.maVungADN = maVungADN;
    }

    public String getTenVungADN() {
        return tenVungADN;
    }

    public VungADN tenVungADN(String tenVungADN) {
        this.tenVungADN = tenVungADN;
        return this;
    }

    public void setTenVungADN(String tenVungADN) {
        this.tenVungADN = tenVungADN;
    }

    public String getMoTa() {
        return moTa;
    }

    public VungADN moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public VungADN isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<DiemDotBien> getVungADNS() {
        return vungADNS;
    }

    public VungADN vungADNS(Set<DiemDotBien> diemDotBiens) {
        this.vungADNS = diemDotBiens;
        return this;
    }

    public VungADN addVungADN(DiemDotBien diemDotBien) {
        this.vungADNS.add(diemDotBien);
        diemDotBien.setVungADN(this);
        return this;
    }

    public VungADN removeVungADN(DiemDotBien diemDotBien) {
        this.vungADNS.remove(diemDotBien);
        diemDotBien.setVungADN(null);
        return this;
    }

    public void setVungADNS(Set<DiemDotBien> diemDotBiens) {
        this.vungADNS = diemDotBiens;
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
        VungADN vungADN = (VungADN) o;
        if (vungADN.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vungADN.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VungADN{" +
            "id=" + getId() +
            ", maVungADN='" + getMaVungADN() + "'" +
            ", tenVungADN='" + getTenVungADN() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
