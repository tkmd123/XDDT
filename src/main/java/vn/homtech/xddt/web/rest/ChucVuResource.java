package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.ChucVu;
import vn.homtech.xddt.repository.ChucVuRepository;
import vn.homtech.xddt.repository.search.ChucVuSearchRepository;
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
 * REST controller for managing ChucVu.
 */
@RestController
@RequestMapping("/api")
public class ChucVuResource {

    private final Logger log = LoggerFactory.getLogger(ChucVuResource.class);

    private static final String ENTITY_NAME = "chucVu";

    private final ChucVuRepository chucVuRepository;

    private final ChucVuSearchRepository chucVuSearchRepository;

    public ChucVuResource(ChucVuRepository chucVuRepository, ChucVuSearchRepository chucVuSearchRepository) {
        this.chucVuRepository = chucVuRepository;
        this.chucVuSearchRepository = chucVuSearchRepository;
    }

    /**
     * POST  /chuc-vus : Create a new chucVu.
     *
     * @param chucVu the chucVu to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chucVu, or with status 400 (Bad Request) if the chucVu has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chuc-vus")
    @Timed
    public ResponseEntity<ChucVu> createChucVu(@RequestBody ChucVu chucVu) throws URISyntaxException {
        log.debug("REST request to save ChucVu : {}", chucVu);
        if (chucVu.getId() != null) {
            throw new BadRequestAlertException("A new chucVu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChucVu result = chucVuRepository.save(chucVu);
        chucVuSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/chuc-vus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chuc-vus : Updates an existing chucVu.
     *
     * @param chucVu the chucVu to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chucVu,
     * or with status 400 (Bad Request) if the chucVu is not valid,
     * or with status 500 (Internal Server Error) if the chucVu couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chuc-vus")
    @Timed
    public ResponseEntity<ChucVu> updateChucVu(@RequestBody ChucVu chucVu) throws URISyntaxException {
        log.debug("REST request to update ChucVu : {}", chucVu);
        if (chucVu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChucVu result = chucVuRepository.save(chucVu);
        chucVuSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chucVu.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chuc-vus : get all the chucVus.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of chucVus in body
     */
    @GetMapping("/chuc-vus")
    @Timed
    public List<ChucVu> getAllChucVus() {
        log.debug("REST request to get all ChucVus");
        return chucVuRepository.findAll();
    }

    /**
     * GET  /chuc-vus/:id : get the "id" chucVu.
     *
     * @param id the id of the chucVu to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chucVu, or with status 404 (Not Found)
     */
    @GetMapping("/chuc-vus/{id}")
    @Timed
    public ResponseEntity<ChucVu> getChucVu(@PathVariable Long id) {
        log.debug("REST request to get ChucVu : {}", id);
        Optional<ChucVu> chucVu = chucVuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chucVu);
    }

    /**
     * DELETE  /chuc-vus/:id : delete the "id" chucVu.
     *
     * @param id the id of the chucVu to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chuc-vus/{id}")
    @Timed
    public ResponseEntity<Void> deleteChucVu(@PathVariable Long id) {
        log.debug("REST request to delete ChucVu : {}", id);

        chucVuRepository.deleteById(id);
        chucVuSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/chuc-vus?query=:query : search for the chucVu corresponding
     * to the query.
     *
     * @param query the query of the chucVu search
     * @return the result of the search
     */
    @GetMapping("/_search/chuc-vus")
    @Timed
    public List<ChucVu> searchChucVus(@RequestParam String query) {
        log.debug("REST request to search ChucVus for query {}", query);
        return StreamSupport
            .stream(chucVuSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
