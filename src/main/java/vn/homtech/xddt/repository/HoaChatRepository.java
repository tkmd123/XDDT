package vn.homtech.xddt.repository;

import vn.homtech.xddt.domain.HoaChat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HoaChat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HoaChatRepository extends JpaRepository<HoaChat, Long> {

}
