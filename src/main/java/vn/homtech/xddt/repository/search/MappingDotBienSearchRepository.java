package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.MappingDotBien;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MappingDotBien entity.
 */
public interface MappingDotBienSearchRepository extends ElasticsearchRepository<MappingDotBien, Long> {
}
