package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.DonViThoiKy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DonViThoiKy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonViThoiKyRepository extends JpaRepository<DonViThoiKy, Long> {

}
