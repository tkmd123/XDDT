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

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

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

    public String getUdf1() {
        return udf1;
    }

    public ChucVu udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public ChucVu udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public ChucVu udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
