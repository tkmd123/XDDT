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
 * A ThongTinKhaiQuat.
 */
@Entity
@Table(name = "thong_tin_khai_quat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "thongtinkhaiquat")
public class ThongTinKhaiQuat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_khai_quat")
    private String maKhaiQuat;

    @Column(name = "nguoi_khai_quat")
    private String nguoiKhaiQuat;

    @Column(name = "don_vi_khai_quat")
    private String donViKhaiQuat;

    @Column(name = "thoi_gian_khai_quat")
    private Instant thoiGianKhaiQuat;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "thongTinKhaiQuat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HaiCotLietSi> khaiQuatHaiCots = new HashSet<>();
    @OneToMany(mappedBy = "thongTinKhaiQuat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiVat> khaiQuatDiVats = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("moLietSis")
    private ThongTinMo thongTinMo;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKhaiQuat() {
        return maKhaiQuat;
    }

    public ThongTinKhaiQuat maKhaiQuat(String maKhaiQuat) {
        this.maKhaiQuat = maKhaiQuat;
        return this;
    }

    public void setMaKhaiQuat(String maKhaiQuat) {
        this.maKhaiQuat = maKhaiQuat;
    }

    public String getNguoiKhaiQuat() {
        return nguoiKhaiQuat;
    }

    public ThongTinKhaiQuat nguoiKhaiQuat(String nguoiKhaiQuat) {
        this.nguoiKhaiQuat = nguoiKhaiQuat;
        return this;
    }

    public void setNguoiKhaiQuat(String nguoiKhaiQuat) {
        this.nguoiKhaiQuat = nguoiKhaiQuat;
    }

    public String getDonViKhaiQuat() {
        return donViKhaiQuat;
    }

    public ThongTinKhaiQuat donViKhaiQuat(String donViKhaiQuat) {
        this.donViKhaiQuat = donViKhaiQuat;
        return this;
    }

    public void setDonViKhaiQuat(String donViKhaiQuat) {
        this.donViKhaiQuat = donViKhaiQuat;
    }

    public Instant getThoiGianKhaiQuat() {
        return thoiGianKhaiQuat;
    }

    public ThongTinKhaiQuat thoiGianKhaiQuat(Instant thoiGianKhaiQuat) {
        this.thoiGianKhaiQuat = thoiGianKhaiQuat;
        return this;
    }

    public void setThoiGianKhaiQuat(Instant thoiGianKhaiQuat) {
        this.thoiGianKhaiQuat = thoiGianKhaiQuat;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ThongTinKhaiQuat isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<HaiCotLietSi> getKhaiQuatHaiCots() {
        return khaiQuatHaiCots;
    }

    public ThongTinKhaiQuat khaiQuatHaiCots(Set<HaiCotLietSi> haiCotLietSis) {
        this.khaiQuatHaiCots = haiCotLietSis;
        return this;
    }

    public ThongTinKhaiQuat addKhaiQuatHaiCot(HaiCotLietSi haiCotLietSi) {
        this.khaiQuatHaiCots.add(haiCotLietSi);
        haiCotLietSi.setThongTinKhaiQuat(this);
        return this;
    }

    public ThongTinKhaiQuat removeKhaiQuatHaiCot(HaiCotLietSi haiCotLietSi) {
        this.khaiQuatHaiCots.remove(haiCotLietSi);
        haiCotLietSi.setThongTinKhaiQuat(null);
        return this;
    }

    public void setKhaiQuatHaiCots(Set<HaiCotLietSi> haiCotLietSis) {
        this.khaiQuatHaiCots = haiCotLietSis;
    }

    public Set<DiVat> getKhaiQuatDiVats() {
        return khaiQuatDiVats;
    }

    public ThongTinKhaiQuat khaiQuatDiVats(Set<DiVat> diVats) {
        this.khaiQuatDiVats = diVats;
        return this;
    }

    public ThongTinKhaiQuat addKhaiQuatDiVat(DiVat diVat) {
        this.khaiQuatDiVats.add(diVat);
        diVat.setThongTinKhaiQuat(this);
        return this;
    }

    public ThongTinKhaiQuat removeKhaiQuatDiVat(DiVat diVat) {
        this.khaiQuatDiVats.remove(diVat);
        diVat.setThongTinKhaiQuat(null);
        return this;
    }

    public void setKhaiQuatDiVats(Set<DiVat> diVats) {
        this.khaiQuatDiVats = diVats;
    }

    public ThongTinMo getThongTinMo() {
        return thongTinMo;
    }

    public ThongTinKhaiQuat thongTinMo(ThongTinMo thongTinMo) {
        this.thongTinMo = thongTinMo;
        return this;
    }

    public void setThongTinMo(ThongTinMo thongTinMo) {
        this.thongTinMo = thongTinMo;
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
        ThongTinKhaiQuat thongTinKhaiQuat = (ThongTinKhaiQuat) o;
        if (thongTinKhaiQuat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongTinKhaiQuat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongTinKhaiQuat{" +
            "id=" + getId() +
            ", maKhaiQuat='" + getMaKhaiQuat() + "'" +
            ", nguoiKhaiQuat='" + getNguoiKhaiQuat() + "'" +
            ", donViKhaiQuat='" + getDonViKhaiQuat() + "'" +
            ", thoiGianKhaiQuat='" + getThoiGianKhaiQuat() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
