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
            "}";
    }
}
