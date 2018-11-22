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
 * A CapBac.
 */
@Entity
@Table(name = "cap_bac")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "capbac")
public class CapBac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_cap_bac")
    private String maCapBac;

    @Column(name = "ten_cap_bac")
    private String tenCapBac;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "capBac")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoLietSi> capBacLietSis = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCapBac() {
        return maCapBac;
    }

    public CapBac maCapBac(String maCapBac) {
        this.maCapBac = maCapBac;
        return this;
    }

    public void setMaCapBac(String maCapBac) {
        this.maCapBac = maCapBac;
    }

    public String getTenCapBac() {
        return tenCapBac;
    }

    public CapBac tenCapBac(String tenCapBac) {
        this.tenCapBac = tenCapBac;
        return this;
    }

    public void setTenCapBac(String tenCapBac) {
        this.tenCapBac = tenCapBac;
    }

    public String getMoTa() {
        return moTa;
    }

    public CapBac moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public CapBac isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<HoSoLietSi> getCapBacLietSis() {
        return capBacLietSis;
    }

    public CapBac capBacLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.capBacLietSis = hoSoLietSis;
        return this;
    }

    public CapBac addCapBacLietSi(HoSoLietSi hoSoLietSi) {
        this.capBacLietSis.add(hoSoLietSi);
        hoSoLietSi.setCapBac(this);
        return this;
    }

    public CapBac removeCapBacLietSi(HoSoLietSi hoSoLietSi) {
        this.capBacLietSis.remove(hoSoLietSi);
        hoSoLietSi.setCapBac(null);
        return this;
    }

    public void setCapBacLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.capBacLietSis = hoSoLietSis;
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
        CapBac capBac = (CapBac) o;
        if (capBac.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), capBac.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CapBac{" +
            "id=" + getId() +
            ", maCapBac='" + getMaCapBac() + "'" +
            ", tenCapBac='" + getTenCapBac() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
