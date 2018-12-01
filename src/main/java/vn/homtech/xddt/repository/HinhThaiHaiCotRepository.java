package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HinhThaiHaiCot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HinhThaiHaiCot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HinhThaiHaiCotRepository extends JpaRepository<HinhThaiHaiCot, Long> {

}
