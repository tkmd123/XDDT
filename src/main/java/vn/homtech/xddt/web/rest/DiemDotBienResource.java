package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.DiemDotBien;
import vn.homtech.xddt.repository.DiemDotBienRepository;
import vn.homtech.xddt.repository.search.DiemDotBienSearchRepository;
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
 * REST controller for managing DiemDotBien.
 */
@RestController
@RequestMapping("/api")
public class DiemDotBienResource {

    private final Logger log = LoggerFactory.getLogger(DiemDotBienResource.class);

    private static final String ENTITY_NAME = "diemDotBien";

    private final DiemDotBienRepository diemDotBienRepository;

    private final DiemDotBienSearchRepository diemDotBienSearchRepository;

    public DiemDotBienResource(DiemDotBienRepository diemDotBienRepository, DiemDotBienSearchRepository diemDotBienSearchRepository) {
        this.diemDotBienRepository = diemDotBienRepository;
        this.diemDotBienSearchRepository = diemDotBienSearchRepository;
    }

    /**
     * POST  /diem-dot-biens : Create a new diemDotBien.
     *
     * @param diemDotBien the diemDotBien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diemDotBien, or with status 400 (Bad Request) if the diemDotBien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/diem-dot-biens")
    @Timed
    public ResponseEntity<DiemDotBien> createDiemDotBien(@RequestBody DiemDotBien diemDotBien) throws URISyntaxException {
        log.debug("REST request to save DiemDotBien : {}", diemDotBien);
        if (diemDotBien.getId() != null) {
            throw new BadRequestAlertException("A new diemDotBien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiemDotBien result = diemDotBienRepository.save(diemDotBien);
        diemDotBienSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/diem-dot-biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diem-dot-biens : Updates an existing diemDotBien.
     *
     * @param diemDotBien the diemDotBien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diemDotBien,
     * or with status 400 (Bad Request) if the diemDotBien is not valid,
     * or with status 500 (Internal Server Error) if the diemDotBien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/diem-dot-biens")
    @Timed
    public ResponseEntity<DiemDotBien> updateDiemDotBien(@RequestBody DiemDotBien diemDotBien) throws URISyntaxException {
        log.debug("REST request to update DiemDotBien : {}", diemDotBien);
        if (diemDotBien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiemDotBien result = diemDotBienRepository.save(diemDotBien);
        diemDotBienSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diemDotBien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diem-dot-biens : get all the diemDotBiens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diemDotBiens in body
     */
    @GetMapping("/diem-dot-biens")
    @Timed
    public ResponseEntity<List<DiemDotBien>> getAllDiemDotBiens(Pageable pageable) {
        log.debug("REST request to get a page of DiemDotBiens");
        Page<DiemDotBien> page = diemDotBienRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/diem-dot-biens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /diem-dot-biens/:id : get the "id" diemDotBien.
     *
     * @param id the id of the diemDotBien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diemDotBien, or with status 404 (Not Found)
     */
    @GetMapping("/diem-dot-biens/{id}")
    @Timed
    public ResponseEntity<DiemDotBien> getDiemDotBien(@PathVariable Long id) {
        log.debug("REST request to get DiemDotBien : {}", id);
        Optional<DiemDotBien> diemDotBien = diemDotBienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(diemDotBien);
    }

    /**
     * DELETE  /diem-dot-biens/:id : delete the "id" diemDotBien.
     *
     * @param id the id of the diemDotBien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/diem-dot-biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiemDotBien(@PathVariable Long id) {
        log.debug("REST request to delete DiemDotBien : {}", id);

        diemDotBienRepository.deleteById(id);
        diemDotBienSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/diem-dot-biens?query=:query : search for the diemDotBien corresponding
     * to the query.
     *
     * @param query the query of the diemDotBien search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/diem-dot-biens")
    @Timed
    public ResponseEntity<List<DiemDotBien>> searchDiemDotBiens(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of DiemDotBiens for query {}", query);
        Page<DiemDotBien> page = diemDotBienSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/diem-dot-biens");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
