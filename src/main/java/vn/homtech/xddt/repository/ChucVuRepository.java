package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.ChucVu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ChucVu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {

}
