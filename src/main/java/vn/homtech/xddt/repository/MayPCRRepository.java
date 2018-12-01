package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.MayPCR;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MayPCR entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MayPCRRepository extends JpaRepository<MayPCR, Long> {

}
