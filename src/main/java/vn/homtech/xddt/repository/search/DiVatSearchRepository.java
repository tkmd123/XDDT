package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.DiVat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DiVat entity.
 */
public interface DiVatSearchRepository extends ElasticsearchRepository<DiVat, Long> {
}
