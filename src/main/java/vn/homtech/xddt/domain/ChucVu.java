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
 * A ChucVu.
 */
@Entity
@Table(name = "chuc_vu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "chucvu")
public class ChucVu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_chuc_vu")
    private String maChucVu;

    @Column(name = "ten_chuc_vu")
    private String tenChucVu;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "chucVu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoLietSi> chucVuLietSis = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public ChucVu maChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
        return this;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public ChucVu tenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
        return this;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public ChucVu moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ChucVu isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<HoSoLietSi> getChucVuLietSis() {
        return chucVuLietSis;
    }

    public ChucVu chucVuLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.chucVuLietSis = hoSoLietSis;
        return this;
    }

    public ChucVu addChucVuLietSi(HoSoLietSi hoSoLietSi) {
        this.chucVuLietSis.add(hoSoLietSi);
        hoSoLietSi.setChucVu(this);
        return this;
    }

    public ChucVu removeChucVuLietSi(HoSoLietSi hoSoLietSi) {
        this.chucVuLietSis.remove(hoSoLietSi);
        hoSoLietSi.setChucVu(null);
        return this;
    }

    public void setChucVuLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.chucVuLietSis = hoSoLietSis;
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
        ChucVu chucVu = (ChucVu) o;
        if (chucVu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chucVu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChucVu{" +
            "id=" + getId() +
            ", maChucVu='" + getMaChucVu() + "'" +
            ", tenChucVu='" + getTenChucVu() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
