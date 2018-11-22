package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.ThongTinADN;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ThongTinADN entity.
 */
public interface ThongTinADNSearchRepository extends ElasticsearchRepository<ThongTinADN, Long> {
}
