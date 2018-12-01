package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TinhSachPhanUng.
 */
@Entity
@Table(name = "tinh_sach_phan_ung")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tinhsachphanung")
public class TinhSachPhanUng implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dung_tich_su_dung")
    private Float dungTichSuDung;

    @Column(name = "chu_trinh_nhiet_do")
    private String chuTrinhNhietDo;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "udf_1")
    private String udf1;

    @Column(name = "udf_2")
    private String udf2;

    @Column(name = "udf_3")
    private String udf3;

    @ManyToOne
    @JsonIgnoreProperties("tinhSaches")
    private TinhSach tinhSach;

    @ManyToOne
    @JsonIgnoreProperties("hoaChatTinhSaches")
    private HoaChat hoaChatTinhSach;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getDungTichSuDung() {
        return dungTichSuDung;
    }

    public TinhSachPhanUng dungTichSuDung(Float dungTichSuDung) {
        this.dungTichSuDung = dungTichSuDung;
        return this;
    }

    public void setDungTichSuDung(Float dungTichSuDung) {
        this.dungTichSuDung = dungTichSuDung;
    }

    public String getChuTrinhNhietDo() {
        return chuTrinhNhietDo;
    }

    public TinhSachPhanUng chuTrinhNhietDo(String chuTrinhNhietDo) {
        this.chuTrinhNhietDo = chuTrinhNhietDo;
        return this;
    }

    public void setChuTrinhNhietDo(String chuTrinhNhietDo) {
        this.chuTrinhNhietDo = chuTrinhNhietDo;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public TinhSachPhanUng isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public TinhSachPhanUng udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public TinhSachPhanUng udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public TinhSachPhanUng udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public TinhSach getTinhSach() {
        return tinhSach;
    }

    public TinhSachPhanUng tinhSach(TinhSach tinhSach) {
        this.tinhSach = tinhSach;
        return this;
    }

    public void setTinhSach(TinhSach tinhSach) {
        this.tinhSach = tinhSach;
    }

    public HoaChat getHoaChatTinhSach() {
        return hoaChatTinhSach;
    }

    public TinhSachPhanUng hoaChatTinhSach(HoaChat hoaChat) {
        this.hoaChatTinhSach = hoaChat;
        return this;
    }

    public void setHoaChatTinhSach(HoaChat hoaChat) {
        this.hoaChatTinhSach = hoaChat;
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
        TinhSachPhanUng tinhSachPhanUng = (TinhSachPhanUng) o;
        if (tinhSachPhanUng.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tinhSachPhanUng.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TinhSachPhanUng{" +
            "id=" + getId() +
            ", dungTichSuDung=" + getDungTichSuDung() +
            ", chuTrinhNhietDo='" + getChuTrinhNhietDo() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
