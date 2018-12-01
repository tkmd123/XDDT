package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCRPhanUng;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCRPhanUng entity.
 */
public interface PCRPhanUngSearchRepository extends ElasticsearchRepository<PCRPhanUng, Long> {
}
