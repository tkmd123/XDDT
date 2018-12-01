package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.MappingDotBien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MappingDotBien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MappingDotBienRepository extends JpaRepository<MappingDotBien, Long> {

}
