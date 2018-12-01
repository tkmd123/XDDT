package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.TachChiet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TachChiet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TachChietRepository extends JpaRepository<TachChiet, Long> {

}
