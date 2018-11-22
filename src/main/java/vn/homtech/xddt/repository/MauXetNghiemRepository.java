package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.MauXetNghiem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MauXetNghiem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MauXetNghiemRepository extends JpaRepository<MauXetNghiem, Long> {

}
