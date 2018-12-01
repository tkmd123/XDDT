package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.LoaiDiVat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LoaiDiVat entity.
 */
public interface LoaiDiVatSearchRepository extends ElasticsearchRepository<LoaiDiVat, Long> {
}
