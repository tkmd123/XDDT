package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.NhanDang;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NhanDang entity.
 */
public interface NhanDangSearchRepository extends ElasticsearchRepository<NhanDang, Long> {
}
