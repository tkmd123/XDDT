package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.ThanNhanLietSi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ThanNhanLietSi entity.
 */
public interface ThanNhanLietSiSearchRepository extends ElasticsearchRepository<ThanNhanLietSi, Long> {
}
