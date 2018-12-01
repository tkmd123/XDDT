package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCR;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCR entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRRepository extends JpaRepository<PCR, Long> {

}
