package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.CapBac;
import vn.homtech.xddt.repository.CapBacRepository;
import vn.homtech.xddt.repository.search.CapBacSearchRepository;
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
 * REST controller for managing CapBac.
 */
@RestController
@RequestMapping("/api")
public class CapBacResource {

    private final Logger log = LoggerFactory.getLogger(CapBacResource.class);

    private static final String ENTITY_NAME = "capBac";

    private final CapBacRepository capBacRepository;

    private final CapBacSearchRepository capBacSearchRepository;

    public CapBacResource(CapBacRepository capBacRepository, CapBacSearchRepository capBacSearchRepository) {
        this.capBacRepository = capBacRepository;
        this.capBacSearchRepository = capBacSearchRepository;
    }

    /**
     * POST  /cap-bacs : Create a new capBac.
     *
     * @param capBac the capBac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new capBac, or with status 400 (Bad Request) if the capBac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cap-bacs")
    @Timed
    public ResponseEntity<CapBac> createCapBac(@RequestBody CapBac capBac) throws URISyntaxException {
        log.debug("REST request to save CapBac : {}", capBac);
        if (capBac.getId() != null) {
            throw new BadRequestAlertException("A new capBac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CapBac result = capBacRepository.save(capBac);
        capBacSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/cap-bacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cap-bacs : Updates an existing capBac.
     *
     * @param capBac the capBac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated capBac,
     * or with status 400 (Bad Request) if the capBac is not valid,
     * or with status 500 (Internal Server Error) if the capBac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cap-bacs")
    @Timed
    public ResponseEntity<CapBac> updateCapBac(@RequestBody CapBac capBac) throws URISyntaxException {
        log.debug("REST request to update CapBac : {}", capBac);
        if (capBac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CapBac result = capBacRepository.save(capBac);
        capBacSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, capBac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cap-bacs : get all the capBacs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of capBacs in body
     */
    @GetMapping("/cap-bacs")
    @Timed
    public List<CapBac> getAllCapBacs() {
        log.debug("REST request to get all CapBacs");
        return capBacRepository.findAll();
    }

    /**
     * GET  /cap-bacs/:id : get the "id" capBac.
     *
     * @param id the id of the capBac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the capBac, or with status 404 (Not Found)
     */
    @GetMapping("/cap-bacs/{id}")
    @Timed
    public ResponseEntity<CapBac> getCapBac(@PathVariable Long id) {
        log.debug("REST request to get CapBac : {}", id);
        Optional<CapBac> capBac = capBacRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(capBac);
    }

    /**
     * DELETE  /cap-bacs/:id : delete the "id" capBac.
     *
     * @param id the id of the capBac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cap-bacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCapBac(@PathVariable Long id) {
        log.debug("REST request to delete CapBac : {}", id);

        capBacRepository.deleteById(id);
        capBacSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/cap-bacs?query=:query : search for the capBac corresponding
     * to the query.
     *
     * @param query the query of the capBac search
     * @return the result of the search
     */
    @GetMapping("/_search/cap-bacs")
    @Timed
    public List<CapBac> searchCapBacs(@RequestParam String query) {
        log.debug("REST request to search CapBacs for query {}", query);
        return StreamSupport
            .stream(capBacSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
