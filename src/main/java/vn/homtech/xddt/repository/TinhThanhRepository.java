package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.TinhThanh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TinhThanh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TinhThanhRepository extends JpaRepository<TinhThanh, Long> {

}
