package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoSoThanNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HoSoThanNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoSoThanNhanRepository extends JpaRepository<HoSoThanNhan, Long> {

}
