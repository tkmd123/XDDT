package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.CapBac;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CapBac entity.
 */
public interface CapBacSearchRepository extends ElasticsearchRepository<CapBac, Long> {
}
