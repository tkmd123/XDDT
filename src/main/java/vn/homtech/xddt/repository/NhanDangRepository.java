package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.NhanDang;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhanDang entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanDangRepository extends JpaRepository<NhanDang, Long> {

}
