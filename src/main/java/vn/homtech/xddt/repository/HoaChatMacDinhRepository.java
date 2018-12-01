package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoaChatMacDinh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HoaChatMacDinh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoaChatMacDinhRepository extends JpaRepository<HoaChatMacDinh, Long> {

}
