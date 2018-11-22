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
 * A DonVi.
 */
@Entity
@Table(name = "don_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "donvi")
public class DonVi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_don_vi")
    private String maDonVi;

    @Column(name = "ten_don_vi")
    private String tenDonVi;

    @Column(name = "ten_tat")
    private String tenTat;

    @Column(name = "phan_muc")
    private String phanMuc;

    @Column(name = "phan_cap")
    private String phanCap;

    @Column(name = "phan_khoi")
    private String phanKhoi;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "donViHiSinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoLietSi> donViHySinhs = new HashSet<>();
    @OneToMany(mappedBy = "donViHuanLuyen")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoLietSi> donViHuanLuyens = new HashSet<>();
    @OneToMany(mappedBy = "donViQuanLy")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DonVi> donViQuanLies = new HashSet<>();
    @OneToMany(mappedBy = "donVi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DonViThoiKy> donViThoiKies = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("donViQuanLies")
    private DonVi donViQuanLy;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDonVi() {
        return maDonVi;
    }

    public DonVi maDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
        return this;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public DonVi tenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
        return this;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public String getTenTat() {
        return tenTat;
    }

    public DonVi tenTat(String tenTat) {
        this.tenTat = tenTat;
        return this;
    }

    public void setTenTat(String tenTat) {
        this.tenTat = tenTat;
    }

    public String getPhanMuc() {
        return phanMuc;
    }

    public DonVi phanMuc(String phanMuc) {
        this.phanMuc = phanMuc;
        return this;
    }

    public void setPhanMuc(String phanMuc) {
        this.phanMuc = phanMuc;
    }

    public String getPhanCap() {
        return phanCap;
    }

    public DonVi phanCap(String phanCap) {
        this.phanCap = phanCap;
        return this;
    }

    public void setPhanCap(String phanCap) {
        this.phanCap = phanCap;
    }

    public String getPhanKhoi() {
        return phanKhoi;
    }

    public DonVi phanKhoi(String phanKhoi) {
        this.phanKhoi = phanKhoi;
        return this;
    }

    public void setPhanKhoi(String phanKhoi) {
        this.phanKhoi = phanKhoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public DonVi moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DonVi ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public DonVi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<HoSoLietSi> getDonViHySinhs() {
        return donViHySinhs;
    }

    public DonVi donViHySinhs(Set<HoSoLietSi> hoSoLietSis) {
        this.donViHySinhs = hoSoLietSis;
        return this;
    }

    public DonVi addDonViHySinh(HoSoLietSi hoSoLietSi) {
        this.donViHySinhs.add(hoSoLietSi);
        hoSoLietSi.setDonViHiSinh(this);
        return this;
    }

    public DonVi removeDonViHySinh(HoSoLietSi hoSoLietSi) {
        this.donViHySinhs.remove(hoSoLietSi);
        hoSoLietSi.setDonViHiSinh(null);
        return this;
    }

    public void setDonViHySinhs(Set<HoSoLietSi> hoSoLietSis) {
        this.donViHySinhs = hoSoLietSis;
    }

    public Set<HoSoLietSi> getDonViHuanLuyens() {
        return donViHuanLuyens;
    }

    public DonVi donViHuanLuyens(Set<HoSoLietSi> hoSoLietSis) {
        this.donViHuanLuyens = hoSoLietSis;
        return this;
    }

    public DonVi addDonViHuanLuyen(HoSoLietSi hoSoLietSi) {
        this.donViHuanLuyens.add(hoSoLietSi);
        hoSoLietSi.setDonViHuanLuyen(this);
        return this;
    }

    public DonVi removeDonViHuanLuyen(HoSoLietSi hoSoLietSi) {
        this.donViHuanLuyens.remove(hoSoLietSi);
        hoSoLietSi.setDonViHuanLuyen(null);
        return this;
    }

    public void setDonViHuanLuyens(Set<HoSoLietSi> hoSoLietSis) {
        this.donViHuanLuyens = hoSoLietSis;
    }

    public Set<DonVi> getDonViQuanLies() {
        return donViQuanLies;
    }

    public DonVi donViQuanLies(Set<DonVi> donVis) {
        this.donViQuanLies = donVis;
        return this;
    }

    public DonVi addDonViQuanLy(DonVi donVi) {
        this.donViQuanLies.add(donVi);
        donVi.setDonViQuanLy(this);
        return this;
    }

    public DonVi removeDonViQuanLy(DonVi donVi) {
        this.donViQuanLies.remove(donVi);
        donVi.setDonViQuanLy(null);
        return this;
    }

    public void setDonViQuanLies(Set<DonVi> donVis) {
        this.donViQuanLies = donVis;
    }

    public Set<DonViThoiKy> getDonViThoiKies() {
        return donViThoiKies;
    }

    public DonVi donViThoiKies(Set<DonViThoiKy> donViThoiKies) {
        this.donViThoiKies = donViThoiKies;
        return this;
    }

    public DonVi addDonViThoiKy(DonViThoiKy donViThoiKy) {
        this.donViThoiKies.add(donViThoiKy);
        donViThoiKy.setDonVi(this);
        return this;
    }

    public DonVi removeDonViThoiKy(DonViThoiKy donViThoiKy) {
        this.donViThoiKies.remove(donViThoiKy);
        donViThoiKy.setDonVi(null);
        return this;
    }

    public void setDonViThoiKies(Set<DonViThoiKy> donViThoiKies) {
        this.donViThoiKies = donViThoiKies;
    }

    public DonVi getDonViQuanLy() {
        return donViQuanLy;
    }

    public DonVi donViQuanLy(DonVi donVi) {
        this.donViQuanLy = donVi;
        return this;
    }

    public void setDonViQuanLy(DonVi donVi) {
        this.donViQuanLy = donVi;
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
        DonVi donVi = (DonVi) o;
        if (donVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonVi{" +
            "id=" + getId() +
            ", maDonVi='" + getMaDonVi() + "'" +
            ", tenDonVi='" + getTenDonVi() + "'" +
            ", tenTat='" + getTenTat() + "'" +
            ", phanMuc='" + getPhanMuc() + "'" +
            ", phanCap='" + getPhanCap() + "'" +
            ", phanKhoi='" + getPhanKhoi() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
