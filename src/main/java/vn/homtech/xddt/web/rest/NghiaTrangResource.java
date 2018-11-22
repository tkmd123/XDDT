package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.NghiaTrang;
import vn.homtech.xddt.repository.NghiaTrangRepository;
import vn.homtech.xddt.repository.search.NghiaTrangSearchRepository;
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
 * REST controller for managing NghiaTrang.
 */
@RestController
@RequestMapping("/api")
public class NghiaTrangResource {

    private final Logger log = LoggerFactory.getLogger(NghiaTrangResource.class);

    private static final String ENTITY_NAME = "nghiaTrang";

    private final NghiaTrangRepository nghiaTrangRepository;

    private final NghiaTrangSearchRepository nghiaTrangSearchRepository;

    public NghiaTrangResource(NghiaTrangRepository nghiaTrangRepository, NghiaTrangSearchRepository nghiaTrangSearchRepository) {
        this.nghiaTrangRepository = nghiaTrangRepository;
        this.nghiaTrangSearchRepository = nghiaTrangSearchRepository;
    }

    /**
     * POST  /nghia-trangs : Create a new nghiaTrang.
     *
     * @param nghiaTrang the nghiaTrang to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nghiaTrang, or with status 400 (Bad Request) if the nghiaTrang has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nghia-trangs")
    @Timed
    public ResponseEntity<NghiaTrang> createNghiaTrang(@RequestBody NghiaTrang nghiaTrang) throws URISyntaxException {
        log.debug("REST request to save NghiaTrang : {}", nghiaTrang);
        if (nghiaTrang.getId() != null) {
            throw new BadRequestAlertException("A new nghiaTrang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NghiaTrang result = nghiaTrangRepository.save(nghiaTrang);
        nghiaTrangSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/nghia-trangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nghia-trangs : Updates an existing nghiaTrang.
     *
     * @param nghiaTrang the nghiaTrang to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nghiaTrang,
     * or with status 400 (Bad Request) if the nghiaTrang is not valid,
     * or with status 500 (Internal Server Error) if the nghiaTrang couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nghia-trangs")
    @Timed
    public ResponseEntity<NghiaTrang> updateNghiaTrang(@RequestBody NghiaTrang nghiaTrang) throws URISyntaxException {
        log.debug("REST request to update NghiaTrang : {}", nghiaTrang);
        if (nghiaTrang.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NghiaTrang result = nghiaTrangRepository.save(nghiaTrang);
        nghiaTrangSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nghiaTrang.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nghia-trangs : get all the nghiaTrangs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of nghiaTrangs in body
     */
    @GetMapping("/nghia-trangs")
    @Timed
    public ResponseEntity<List<NghiaTrang>> getAllNghiaTrangs(Pageable pageable) {
        log.debug("REST request to get a page of NghiaTrangs");
        Page<NghiaTrang> page = nghiaTrangRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nghia-trangs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /nghia-trangs/:id : get the "id" nghiaTrang.
     *
     * @param id the id of the nghiaTrang to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nghiaTrang, or with status 404 (Not Found)
     */
    @GetMapping("/nghia-trangs/{id}")
    @Timed
    public ResponseEntity<NghiaTrang> getNghiaTrang(@PathVariable Long id) {
        log.debug("REST request to get NghiaTrang : {}", id);
        Optional<NghiaTrang> nghiaTrang = nghiaTrangRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nghiaTrang);
    }

    /**
     * DELETE  /nghia-trangs/:id : delete the "id" nghiaTrang.
     *
     * @param id the id of the nghiaTrang to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nghia-trangs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNghiaTrang(@PathVariable Long id) {
        log.debug("REST request to delete NghiaTrang : {}", id);

        nghiaTrangRepository.deleteById(id);
        nghiaTrangSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/nghia-trangs?query=:query : search for the nghiaTrang corresponding
     * to the query.
     *
     * @param query the query of the nghiaTrang search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/nghia-trangs")
    @Timed
    public ResponseEntity<List<NghiaTrang>> searchNghiaTrangs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of NghiaTrangs for query {}", query);
        Page<NghiaTrang> page = nghiaTrangSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/nghia-trangs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
