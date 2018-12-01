package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.TachChiet;
import vn.homtech.xddt.repository.TachChietRepository;
import vn.homtech.xddt.repository.search.TachChietSearchRepository;
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
 * REST controller for managing TachChiet.
 */
@RestController
@RequestMapping("/api")
public class TachChietResource {

    private final Logger log = LoggerFactory.getLogger(TachChietResource.class);

    private static final String ENTITY_NAME = "tachChiet";

    private final TachChietRepository tachChietRepository;

    private final TachChietSearchRepository tachChietSearchRepository;

    public TachChietResource(TachChietRepository tachChietRepository, TachChietSearchRepository tachChietSearchRepository) {
        this.tachChietRepository = tachChietRepository;
        this.tachChietSearchRepository = tachChietSearchRepository;
    }

    /**
     * POST  /tach-chiets : Create a new tachChiet.
     *
     * @param tachChiet the tachChiet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tachChiet, or with status 400 (Bad Request) if the tachChiet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tach-chiets")
    @Timed
    public ResponseEntity<TachChiet> createTachChiet(@RequestBody TachChiet tachChiet) throws URISyntaxException {
        log.debug("REST request to save TachChiet : {}", tachChiet);
        if (tachChiet.getId() != null) {
            throw new BadRequestAlertException("A new tachChiet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TachChiet result = tachChietRepository.save(tachChiet);
        tachChietSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tach-chiets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tach-chiets : Updates an existing tachChiet.
     *
     * @param tachChiet the tachChiet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tachChiet,
     * or with status 400 (Bad Request) if the tachChiet is not valid,
     * or with status 500 (Internal Server Error) if the tachChiet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tach-chiets")
    @Timed
    public ResponseEntity<TachChiet> updateTachChiet(@RequestBody TachChiet tachChiet) throws URISyntaxException {
        log.debug("REST request to update TachChiet : {}", tachChiet);
        if (tachChiet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TachChiet result = tachChietRepository.save(tachChiet);
        tachChietSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tachChiet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tach-chiets : get all the tachChiets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tachChiets in body
     */
    @GetMapping("/tach-chiets")
    @Timed
    public List<TachChiet> getAllTachChiets() {
        log.debug("REST request to get all TachChiets");
        return tachChietRepository.findAll();
    }

    /**
     * GET  /tach-chiets/:id : get the "id" tachChiet.
     *
     * @param id the id of the tachChiet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tachChiet, or with status 404 (Not Found)
     */
    @GetMapping("/tach-chiets/{id}")
    @Timed
    public ResponseEntity<TachChiet> getTachChiet(@PathVariable Long id) {
        log.debug("REST request to get TachChiet : {}", id);
        Optional<TachChiet> tachChiet = tachChietRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tachChiet);
    }

    /**
     * DELETE  /tach-chiets/:id : delete the "id" tachChiet.
     *
     * @param id the id of the tachChiet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tach-chiets/{id}")
    @Timed
    public ResponseEntity<Void> deleteTachChiet(@PathVariable Long id) {
        log.debug("REST request to delete TachChiet : {}", id);

        tachChietRepository.deleteById(id);
        tachChietSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tach-chiets?query=:query : search for the tachChiet corresponding
     * to the query.
     *
     * @param query the query of the tachChiet search
     * @return the result of the search
     */
    @GetMapping("/_search/tach-chiets")
    @Timed
    public List<TachChiet> searchTachChiets(@RequestParam String query) {
        log.debug("REST request to search TachChiets for query {}", query);
        return StreamSupport
            .stream(tachChietSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
