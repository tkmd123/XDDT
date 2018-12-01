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
 * A HaiCotLietSi.
 */
@Entity
@Table(name = "hai_cot_liet_si")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "haicotlietsi")
public class HaiCotLietSi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hai_cot")
    private String maHaiCot;

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

    @OneToMany(mappedBy = "haiCotLietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HinhThaiHaiCot> thongTinHinhThaiHocs = new HashSet<>();
    @OneToMany(mappedBy = "haiCotLietSi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> haiCotMaus = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("lietSiMos")
    private HoSoLietSi lietSi;

    @ManyToOne
    @JsonIgnoreProperties("khaiQuatHaiCots")
    private ThongTinKhaiQuat thongTinKhaiQuat;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHaiCot() {
        return maHaiCot;
    }

    public HaiCotLietSi maHaiCot(String maHaiCot) {
        this.maHaiCot = maHaiCot;
        return this;
    }

    public void setMaHaiCot(String maHaiCot) {
        this.maHaiCot = maHaiCot;
    }

    public String getMoTa() {
        return moTa;
    }

    public HaiCotLietSi moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HaiCotLietSi isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public HaiCotLietSi udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HaiCotLietSi udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HaiCotLietSi udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<HinhThaiHaiCot> getThongTinHinhThaiHocs() {
        return thongTinHinhThaiHocs;
    }

    public HaiCotLietSi thongTinHinhThaiHocs(Set<HinhThaiHaiCot> hinhThaiHaiCots) {
        this.thongTinHinhThaiHocs = hinhThaiHaiCots;
        return this;
    }

    public HaiCotLietSi addThongTinHinhThaiHoc(HinhThaiHaiCot hinhThaiHaiCot) {
        this.thongTinHinhThaiHocs.add(hinhThaiHaiCot);
        hinhThaiHaiCot.setHaiCotLietSi(this);
        return this;
    }

    public HaiCotLietSi removeThongTinHinhThaiHoc(HinhThaiHaiCot hinhThaiHaiCot) {
        this.thongTinHinhThaiHocs.remove(hinhThaiHaiCot);
        hinhThaiHaiCot.setHaiCotLietSi(null);
        return this;
    }

    public void setThongTinHinhThaiHocs(Set<HinhThaiHaiCot> hinhThaiHaiCots) {
        this.thongTinHinhThaiHocs = hinhThaiHaiCots;
    }

    public Set<MauXetNghiem> getHaiCotMaus() {
        return haiCotMaus;
    }

    public HaiCotLietSi haiCotMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.haiCotMaus = mauXetNghiems;
        return this;
    }

    public HaiCotLietSi addHaiCotMau(MauXetNghiem mauXetNghiem) {
        this.haiCotMaus.add(mauXetNghiem);
        mauXetNghiem.setHaiCotLietSi(this);
        return this;
    }

    public HaiCotLietSi removeHaiCotMau(MauXetNghiem mauXetNghiem) {
        this.haiCotMaus.remove(mauXetNghiem);
        mauXetNghiem.setHaiCotLietSi(null);
        return this;
    }

    public void setHaiCotMaus(Set<MauXetNghiem> mauXetNghiems) {
        this.haiCotMaus = mauXetNghiems;
    }

    public HoSoLietSi getLietSi() {
        return lietSi;
    }

    public HaiCotLietSi lietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
        return this;
    }

    public void setLietSi(HoSoLietSi hoSoLietSi) {
        this.lietSi = hoSoLietSi;
    }

    public ThongTinKhaiQuat getThongTinKhaiQuat() {
        return thongTinKhaiQuat;
    }

    public HaiCotLietSi thongTinKhaiQuat(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.thongTinKhaiQuat = thongTinKhaiQuat;
        return this;
    }

    public void setThongTinKhaiQuat(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.thongTinKhaiQuat = thongTinKhaiQuat;
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
        HaiCotLietSi haiCotLietSi = (HaiCotLietSi) o;
        if (haiCotLietSi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), haiCotLietSi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HaiCotLietSi{" +
            "id=" + getId() +
            ", maHaiCot='" + getMaHaiCot() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
