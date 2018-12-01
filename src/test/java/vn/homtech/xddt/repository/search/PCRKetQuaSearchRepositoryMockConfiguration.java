package vn.homtech.xddt.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of PCRKetQuaSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PCRKetQuaSearchRepositoryMockConfiguration {

    @MockBean
    private PCRKetQuaSearchRepository mockPCRKetQuaSearchRepository;

}
