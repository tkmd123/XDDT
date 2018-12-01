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
 * A NghiaTrang.
 */
@Entity
@Table(name = "nghia_trang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "nghiatrang")
public class NghiaTrang extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_nghia_trang")
    private String maNghiaTrang;

    @Column(name = "ten_nghia_trang")
    private String tenNghiaTrang;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "nguoi_lien_he")
    private String nguoiLienHe;

    @Column(name = "dien_thoai")
    private String dienThoai;

    @Column(name = "email")
    private String email;

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

    @OneToMany(mappedBy = "nghiaTrang")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThongTinMo> nghiaTrangMos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("phuongXaNghiaTrangs")
    private PhuongXa phuongXa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNghiaTrang() {
        return maNghiaTrang;
    }

    public NghiaTrang maNghiaTrang(String maNghiaTrang) {
        this.maNghiaTrang = maNghiaTrang;
        return this;
    }

    public void setMaNghiaTrang(String maNghiaTrang) {
        this.maNghiaTrang = maNghiaTrang;
    }

    public String getTenNghiaTrang() {
        return tenNghiaTrang;
    }

    public NghiaTrang tenNghiaTrang(String tenNghiaTrang) {
        this.tenNghiaTrang = tenNghiaTrang;
        return this;
    }

    public void setTenNghiaTrang(String tenNghiaTrang) {
        this.tenNghiaTrang = tenNghiaTrang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public NghiaTrang diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNguoiLienHe() {
        return nguoiLienHe;
    }

    public NghiaTrang nguoiLienHe(String nguoiLienHe) {
        this.nguoiLienHe = nguoiLienHe;
        return this;
    }

    public void setNguoiLienHe(String nguoiLienHe) {
        this.nguoiLienHe = nguoiLienHe;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public NghiaTrang dienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
        return this;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public NghiaTrang email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMoTa() {
        return moTa;
    }

    public NghiaTrang moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public NghiaTrang isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public NghiaTrang udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public NghiaTrang udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public NghiaTrang udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<ThongTinMo> getNghiaTrangMos() {
        return nghiaTrangMos;
    }

    public NghiaTrang nghiaTrangMos(Set<ThongTinMo> thongTinMos) {
        this.nghiaTrangMos = thongTinMos;
        return this;
    }

    public NghiaTrang addNghiaTrangMo(ThongTinMo thongTinMo) {
        this.nghiaTrangMos.add(thongTinMo);
        thongTinMo.setNghiaTrang(this);
        return this;
    }

    public NghiaTrang removeNghiaTrangMo(ThongTinMo thongTinMo) {
        this.nghiaTrangMos.remove(thongTinMo);
        thongTinMo.setNghiaTrang(null);
        return this;
    }

    public void setNghiaTrangMos(Set<ThongTinMo> thongTinMos) {
        this.nghiaTrangMos = thongTinMos;
    }

    public PhuongXa getPhuongXa() {
        return phuongXa;
    }

    public NghiaTrang phuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
        return this;
    }

    public void setPhuongXa(PhuongXa phuongXa) {
        this.phuongXa = phuongXa;
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
        NghiaTrang nghiaTrang = (NghiaTrang) o;
        if (nghiaTrang.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nghiaTrang.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NghiaTrang{" +
            "id=" + getId() +
            ", maNghiaTrang='" + getMaNghiaTrang() + "'" +
            ", tenNghiaTrang='" + getTenNghiaTrang() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", nguoiLienHe='" + getNguoiLienHe() + "'" +
            ", dienThoai='" + getDienThoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
