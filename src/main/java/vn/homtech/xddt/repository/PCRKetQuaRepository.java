package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.PCRKetQua;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PCRKetQua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PCRKetQuaRepository extends JpaRepository<PCRKetQua, Long> {

}
