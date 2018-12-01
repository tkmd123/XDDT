package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCRMoi;
import vn.homtech.xddt.repository.PCRMoiRepository;
import vn.homtech.xddt.repository.search.PCRMoiSearchRepository;
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
 * REST controller for managing PCRMoi.
 */
@RestController
@RequestMapping("/api")
public class PCRMoiResource {

    private final Logger log = LoggerFactory.getLogger(PCRMoiResource.class);

    private static final String ENTITY_NAME = "pCRMoi";

    private final PCRMoiRepository pCRMoiRepository;

    private final PCRMoiSearchRepository pCRMoiSearchRepository;

    public PCRMoiResource(PCRMoiRepository pCRMoiRepository, PCRMoiSearchRepository pCRMoiSearchRepository) {
        this.pCRMoiRepository = pCRMoiRepository;
        this.pCRMoiSearchRepository = pCRMoiSearchRepository;
    }

    /**
     * POST  /pcr-mois : Create a new pCRMoi.
     *
     * @param pCRMoi the pCRMoi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCRMoi, or with status 400 (Bad Request) if the pCRMoi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcr-mois")
    @Timed
    public ResponseEntity<PCRMoi> createPCRMoi(@RequestBody PCRMoi pCRMoi) throws URISyntaxException {
        log.debug("REST request to save PCRMoi : {}", pCRMoi);
        if (pCRMoi.getId() != null) {
            throw new BadRequestAlertException("A new pCRMoi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCRMoi result = pCRMoiRepository.save(pCRMoi);
        pCRMoiSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcr-mois/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcr-mois : Updates an existing pCRMoi.
     *
     * @param pCRMoi the pCRMoi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCRMoi,
     * or with status 400 (Bad Request) if the pCRMoi is not valid,
     * or with status 500 (Internal Server Error) if the pCRMoi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcr-mois")
    @Timed
    public ResponseEntity<PCRMoi> updatePCRMoi(@RequestBody PCRMoi pCRMoi) throws URISyntaxException {
        log.debug("REST request to update PCRMoi : {}", pCRMoi);
        if (pCRMoi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCRMoi result = pCRMoiRepository.save(pCRMoi);
        pCRMoiSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCRMoi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcr-mois : get all the pCRMois.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRMois in body
     */
    @GetMapping("/pcr-mois")
    @Timed
    public List<PCRMoi> getAllPCRMois() {
        log.debug("REST request to get all PCRMois");
        return pCRMoiRepository.findAll();
    }

    /**
     * GET  /pcr-mois/:id : get the "id" pCRMoi.
     *
     * @param id the id of the pCRMoi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCRMoi, or with status 404 (Not Found)
     */
    @GetMapping("/pcr-mois/{id}")
    @Timed
    public ResponseEntity<PCRMoi> getPCRMoi(@PathVariable Long id) {
        log.debug("REST request to get PCRMoi : {}", id);
        Optional<PCRMoi> pCRMoi = pCRMoiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCRMoi);
    }

    /**
     * DELETE  /pcr-mois/:id : delete the "id" pCRMoi.
     *
     * @param id the id of the pCRMoi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcr-mois/{id}")
    @Timed
    public ResponseEntity<Void> deletePCRMoi(@PathVariable Long id) {
        log.debug("REST request to delete PCRMoi : {}", id);

        pCRMoiRepository.deleteById(id);
        pCRMoiSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcr-mois?query=:query : search for the pCRMoi corresponding
     * to the query.
     *
     * @param query the query of the pCRMoi search
     * @return the result of the search
     */
    @GetMapping("/_search/pcr-mois")
    @Timed
    public List<PCRMoi> searchPCRMois(@RequestParam String query) {
        log.debug("REST request to search PCRMois for query {}", query);
        return StreamSupport
            .stream(pCRMoiSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
