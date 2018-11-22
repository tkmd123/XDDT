package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.ChucVu;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ChucVu entity.
 */
public interface ChucVuSearchRepository extends ElasticsearchRepository<ChucVu, Long> {
}
