package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.QuanHeThanNhan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the QuanHeThanNhan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuanHeThanNhanRepository extends JpaRepository<QuanHeThanNhan, Long> {

}
