package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoaChatTachChiet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HoaChatTachChiet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoaChatTachChietRepository extends JpaRepository<HoaChatTachChiet, Long> {

}
