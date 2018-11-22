package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.NhanDangLietSi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NhanDangLietSi entity.
 */
public interface NhanDangLietSiSearchRepository extends ElasticsearchRepository<NhanDangLietSi, Long> {
}
