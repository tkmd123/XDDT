package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.ThongTinKhaiQuat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThongTinKhaiQuat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThongTinKhaiQuatRepository extends JpaRepository<ThongTinKhaiQuat, Long> {

}
