package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.QuanHuyen;
import vn.homtech.xddt.repository.QuanHuyenRepository;
import vn.homtech.xddt.repository.search.QuanHuyenSearchRepository;
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
 * REST controller for managing QuanHuyen.
 */
@RestController
@RequestMapping("/api")
public class QuanHuyenResource {

    private final Logger log = LoggerFactory.getLogger(QuanHuyenResource.class);

    private static final String ENTITY_NAME = "quanHuyen";

    private final QuanHuyenRepository quanHuyenRepository;

    private final QuanHuyenSearchRepository quanHuyenSearchRepository;

    public QuanHuyenResource(QuanHuyenRepository quanHuyenRepository, QuanHuyenSearchRepository quanHuyenSearchRepository) {
        this.quanHuyenRepository = quanHuyenRepository;
        this.quanHuyenSearchRepository = quanHuyenSearchRepository;
    }

    /**
     * POST  /quan-huyens : Create a new quanHuyen.
     *
     * @param quanHuyen the quanHuyen to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quanHuyen, or with status 400 (Bad Request) if the quanHuyen has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quan-huyens")
    @Timed
    public ResponseEntity<QuanHuyen> createQuanHuyen(@RequestBody QuanHuyen quanHuyen) throws URISyntaxException {
        log.debug("REST request to save QuanHuyen : {}", quanHuyen);
        if (quanHuyen.getId() != null) {
            throw new BadRequestAlertException("A new quanHuyen cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuanHuyen result = quanHuyenRepository.save(quanHuyen);
        quanHuyenSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/quan-huyens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quan-huyens : Updates an existing quanHuyen.
     *
     * @param quanHuyen the quanHuyen to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quanHuyen,
     * or with status 400 (Bad Request) if the quanHuyen is not valid,
     * or with status 500 (Internal Server Error) if the quanHuyen couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quan-huyens")
    @Timed
    public ResponseEntity<QuanHuyen> updateQuanHuyen(@RequestBody QuanHuyen quanHuyen) throws URISyntaxException {
        log.debug("REST request to update QuanHuyen : {}", quanHuyen);
        if (quanHuyen.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuanHuyen result = quanHuyenRepository.save(quanHuyen);
        quanHuyenSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quanHuyen.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quan-huyens : get all the quanHuyens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of quanHuyens in body
     */
    @GetMapping("/quan-huyens")
    @Timed
    public ResponseEntity<List<QuanHuyen>> getAllQuanHuyens(Pageable pageable) {
        log.debug("REST request to get a page of QuanHuyens");
        Page<QuanHuyen> page = quanHuyenRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/quan-huyens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /quan-huyens/:id : get the "id" quanHuyen.
     *
     * @param id the id of the quanHuyen to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quanHuyen, or with status 404 (Not Found)
     */
    @GetMapping("/quan-huyens/{id}")
    @Timed
    public ResponseEntity<QuanHuyen> getQuanHuyen(@PathVariable Long id) {
        log.debug("REST request to get QuanHuyen : {}", id);
        Optional<QuanHuyen> quanHuyen = quanHuyenRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quanHuyen);
    }

    /**
     * DELETE  /quan-huyens/:id : delete the "id" quanHuyen.
     *
     * @param id the id of the quanHuyen to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quan-huyens/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuanHuyen(@PathVariable Long id) {
        log.debug("REST request to delete QuanHuyen : {}", id);

        quanHuyenRepository.deleteById(id);
        quanHuyenSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quan-huyens?query=:query : search for the quanHuyen corresponding
     * to the query.
     *
     * @param query the query of the quanHuyen search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/quan-huyens")
    @Timed
    public ResponseEntity<List<QuanHuyen>> searchQuanHuyens(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of QuanHuyens for query {}", query);
        Page<QuanHuyen> page = quanHuyenSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/quan-huyens");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
