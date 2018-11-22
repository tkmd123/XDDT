package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A HoSoThanNhan.
 */
@Entity
@Table(name = "ho_so_than_nhan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hosothannhan")
public class HoSoThanNhan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_than_nhan")
    private String maThanNhan;

    @Column(name = "is_lay_mau")
    private Boolean isLayMau;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "nam_sinh")
    private Instant namSinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "so_cmt")
    private String soCMT;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "dien_thoai_chinh")
    private String dienThoaiChinh;

    @Column(name = "dien_thoai_phu")
    private String dienThoaiPhu;

    @Column(name = "email")
    private String email;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "u_df_1")
    private String uDF1;

    @Column(name = "u_df_2")
    private String uDF2;

    @Column(name = "u_df_3")
    private String uDF3;

    @OneToMany(mappedBy = "thanNhan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThanNhanLietSi> thanNhans = new HashSet<>();
    @OneToMany(mappedBy = "thanNhan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> thanNhanMaus = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("phuongXaThanNhans")
    private PhuongXa phuongXa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaThanNhan() {
        return maThanNhan;
    }

    public HoSoThanNhan maThanNhan(String maThanNhan) {
        this.maThanNhan = maThanNhan;
        return this;
    }

    public void setMaThanNhan(String maThanNhan) {
        this.maThanNhan = maThanNhan;
    }

    public Boolean isIsLayMau() {
        return isLayMau;
    }

    public HoSoThanNhan isLayMau(Boolean isLayMau) {
        this.isLayMau = isLayMau;
        return this;
    }

    public void setIsLayMau(Boolean isLayMau) {
        this.isLayMau = isLayMau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public HoSoThanNhan hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Instant getNamSinh() {
        return namSinh;
    }

    public HoSoThanNhan namSinh(Instant namSinh) {
        this.namSinh = namSinh;
        return this;
    }

    public void setNamSinh(Instant namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public HoSoThanNhan gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoCMT() {
        return soCMT;
    }

    public HoSoThanNhan soCMT(String soCMT) {
        this.soCMT = soCMT;
        return this;
    }

    public void setSoCMT(String soCMT) {
        this.soCMT = soCMT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public HoSoThanNhan diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoaiChinh() {
        return dienThoaiChinh;
    }

    public HoSoThanNhan dienThoaiChinh(String dienThoaiChinh) {
        this.dienThoaiChinh = dienThoaiChinh;
        return this;
    }

    public void setDienThoaiChinh(String dienThoaiChinh) {
        this.dienThoaiChinh = dienThoaiChinh;
    }

    public String getDienThoaiPhu() {
        return dienThoaiPhu;
    }

    public HoSoThanNhan dienThoaiPhu(String dienThoaiPhu) {
        this.dienThoaiPhu = dienThoaiPhu;
        return this;
    }

    public void setDienThoaiPhu(String dienThoaiPhu) {
        this.dienThoaiPhu = dienThoaiPhu;
    }

    public String getEmail() {
        return email;
    }

    public HoSoThanNhan email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public HoSoThanNhan ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HoSoThanNhan isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getuDF1() {
        return uDF1;
    }

    public HoSoThanNhan uDF1(String uDF1) {
        this.uDF1 = uDF1;
        return this;
    }

    public void setuDF1(String uDF1) {
        this.uDF1 = uDF1;
    }

    public String getuDF2() {
        return uDF2;
    }

    public HoSoThanNhan uDF2(String uDF2) {
        this.uDF2 = uDF2;
        return this;
    }

    public void setuDF2(String uDF2) {
        this.uDF2 = uDF2;
    }

    public String getuDF3() {
        return uDF3;
    }

    public HoSoThanNhan uDF3(String uDF3) {
        this.uDF3 = uDF3;
        return this;
    }

    public void setuDF3(String uDF3) {
        this.uDF3 = uDF3;
    }

    public Set<ThanNhanLietSi> getThanNhans() {
        return thanNhans;
    }

    public HoSoThanNhan thanNhans(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.thanNhans = thanNhanLietSis;
        return this;
    }

    public HoSoThanNhan addThanNhan(ThanNhanLietSi thanNhanLietSi) {
        this.thanNhans.add(thanNhanLietSi);
        thanNhanLietSi.setThanNhan(this);
        return this;
    }

    public HoSoThanNhan removeThanNhan(ThanNhanLietSi thanNhanLietSi) {
        this.thanNhans.remove(thanNhanLietSi);
        thanNhanLietSi.setThanNhan(null);
        return this;
    }

    public void setThanNhans(Set<ThanNhanLietSi> thanNhanLietSis) {
        this.thanNhans = thanNhanLietSis;
    }

    public Set<MauXetNghiem> getThanNhanMaus() {
        return thanNhanMaus;
    }

    public HoSoThanNhan thanNhanMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.thanNhanMaus = mauXetNghiems;
        return this;
    }

    public HoSoThanNhan addThanNhanMau(MauXetNghiem mauXetNghiem) {
        this.thanNhanMaus.add(mauXetNghiem);
        mauXetNghiem.setThanNhan(this);
        return this;
    }

    public HoSoThanNhan removeThanNhanMau(MauXetNghiem mauXetNghiem) {
        this.thanNhanMaus.remove(mauXetNghiem);
        mauXetNghiem.setThanNhan(null);
        return this;
    }

    public void setThanNhanMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.thanNhanMaus = mauXetNghiems;
    }

    public PhuongXa getPhuongXa() {
        return phuongXa;
    }

    public HoSoThanNhan phuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
        return this;
    }

    public void setPhuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
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
        HoSoThanNhan hoSoThanNhan = (HoSoThanNhan) o;
        if (hoSoThanNhan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoSoThanNhan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoSoThanNhan{" +
            "id=" + getId() +
            ", maThanNhan='" + getMaThanNhan() + "'" +
            ", isLayMau='" + isIsLayMau() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", namSinh='" + getNamSinh() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", soCMT='" + getSoCMT() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", dienThoaiChinh='" + getDienThoaiChinh() + "'" +
            ", dienThoaiPhu='" + getDienThoaiPhu() + "'" +
            ", email='" + getEmail() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", uDF1='" + getuDF1() + "'" +
            ", uDF2='" + getuDF2() + "'" +
            ", uDF3='" + getuDF3() + "'" +
            "}";
    }
}
