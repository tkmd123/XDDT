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
 * A TinhThanh.
 */
@Entity
@Table(name = "tinh_thanh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tinhthanh")
public class TinhThanh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_tinh")
    private String maTinh;

    @Column(name = "ten_tinh")
    private String tenTinh;

    @Column(name = "mo_ta")
    private String moTa;

    @OneToMany(mappedBy = "tinhThanh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<QuanHuyen> tinhThanhQuanHuyens = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public TinhThanh maTinh(String maTinh) {
        this.maTinh = maTinh;
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getTenTinh() {
        return tenTinh;
    }

    public TinhThanh tenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
        return this;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public TinhThanh moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Set<QuanHuyen> getTinhThanhQuanHuyens() {
        return tinhThanhQuanHuyens;
    }

    public TinhThanh tinhThanhQuanHuyens(Set<QuanHuyen> quanHuyens) {
        this.tinhThanhQuanHuyens = quanHuyens;
        return this;
    }

    public TinhThanh addTinhThanhQuanHuyen(QuanHuyen quanHuyen) {
        this.tinhThanhQuanHuyens.add(quanHuyen);
        quanHuyen.setTinhThanh(this);
        return this;
    }

    public TinhThanh removeTinhThanhQuanHuyen(QuanHuyen quanHuyen) {
        this.tinhThanhQuanHuyens.remove(quanHuyen);
        quanHuyen.setTinhThanh(null);
        return this;
    }

    public void setTinhThanhQuanHuyens(Set<QuanHuyen> quanHuyens) {
        this.tinhThanhQuanHuyens = quanHuyens;
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
        TinhThanh tinhThanh = (TinhThanh) o;
        if (tinhThanh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tinhThanh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TinhThanh{" +
            "id=" + getId() +
            ", maTinh='" + getMaTinh() + "'" +
            ", tenTinh='" + getTenTinh() + "'" +
            ", moTa='" + getMoTa() + "'" +
            "}";
    }
}
