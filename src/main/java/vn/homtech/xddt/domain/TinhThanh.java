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
public class TinhThanh extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

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

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public TinhThanh isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public TinhThanh udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public TinhThanh udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public TinhThanh udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
