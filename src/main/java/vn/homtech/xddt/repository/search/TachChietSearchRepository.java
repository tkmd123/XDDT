package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.TachChiet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TachChiet entity.
 */
public interface TachChietSearchRepository extends ElasticsearchRepository<TachChiet, Long> {
}
