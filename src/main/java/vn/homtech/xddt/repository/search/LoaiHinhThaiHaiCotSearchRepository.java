package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.LoaiHinhThaiHaiCot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LoaiHinhThaiHaiCot entity.
 */
public interface LoaiHinhThaiHaiCotSearchRepository extends ElasticsearchRepository<LoaiHinhThaiHaiCot, Long> {
}
