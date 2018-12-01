package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.TrungTam;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TrungTam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrungTamRepository extends JpaRepository<TrungTam, Long> {

}
