package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HaiCotLietSi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HaiCotLietSi entity.
 */
public interface HaiCotLietSiSearchRepository extends ElasticsearchRepository<HaiCotLietSi, Long> {
}
