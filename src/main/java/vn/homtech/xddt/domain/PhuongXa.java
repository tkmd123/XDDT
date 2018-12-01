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
 * A PhuongXa.
 */
@Entity
@Table(name = "phuong_xa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "phuongxa")
public class PhuongXa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_xa")
    private String maXa;

    @Column(name = "ten_xa")
    private String tenXa;

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

    @ManyToOne
    @JsonIgnoreProperties("quanHuyenPhuongXas")
    private QuanHuyen quanHuyen;

    @OneToMany(mappedBy = "phuongXa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NghiaTrang> phuongXaNghiaTrangs = new HashSet<>();
    @OneToMany(mappedBy = "phuongXa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoLietSi> phuongXaLietSis = new HashSet<>();
    @OneToMany(mappedBy = "phuongXa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoSoThanNhan> phuongXaThanNhans = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaXa() {
        return maXa;
    }

    public PhuongXa maXa(String maXa) {
        this.maXa = maXa;
        return this;
    }

    public void setMaXa(String maXa) {
        this.maXa = maXa;
    }

    public String getTenXa() {
        return tenXa;
    }

    public PhuongXa tenXa(String tenXa) {
        this.tenXa = tenXa;
        return this;
    }

    public void setTenXa(String tenXa) {
        this.tenXa = tenXa;
    }

    public String getMoTa() {
        return moTa;
    }

    public PhuongXa moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public PhuongXa isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public PhuongXa udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public PhuongXa udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public PhuongXa udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public QuanHuyen getQuanHuyen() {
        return quanHuyen;
    }

    public PhuongXa quanHuyen(QuanHuyen quanHuyen) {
        this.quanHuyen = quanHuyen;
        return this;
    }

    public void setQuanHuyen(QuanHuyen quanHuyen) {
        this.quanHuyen = quanHuyen;
    }

    public Set<NghiaTrang> getPhuongXaNghiaTrangs() {
        return phuongXaNghiaTrangs;
    }

    public PhuongXa phuongXaNghiaTrangs(Set<NghiaTrang> nghiaTrangs) {
        this.phuongXaNghiaTrangs = nghiaTrangs;
        return this;
    }

    public PhuongXa addPhuongXaNghiaTrang(NghiaTrang nghiaTrang) {
        this.phuongXaNghiaTrangs.add(nghiaTrang);
        nghiaTrang.setPhuongXa(this);
        return this;
    }

    public PhuongXa removePhuongXaNghiaTrang(NghiaTrang nghiaTrang) {
        this.phuongXaNghiaTrangs.remove(nghiaTrang);
        nghiaTrang.setPhuongXa(null);
        return this;
    }

    public void setPhuongXaNghiaTrangs(Set<NghiaTrang> nghiaTrangs) {
        this.phuongXaNghiaTrangs = nghiaTrangs;
    }

    public Set<HoSoLietSi> getPhuongXaLietSis() {
        return phuongXaLietSis;
    }

    public PhuongXa phuongXaLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.phuongXaLietSis = hoSoLietSis;
        return this;
    }

    public PhuongXa addPhuongXaLietSi(HoSoLietSi hoSoLietSi) {
        this.phuongXaLietSis.add(hoSoLietSi);
        hoSoLietSi.setPhuongXa(this);
        return this;
    }

    public PhuongXa removePhuongXaLietSi(HoSoLietSi hoSoLietSi) {
        this.phuongXaLietSis.remove(hoSoLietSi);
        hoSoLietSi.setPhuongXa(null);
        return this;
    }

    public void setPhuongXaLietSis(Set<HoSoLietSi> hoSoLietSis) {
        this.phuongXaLietSis = hoSoLietSis;
    }

    public Set<HoSoThanNhan> getPhuongXaThanNhans() {
        return phuongXaThanNhans;
    }

    public PhuongXa phuongXaThanNhans(Set<HoSoThanNhan> hoSoThanNhans) {
        this.phuongXaThanNhans = hoSoThanNhans;
        return this;
    }

    public PhuongXa addPhuongXaThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.phuongXaThanNhans.add(hoSoThanNhan);
        hoSoThanNhan.setPhuongXa(this);
        return this;
    }

    public PhuongXa removePhuongXaThanNhan(HoSoThanNhan hoSoThanNhan) {
        this.phuongXaThanNhans.remove(hoSoThanNhan);
        hoSoThanNhan.setPhuongXa(null);
        return this;
    }

    public void setPhuongXaThanNhans(Set<HoSoThanNhan> hoSoThanNhans) {
        this.phuongXaThanNhans = hoSoThanNhans;
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
        PhuongXa phuongXa = (PhuongXa) o;
        if (phuongXa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phuongXa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhuongXa{" +
            "id=" + getId() +
            ", maXa='" + getMaXa() + "'" +
            ", tenXa='" + getTenXa() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
