package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCRMau;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCRMau entity.
 */
public interface PCRMauSearchRepository extends ElasticsearchRepository<PCRMau, Long> {
}
