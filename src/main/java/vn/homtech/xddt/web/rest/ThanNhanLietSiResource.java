package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.ThanNhanLietSi;
import vn.homtech.xddt.repository.ThanNhanLietSiRepository;
import vn.homtech.xddt.repository.search.ThanNhanLietSiSearchRepository;
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
 * REST controller for managing ThanNhanLietSi.
 */
@RestController
@RequestMapping("/api")
public class ThanNhanLietSiResource {

    private final Logger log = LoggerFactory.getLogger(ThanNhanLietSiResource.class);

    private static final String ENTITY_NAME = "thanNhanLietSi";

    private final ThanNhanLietSiRepository thanNhanLietSiRepository;

    private final ThanNhanLietSiSearchRepository thanNhanLietSiSearchRepository;

    public ThanNhanLietSiResource(ThanNhanLietSiRepository thanNhanLietSiRepository, ThanNhanLietSiSearchRepository thanNhanLietSiSearchRepository) {
        this.thanNhanLietSiRepository = thanNhanLietSiRepository;
        this.thanNhanLietSiSearchRepository = thanNhanLietSiSearchRepository;
    }

    /**
     * POST  /than-nhan-liet-sis : Create a new thanNhanLietSi.
     *
     * @param thanNhanLietSi the thanNhanLietSi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new thanNhanLietSi, or with status 400 (Bad Request) if the thanNhanLietSi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/than-nhan-liet-sis")
    @Timed
    public ResponseEntity<ThanNhanLietSi> createThanNhanLietSi(@RequestBody ThanNhanLietSi thanNhanLietSi) throws URISyntaxException {
        log.debug("REST request to save ThanNhanLietSi : {}", thanNhanLietSi);
        if (thanNhanLietSi.getId() != null) {
            throw new BadRequestAlertException("A new thanNhanLietSi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThanNhanLietSi result = thanNhanLietSiRepository.save(thanNhanLietSi);
        thanNhanLietSiSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/than-nhan-liet-sis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /than-nhan-liet-sis : Updates an existing thanNhanLietSi.
     *
     * @param thanNhanLietSi the thanNhanLietSi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated thanNhanLietSi,
     * or with status 400 (Bad Request) if the thanNhanLietSi is not valid,
     * or with status 500 (Internal Server Error) if the thanNhanLietSi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/than-nhan-liet-sis")
    @Timed
    public ResponseEntity<ThanNhanLietSi> updateThanNhanLietSi(@RequestBody ThanNhanLietSi thanNhanLietSi) throws URISyntaxException {
        log.debug("REST request to update ThanNhanLietSi : {}", thanNhanLietSi);
        if (thanNhanLietSi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThanNhanLietSi result = thanNhanLietSiRepository.save(thanNhanLietSi);
        thanNhanLietSiSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, thanNhanLietSi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /than-nhan-liet-sis : get all the thanNhanLietSis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of thanNhanLietSis in body
     */
    @GetMapping("/than-nhan-liet-sis")
    @Timed
    public List<ThanNhanLietSi> getAllThanNhanLietSis() {
        log.debug("REST request to get all ThanNhanLietSis");
        return thanNhanLietSiRepository.findAll();
    }

    /**
     * GET  /than-nhan-liet-sis/:id : get the "id" thanNhanLietSi.
     *
     * @param id the id of the thanNhanLietSi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the thanNhanLietSi, or with status 404 (Not Found)
     */
    @GetMapping("/than-nhan-liet-sis/{id}")
    @Timed
    public ResponseEntity<ThanNhanLietSi> getThanNhanLietSi(@PathVariable Long id) {
        log.debug("REST request to get ThanNhanLietSi : {}", id);
        Optional<ThanNhanLietSi> thanNhanLietSi = thanNhanLietSiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thanNhanLietSi);
    }

    /**
     * DELETE  /than-nhan-liet-sis/:id : delete the "id" thanNhanLietSi.
     *
     * @param id the id of the thanNhanLietSi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/than-nhan-liet-sis/{id}")
    @Timed
    public ResponseEntity<Void> deleteThanNhanLietSi(@PathVariable Long id) {
        log.debug("REST request to delete ThanNhanLietSi : {}", id);

        thanNhanLietSiRepository.deleteById(id);
        thanNhanLietSiSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/than-nhan-liet-sis?query=:query : search for the thanNhanLietSi corresponding
     * to the query.
     *
     * @param query the query of the thanNhanLietSi search
     * @return the result of the search
     */
    @GetMapping("/_search/than-nhan-liet-sis")
    @Timed
    public List<ThanNhanLietSi> searchThanNhanLietSis(@RequestParam String query) {
        log.debug("REST request to search ThanNhanLietSis for query {}", query);
        return StreamSupport
            .stream(thanNhanLietSiSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
