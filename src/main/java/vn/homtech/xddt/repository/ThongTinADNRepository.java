package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.ThongTinADN;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongTinADN entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinADNRepository extends JpaRepository<ThongTinADN, Long> {

}
