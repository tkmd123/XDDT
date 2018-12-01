package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCRMau;
import vn.homtech.xddt.repository.PCRMauRepository;
import vn.homtech.xddt.repository.search.PCRMauSearchRepository;
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
 * REST controller for managing PCRMau.
 */
@RestController
@RequestMapping("/api")
public class PCRMauResource {

    private final Logger log = LoggerFactory.getLogger(PCRMauResource.class);

    private static final String ENTITY_NAME = "pCRMau";

    private final PCRMauRepository pCRMauRepository;

    private final PCRMauSearchRepository pCRMauSearchRepository;

    public PCRMauResource(PCRMauRepository pCRMauRepository, PCRMauSearchRepository pCRMauSearchRepository) {
        this.pCRMauRepository = pCRMauRepository;
        this.pCRMauSearchRepository = pCRMauSearchRepository;
    }

    /**
     * POST  /pcr-maus : Create a new pCRMau.
     *
     * @param pCRMau the pCRMau to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCRMau, or with status 400 (Bad Request) if the pCRMau has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcr-maus")
    @Timed
    public ResponseEntity<PCRMau> createPCRMau(@RequestBody PCRMau pCRMau) throws URISyntaxException {
        log.debug("REST request to save PCRMau : {}", pCRMau);
        if (pCRMau.getId() != null) {
            throw new BadRequestAlertException("A new pCRMau cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCRMau result = pCRMauRepository.save(pCRMau);
        pCRMauSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcr-maus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcr-maus : Updates an existing pCRMau.
     *
     * @param pCRMau the pCRMau to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCRMau,
     * or with status 400 (Bad Request) if the pCRMau is not valid,
     * or with status 500 (Internal Server Error) if the pCRMau couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcr-maus")
    @Timed
    public ResponseEntity<PCRMau> updatePCRMau(@RequestBody PCRMau pCRMau) throws URISyntaxException {
        log.debug("REST request to update PCRMau : {}", pCRMau);
        if (pCRMau.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCRMau result = pCRMauRepository.save(pCRMau);
        pCRMauSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCRMau.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcr-maus : get all the pCRMaus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRMaus in body
     */
    @GetMapping("/pcr-maus")
    @Timed
    public List<PCRMau> getAllPCRMaus() {
        log.debug("REST request to get all PCRMaus");
        return pCRMauRepository.findAll();
    }

    /**
     * GET  /pcr-maus/:id : get the "id" pCRMau.
     *
     * @param id the id of the pCRMau to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCRMau, or with status 404 (Not Found)
     */
    @GetMapping("/pcr-maus/{id}")
    @Timed
    public ResponseEntity<PCRMau> getPCRMau(@PathVariable Long id) {
        log.debug("REST request to get PCRMau : {}", id);
        Optional<PCRMau> pCRMau = pCRMauRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCRMau);
    }

    /**
     * DELETE  /pcr-maus/:id : delete the "id" pCRMau.
     *
     * @param id the id of the pCRMau to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcr-maus/{id}")
    @Timed
    public ResponseEntity<Void> deletePCRMau(@PathVariable Long id) {
        log.debug("REST request to delete PCRMau : {}", id);

        pCRMauRepository.deleteById(id);
        pCRMauSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcr-maus?query=:query : search for the pCRMau corresponding
     * to the query.
     *
     * @param query the query of the pCRMau search
     * @return the result of the search
     */
    @GetMapping("/_search/pcr-maus")
    @Timed
    public List<PCRMau> searchPCRMaus(@RequestParam String query) {
        log.debug("REST request to search PCRMaus for query {}", query);
        return StreamSupport
            .stream(pCRMauSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
