package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.DonViThoiKy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DonViThoiKy entity.
 */
public interface DonViThoiKySearchRepository extends ElasticsearchRepository<DonViThoiKy, Long> {
}
