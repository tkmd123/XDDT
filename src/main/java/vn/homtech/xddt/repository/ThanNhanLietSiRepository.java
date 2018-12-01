package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.ThanNhanLietSi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ThanNhanLietSi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ThanNhanLietSiRepository extends JpaRepository<ThanNhanLietSi, Long> {

}
