package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.TinhSachPhanUng;
import vn.homtech.xddt.repository.TinhSachPhanUngRepository;
import vn.homtech.xddt.repository.search.TinhSachPhanUngSearchRepository;
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
 * REST controller for managing TinhSachPhanUng.
 */
@RestController
@RequestMapping("/api")
public class TinhSachPhanUngResource {

    private final Logger log = LoggerFactory.getLogger(TinhSachPhanUngResource.class);

    private static final String ENTITY_NAME = "tinhSachPhanUng";

    private final TinhSachPhanUngRepository tinhSachPhanUngRepository;

    private final TinhSachPhanUngSearchRepository tinhSachPhanUngSearchRepository;

    public TinhSachPhanUngResource(TinhSachPhanUngRepository tinhSachPhanUngRepository, TinhSachPhanUngSearchRepository tinhSachPhanUngSearchRepository) {
        this.tinhSachPhanUngRepository = tinhSachPhanUngRepository;
        this.tinhSachPhanUngSearchRepository = tinhSachPhanUngSearchRepository;
    }

    /**
     * POST  /tinh-sach-phan-ungs : Create a new tinhSachPhanUng.
     *
     * @param tinhSachPhanUng the tinhSachPhanUng to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tinhSachPhanUng, or with status 400 (Bad Request) if the tinhSachPhanUng has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tinh-sach-phan-ungs")
    @Timed
    public ResponseEntity<TinhSachPhanUng> createTinhSachPhanUng(@RequestBody TinhSachPhanUng tinhSachPhanUng) throws URISyntaxException {
        log.debug("REST request to save TinhSachPhanUng : {}", tinhSachPhanUng);
        if (tinhSachPhanUng.getId() != null) {
            throw new BadRequestAlertException("A new tinhSachPhanUng cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TinhSachPhanUng result = tinhSachPhanUngRepository.save(tinhSachPhanUng);
        tinhSachPhanUngSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tinh-sach-phan-ungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tinh-sach-phan-ungs : Updates an existing tinhSachPhanUng.
     *
     * @param tinhSachPhanUng the tinhSachPhanUng to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tinhSachPhanUng,
     * or with status 400 (Bad Request) if the tinhSachPhanUng is not valid,
     * or with status 500 (Internal Server Error) if the tinhSachPhanUng couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tinh-sach-phan-ungs")
    @Timed
    public ResponseEntity<TinhSachPhanUng> updateTinhSachPhanUng(@RequestBody TinhSachPhanUng tinhSachPhanUng) throws URISyntaxException {
        log.debug("REST request to update TinhSachPhanUng : {}", tinhSachPhanUng);
        if (tinhSachPhanUng.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TinhSachPhanUng result = tinhSachPhanUngRepository.save(tinhSachPhanUng);
        tinhSachPhanUngSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tinhSachPhanUng.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tinh-sach-phan-ungs : get all the tinhSachPhanUngs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tinhSachPhanUngs in body
     */
    @GetMapping("/tinh-sach-phan-ungs")
    @Timed
    public List<TinhSachPhanUng> getAllTinhSachPhanUngs() {
        log.debug("REST request to get all TinhSachPhanUngs");
        return tinhSachPhanUngRepository.findAll();
    }

    /**
     * GET  /tinh-sach-phan-ungs/:id : get the "id" tinhSachPhanUng.
     *
     * @param id the id of the tinhSachPhanUng to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tinhSachPhanUng, or with status 404 (Not Found)
     */
    @GetMapping("/tinh-sach-phan-ungs/{id}")
    @Timed
    public ResponseEntity<TinhSachPhanUng> getTinhSachPhanUng(@PathVariable Long id) {
        log.debug("REST request to get TinhSachPhanUng : {}", id);
        Optional<TinhSachPhanUng> tinhSachPhanUng = tinhSachPhanUngRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tinhSachPhanUng);
    }

    /**
     * DELETE  /tinh-sach-phan-ungs/:id : delete the "id" tinhSachPhanUng.
     *
     * @param id the id of the tinhSachPhanUng to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tinh-sach-phan-ungs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTinhSachPhanUng(@PathVariable Long id) {
        log.debug("REST request to delete TinhSachPhanUng : {}", id);

        tinhSachPhanUngRepository.deleteById(id);
        tinhSachPhanUngSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tinh-sach-phan-ungs?query=:query : search for the tinhSachPhanUng corresponding
     * to the query.
     *
     * @param query the query of the tinhSachPhanUng search
     * @return the result of the search
     */
    @GetMapping("/_search/tinh-sach-phan-ungs")
    @Timed
    public List<TinhSachPhanUng> searchTinhSachPhanUngs(@RequestParam String query) {
        log.debug("REST request to search TinhSachPhanUngs for query {}", query);
        return StreamSupport
            .stream(tinhSachPhanUngSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
