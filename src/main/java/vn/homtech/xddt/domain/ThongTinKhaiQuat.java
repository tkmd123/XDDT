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
public class ThongTinKhaiQuat extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "co_hai_cot")
    private Boolean coHaiCot;

    @Column(name = "so_luong_hai_cot")
    private Integer soLuongHaiCot;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

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

    public Boolean isCoHaiCot() {
        return coHaiCot;
    }

    public ThongTinKhaiQuat coHaiCot(Boolean coHaiCot) {
        this.coHaiCot = coHaiCot;
        return this;
    }

    public void setCoHaiCot(Boolean coHaiCot) {
        this.coHaiCot = coHaiCot;
    }

    public Integer getSoLuongHaiCot() {
        return soLuongHaiCot;
    }

    public ThongTinKhaiQuat soLuongHaiCot(Integer soLuongHaiCot) {
        this.soLuongHaiCot = soLuongHaiCot;
        return this;
    }

    public void setSoLuongHaiCot(Integer soLuongHaiCot) {
        this.soLuongHaiCot = soLuongHaiCot;
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

    public String getUdf1() {
        return udf1;
    }

    public ThongTinKhaiQuat udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public ThongTinKhaiQuat udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public ThongTinKhaiQuat udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
            ", coHaiCot='" + isCoHaiCot() + "'" +
            ", soLuongHaiCot=" + getSoLuongHaiCot() +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
