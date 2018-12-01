package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HaiCotLietSi;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HaiCotLietSi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HaiCotLietSiRepository extends JpaRepository<HaiCotLietSi, Long> {

}
