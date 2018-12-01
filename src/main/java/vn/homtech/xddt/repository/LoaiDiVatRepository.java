package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.LoaiDiVat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LoaiDiVat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoaiDiVatRepository extends JpaRepository<LoaiDiVat, Long> {

}
