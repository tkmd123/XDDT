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
 * A NhanDang.
 */
@Entity
@Table(name = "nhan_dang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "nhandang")
public class NhanDang implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nhan_dang")
    private String maNhanDang;

    @Column(name = "ten_nhan_dang")
    private String tenNhanDang;

    @Column(name = "don_vi_tinh")
    private String donViTinh;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "nhanDang")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhanDangLietSi> nhanDangLietSis = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanDang() {
        return maNhanDang;
    }

    public NhanDang maNhanDang(String maNhanDang) {
        this.maNhanDang = maNhanDang;
        return this;
    }

    public void setMaNhanDang(String maNhanDang) {
        this.maNhanDang = maNhanDang;
    }

    public String getTenNhanDang() {
        return tenNhanDang;
    }

    public NhanDang tenNhanDang(String tenNhanDang) {
        this.tenNhanDang = tenNhanDang;
        return this;
    }

    public void setTenNhanDang(String tenNhanDang) {
        this.tenNhanDang = tenNhanDang;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public NhanDang donViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
        return this;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public NhanDang moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NhanDang isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<NhanDangLietSi> getNhanDangLietSis() {
        return nhanDangLietSis;
    }

    public NhanDang nhanDangLietSis(Set<NhanDangLietSi> nhanDangLietSis) {
        this.nhanDangLietSis = nhanDangLietSis;
        return this;
    }

    public NhanDang addNhanDangLietSi(NhanDangLietSi nhanDangLietSi) {
        this.nhanDangLietSis.add(nhanDangLietSi);
        nhanDangLietSi.setNhanDang(this);
        return this;
    }

    public NhanDang removeNhanDangLietSi(NhanDangLietSi nhanDangLietSi) {
        this.nhanDangLietSis.remove(nhanDangLietSi);
        nhanDangLietSi.setNhanDang(null);
        return this;
    }

    public void setNhanDangLietSis(Set<NhanDangLietSi> nhanDangLietSis) {
        this.nhanDangLietSis = nhanDangLietSis;
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
        NhanDang nhanDang = (NhanDang) o;
        if (nhanDang.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanDang.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanDang{" +
            "id=" + getId() +
            ", maNhanDang='" + getMaNhanDang() + "'" +
            ", tenNhanDang='" + getTenNhanDang() + "'" +
            ", donViTinh='" + getDonViTinh() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
