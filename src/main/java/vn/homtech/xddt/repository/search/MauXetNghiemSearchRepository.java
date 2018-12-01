package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.MauXetNghiem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MauXetNghiem entity.
 */
public interface MauXetNghiemSearchRepository extends ElasticsearchRepository<MauXetNghiem, Long> {
}
