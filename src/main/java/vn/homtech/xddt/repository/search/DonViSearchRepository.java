package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.DonVi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DonVi entity.
 */
public interface DonViSearchRepository extends ElasticsearchRepository<DonVi, Long> {
}
