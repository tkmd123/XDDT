package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.Objects;

/**
 * A HoaChatTachChiet.
 */
@Entity
@Table(name = "hoa_chat_tach_chiet")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hoachattachchiet")
public class HoaChatTachChiet extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "so_luong")
    private Float soLuong;

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
    @JsonIgnoreProperties("hoaChatTachChiets")
    private HoaChat hoaChat;

    @ManyToOne
    @JsonIgnoreProperties("tachChietHoaChats")
    private TachChiet tachChiet;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSoLuong() {
        return soLuong;
    }

    public HoaChatTachChiet soLuong(Float soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Float soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public HoaChatTachChiet moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HoaChatTachChiet isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public HoaChatTachChiet udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HoaChatTachChiet udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HoaChatTachChiet udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public HoaChat getHoaChat() {
        return hoaChat;
    }

    public HoaChatTachChiet hoaChat(HoaChat hoaChat) {
        this.hoaChat = hoaChat;
        return this;
    }

    public void setHoaChat(HoaChat hoaChat) {
        this.hoaChat = hoaChat;
    }

    public TachChiet getTachChiet() {
        return tachChiet;
    }

    public HoaChatTachChiet tachChiet(TachChiet tachChiet) {
        this.tachChiet = tachChiet;
        return this;
    }

    public void setTachChiet(TachChiet tachChiet) {
        this.tachChiet = tachChiet;
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
        HoaChatTachChiet hoaChatTachChiet = (HoaChatTachChiet) o;
        if (hoaChatTachChiet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoaChatTachChiet.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoaChatTachChiet{" +
            "id=" + getId() +
            ", soLuong=" + getSoLuong() +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
