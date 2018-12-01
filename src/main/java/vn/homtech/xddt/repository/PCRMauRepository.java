package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCRMau;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCRMau entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRMauRepository extends JpaRepository<PCRMau, Long> {

}
