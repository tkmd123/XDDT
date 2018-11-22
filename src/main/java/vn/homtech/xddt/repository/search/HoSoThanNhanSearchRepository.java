package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HoSoThanNhan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HoSoThanNhan entity.
 */
public interface HoSoThanNhanSearchRepository extends ElasticsearchRepository<HoSoThanNhan, Long> {
}
