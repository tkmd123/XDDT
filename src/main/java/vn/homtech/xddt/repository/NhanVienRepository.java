package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.NhanVien;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NhanVien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {

}
