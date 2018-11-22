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
 * A ThongTinMo.
 */
@Entity
@Table(name = "thong_tin_mo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "thongtinmo")
public class ThongTinMo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "khu_mo")
    private String khuMo;

    @Column(name = "lo_mo")
    private String loMo;

    @Column(name = "hang_mo")
    private Integer hangMo;

    @Column(name = "so_mo")
    private Integer soMo;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "thongTinMo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongTinKhaiQuat> moLietSis = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("nghiaTrangMos")
    private NghiaTrang nghiaTrang;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKhuMo() {
        return khuMo;
    }

    public ThongTinMo khuMo(String khuMo) {
        this.khuMo = khuMo;
        return this;
    }

    public void setKhuMo(String khuMo) {
        this.khuMo = khuMo;
    }

    public String getLoMo() {
        return loMo;
    }

    public ThongTinMo loMo(String loMo) {
        this.loMo = loMo;
        return this;
    }

    public void setLoMo(String loMo) {
        this.loMo = loMo;
    }

    public Integer getHangMo() {
        return hangMo;
    }

    public ThongTinMo hangMo(Integer hangMo) {
        this.hangMo = hangMo;
        return this;
    }

    public void setHangMo(Integer hangMo) {
        this.hangMo = hangMo;
    }

    public Integer getSoMo() {
        return soMo;
    }

    public ThongTinMo soMo(Integer soMo) {
        this.soMo = soMo;
        return this;
    }

    public void setSoMo(Integer soMo) {
        this.soMo = soMo;
    }

    public String getMoTa() {
        return moTa;
    }

    public ThongTinMo moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ThongTinMo isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<ThongTinKhaiQuat> getMoLietSis() {
        return moLietSis;
    }

    public ThongTinMo moLietSis(Set<ThongTinKhaiQuat> thongTinKhaiQuats) {
        this.moLietSis = thongTinKhaiQuats;
        return this;
    }

    public ThongTinMo addMoLietSi(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.moLietSis.add(thongTinKhaiQuat);
        thongTinKhaiQuat.setThongTinMo(this);
        return this;
    }

    public ThongTinMo removeMoLietSi(ThongTinKhaiQuat thongTinKhaiQuat) {
        this.moLietSis.remove(thongTinKhaiQuat);
        thongTinKhaiQuat.setThongTinMo(null);
        return this;
    }

    public void setMoLietSis(Set<ThongTinKhaiQuat> thongTinKhaiQuats) {
        this.moLietSis = thongTinKhaiQuats;
    }

    public NghiaTrang getNghiaTrang() {
        return nghiaTrang;
    }

    public ThongTinMo nghiaTrang(NghiaTrang nghiaTrang) {
        this.nghiaTrang = nghiaTrang;
        return this;
    }

    public void setNghiaTrang(NghiaTrang nghiaTrang) {
        this.nghiaTrang = nghiaTrang;
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
        ThongTinMo thongTinMo = (ThongTinMo) o;
        if (thongTinMo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongTinMo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongTinMo{" +
            "id=" + getId() +
            ", khuMo='" + getKhuMo() + "'" +
            ", loMo='" + getLoMo() + "'" +
            ", hangMo=" + getHangMo() +
            ", soMo=" + getSoMo() +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
