package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DonViThoiKy.
 */
@Entity
@Table(name = "don_vi_thoi_ky")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "donvithoiky")
public class DonViThoiKy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tu_nam")
    private Integer tuNam;

    @Column(name = "den_nam")
    private Integer denNam;

    @Column(name = "dia_diem_mo_ta")
    private String diaDiemMoTa;

    @Column(name = "dia_diem_xa")
    private String diaDiemXa;

    @Column(name = "dia_diem_huyen")
    private String diaDiemHuyen;

    @Column(name = "dia_diem_tinh")
    private String diaDiemTinh;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JsonIgnoreProperties("donViThoiKies")
    private DonVi donVi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTuNam() {
        return tuNam;
    }

    public DonViThoiKy tuNam(Integer tuNam) {
        this.tuNam = tuNam;
        return this;
    }

    public void setTuNam(Integer tuNam) {
        this.tuNam = tuNam;
    }

    public Integer getDenNam() {
        return denNam;
    }

    public DonViThoiKy denNam(Integer denNam) {
        this.denNam = denNam;
        return this;
    }

    public void setDenNam(Integer denNam) {
        this.denNam = denNam;
    }

    public String getDiaDiemMoTa() {
        return diaDiemMoTa;
    }

    public DonViThoiKy diaDiemMoTa(String diaDiemMoTa) {
        this.diaDiemMoTa = diaDiemMoTa;
        return this;
    }

    public void setDiaDiemMoTa(String diaDiemMoTa) {
        this.diaDiemMoTa = diaDiemMoTa;
    }

    public String getDiaDiemXa() {
        return diaDiemXa;
    }

    public DonViThoiKy diaDiemXa(String diaDiemXa) {
        this.diaDiemXa = diaDiemXa;
        return this;
    }

    public void setDiaDiemXa(String diaDiemXa) {
        this.diaDiemXa = diaDiemXa;
    }

    public String getDiaDiemHuyen() {
        return diaDiemHuyen;
    }

    public DonViThoiKy diaDiemHuyen(String diaDiemHuyen) {
        this.diaDiemHuyen = diaDiemHuyen;
        return this;
    }

    public void setDiaDiemHuyen(String diaDiemHuyen) {
        this.diaDiemHuyen = diaDiemHuyen;
    }

    public String getDiaDiemTinh() {
        return diaDiemTinh;
    }

    public DonViThoiKy diaDiemTinh(String diaDiemTinh) {
        this.diaDiemTinh = diaDiemTinh;
        return this;
    }

    public void setDiaDiemTinh(String diaDiemTinh) {
        this.diaDiemTinh = diaDiemTinh;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public DonViThoiKy isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DonViThoiKy ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public DonVi getDonVi() {
        return donVi;
    }

    public DonViThoiKy donVi(DonVi donVi) {
        this.donVi = donVi;
        return this;
    }

    public void setDonVi(DonVi donVi) {
        this.donVi = donVi;
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
        DonViThoiKy donViThoiKy = (DonViThoiKy) o;
        if (donViThoiKy.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donViThoiKy.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonViThoiKy{" +
            "id=" + getId() +
            ", tuNam=" + getTuNam() +
            ", denNam=" + getDenNam() +
            ", diaDiemMoTa='" + getDiaDiemMoTa() + "'" +
            ", diaDiemXa='" + getDiaDiemXa() + "'" +
            ", diaDiemHuyen='" + getDiaDiemHuyen() + "'" +
            ", diaDiemTinh='" + getDiaDiemTinh() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
