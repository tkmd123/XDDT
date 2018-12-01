package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A QuanHuyen.
 */
@Entity
@Table(name = "quan_huyen")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "quanhuyen")
public class QuanHuyen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_huyen")
    private String maHuyen;

    @Column(name = "ten_huyen")
    private String tenHuyen;

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

    @ManyToOne
    @JsonIgnoreProperties("tinhThanhQuanHuyens")
    private TinhThanh tinhThanh;

    @OneToMany(mappedBy = "quanHuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PhuongXa> quanHuyenPhuongXas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHuyen() {
        return maHuyen;
    }

    public QuanHuyen maHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
        return this;
    }

    public void setMaHuyen(String maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public QuanHuyen tenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
        return this;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public QuanHuyen moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public QuanHuyen isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public QuanHuyen udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public QuanHuyen udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public QuanHuyen udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public TinhThanh getTinhThanh() {
        return tinhThanh;
    }

    public QuanHuyen tinhThanh(TinhThanh tinhThanh) {
        this.tinhThanh = tinhThanh;
        return this;
    }

    public void setTinhThanh(TinhThanh tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public Set<PhuongXa> getQuanHuyenPhuongXas() {
        return quanHuyenPhuongXas;
    }

    public QuanHuyen quanHuyenPhuongXas(Set<PhuongXa> phuongXas) {
        this.quanHuyenPhuongXas = phuongXas;
        return this;
    }

    public QuanHuyen addQuanHuyenPhuongXa(PhuongXa phuongXa) {
        this.quanHuyenPhuongXas.add(phuongXa);
        phuongXa.setQuanHuyen(this);
        return this;
    }

    public QuanHuyen removeQuanHuyenPhuongXa(PhuongXa phuongXa) {
        this.quanHuyenPhuongXas.remove(phuongXa);
        phuongXa.setQuanHuyen(null);
        return this;
    }

    public void setQuanHuyenPhuongXas(Set<PhuongXa> phuongXas) {
        this.quanHuyenPhuongXas = phuongXas;
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
        QuanHuyen quanHuyen = (QuanHuyen) o;
        if (quanHuyen.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), quanHuyen.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "QuanHuyen{" +
            "id=" + getId() +
            ", maHuyen='" + getMaHuyen() + "'" +
            ", tenHuyen='" + getTenHuyen() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
