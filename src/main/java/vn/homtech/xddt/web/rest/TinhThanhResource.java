package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.TinhThanh;
import vn.homtech.xddt.repository.TinhThanhRepository;
import vn.homtech.xddt.repository.search.TinhThanhSearchRepository;
import vn.homtech.xddt.web.rest.errors.BadRequestAlertException;
import vn.homtech.xddt.web.rest.util.HeaderUtil;
import vn.homtech.xddt.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing TinhThanh.
 */
@RestController
@RequestMapping("/api")
public class TinhThanhResource {

    private final Logger log = LoggerFactory.getLogger(TinhThanhResource.class);

    private static final String ENTITY_NAME = "tinhThanh";

    private final TinhThanhRepository tinhThanhRepository;

    private final TinhThanhSearchRepository tinhThanhSearchRepository;

    public TinhThanhResource(TinhThanhRepository tinhThanhRepository, TinhThanhSearchRepository tinhThanhSearchRepository) {
        this.tinhThanhRepository = tinhThanhRepository;
        this.tinhThanhSearchRepository = tinhThanhSearchRepository;
    }

    /**
     * POST  /tinh-thanhs : Create a new tinhThanh.
     *
     * @param tinhThanh the tinhThanh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tinhThanh, or with status 400 (Bad Request) if the tinhThanh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tinh-thanhs")
    @Timed
    public ResponseEntity<TinhThanh> createTinhThanh(@RequestBody TinhThanh tinhThanh) throws URISyntaxException {
        log.debug("REST request to save TinhThanh : {}", tinhThanh);
        if (tinhThanh.getId() != null) {
            throw new BadRequestAlertException("A new tinhThanh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TinhThanh result = tinhThanhRepository.save(tinhThanh);
        tinhThanhSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/tinh-thanhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tinh-thanhs : Updates an existing tinhThanh.
     *
     * @param tinhThanh the tinhThanh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tinhThanh,
     * or with status 400 (Bad Request) if the tinhThanh is not valid,
     * or with status 500 (Internal Server Error) if the tinhThanh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tinh-thanhs")
    @Timed
    public ResponseEntity<TinhThanh> updateTinhThanh(@RequestBody TinhThanh tinhThanh) throws URISyntaxException {
        log.debug("REST request to update TinhThanh : {}", tinhThanh);
        if (tinhThanh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TinhThanh result = tinhThanhRepository.save(tinhThanh);
        tinhThanhSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tinhThanh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tinh-thanhs : get all the tinhThanhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tinhThanhs in body
     */
    @GetMapping("/tinh-thanhs")
    @Timed
    public ResponseEntity<List<TinhThanh>> getAllTinhThanhs(Pageable pageable) {
        log.debug("REST request to get a page of TinhThanhs");
        Page<TinhThanh> page = tinhThanhRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tinh-thanhs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tinh-thanhs/:id : get the "id" tinhThanh.
     *
     * @param id the id of the tinhThanh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tinhThanh, or with status 404 (Not Found)
     */
    @GetMapping("/tinh-thanhs/{id}")
    @Timed
    public ResponseEntity<TinhThanh> getTinhThanh(@PathVariable Long id) {
        log.debug("REST request to get TinhThanh : {}", id);
        Optional<TinhThanh> tinhThanh = tinhThanhRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tinhThanh);
    }

    /**
     * DELETE  /tinh-thanhs/:id : delete the "id" tinhThanh.
     *
     * @param id the id of the tinhThanh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tinh-thanhs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTinhThanh(@PathVariable Long id) {
        log.debug("REST request to delete TinhThanh : {}", id);

        tinhThanhRepository.deleteById(id);
        tinhThanhSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tinh-thanhs?query=:query : search for the tinhThanh corresponding
     * to the query.
     *
     * @param query the query of the tinhThanh search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/tinh-thanhs")
    @Timed
    public ResponseEntity<List<TinhThanh>> searchTinhThanhs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of TinhThanhs for query {}", query);
        Page<TinhThanh> page = tinhThanhSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/tinh-thanhs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
