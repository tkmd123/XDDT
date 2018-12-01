package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.MauXetNghiem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MauXetNghiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MauXetNghiemRepository extends JpaRepository<MauXetNghiem, Long> {

    @Query(value = "select distinct mau_xet_nghiem from MauXetNghiem mau_xet_nghiem left join fetch mau_xet_nghiem.mauTinhSaches",
        countQuery = "select count(distinct mau_xet_nghiem) from MauXetNghiem mau_xet_nghiem")
    Page<MauXetNghiem> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct mau_xet_nghiem from MauXetNghiem mau_xet_nghiem left join fetch mau_xet_nghiem.mauTinhSaches")
    List<MauXetNghiem> findAllWithEagerRelationships();

    @Query("select mau_xet_nghiem from MauXetNghiem mau_xet_nghiem left join fetch mau_xet_nghiem.mauTinhSaches where mau_xet_nghiem.id =:id")
    Optional<MauXetNghiem> findOneWithEagerRelationships(@Param("id") Long id);

}
