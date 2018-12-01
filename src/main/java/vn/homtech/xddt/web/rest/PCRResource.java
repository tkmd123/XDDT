package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCR;
import vn.homtech.xddt.repository.PCRRepository;
import vn.homtech.xddt.repository.search.PCRSearchRepository;
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
 * REST controller for managing PCR.
 */
@RestController
@RequestMapping("/api")
public class PCRResource {

    private final Logger log = LoggerFactory.getLogger(PCRResource.class);

    private static final String ENTITY_NAME = "pCR";

    private final PCRRepository pCRRepository;

    private final PCRSearchRepository pCRSearchRepository;

    public PCRResource(PCRRepository pCRRepository, PCRSearchRepository pCRSearchRepository) {
        this.pCRRepository = pCRRepository;
        this.pCRSearchRepository = pCRSearchRepository;
    }

    /**
     * POST  /pcrs : Create a new pCR.
     *
     * @param pCR the pCR to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCR, or with status 400 (Bad Request) if the pCR has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcrs")
    @Timed
    public ResponseEntity<PCR> createPCR(@RequestBody PCR pCR) throws URISyntaxException {
        log.debug("REST request to save PCR : {}", pCR);
        if (pCR.getId() != null) {
            throw new BadRequestAlertException("A new pCR cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCR result = pCRRepository.save(pCR);
        pCRSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcrs : Updates an existing pCR.
     *
     * @param pCR the pCR to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCR,
     * or with status 400 (Bad Request) if the pCR is not valid,
     * or with status 500 (Internal Server Error) if the pCR couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcrs")
    @Timed
    public ResponseEntity<PCR> updatePCR(@RequestBody PCR pCR) throws URISyntaxException {
        log.debug("REST request to update PCR : {}", pCR);
        if (pCR.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCR result = pCRRepository.save(pCR);
        pCRSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCR.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcrs : get all the pCRS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRS in body
     */
    @GetMapping("/pcrs")
    @Timed
    public List<PCR> getAllPCRS() {
        log.debug("REST request to get all PCRS");
        return pCRRepository.findAll();
    }

    /**
     * GET  /pcrs/:id : get the "id" pCR.
     *
     * @param id the id of the pCR to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCR, or with status 404 (Not Found)
     */
    @GetMapping("/pcrs/{id}")
    @Timed
    public ResponseEntity<PCR> getPCR(@PathVariable Long id) {
        log.debug("REST request to get PCR : {}", id);
        Optional<PCR> pCR = pCRRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCR);
    }

    /**
     * DELETE  /pcrs/:id : delete the "id" pCR.
     *
     * @param id the id of the pCR to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcrs/{id}")
    @Timed
    public ResponseEntity<Void> deletePCR(@PathVariable Long id) {
        log.debug("REST request to delete PCR : {}", id);

        pCRRepository.deleteById(id);
        pCRSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcrs?query=:query : search for the pCR corresponding
     * to the query.
     *
     * @param query the query of the pCR search
     * @return the result of the search
     */
    @GetMapping("/_search/pcrs")
    @Timed
    public List<PCR> searchPCRS(@RequestParam String query) {
        log.debug("REST request to search PCRS for query {}", query);
        return StreamSupport
            .stream(pCRSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
