package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.NghiaTrang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NghiaTrang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NghiaTrangRepository extends JpaRepository<NghiaTrang, Long> {

}
