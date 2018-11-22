package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoSoLietSi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HoSoLietSi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoSoLietSiRepository extends JpaRepository<HoSoLietSi, Long> {

}
