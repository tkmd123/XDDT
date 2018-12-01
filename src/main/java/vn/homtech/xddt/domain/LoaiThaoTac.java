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
 * A LoaiThaoTac.
 */
@Entity
@Table(name = "loai_thao_tac")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "loaithaotac")
public class LoaiThaoTac implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_loai_thao_tac")
    private String maLoaiThaoTac;

    @Column(name = "ten_loai_thao_tac")
    private String tenLoaiThaoTac;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "loaiThaoTac")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MauXetNghiem> thaoTacHienTais = new HashSet<>();
    @OneToMany(mappedBy = "loaiThaoTac")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ThaoTac> loaiThaoTacs = new HashSet<>();
    @OneToMany(mappedBy = "thaoTacTiepTheo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<LoaiThaoTac> thaoTacTiepTheos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("thaoTacTiepTheos")
    private LoaiThaoTac thaoTacTiepTheo;

    @ManyToOne
    @JsonIgnoreProperties("phongBanThaoTacs")
    private PhongBan phongBan;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaLoaiThaoTac() {
        return maLoaiThaoTac;
    }

    public LoaiThaoTac maLoaiThaoTac(String maLoaiThaoTac) {
        this.maLoaiThaoTac = maLoaiThaoTac;
        return this;
    }

    public void setMaLoaiThaoTac(String maLoaiThaoTac) {
        this.maLoaiThaoTac = maLoaiThaoTac;
    }

    public String getTenLoaiThaoTac() {
        return tenLoaiThaoTac;
    }

    public LoaiThaoTac tenLoaiThaoTac(String tenLoaiThaoTac) {
        this.tenLoaiThaoTac = tenLoaiThaoTac;
        return this;
    }

    public void setTenLoaiThaoTac(String tenLoaiThaoTac) {
        this.tenLoaiThaoTac = tenLoaiThaoTac;
    }

    public String getMoTa() {
        return moTa;
    }

    public LoaiThaoTac moTa(String moTa) {
        this.moTa = moTa;
        return this;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public LoaiThaoTac isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<MauXetNghiem> getThaoTacHienTais() {
        return thaoTacHienTais;
    }

    public LoaiThaoTac thaoTacHienTais(Set<MauXetNghiem> mauXetNghiems) {
        this.thaoTacHienTais = mauXetNghiems;
        return this;
    }

    public LoaiThaoTac addThaoTacHienTai(MauXetNghiem mauXetNghiem) {
        this.thaoTacHienTais.add(mauXetNghiem);
        mauXetNghiem.setLoaiThaoTac(this);
        return this;
    }

    public LoaiThaoTac removeThaoTacHienTai(MauXetNghiem mauXetNghiem) {
        this.thaoTacHienTais.remove(mauXetNghiem);
        mauXetNghiem.setLoaiThaoTac(null);
        return this;
    }

    public void setThaoTacHienTais(Set<MauXetNghiem> mauXetNghiems) {
        this.thaoTacHienTais = mauXetNghiems;
    }

    public Set<ThaoTac> getLoaiThaoTacs() {
        return loaiThaoTacs;
    }

    public LoaiThaoTac loaiThaoTacs(Set<ThaoTac> thaoTacs) {
        this.loaiThaoTacs = thaoTacs;
        return this;
    }

    public LoaiThaoTac addLoaiThaoTac(ThaoTac thaoTac) {
        this.loaiThaoTacs.add(thaoTac);
        thaoTac.setLoaiThaoTac(this);
        return this;
    }

    public LoaiThaoTac removeLoaiThaoTac(ThaoTac thaoTac) {
        this.loaiThaoTacs.remove(thaoTac);
        thaoTac.setLoaiThaoTac(null);
        return this;
    }

    public void setLoaiThaoTacs(Set<ThaoTac> thaoTacs) {
        this.loaiThaoTacs = thaoTacs;
    }

    public Set<LoaiThaoTac> getThaoTacTiepTheos() {
        return thaoTacTiepTheos;
    }

    public LoaiThaoTac thaoTacTiepTheos(Set<LoaiThaoTac> loaiThaoTacs) {
        this.thaoTacTiepTheos = loaiThaoTacs;
        return this;
    }

    public LoaiThaoTac addThaoTacTiepTheo(LoaiThaoTac loaiThaoTac) {
        this.thaoTacTiepTheos.add(loaiThaoTac);
        loaiThaoTac.setThaoTacTiepTheo(this);
        return this;
    }

    public LoaiThaoTac removeThaoTacTiepTheo(LoaiThaoTac loaiThaoTac) {
        this.thaoTacTiepTheos.remove(loaiThaoTac);
        loaiThaoTac.setThaoTacTiepTheo(null);
        return this;
    }

    public void setThaoTacTiepTheos(Set<LoaiThaoTac> loaiThaoTacs) {
        this.thaoTacTiepTheos = loaiThaoTacs;
    }

    public LoaiThaoTac getThaoTacTiepTheo() {
        return thaoTacTiepTheo;
    }

    public LoaiThaoTac thaoTacTiepTheo(LoaiThaoTac loaiThaoTac) {
        this.thaoTacTiepTheo = loaiThaoTac;
        return this;
    }

    public void setThaoTacTiepTheo(LoaiThaoTac loaiThaoTac) {
        this.thaoTacTiepTheo = loaiThaoTac;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public LoaiThaoTac phongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
        return this;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
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
        LoaiThaoTac loaiThaoTac = (LoaiThaoTac) o;
        if (loaiThaoTac.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loaiThaoTac.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoaiThaoTac{" +
            "id=" + getId() +
            ", maLoaiThaoTac='" + getMaLoaiThaoTac() + "'" +
            ", tenLoaiThaoTac='" + getTenLoaiThaoTac() + "'" +
            ", moTa='" + getMoTa() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
