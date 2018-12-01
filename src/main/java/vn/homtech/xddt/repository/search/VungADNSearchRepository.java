package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.VungADN;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the VungADN entity.
 */
public interface VungADNSearchRepository extends ElasticsearchRepository<VungADN, Long> {
}
