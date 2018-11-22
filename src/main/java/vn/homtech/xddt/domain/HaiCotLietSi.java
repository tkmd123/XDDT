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

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "co_hai_cot")
    private Boolean coHaiCot;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

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

    public Boolean isCoHaiCot() {
        return coHaiCot;
    }

    public HaiCotLietSi coHaiCot(Boolean coHaiCot) {
        this.coHaiCot = coHaiCot;
        return this;
    }

    public void setCoHaiCot(Boolean coHaiCot) {
        this.coHaiCot = coHaiCot;
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
            ", moTa='" + getMoTa() + "'" +
            ", coHaiCot='" + isCoHaiCot() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
