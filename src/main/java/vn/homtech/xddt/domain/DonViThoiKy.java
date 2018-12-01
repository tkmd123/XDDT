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
public class DonViThoiKy extends AbstractAuditingEntity implements Serializable {

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

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

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

    public String getUdf1() {
        return udf1;
    }

    public DonViThoiKy udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public DonViThoiKy udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public DonViThoiKy udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
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
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
