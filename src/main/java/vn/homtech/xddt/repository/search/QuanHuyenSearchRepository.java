package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.QuanHuyen;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the QuanHuyen entity.
 */
public interface QuanHuyenSearchRepository extends ElasticsearchRepository<QuanHuyen, Long> {
}
