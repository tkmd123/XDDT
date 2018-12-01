package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HoaChatMacDinh;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HoaChatMacDinh entity.
 */
public interface HoaChatMacDinhSearchRepository extends ElasticsearchRepository<HoaChatMacDinh, Long> {
}
