package vn.homtech.xddt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.Document;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A HoaChat.
 */
@Entity
@Table(name = "hoa_chat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hoachat")
public class HoaChat extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoa_chat")
    private String maHoaChat;

    @Column(name = "ten_hoa_chat")
    private String tenHoaChat;

    @Column(name = "hang_hoa_chat")
    private String hangHoaChat;

    @Column(name = "don_vi_tinh")
    private String donViTinh;

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

    @OneToMany(mappedBy = "hoaChatTinhSach")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TinhSachPhanUng> hoaChatTinhSaches = new HashSet<>();
    @OneToMany(mappedBy = "hoaChatPhanUng")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PCRPhanUng> hoaChatPhanUngs = new HashSet<>();
    @OneToMany(mappedBy = "hoaChat")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<HoaChatTachChiet> hoaChatTachChiets = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaHoaChat() {
        return maHoaChat;
    }

    public HoaChat maHoaChat(String maHoaChat) {
        this.maHoaChat = maHoaChat;
        return this;
    }

    public void setMaHoaChat(String maHoaChat) {
        this.maHoaChat = maHoaChat;
    }

    public String getTenHoaChat() {
        return tenHoaChat;
    }

    public HoaChat tenHoaChat(String tenHoaChat) {
        this.tenHoaChat = tenHoaChat;
        return this;
    }

    public void setTenHoaChat(String tenHoaChat) {
        this.tenHoaChat = tenHoaChat;
    }

    public String getHangHoaChat() {
        return hangHoaChat;
    }

    public HoaChat hangHoaChat(String hangHoaChat) {
        this.hangHoaChat = hangHoaChat;
        return this;
    }

    public void setHangHoaChat(String hangHoaChat) {
        this.hangHoaChat = hangHoaChat;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public HoaChat donViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
        return this;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public HoaChat moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public HoaChat isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUdf1() {
        return udf1;
    }

    public HoaChat udf1(String udf1) {
        this.udf1 = udf1;
        return this;
    }

    public void setUdf1(String udf1) {
        this.udf1 = udf1;
    }

    public String getUdf2() {
        return udf2;
    }

    public HoaChat udf2(String udf2) {
        this.udf2 = udf2;
        return this;
    }

    public void setUdf2(String udf2) {
        this.udf2 = udf2;
    }

    public String getUdf3() {
        return udf3;
    }

    public HoaChat udf3(String udf3) {
        this.udf3 = udf3;
        return this;
    }

    public void setUdf3(String udf3) {
        this.udf3 = udf3;
    }

    public Set<TinhSachPhanUng> getHoaChatTinhSaches() {
        return hoaChatTinhSaches;
    }

    public HoaChat hoaChatTinhSaches(Set<TinhSachPhanUng> tinhSachPhanUngs) {
        this.hoaChatTinhSaches = tinhSachPhanUngs;
        return this;
    }

    public HoaChat addHoaChatTinhSach(TinhSachPhanUng tinhSachPhanUng) {
        this.hoaChatTinhSaches.add(tinhSachPhanUng);
        tinhSachPhanUng.setHoaChatTinhSach(this);
        return this;
    }

    public HoaChat removeHoaChatTinhSach(TinhSachPhanUng tinhSachPhanUng) {
        this.hoaChatTinhSaches.remove(tinhSachPhanUng);
        tinhSachPhanUng.setHoaChatTinhSach(null);
        return this;
    }

    public void setHoaChatTinhSaches(Set<TinhSachPhanUng> tinhSachPhanUngs) {
        this.hoaChatTinhSaches = tinhSachPhanUngs;
    }

    public Set<PCRPhanUng> getHoaChatPhanUngs() {
        return hoaChatPhanUngs;
    }

    public HoaChat hoaChatPhanUngs(Set<PCRPhanUng> pCRPhanUngs) {
        this.hoaChatPhanUngs = pCRPhanUngs;
        return this;
    }

    public HoaChat addHoaChatPhanUng(PCRPhanUng pCRPhanUng) {
        this.hoaChatPhanUngs.add(pCRPhanUng);
        pCRPhanUng.setHoaChatPhanUng(this);
        return this;
    }

    public HoaChat removeHoaChatPhanUng(PCRPhanUng pCRPhanUng) {
        this.hoaChatPhanUngs.remove(pCRPhanUng);
        pCRPhanUng.setHoaChatPhanUng(null);
        return this;
    }

    public void setHoaChatPhanUngs(Set<PCRPhanUng> pCRPhanUngs) {
        this.hoaChatPhanUngs = pCRPhanUngs;
    }

    public Set<HoaChatTachChiet> getHoaChatTachChiets() {
        return hoaChatTachChiets;
    }

    public HoaChat hoaChatTachChiets(Set<HoaChatTachChiet> hoaChatTachChiets) {
        this.hoaChatTachChiets = hoaChatTachChiets;
        return this;
    }

    public HoaChat addHoaChatTachChiet(HoaChatTachChiet hoaChatTachChiet) {
        this.hoaChatTachChiets.add(hoaChatTachChiet);
        hoaChatTachChiet.setHoaChat(this);
        return this;
    }

    public HoaChat removeHoaChatTachChiet(HoaChatTachChiet hoaChatTachChiet) {
        this.hoaChatTachChiets.remove(hoaChatTachChiet);
        hoaChatTachChiet.setHoaChat(null);
        return this;
    }

    public void setHoaChatTachChiets(Set<HoaChatTachChiet> hoaChatTachChiets) {
        this.hoaChatTachChiets = hoaChatTachChiets;
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
        HoaChat hoaChat = (HoaChat) o;
        if (hoaChat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hoaChat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HoaChat{" +
            "id=" + getId() +
            ", maHoaChat='" + getMaHoaChat() + "'" +
            ", tenHoaChat='" + getTenHoaChat() + "'" +
            ", hangHoaChat='" + getHangHoaChat() + "'" +
            ", donViTinh='" + getDonViTinh() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            ", udf1='" + getUdf1() + "'" +
            ", udf2='" + getUdf2() + "'" +
            ", udf3='" + getUdf3() + "'" +
            "}";
    }
}
