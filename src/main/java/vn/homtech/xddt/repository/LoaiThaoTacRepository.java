package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.LoaiThaoTac;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoaiThaoTac entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiThaoTacRepository extends JpaRepository<LoaiThaoTac, Long> {

}
