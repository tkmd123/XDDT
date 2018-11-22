package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ThanNhanLietSi.
 */
@Entity
@Table(name = "than_nhan_liet_si")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "thannhanlietsi")
public class ThanNhanLietSi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("lietSis")
    private HoSoLietSi lietSi;

    @ManyToOne
    @JsonIgnoreProperties("thanNhans")
    private HoSoThanNhan thanNhan;

    @ManyToOne
    @JsonIgnoreProperties("quanHeThanNhans")
    private QuanHeThanNhan quanHeThanNhan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public ThanNhanLietSi moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ThanNhanLietSi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public HoSoLietSi getLietSi() {
        return lietSi;
    }

    public ThanNhanLietSi lietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
        return this;
    }

    public void setLietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
    }

    public HoSoThanNhan getThanNhan() {
        return thanNhan;
    }

    public ThanNhanLietSi thanNhan(HoSoThanNhan hoSoThanNhan) {
        this.thanNhan = hoSoThanNhan;
        return this;
    }

    public void setThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.thanNhan = hoSoThanNhan;
    }

    public QuanHeThanNhan getQuanHeThanNhan() {
        return quanHeThanNhan;
    }

    public ThanNhanLietSi quanHeThanNhan(QuanHeThanNhan quanHeThanNhan) {
        this.quanHeThanNhan = quanHeThanNhan;
        return this;
    }

    public void setQuanHeThanNhan(QuanHeThanNhan quanHeThanNhan) {
        this.quanHeThanNhan = quanHeThanNhan;
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
        ThanNhanLietSi thanNhanLietSi = (ThanNhanLietSi) o;
        if (thanNhanLietSi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thanNhanLietSi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThanNhanLietSi{" +
            "id=" + getId() +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
