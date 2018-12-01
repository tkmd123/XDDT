package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.TinhSach;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TinhSach entity.
 */
public interface TinhSachSearchRepository extends ElasticsearchRepository<TinhSach, Long> {
}
