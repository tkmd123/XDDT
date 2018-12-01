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
 * A HoSoGiamDinh.
 */
@Entity
@Table(name = "ho_so_giam_dinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hosogiamdinh")
public class HoSoGiamDinh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hsgd")
    private String maHSGD;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @OneToMany(mappedBy = "hoSoGiamDinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> hoSoGiamDinhs = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ho_so_giam_dinh_giam_dinh_than_nhan",
               joinColumns = @JoinColumn(name = "ho_so_giam_dinhs_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "giam_dinh_than_nhans_id", referencedColumnName = "id"))
    private Set<HoSoThanNhan> giamDinhThanNhans = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ho_so_giam_dinh_giam_dinh_liet_si",
               joinColumns = @JoinColumn(name = "ho_so_giam_dinhs_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "giam_dinh_liet_sis_id", referencedColumnName = "id"))
    private Set<HoSoLietSi> giamDinhLietSis = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("nhanVienHSGDS")
    private NhanVien nhanVienHSGD;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHSGD() {
        return maHSGD;
    }

    public HoSoGiamDinh maHSGD(String maHSGD) {
        this.maHSGD = maHSGD;
        return this;
    }

    public void setMaHSGD(String maHSGD) {
        this.maHSGD = maHSGD;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HoSoGiamDinh isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public HoSoGiamDinh udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HoSoGiamDinh udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HoSoGiamDinh udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<MauXetNghiem> getHoSoGiamDinhs() {
        return hoSoGiamDinhs;
    }

    public HoSoGiamDinh hoSoGiamDinhs(Set<MauXetNghiem> mauXetNghiems) {
        this.hoSoGiamDinhs = mauXetNghiems;
        return this;
    }

    public HoSoGiamDinh addHoSoGiamDinh(MauXetNghiem mauXetNghiem) {
        this.hoSoGiamDinhs.add(mauXetNghiem);
        mauXetNghiem.setHoSoGiamDinh(this);
        return this;
    }

    public HoSoGiamDinh removeHoSoGiamDinh(MauXetNghiem mauXetNghiem) {
        this.hoSoGiamDinhs.remove(mauXetNghiem);
        mauXetNghiem.setHoSoGiamDinh(null);
        return this;
    }

    public void setHoSoGiamDinhs(Set<MauXetNghiem> mauXetNghiems) {
        this.hoSoGiamDinhs = mauXetNghiems;
    }

    public Set<HoSoThanNhan> getGiamDinhThanNhans() {
        return giamDinhThanNhans;
    }

    public HoSoGiamDinh giamDinhThanNhans(Set<HoSoThanNhan> hoSoThanNhans) {
        this.giamDinhThanNhans = hoSoThanNhans;
        return this;
    }

    public HoSoGiamDinh addGiamDinhThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.giamDinhThanNhans.add(hoSoThanNhan);
        return this;
    }

    public HoSoGiamDinh removeGiamDinhThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.giamDinhThanNhans.remove(hoSoThanNhan);
        return this;
    }

    public void setGiamDinhThanNhans(Set<HoSoThanNhan> hoSoThanNhans) {
        this.giamDinhThanNhans = hoSoThanNhans;
    }

    public Set<HoSoLietSi> getGiamDinhLietSis() {
        return giamDinhLietSis;
    }

    public HoSoGiamDinh giamDinhLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.giamDinhLietSis = hoSoLietSis;
        return this;
    }

    public HoSoGiamDinh addGiamDinhLietSi(HoSoLietSi hoSoLietSi) {
        this.giamDinhLietSis.add(hoSoLietSi);
        return this;
    }

    public HoSoGiamDinh removeGiamDinhLietSi(HoSoLietSi hoSoLietSi) {
        this.giamDinhLietSis.remove(hoSoLietSi);
        return this;
    }

    public void setGiamDinhLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.giamDinhLietSis = hoSoLietSis;
    }

    public NhanVien getNhanVienHSGD() {
        return nhanVienHSGD;
    }

    public HoSoGiamDinh nhanVienHSGD(NhanVien nhanVien) {
        this.nhanVienHSGD = nhanVien;
        return this;
    }

    public void setNhanVienHSGD(NhanVien nhanVien) {
        this.nhanVienHSGD = nhanVien;
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
        HoSoGiamDinh hoSoGiamDinh = (HoSoGiamDinh) o;
        if (hoSoGiamDinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoSoGiamDinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoSoGiamDinh{" +
            "id=" + getId() +
            ", maHSGD='" + getMaHSGD() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
