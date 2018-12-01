package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PhuongXa;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PhuongXa entity.
 */
public interface PhuongXaSearchRepository extends ElasticsearchRepository<PhuongXa, Long> {
}
