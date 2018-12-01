package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoSoGiamDinh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the HoSoGiamDinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoSoGiamDinhRepository extends JpaRepository<HoSoGiamDinh, Long> {

    @Query(value = "select distinct ho_so_giam_dinh from HoSoGiamDinh ho_so_giam_dinh left join fetch ho_so_giam_dinh.giamDinhThanNhans left join fetch ho_so_giam_dinh.giamDinhLietSis",
        countQuery = "select count(distinct ho_so_giam_dinh) from HoSoGiamDinh ho_so_giam_dinh")
    Page<HoSoGiamDinh> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct ho_so_giam_dinh from HoSoGiamDinh ho_so_giam_dinh left join fetch ho_so_giam_dinh.giamDinhThanNhans left join fetch ho_so_giam_dinh.giamDinhLietSis")
    List<HoSoGiamDinh> findAllWithEagerRelationships();

    @Query("select ho_so_giam_dinh from HoSoGiamDinh ho_so_giam_dinh left join fetch ho_so_giam_dinh.giamDinhThanNhans left join fetch ho_so_giam_dinh.giamDinhLietSis where ho_so_giam_dinh.id =:id")
    Optional<HoSoGiamDinh> findOneWithEagerRelationships(@Param("id") Long id);

}
