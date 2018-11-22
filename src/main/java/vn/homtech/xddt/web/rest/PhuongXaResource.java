package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PhuongXa;
import vn.homtech.xddt.repository.PhuongXaRepository;
import vn.homtech.xddt.repository.search.PhuongXaSearchRepository;
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
 * REST controller for managing PhuongXa.
 */
@RestController
@RequestMapping("/api")
public class PhuongXaResource {

    private final Logger log = LoggerFactory.getLogger(PhuongXaResource.class);

    private static final String ENTITY_NAME = "phuongXa";

    private final PhuongXaRepository phuongXaRepository;

    private final PhuongXaSearchRepository phuongXaSearchRepository;

    public PhuongXaResource(PhuongXaRepository phuongXaRepository, PhuongXaSearchRepository phuongXaSearchRepository) {
        this.phuongXaRepository = phuongXaRepository;
        this.phuongXaSearchRepository = phuongXaSearchRepository;
    }

    /**
     * POST  /phuong-xas : Create a new phuongXa.
     *
     * @param phuongXa the phuongXa to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phuongXa, or with status 400 (Bad Request) if the phuongXa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/phuong-xas")
    @Timed
    public ResponseEntity<PhuongXa> createPhuongXa(@RequestBody PhuongXa phuongXa) throws URISyntaxException {
        log.debug("REST request to save PhuongXa : {}", phuongXa);
        if (phuongXa.getId() != null) {
            throw new BadRequestAlertException("A new phuongXa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhuongXa result = phuongXaRepository.save(phuongXa);
        phuongXaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/phuong-xas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /phuong-xas : Updates an existing phuongXa.
     *
     * @param phuongXa the phuongXa to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated phuongXa,
     * or with status 400 (Bad Request) if the phuongXa is not valid,
     * or with status 500 (Internal Server Error) if the phuongXa couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/phuong-xas")
    @Timed
    public ResponseEntity<PhuongXa> updatePhuongXa(@RequestBody PhuongXa phuongXa) throws URISyntaxException {
        log.debug("REST request to update PhuongXa : {}", phuongXa);
        if (phuongXa.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhuongXa result = phuongXaRepository.save(phuongXa);
        phuongXaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, phuongXa.getId().toString()))
            .body(result);
    }

    /**
     * GET  /phuong-xas : get all the phuongXas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of phuongXas in body
     */
    @GetMapping("/phuong-xas")
    @Timed
    public ResponseEntity<List<PhuongXa>> getAllPhuongXas(Pageable pageable) {
        log.debug("REST request to get a page of PhuongXas");
        Page<PhuongXa> page = phuongXaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/phuong-xas");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /phuong-xas/:id : get the "id" phuongXa.
     *
     * @param id the id of the phuongXa to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the phuongXa, or with status 404 (Not Found)
     */
    @GetMapping("/phuong-xas/{id}")
    @Timed
    public ResponseEntity<PhuongXa> getPhuongXa(@PathVariable Long id) {
        log.debug("REST request to get PhuongXa : {}", id);
        Optional<PhuongXa> phuongXa = phuongXaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(phuongXa);
    }

    /**
     * DELETE  /phuong-xas/:id : delete the "id" phuongXa.
     *
     * @param id the id of the phuongXa to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/phuong-xas/{id}")
    @Timed
    public ResponseEntity<Void> deletePhuongXa(@PathVariable Long id) {
        log.debug("REST request to delete PhuongXa : {}", id);

        phuongXaRepository.deleteById(id);
        phuongXaSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/phuong-xas?query=:query : search for the phuongXa corresponding
     * to the query.
     *
     * @param query the query of the phuongXa search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/phuong-xas")
    @Timed
    public ResponseEntity<List<PhuongXa>> searchPhuongXas(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PhuongXas for query {}", query);
        Page<PhuongXa> page = phuongXaSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/phuong-xas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
