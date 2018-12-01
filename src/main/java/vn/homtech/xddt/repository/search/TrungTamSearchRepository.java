package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.TrungTam;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TrungTam entity.
 */
public interface TrungTamSearchRepository extends ElasticsearchRepository<TrungTam, Long> {
}
