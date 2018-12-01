package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.ThongTinMo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongTinMo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinMoRepository extends JpaRepository<ThongTinMo, Long> {

}
