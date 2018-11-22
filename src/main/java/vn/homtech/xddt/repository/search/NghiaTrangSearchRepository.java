package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.NghiaTrang;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NghiaTrang entity.
 */
public interface NghiaTrangSearchRepository extends ElasticsearchRepository<NghiaTrang, Long> {
}
