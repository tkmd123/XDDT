package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.MayPCR;
import vn.homtech.xddt.repository.MayPCRRepository;
import vn.homtech.xddt.repository.search.MayPCRSearchRepository;
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
 * REST controller for managing MayPCR.
 */
@RestController
@RequestMapping("/api")
public class MayPCRResource {

    private final Logger log = LoggerFactory.getLogger(MayPCRResource.class);

    private static final String ENTITY_NAME = "mayPCR";

    private final MayPCRRepository mayPCRRepository;

    private final MayPCRSearchRepository mayPCRSearchRepository;

    public MayPCRResource(MayPCRRepository mayPCRRepository, MayPCRSearchRepository mayPCRSearchRepository) {
        this.mayPCRRepository = mayPCRRepository;
        this.mayPCRSearchRepository = mayPCRSearchRepository;
    }

    /**
     * POST  /may-pcrs : Create a new mayPCR.
     *
     * @param mayPCR the mayPCR to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mayPCR, or with status 400 (Bad Request) if the mayPCR has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/may-pcrs")
    @Timed
    public ResponseEntity<MayPCR> createMayPCR(@RequestBody MayPCR mayPCR) throws URISyntaxException {
        log.debug("REST request to save MayPCR : {}", mayPCR);
        if (mayPCR.getId() != null) {
            throw new BadRequestAlertException("A new mayPCR cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MayPCR result = mayPCRRepository.save(mayPCR);
        mayPCRSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/may-pcrs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /may-pcrs : Updates an existing mayPCR.
     *
     * @param mayPCR the mayPCR to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mayPCR,
     * or with status 400 (Bad Request) if the mayPCR is not valid,
     * or with status 500 (Internal Server Error) if the mayPCR couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/may-pcrs")
    @Timed
    public ResponseEntity<MayPCR> updateMayPCR(@RequestBody MayPCR mayPCR) throws URISyntaxException {
        log.debug("REST request to update MayPCR : {}", mayPCR);
        if (mayPCR.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MayPCR result = mayPCRRepository.save(mayPCR);
        mayPCRSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mayPCR.getId().toString()))
            .body(result);
    }

    /**
     * GET  /may-pcrs : get all the mayPCRS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mayPCRS in body
     */
    @GetMapping("/may-pcrs")
    @Timed
    public List<MayPCR> getAllMayPCRS() {
        log.debug("REST request to get all MayPCRS");
        return mayPCRRepository.findAll();
    }

    /**
     * GET  /may-pcrs/:id : get the "id" mayPCR.
     *
     * @param id the id of the mayPCR to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mayPCR, or with status 404 (Not Found)
     */
    @GetMapping("/may-pcrs/{id}")
    @Timed
    public ResponseEntity<MayPCR> getMayPCR(@PathVariable Long id) {
        log.debug("REST request to get MayPCR : {}", id);
        Optional<MayPCR> mayPCR = mayPCRRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mayPCR);
    }

    /**
     * DELETE  /may-pcrs/:id : delete the "id" mayPCR.
     *
     * @param id the id of the mayPCR to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/may-pcrs/{id}")
    @Timed
    public ResponseEntity<Void> deleteMayPCR(@PathVariable Long id) {
        log.debug("REST request to delete MayPCR : {}", id);

        mayPCRRepository.deleteById(id);
        mayPCRSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/may-pcrs?query=:query : search for the mayPCR corresponding
     * to the query.
     *
     * @param query the query of the mayPCR search
     * @return the result of the search
     */
    @GetMapping("/_search/may-pcrs")
    @Timed
    public List<MayPCR> searchMayPCRS(@RequestParam String query) {
        log.debug("REST request to search MayPCRS for query {}", query);
        return StreamSupport
            .stream(mayPCRSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
