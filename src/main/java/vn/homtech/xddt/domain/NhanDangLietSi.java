package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A NhanDangLietSi.
 */
@Entity
@Table(name = "nhan_dang_liet_si")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "nhandanglietsi")
public class NhanDangLietSi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gia_tri")
    private String giaTri;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("nhanDangLietSis")
    private NhanDang nhanDang;

    @ManyToOne
    @JsonIgnoreProperties("lietSiNhanDangs")
    private HoSoLietSi lietSi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public NhanDangLietSi giaTri(String giaTri) {
        this.giaTri = giaTri;
        return this;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    public String getMoTa() {
        return moTa;
    }

    public NhanDangLietSi moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NhanDangLietSi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public NhanDang getNhanDang() {
        return nhanDang;
    }

    public NhanDangLietSi nhanDang(NhanDang nhanDang) {
        this.nhanDang = nhanDang;
        return this;
    }

    public void setNhanDang(NhanDang nhanDang) {
        this.nhanDang = nhanDang;
    }

    public HoSoLietSi getLietSi() {
        return lietSi;
    }

    public NhanDangLietSi lietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
        return this;
    }

    public void setLietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
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
        NhanDangLietSi nhanDangLietSi = (NhanDangLietSi) o;
        if (nhanDangLietSi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanDangLietSi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanDangLietSi{" +
            "id=" + getId() +
            ", giaTri='" + getGiaTri() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
