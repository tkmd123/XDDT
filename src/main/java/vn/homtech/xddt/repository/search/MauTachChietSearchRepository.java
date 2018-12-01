package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.MauTachChiet;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MauTachChiet entity.
 */
public interface MauTachChietSearchRepository extends ElasticsearchRepository<MauTachChiet, Long> {
}
