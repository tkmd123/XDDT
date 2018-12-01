package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCRPhanUng;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCRPhanUng entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRPhanUngRepository extends JpaRepository<PCRPhanUng, Long> {

}
