package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ThaoTac.
 */
@Entity
@Table(name = "thao_tac")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "thaotac")
public class ThaoTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mo_ta_ket_qua")
    private String moTaKetQua;

    @Column(name = "trang_thai_xu_ly")
    private Boolean trangThaiXuLy;

    @Column(name = "is_dang_thuc_hien")
    private Boolean isDangThucHien;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JsonIgnoreProperties("mauPhanTiches")
    private MauXetNghiem mauXetNghiem;

    @ManyToOne
    @JsonIgnoreProperties("phongLabPhanTiches")
    private PhongBan phongBan;

    @ManyToOne
    @JsonIgnoreProperties("loaiThaoTacs")
    private LoaiThaoTac loaiThaoTac;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoTaKetQua() {
        return moTaKetQua;
    }

    public ThaoTac moTaKetQua(String moTaKetQua) {
        this.moTaKetQua = moTaKetQua;
        return this;
    }

    public void setMoTaKetQua(String moTaKetQua) {
        this.moTaKetQua = moTaKetQua;
    }

    public Boolean isTrangThaiXuLy() {
        return trangThaiXuLy;
    }

    public ThaoTac trangThaiXuLy(Boolean trangThaiXuLy) {
        this.trangThaiXuLy = trangThaiXuLy;
        return this;
    }

    public void setTrangThaiXuLy(Boolean trangThaiXuLy) {
        this.trangThaiXuLy = trangThaiXuLy;
    }

    public Boolean isIsDangThucHien() {
        return isDangThucHien;
    }

    public ThaoTac isDangThucHien(Boolean isDangThucHien) {
        this.isDangThucHien = isDangThucHien;
        return this;
    }

    public void setIsDangThucHien(Boolean isDangThucHien) {
        this.isDangThucHien = isDangThucHien;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ThaoTac isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public MauXetNghiem getMauXetNghiem() {
        return mauXetNghiem;
    }

    public ThaoTac mauXetNghiem(MauXetNghiem mauXetNghiem) {
        this.mauXetNghiem = mauXetNghiem;
        return this;
    }

    public void setMauXetNghiem(MauXetNghiem mauXetNghiem) {
        this.mauXetNghiem = mauXetNghiem;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public ThaoTac phongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
        return this;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public LoaiThaoTac getLoaiThaoTac() {
        return loaiThaoTac;
    }

    public ThaoTac loaiThaoTac(LoaiThaoTac loaiThaoTac) {
        this.loaiThaoTac = loaiThaoTac;
        return this;
    }

    public void setLoaiThaoTac(LoaiThaoTac loaiThaoTac) {
        this.loaiThaoTac = loaiThaoTac;
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
        ThaoTac thaoTac = (ThaoTac) o;
        if (thaoTac.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thaoTac.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThaoTac{" +
            "id=" + getId() +
            ", moTaKetQua='" + getMoTaKetQua() + "'" +
            ", trangThaiXuLy='" + isTrangThaiXuLy() + "'" +
            ", isDangThucHien='" + isIsDangThucHien() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
