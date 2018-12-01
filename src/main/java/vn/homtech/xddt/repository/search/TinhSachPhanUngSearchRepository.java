package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.TinhSachPhanUng;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TinhSachPhanUng entity.
 */
public interface TinhSachPhanUngSearchRepository extends ElasticsearchRepository<TinhSachPhanUng, Long> {
}
