package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.LoaiMauXetNghiem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LoaiMauXetNghiem entity.
 */
public interface LoaiMauXetNghiemSearchRepository extends ElasticsearchRepository<LoaiMauXetNghiem, Long> {
}
