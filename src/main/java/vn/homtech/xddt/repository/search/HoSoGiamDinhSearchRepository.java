package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HoSoGiamDinh;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HoSoGiamDinh entity.
 */
public interface HoSoGiamDinhSearchRepository extends ElasticsearchRepository<HoSoGiamDinh, Long> {
}
