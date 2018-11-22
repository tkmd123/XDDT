package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.DiVat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DiVat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiVatRepository extends JpaRepository<DiVat, Long> {

}
