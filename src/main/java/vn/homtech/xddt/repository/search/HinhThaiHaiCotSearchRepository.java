package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HinhThaiHaiCot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HinhThaiHaiCot entity.
 */
public interface HinhThaiHaiCotSearchRepository extends ElasticsearchRepository<HinhThaiHaiCot, Long> {
}
