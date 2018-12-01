package vn.homtech.xddt.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PhongBanSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PhongBanSearchRepositoryMockConfiguration {

    @MockBean
    private PhongBanSearchRepository mockPhongBanSearchRepository;

}
