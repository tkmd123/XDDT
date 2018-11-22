package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PhongBan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PhongBan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {

}
