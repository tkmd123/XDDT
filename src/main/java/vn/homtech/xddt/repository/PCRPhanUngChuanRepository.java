package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCRPhanUngChuan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCRPhanUngChuan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRPhanUngChuanRepository extends JpaRepository<PCRPhanUngChuan, Long> {

}
