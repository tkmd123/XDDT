package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCRPhanUng;
import vn.homtech.xddt.repository.PCRPhanUngRepository;
import vn.homtech.xddt.repository.search.PCRPhanUngSearchRepository;
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
 * REST controller for managing PCRPhanUng.
 */
@RestController
@RequestMapping("/api")
public class PCRPhanUngResource {

    private final Logger log = LoggerFactory.getLogger(PCRPhanUngResource.class);

    private static final String ENTITY_NAME = "pCRPhanUng";

    private final PCRPhanUngRepository pCRPhanUngRepository;

    private final PCRPhanUngSearchRepository pCRPhanUngSearchRepository;

    public PCRPhanUngResource(PCRPhanUngRepository pCRPhanUngRepository, PCRPhanUngSearchRepository pCRPhanUngSearchRepository) {
        this.pCRPhanUngRepository = pCRPhanUngRepository;
        this.pCRPhanUngSearchRepository = pCRPhanUngSearchRepository;
    }

    /**
     * POST  /pcr-phan-ungs : Create a new pCRPhanUng.
     *
     * @param pCRPhanUng the pCRPhanUng to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCRPhanUng, or with status 400 (Bad Request) if the pCRPhanUng has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcr-phan-ungs")
    @Timed
    public ResponseEntity<PCRPhanUng> createPCRPhanUng(@RequestBody PCRPhanUng pCRPhanUng) throws URISyntaxException {
        log.debug("REST request to save PCRPhanUng : {}", pCRPhanUng);
        if (pCRPhanUng.getId() != null) {
            throw new BadRequestAlertException("A new pCRPhanUng cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCRPhanUng result = pCRPhanUngRepository.save(pCRPhanUng);
        pCRPhanUngSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcr-phan-ungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcr-phan-ungs : Updates an existing pCRPhanUng.
     *
     * @param pCRPhanUng the pCRPhanUng to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCRPhanUng,
     * or with status 400 (Bad Request) if the pCRPhanUng is not valid,
     * or with status 500 (Internal Server Error) if the pCRPhanUng couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcr-phan-ungs")
    @Timed
    public ResponseEntity<PCRPhanUng> updatePCRPhanUng(@RequestBody PCRPhanUng pCRPhanUng) throws URISyntaxException {
        log.debug("REST request to update PCRPhanUng : {}", pCRPhanUng);
        if (pCRPhanUng.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCRPhanUng result = pCRPhanUngRepository.save(pCRPhanUng);
        pCRPhanUngSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCRPhanUng.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcr-phan-ungs : get all the pCRPhanUngs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRPhanUngs in body
     */
    @GetMapping("/pcr-phan-ungs")
    @Timed
    public List<PCRPhanUng> getAllPCRPhanUngs() {
        log.debug("REST request to get all PCRPhanUngs");
        return pCRPhanUngRepository.findAll();
    }

    /**
     * GET  /pcr-phan-ungs/:id : get the "id" pCRPhanUng.
     *
     * @param id the id of the pCRPhanUng to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCRPhanUng, or with status 404 (Not Found)
     */
    @GetMapping("/pcr-phan-ungs/{id}")
    @Timed
    public ResponseEntity<PCRPhanUng> getPCRPhanUng(@PathVariable Long id) {
        log.debug("REST request to get PCRPhanUng : {}", id);
        Optional<PCRPhanUng> pCRPhanUng = pCRPhanUngRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCRPhanUng);
    }

    /**
     * DELETE  /pcr-phan-ungs/:id : delete the "id" pCRPhanUng.
     *
     * @param id the id of the pCRPhanUng to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcr-phan-ungs/{id}")
    @Timed
    public ResponseEntity<Void> deletePCRPhanUng(@PathVariable Long id) {
        log.debug("REST request to delete PCRPhanUng : {}", id);

        pCRPhanUngRepository.deleteById(id);
        pCRPhanUngSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcr-phan-ungs?query=:query : search for the pCRPhanUng corresponding
     * to the query.
     *
     * @param query the query of the pCRPhanUng search
     * @return the result of the search
     */
    @GetMapping("/_search/pcr-phan-ungs")
    @Timed
    public List<PCRPhanUng> searchPCRPhanUngs(@RequestParam String query) {
        log.debug("REST request to search PCRPhanUngs for query {}", query);
        return StreamSupport
            .stream(pCRPhanUngSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
