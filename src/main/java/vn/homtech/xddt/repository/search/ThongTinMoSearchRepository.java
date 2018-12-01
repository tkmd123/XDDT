package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.ThongTinMo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ThongTinMo entity.
 */
public interface ThongTinMoSearchRepository extends ElasticsearchRepository<ThongTinMo, Long> {
}
