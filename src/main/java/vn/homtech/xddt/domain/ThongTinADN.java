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
 * A ThongTinADN.
 */
@Entity
@Table(name = "thong_tin_adn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "thongtinadn")
public class ThongTinADN implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "file_du_lieu")
    private String fileDuLieu;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "thongTinADN")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiemDotBien> thongTinADNDotBiens = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoTa() {
        return moTa;
    }

    public ThongTinADN moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getFileDuLieu() {
        return fileDuLieu;
    }

    public ThongTinADN fileDuLieu(String fileDuLieu) {
        this.fileDuLieu = fileDuLieu;
        return this;
    }

    public void setFileDuLieu(String fileDuLieu) {
        this.fileDuLieu = fileDuLieu;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public ThongTinADN isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<DiemDotBien> getThongTinADNDotBiens() {
        return thongTinADNDotBiens;
    }

    public ThongTinADN thongTinADNDotBiens(Set<DiemDotBien> diemDotBiens) {
        this.thongTinADNDotBiens = diemDotBiens;
        return this;
    }

    public ThongTinADN addThongTinADNDotBien(DiemDotBien diemDotBien) {
        this.thongTinADNDotBiens.add(diemDotBien);
        diemDotBien.setThongTinADN(this);
        return this;
    }

    public ThongTinADN removeThongTinADNDotBien(DiemDotBien diemDotBien) {
        this.thongTinADNDotBiens.remove(diemDotBien);
        diemDotBien.setThongTinADN(null);
        return this;
    }

    public void setThongTinADNDotBiens(Set<DiemDotBien> diemDotBiens) {
        this.thongTinADNDotBiens = diemDotBiens;
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
        ThongTinADN thongTinADN = (ThongTinADN) o;
        if (thongTinADN.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), thongTinADN.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ThongTinADN{" +
            "id=" + getId() +
            ", moTa='" + getMoTa() + "'" +
            ", fileDuLieu='" + getFileDuLieu() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
