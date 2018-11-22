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
 * A LoaiDiVat.
 */
@Entity
@Table(name = "loai_di_vat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "loaidivat")
public class LoaiDiVat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_di_vat")
    private String maDiVat;

    @Column(name = "loai_di_vat")
    private String loaiDiVat;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "loaiDiVat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiVat> loaiDiVats = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDiVat() {
        return maDiVat;
    }

    public LoaiDiVat maDiVat(String maDiVat) {
        this.maDiVat = maDiVat;
        return this;
    }

    public void setMaDiVat(String maDiVat) {
        this.maDiVat = maDiVat;
    }

    public String getLoaiDiVat() {
        return loaiDiVat;
    }

    public LoaiDiVat loaiDiVat(String loaiDiVat) {
        this.loaiDiVat = loaiDiVat;
        return this;
    }

    public void setLoaiDiVat(String loaiDiVat) {
        this.loaiDiVat = loaiDiVat;
    }

    public String getMoTa() {
        return moTa;
    }

    public LoaiDiVat moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public LoaiDiVat isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<DiVat> getLoaiDiVats() {
        return loaiDiVats;
    }

    public LoaiDiVat loaiDiVats(Set<DiVat> diVats) {
        this.loaiDiVats = diVats;
        return this;
    }

    public LoaiDiVat addLoaiDiVat(DiVat diVat) {
        this.loaiDiVats.add(diVat);
        diVat.setLoaiDiVat(this);
        return this;
    }

    public LoaiDiVat removeLoaiDiVat(DiVat diVat) {
        this.loaiDiVats.remove(diVat);
        diVat.setLoaiDiVat(null);
        return this;
    }

    public void setLoaiDiVats(Set<DiVat> diVats) {
        this.loaiDiVats = diVats;
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
        LoaiDiVat loaiDiVat = (LoaiDiVat) o;
        if (loaiDiVat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiDiVat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiDiVat{" +
            "id=" + getId() +
            ", maDiVat='" + getMaDiVat() + "'" +
            ", loaiDiVat='" + getLoaiDiVat() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
