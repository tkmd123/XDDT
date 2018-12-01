package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.TinhSach;
import vn.homtech.xddt.repository.TinhSachRepository;
import vn.homtech.xddt.repository.search.TinhSachSearchRepository;
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
 * REST controller for managing TinhSach.
 */
@RestController
@RequestMapping("/api")
public class TinhSachResource {

    private final Logger log = LoggerFactory.getLogger(TinhSachResource.class);

    private static final String ENTITY_NAME = "tinhSach";

    private final TinhSachRepository tinhSachRepository;

    private final TinhSachSearchRepository tinhSachSearchRepository;

    public TinhSachResource(TinhSachRepository tinhSachRepository, TinhSachSearchRepository tinhSachSearchRepository) {
        this.tinhSachRepository = tinhSachRepository;
        this.tinhSachSearchRepository = tinhSachSearchRepository;
    }

    /**
     * POST  /tinh-saches : Create a new tinhSach.
     *
     * @param tinhSach the tinhSach to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tinhSach, or with status 400 (Bad Request) if the tinhSach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tinh-saches")
    @Timed
    public ResponseEntity<TinhSach> createTinhSach(@RequestBody TinhSach tinhSach) throws URISyntaxException {
        log.debug("REST request to save TinhSach : {}", tinhSach);
        if (tinhSach.getId() != null) {
            throw new BadRequestAlertException("A new tinhSach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TinhSach result = tinhSachRepository.save(tinhSach);
        tinhSachSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tinh-saches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tinh-saches : Updates an existing tinhSach.
     *
     * @param tinhSach the tinhSach to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tinhSach,
     * or with status 400 (Bad Request) if the tinhSach is not valid,
     * or with status 500 (Internal Server Error) if the tinhSach couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tinh-saches")
    @Timed
    public ResponseEntity<TinhSach> updateTinhSach(@RequestBody TinhSach tinhSach) throws URISyntaxException {
        log.debug("REST request to update TinhSach : {}", tinhSach);
        if (tinhSach.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TinhSach result = tinhSachRepository.save(tinhSach);
        tinhSachSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tinhSach.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tinh-saches : get all the tinhSaches.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tinhSaches in body
     */
    @GetMapping("/tinh-saches")
    @Timed
    public List<TinhSach> getAllTinhSaches() {
        log.debug("REST request to get all TinhSaches");
        return tinhSachRepository.findAll();
    }

    /**
     * GET  /tinh-saches/:id : get the "id" tinhSach.
     *
     * @param id the id of the tinhSach to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tinhSach, or with status 404 (Not Found)
     */
    @GetMapping("/tinh-saches/{id}")
    @Timed
    public ResponseEntity<TinhSach> getTinhSach(@PathVariable Long id) {
        log.debug("REST request to get TinhSach : {}", id);
        Optional<TinhSach> tinhSach = tinhSachRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tinhSach);
    }

    /**
     * DELETE  /tinh-saches/:id : delete the "id" tinhSach.
     *
     * @param id the id of the tinhSach to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tinh-saches/{id}")
    @Timed
    public ResponseEntity<Void> deleteTinhSach(@PathVariable Long id) {
        log.debug("REST request to delete TinhSach : {}", id);

        tinhSachRepository.deleteById(id);
        tinhSachSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tinh-saches?query=:query : search for the tinhSach corresponding
     * to the query.
     *
     * @param query the query of the tinhSach search
     * @return the result of the search
     */
    @GetMapping("/_search/tinh-saches")
    @Timed
    public List<TinhSach> searchTinhSaches(@RequestParam String query) {
        log.debug("REST request to search TinhSaches for query {}", query);
        return StreamSupport
            .stream(tinhSachSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
