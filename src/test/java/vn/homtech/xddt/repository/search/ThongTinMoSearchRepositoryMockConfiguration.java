package vn.homtech.xddt.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ThongTinMoSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ThongTinMoSearchRepositoryMockConfiguration {

    @MockBean
    private ThongTinMoSearchRepository mockThongTinMoSearchRepository;

}
