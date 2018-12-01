package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCRMoi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCRMoi entity.
 */
public interface PCRMoiSearchRepository extends ElasticsearchRepository<PCRMoi, Long> {
}
