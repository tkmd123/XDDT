package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.ThongTinKhaiQuat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ThongTinKhaiQuat entity.
 */
public interface ThongTinKhaiQuatSearchRepository extends ElasticsearchRepository<ThongTinKhaiQuat, Long> {
}
