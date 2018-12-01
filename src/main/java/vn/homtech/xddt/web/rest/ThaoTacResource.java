package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.ThaoTac;
import vn.homtech.xddt.repository.ThaoTacRepository;
import vn.homtech.xddt.repository.search.ThaoTacSearchRepository;
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
 * REST controller for managing ThaoTac.
 */
@RestController
@RequestMapping("/api")
public class ThaoTacResource {

    private final Logger log = LoggerFactory.getLogger(ThaoTacResource.class);

    private static final String ENTITY_NAME = "thaoTac";

    private final ThaoTacRepository thaoTacRepository;

    private final ThaoTacSearchRepository thaoTacSearchRepository;

    public ThaoTacResource(ThaoTacRepository thaoTacRepository, ThaoTacSearchRepository thaoTacSearchRepository) {
        this.thaoTacRepository = thaoTacRepository;
        this.thaoTacSearchRepository = thaoTacSearchRepository;
    }

    /**
     * POST  /thao-tacs : Create a new thaoTac.
     *
     * @param thaoTac the thaoTac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new thaoTac, or with status 400 (Bad Request) if the thaoTac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/thao-tacs")
    @Timed
    public ResponseEntity<ThaoTac> createThaoTac(@RequestBody ThaoTac thaoTac) throws URISyntaxException {
        log.debug("REST request to save ThaoTac : {}", thaoTac);
        if (thaoTac.getId() != null) {
            throw new BadRequestAlertException("A new thaoTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThaoTac result = thaoTacRepository.save(thaoTac);
        thaoTacSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/thao-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /thao-tacs : Updates an existing thaoTac.
     *
     * @param thaoTac the thaoTac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated thaoTac,
     * or with status 400 (Bad Request) if the thaoTac is not valid,
     * or with status 500 (Internal Server Error) if the thaoTac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/thao-tacs")
    @Timed
    public ResponseEntity<ThaoTac> updateThaoTac(@RequestBody ThaoTac thaoTac) throws URISyntaxException {
        log.debug("REST request to update ThaoTac : {}", thaoTac);
        if (thaoTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThaoTac result = thaoTacRepository.save(thaoTac);
        thaoTacSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, thaoTac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /thao-tacs : get all the thaoTacs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of thaoTacs in body
     */
    @GetMapping("/thao-tacs")
    @Timed
    public List<ThaoTac> getAllThaoTacs() {
        log.debug("REST request to get all ThaoTacs");
        return thaoTacRepository.findAll();
    }

    /**
     * GET  /thao-tacs/:id : get the "id" thaoTac.
     *
     * @param id the id of the thaoTac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the thaoTac, or with status 404 (Not Found)
     */
    @GetMapping("/thao-tacs/{id}")
    @Timed
    public ResponseEntity<ThaoTac> getThaoTac(@PathVariable Long id) {
        log.debug("REST request to get ThaoTac : {}", id);
        Optional<ThaoTac> thaoTac = thaoTacRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thaoTac);
    }

    /**
     * DELETE  /thao-tacs/:id : delete the "id" thaoTac.
     *
     * @param id the id of the thaoTac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/thao-tacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteThaoTac(@PathVariable Long id) {
        log.debug("REST request to delete ThaoTac : {}", id);

        thaoTacRepository.deleteById(id);
        thaoTacSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/thao-tacs?query=:query : search for the thaoTac corresponding
     * to the query.
     *
     * @param query the query of the thaoTac search
     * @return the result of the search
     */
    @GetMapping("/_search/thao-tacs")
    @Timed
    public List<ThaoTac> searchThaoTacs(@RequestParam String query) {
        log.debug("REST request to search ThaoTacs for query {}", query);
        return StreamSupport
            .stream(thaoTacSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
