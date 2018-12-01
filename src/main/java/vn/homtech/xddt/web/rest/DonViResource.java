package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.DonVi;
import vn.homtech.xddt.repository.DonViRepository;
import vn.homtech.xddt.repository.search.DonViSearchRepository;
import vn.homtech.xddt.web.rest.errors.BadRequestAlertException;
import vn.homtech.xddt.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DonVi.
 */
@RestController
@RequestMapping("/api")
public class DonViResource {

    private final Logger log = LoggerFactory.getLogger(DonViResource.class);

    private static final String ENTITY_NAME = "donVi";

    private final DonViRepository donViRepository;

    private final DonViSearchRepository donViSearchRepository;

    public DonViResource(DonViRepository donViRepository, DonViSearchRepository donViSearchRepository) {
        this.donViRepository = donViRepository;
        this.donViSearchRepository = donViSearchRepository;
    }

    /**
     * POST  /don-vis : Create a new donVi.
     *
     * @param donVi the donVi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donVi, or with status 400 (Bad Request) if the donVi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/don-vis")
    @Timed
    public ResponseEntity<DonVi> createDonVi(@RequestBody DonVi donVi) throws URISyntaxException {
        log.debug("REST request to save DonVi : {}", donVi);
        if (donVi.getId() != null) {
            throw new BadRequestAlertException("A new donVi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonVi result = donViRepository.save(donVi);
        donViSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/don-vis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /don-vis : Updates an existing donVi.
     *
     * @param donVi the donVi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donVi,
     * or with status 400 (Bad Request) if the donVi is not valid,
     * or with status 500 (Internal Server Error) if the donVi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/don-vis")
    @Timed
    public ResponseEntity<DonVi> updateDonVi(@RequestBody DonVi donVi) throws URISyntaxException {
        log.debug("REST request to update DonVi : {}", donVi);
        if (donVi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonVi result = donViRepository.save(donVi);
        donViSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donVi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /don-vis : get all the donVis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of donVis in body
     */
    @GetMapping("/don-vis")
    @Timed
    public List<DonVi> getAllDonVis() {
        log.debug("REST request to get all DonVis");
        return donViRepository.findAll();
    }

    /**
     * GET  /don-vis/:id : get the "id" donVi.
     *
     * @param id the id of the donVi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donVi, or with status 404 (Not Found)
     */
    @GetMapping("/don-vis/{id}")
    @Timed
    public ResponseEntity<DonVi> getDonVi(@PathVariable Long id) {
        log.debug("REST request to get DonVi : {}", id);
        Optional<DonVi> donVi = donViRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(donVi);
    }

    /**
     * DELETE  /don-vis/:id : delete the "id" donVi.
     *
     * @param id the id of the donVi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/don-vis/{id}")
    @Timed
    public ResponseEntity<Void> deleteDonVi(@PathVariable Long id) {
        log.debug("REST request to delete DonVi : {}", id);

        donViRepository.deleteById(id);
        donViSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/don-vis?query=:query : search for the donVi corresponding
     * to the query.
     *
     * @param query the query of the donVi search
     * @return the result of the search
     */
    @GetMapping("/_search/don-vis")
    @Timed
    public List<DonVi> searchDonVis(@RequestParam String query) {
        log.debug("REST request to search DonVis for query {}", query);
        return StreamSupport
            .stream(donViSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
