package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCRKetQua;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCRKetQua entity.
 */
public interface PCRKetQuaSearchRepository extends ElasticsearchRepository<PCRKetQua, Long> {
}
