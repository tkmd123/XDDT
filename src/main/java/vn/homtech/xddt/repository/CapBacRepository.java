package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.CapBac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CapBac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CapBacRepository extends JpaRepository<CapBac, Long> {

}
