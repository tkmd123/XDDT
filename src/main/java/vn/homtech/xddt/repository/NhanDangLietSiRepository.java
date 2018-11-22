package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.NhanDangLietSi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhanDangLietSi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanDangLietSiRepository extends JpaRepository<NhanDangLietSi, Long> {

}
