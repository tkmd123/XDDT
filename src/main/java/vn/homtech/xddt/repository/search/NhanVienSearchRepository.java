package vn.homtech.xddt.repository.search;

import vn.homtech.xddt.domain.NhanVien;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the NhanVien entity.
 */
public interface NhanVienSearchRepository extends ElasticsearchRepository<NhanVien, Long> {
}
