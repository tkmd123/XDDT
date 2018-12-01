package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.PCRPhanUngChuan;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the PCRPhanUngChuan entity.
 */
public interface PCRPhanUngChuanSearchRepository extends ElasticsearchRepository<PCRPhanUngChuan, Long> {
}
