package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.LoaiHinhThaiHaiCot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoaiHinhThaiHaiCot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiHinhThaiHaiCotRepository extends JpaRepository<LoaiHinhThaiHaiCot, Long> {

}
