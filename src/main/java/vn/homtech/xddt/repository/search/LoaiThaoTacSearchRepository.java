package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.LoaiThaoTac;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LoaiThaoTac entity.
 */
public interface LoaiThaoTacSearchRepository extends ElasticsearchRepository<LoaiThaoTac, Long> {
}
