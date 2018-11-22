package vn.homtech.xddt.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of TinhThanhSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TinhThanhSearchRepositoryMockConfiguration {

    @MockBean
    private TinhThanhSearchRepository mockTinhThanhSearchRepository;

}
