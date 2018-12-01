package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCRMoi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCRMoi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRMoiRepository extends JpaRepository<PCRMoi, Long> {

}
