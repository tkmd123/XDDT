package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HoaChatTachChiet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HoaChatTachChiet entity.
 */
public interface HoaChatTachChietSearchRepository extends ElasticsearchRepository<HoaChatTachChiet, Long> {
}
