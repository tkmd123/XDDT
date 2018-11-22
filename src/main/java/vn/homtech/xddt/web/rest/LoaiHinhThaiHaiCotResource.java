package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.LoaiHinhThaiHaiCot;
import vn.homtech.xddt.repository.LoaiHinhThaiHaiCotRepository;
import vn.homtech.xddt.repository.search.LoaiHinhThaiHaiCotSearchRepository;
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
 * REST controller for managing LoaiHinhThaiHaiCot.
 */
@RestController
@RequestMapping("/api")
public class LoaiHinhThaiHaiCotResource {

    private final Logger log = LoggerFactory.getLogger(LoaiHinhThaiHaiCotResource.class);

    private static final String ENTITY_NAME = "loaiHinhThaiHaiCot";

    private final LoaiHinhThaiHaiCotRepository loaiHinhThaiHaiCotRepository;

    private final LoaiHinhThaiHaiCotSearchRepository loaiHinhThaiHaiCotSearchRepository;

    public LoaiHinhThaiHaiCotResource(LoaiHinhThaiHaiCotRepository loaiHinhThaiHaiCotRepository, LoaiHinhThaiHaiCotSearchRepository loaiHinhThaiHaiCotSearchRepository) {
        this.loaiHinhThaiHaiCotRepository = loaiHinhThaiHaiCotRepository;
        this.loaiHinhThaiHaiCotSearchRepository = loaiHinhThaiHaiCotSearchRepository;
    }

    /**
     * POST  /loai-hinh-thai-hai-cots : Create a new loaiHinhThaiHaiCot.
     *
     * @param loaiHinhThaiHaiCot the loaiHinhThaiHaiCot to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loaiHinhThaiHaiCot, or with status 400 (Bad Request) if the loaiHinhThaiHaiCot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loai-hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<LoaiHinhThaiHaiCot> createLoaiHinhThaiHaiCot(@RequestBody LoaiHinhThaiHaiCot loaiHinhThaiHaiCot) throws URISyntaxException {
        log.debug("REST request to save LoaiHinhThaiHaiCot : {}", loaiHinhThaiHaiCot);
        if (loaiHinhThaiHaiCot.getId() != null) {
            throw new BadRequestAlertException("A new loaiHinhThaiHaiCot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiHinhThaiHaiCot result = loaiHinhThaiHaiCotRepository.save(loaiHinhThaiHaiCot);
        loaiHinhThaiHaiCotSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/loai-hinh-thai-hai-cots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loai-hinh-thai-hai-cots : Updates an existing loaiHinhThaiHaiCot.
     *
     * @param loaiHinhThaiHaiCot the loaiHinhThaiHaiCot to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loaiHinhThaiHaiCot,
     * or with status 400 (Bad Request) if the loaiHinhThaiHaiCot is not valid,
     * or with status 500 (Internal Server Error) if the loaiHinhThaiHaiCot couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loai-hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<LoaiHinhThaiHaiCot> updateLoaiHinhThaiHaiCot(@RequestBody LoaiHinhThaiHaiCot loaiHinhThaiHaiCot) throws URISyntaxException {
        log.debug("REST request to update LoaiHinhThaiHaiCot : {}", loaiHinhThaiHaiCot);
        if (loaiHinhThaiHaiCot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiHinhThaiHaiCot result = loaiHinhThaiHaiCotRepository.save(loaiHinhThaiHaiCot);
        loaiHinhThaiHaiCotSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loaiHinhThaiHaiCot.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loai-hinh-thai-hai-cots : get all the loaiHinhThaiHaiCots.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiHinhThaiHaiCots in body
     */
    @GetMapping("/loai-hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<List<LoaiHinhThaiHaiCot>> getAllLoaiHinhThaiHaiCots(Pageable pageable) {
        log.debug("REST request to get a page of LoaiHinhThaiHaiCots");
        Page<LoaiHinhThaiHaiCot> page = loaiHinhThaiHaiCotRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-hinh-thai-hai-cots");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-hinh-thai-hai-cots/:id : get the "id" loaiHinhThaiHaiCot.
     *
     * @param id the id of the loaiHinhThaiHaiCot to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiHinhThaiHaiCot, or with status 404 (Not Found)
     */
    @GetMapping("/loai-hinh-thai-hai-cots/{id}")
    @Timed
    public ResponseEntity<LoaiHinhThaiHaiCot> getLoaiHinhThaiHaiCot(@PathVariable Long id) {
        log.debug("REST request to get LoaiHinhThaiHaiCot : {}", id);
        Optional<LoaiHinhThaiHaiCot> loaiHinhThaiHaiCot = loaiHinhThaiHaiCotRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loaiHinhThaiHaiCot);
    }

    /**
     * DELETE  /loai-hinh-thai-hai-cots/:id : delete the "id" loaiHinhThaiHaiCot.
     *
     * @param id the id of the loaiHinhThaiHaiCot to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loai-hinh-thai-hai-cots/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoaiHinhThaiHaiCot(@PathVariable Long id) {
        log.debug("REST request to delete LoaiHinhThaiHaiCot : {}", id);

        loaiHinhThaiHaiCotRepository.deleteById(id);
        loaiHinhThaiHaiCotSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/loai-hinh-thai-hai-cots?query=:query : search for the loaiHinhThaiHaiCot corresponding
     * to the query.
     *
     * @param query the query of the loaiHinhThaiHaiCot search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/loai-hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<List<LoaiHinhThaiHaiCot>> searchLoaiHinhThaiHaiCots(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LoaiHinhThaiHaiCots for query {}", query);
        Page<LoaiHinhThaiHaiCot> page = loaiHinhThaiHaiCotSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/loai-hinh-thai-hai-cots");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
