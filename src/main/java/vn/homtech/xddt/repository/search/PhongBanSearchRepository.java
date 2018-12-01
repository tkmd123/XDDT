package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PhongBan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PhongBan entity.
 */
public interface PhongBanSearchRepository extends ElasticsearchRepository<PhongBan, Long> {
}
