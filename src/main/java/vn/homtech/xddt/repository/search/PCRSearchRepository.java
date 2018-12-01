package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCR;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCR entity.
 */
public interface PCRSearchRepository extends ElasticsearchRepository<PCR, Long> {
}
