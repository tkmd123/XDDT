package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.HoaChat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the HoaChat entity.
 */
public interface HoaChatSearchRepository extends ElasticsearchRepository<HoaChat, Long> {
}
