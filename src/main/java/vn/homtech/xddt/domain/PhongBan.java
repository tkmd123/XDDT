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
 * A PhongBan.
 */
@Entity
@Table(name = "phong_ban")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "phongban")
public class PhongBan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_phong_ban")
    private String maPhongBan;

    @Column(name = "ten_phong_ban")
    private String tenPhongBan;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "phongBan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThaoTac> phongLabPhanTiches = new HashSet<>();
    @OneToMany(mappedBy = "phongBan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LoaiThaoTac> phongBanThaoTacs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    public PhongBan maPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
        return this;
    }

    public void setMaPhongBan(String maPhongBan) {
        this.maPhongBan = maPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public PhongBan tenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
        return this;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public PhongBan moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsActive() {
        return isActive;
    }

    public PhongBan isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PhongBan isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<ThaoTac> getPhongLabPhanTiches() {
        return phongLabPhanTiches;
    }

    public PhongBan phongLabPhanTiches(Set<ThaoTac> thaoTacs) {
        this.phongLabPhanTiches = thaoTacs;
        return this;
    }

    public PhongBan addPhongLabPhanTich(ThaoTac thaoTac) {
        this.phongLabPhanTiches.add(thaoTac);
        thaoTac.setPhongBan(this);
        return this;
    }

    public PhongBan removePhongLabPhanTich(ThaoTac thaoTac) {
        this.phongLabPhanTiches.remove(thaoTac);
        thaoTac.setPhongBan(null);
        return this;
    }

    public void setPhongLabPhanTiches(Set<ThaoTac> thaoTacs) {
        this.phongLabPhanTiches = thaoTacs;
    }

    public Set<LoaiThaoTac> getPhongBanThaoTacs() {
        return phongBanThaoTacs;
    }

    public PhongBan phongBanThaoTacs(Set<LoaiThaoTac> loaiThaoTacs) {
        this.phongBanThaoTacs = loaiThaoTacs;
        return this;
    }

    public PhongBan addPhongBanThaoTac(LoaiThaoTac loaiThaoTac) {
        this.phongBanThaoTacs.add(loaiThaoTac);
        loaiThaoTac.setPhongBan(this);
        return this;
    }

    public PhongBan removePhongBanThaoTac(LoaiThaoTac loaiThaoTac) {
        this.phongBanThaoTacs.remove(loaiThaoTac);
        loaiThaoTac.setPhongBan(null);
        return this;
    }

    public void setPhongBanThaoTacs(Set<LoaiThaoTac> loaiThaoTacs) {
        this.phongBanThaoTacs = loaiThaoTacs;
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
        PhongBan phongBan = (PhongBan) o;
        if (phongBan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phongBan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhongBan{" +
            "id=" + getId() +
            ", maPhongBan='" + getMaPhongBan() + "'" +
            ", tenPhongBan='" + getTenPhongBan() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isActive='" + isIsActive() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
