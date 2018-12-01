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
 * A NhanVien.
 */
@Entity
@Table(name = "nhan_vien")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "nhanvien")
public class NhanVien extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nhan_vien")
    private String maNhanVien;

    @Column(name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @OneToOne    @JoinColumn(unique = true)
    private User userNhanVien;

    @OneToMany(mappedBy = "nhanVienTinhSach")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TinhSach> nhanVienTinhSaches = new HashSet<>();
    @OneToMany(mappedBy = "nhanVienPCR")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCR> nhanVienPCRS = new HashSet<>();
    @OneToMany(mappedBy = "nhanVienNghienMau")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TachChiet> nhanVienNghienMaus = new HashSet<>();
    @OneToMany(mappedBy = "nhanVienTachADN")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TachChiet> nhanVienTachADNS = new HashSet<>();
    @OneToMany(mappedBy = "nhanVienHSGD")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoGiamDinh> nhanVienHSGDS = new HashSet<>();
    @OneToMany(mappedBy = "nhanVienNhanMau")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> nhanVienNhanMaus = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("phongbans")
    private PhongBan phongban;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public NhanVien maNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
        return this;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public NhanVien tenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
        return this;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public NhanVien soDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
        return this;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public NhanVien email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoTa() {
        return moTa;
    }

    public NhanVien moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public NhanVien ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NhanVien isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public NhanVien udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public NhanVien udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public NhanVien udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public User getUserNhanVien() {
        return userNhanVien;
    }

    public NhanVien userNhanVien(User user) {
        this.userNhanVien = user;
        return this;
    }

    public void setUserNhanVien(User user) {
        this.userNhanVien = user;
    }

    public Set<TinhSach> getNhanVienTinhSaches() {
        return nhanVienTinhSaches;
    }

    public NhanVien nhanVienTinhSaches(Set<TinhSach> tinhSaches) {
        this.nhanVienTinhSaches = tinhSaches;
        return this;
    }

    public NhanVien addNhanVienTinhSach(TinhSach tinhSach) {
        this.nhanVienTinhSaches.add(tinhSach);
        tinhSach.setNhanVienTinhSach(this);
        return this;
    }

    public NhanVien removeNhanVienTinhSach(TinhSach tinhSach) {
        this.nhanVienTinhSaches.remove(tinhSach);
        tinhSach.setNhanVienTinhSach(null);
        return this;
    }

    public void setNhanVienTinhSaches(Set<TinhSach> tinhSaches) {
        this.nhanVienTinhSaches = tinhSaches;
    }

    public Set<PCR> getNhanVienPCRS() {
        return nhanVienPCRS;
    }

    public NhanVien nhanVienPCRS(Set<PCR> pCRS) {
        this.nhanVienPCRS = pCRS;
        return this;
    }

    public NhanVien addNhanVienPCR(PCR pCR) {
        this.nhanVienPCRS.add(pCR);
        pCR.setNhanVienPCR(this);
        return this;
    }

    public NhanVien removeNhanVienPCR(PCR pCR) {
        this.nhanVienPCRS.remove(pCR);
        pCR.setNhanVienPCR(null);
        return this;
    }

    public void setNhanVienPCRS(Set<PCR> pCRS) {
        this.nhanVienPCRS = pCRS;
    }

    public Set<TachChiet> getNhanVienNghienMaus() {
        return nhanVienNghienMaus;
    }

    public NhanVien nhanVienNghienMaus(Set<TachChiet> tachChiets) {
        this.nhanVienNghienMaus = tachChiets;
        return this;
    }

    public NhanVien addNhanVienNghienMau(TachChiet tachChiet) {
        this.nhanVienNghienMaus.add(tachChiet);
        tachChiet.setNhanVienNghienMau(this);
        return this;
    }

    public NhanVien removeNhanVienNghienMau(TachChiet tachChiet) {
        this.nhanVienNghienMaus.remove(tachChiet);
        tachChiet.setNhanVienNghienMau(null);
        return this;
    }

    public void setNhanVienNghienMaus(Set<TachChiet> tachChiets) {
        this.nhanVienNghienMaus = tachChiets;
    }

    public Set<TachChiet> getNhanVienTachADNS() {
        return nhanVienTachADNS;
    }

    public NhanVien nhanVienTachADNS(Set<TachChiet> tachChiets) {
        this.nhanVienTachADNS = tachChiets;
        return this;
    }

    public NhanVien addNhanVienTachADN(TachChiet tachChiet) {
        this.nhanVienTachADNS.add(tachChiet);
        tachChiet.setNhanVienTachADN(this);
        return this;
    }

    public NhanVien removeNhanVienTachADN(TachChiet tachChiet) {
        this.nhanVienTachADNS.remove(tachChiet);
        tachChiet.setNhanVienTachADN(null);
        return this;
    }

    public void setNhanVienTachADNS(Set<TachChiet> tachChiets) {
        this.nhanVienTachADNS = tachChiets;
    }

    public Set<HoSoGiamDinh> getNhanVienHSGDS() {
        return nhanVienHSGDS;
    }

    public NhanVien nhanVienHSGDS(Set<HoSoGiamDinh> hoSoGiamDinhs) {
        this.nhanVienHSGDS = hoSoGiamDinhs;
        return this;
    }

    public NhanVien addNhanVienHSGD(HoSoGiamDinh hoSoGiamDinh) {
        this.nhanVienHSGDS.add(hoSoGiamDinh);
        hoSoGiamDinh.setNhanVienHSGD(this);
        return this;
    }

    public NhanVien removeNhanVienHSGD(HoSoGiamDinh hoSoGiamDinh) {
        this.nhanVienHSGDS.remove(hoSoGiamDinh);
        hoSoGiamDinh.setNhanVienHSGD(null);
        return this;
    }

    public void setNhanVienHSGDS(Set<HoSoGiamDinh> hoSoGiamDinhs) {
        this.nhanVienHSGDS = hoSoGiamDinhs;
    }

    public Set<MauXetNghiem> getNhanVienNhanMaus() {
        return nhanVienNhanMaus;
    }

    public NhanVien nhanVienNhanMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.nhanVienNhanMaus = mauXetNghiems;
        return this;
    }

    public NhanVien addNhanVienNhanMau(MauXetNghiem mauXetNghiem) {
        this.nhanVienNhanMaus.add(mauXetNghiem);
        mauXetNghiem.setNhanVienNhanMau(this);
        return this;
    }

    public NhanVien removeNhanVienNhanMau(MauXetNghiem mauXetNghiem) {
        this.nhanVienNhanMaus.remove(mauXetNghiem);
        mauXetNghiem.setNhanVienNhanMau(null);
        return this;
    }

    public void setNhanVienNhanMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.nhanVienNhanMaus = mauXetNghiems;
    }

    public PhongBan getPhongban() {
        return phongban;
    }

    public NhanVien phongban(PhongBan phongBan) {
        this.phongban = phongBan;
        return this;
    }

    public void setPhongban(PhongBan phongBan) {
        this.phongban = phongBan;
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
        NhanVien nhanVien = (NhanVien) o;
        if (nhanVien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhanVien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhanVien{" +
            "id=" + getId() +
            ", maNhanVien='" + getMaNhanVien() + "'" +
            ", tenNhanVien='" + getTenNhanVien() + "'" +
            ", soDienThoai='" + getSoDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
