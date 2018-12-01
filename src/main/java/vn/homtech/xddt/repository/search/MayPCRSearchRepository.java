package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.MayPCR;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MayPCR entity.
 */
public interface MayPCRSearchRepository extends ElasticsearchRepository<MayPCR, Long> {
}
