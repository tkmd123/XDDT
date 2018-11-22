package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.LoaiDiVat;
import vn.homtech.xddt.repository.LoaiDiVatRepository;
import vn.homtech.xddt.repository.search.LoaiDiVatSearchRepository;
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
 * REST controller for managing LoaiDiVat.
 */
@RestController
@RequestMapping("/api")
public class LoaiDiVatResource {

    private final Logger log = LoggerFactory.getLogger(LoaiDiVatResource.class);

    private static final String ENTITY_NAME = "loaiDiVat";

    private final LoaiDiVatRepository loaiDiVatRepository;

    private final LoaiDiVatSearchRepository loaiDiVatSearchRepository;

    public LoaiDiVatResource(LoaiDiVatRepository loaiDiVatRepository, LoaiDiVatSearchRepository loaiDiVatSearchRepository) {
        this.loaiDiVatRepository = loaiDiVatRepository;
        this.loaiDiVatSearchRepository = loaiDiVatSearchRepository;
    }

    /**
     * POST  /loai-di-vats : Create a new loaiDiVat.
     *
     * @param loaiDiVat the loaiDiVat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loaiDiVat, or with status 400 (Bad Request) if the loaiDiVat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loai-di-vats")
    @Timed
    public ResponseEntity<LoaiDiVat> createLoaiDiVat(@RequestBody LoaiDiVat loaiDiVat) throws URISyntaxException {
        log.debug("REST request to save LoaiDiVat : {}", loaiDiVat);
        if (loaiDiVat.getId() != null) {
            throw new BadRequestAlertException("A new loaiDiVat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiDiVat result = loaiDiVatRepository.save(loaiDiVat);
        loaiDiVatSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/loai-di-vats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loai-di-vats : Updates an existing loaiDiVat.
     *
     * @param loaiDiVat the loaiDiVat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loaiDiVat,
     * or with status 400 (Bad Request) if the loaiDiVat is not valid,
     * or with status 500 (Internal Server Error) if the loaiDiVat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loai-di-vats")
    @Timed
    public ResponseEntity<LoaiDiVat> updateLoaiDiVat(@RequestBody LoaiDiVat loaiDiVat) throws URISyntaxException {
        log.debug("REST request to update LoaiDiVat : {}", loaiDiVat);
        if (loaiDiVat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiDiVat result = loaiDiVatRepository.save(loaiDiVat);
        loaiDiVatSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loaiDiVat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loai-di-vats : get all the loaiDiVats.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiDiVats in body
     */
    @GetMapping("/loai-di-vats")
    @Timed
    public ResponseEntity<List<LoaiDiVat>> getAllLoaiDiVats(Pageable pageable) {
        log.debug("REST request to get a page of LoaiDiVats");
        Page<LoaiDiVat> page = loaiDiVatRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-di-vats");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-di-vats/:id : get the "id" loaiDiVat.
     *
     * @param id the id of the loaiDiVat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiDiVat, or with status 404 (Not Found)
     */
    @GetMapping("/loai-di-vats/{id}")
    @Timed
    public ResponseEntity<LoaiDiVat> getLoaiDiVat(@PathVariable Long id) {
        log.debug("REST request to get LoaiDiVat : {}", id);
        Optional<LoaiDiVat> loaiDiVat = loaiDiVatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loaiDiVat);
    }

    /**
     * DELETE  /loai-di-vats/:id : delete the "id" loaiDiVat.
     *
     * @param id the id of the loaiDiVat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loai-di-vats/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoaiDiVat(@PathVariable Long id) {
        log.debug("REST request to delete LoaiDiVat : {}", id);

        loaiDiVatRepository.deleteById(id);
        loaiDiVatSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/loai-di-vats?query=:query : search for the loaiDiVat corresponding
     * to the query.
     *
     * @param query the query of the loaiDiVat search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/loai-di-vats")
    @Timed
    public ResponseEntity<List<LoaiDiVat>> searchLoaiDiVats(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LoaiDiVats for query {}", query);
        Page<LoaiDiVat> page = loaiDiVatSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/loai-di-vats");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
