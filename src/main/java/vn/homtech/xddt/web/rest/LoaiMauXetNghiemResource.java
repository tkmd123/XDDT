package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.LoaiMauXetNghiem;
import vn.homtech.xddt.repository.LoaiMauXetNghiemRepository;
import vn.homtech.xddt.repository.search.LoaiMauXetNghiemSearchRepository;
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
 * REST controller for managing LoaiMauXetNghiem.
 */
@RestController
@RequestMapping("/api")
public class LoaiMauXetNghiemResource {

    private final Logger log = LoggerFactory.getLogger(LoaiMauXetNghiemResource.class);

    private static final String ENTITY_NAME = "loaiMauXetNghiem";

    private final LoaiMauXetNghiemRepository loaiMauXetNghiemRepository;

    private final LoaiMauXetNghiemSearchRepository loaiMauXetNghiemSearchRepository;

    public LoaiMauXetNghiemResource(LoaiMauXetNghiemRepository loaiMauXetNghiemRepository, LoaiMauXetNghiemSearchRepository loaiMauXetNghiemSearchRepository) {
        this.loaiMauXetNghiemRepository = loaiMauXetNghiemRepository;
        this.loaiMauXetNghiemSearchRepository = loaiMauXetNghiemSearchRepository;
    }

    /**
     * POST  /loai-mau-xet-nghiems : Create a new loaiMauXetNghiem.
     *
     * @param loaiMauXetNghiem the loaiMauXetNghiem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loaiMauXetNghiem, or with status 400 (Bad Request) if the loaiMauXetNghiem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loai-mau-xet-nghiems")
    @Timed
    public ResponseEntity<LoaiMauXetNghiem> createLoaiMauXetNghiem(@RequestBody LoaiMauXetNghiem loaiMauXetNghiem) throws URISyntaxException {
        log.debug("REST request to save LoaiMauXetNghiem : {}", loaiMauXetNghiem);
        if (loaiMauXetNghiem.getId() != null) {
            throw new BadRequestAlertException("A new loaiMauXetNghiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiMauXetNghiem result = loaiMauXetNghiemRepository.save(loaiMauXetNghiem);
        loaiMauXetNghiemSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/loai-mau-xet-nghiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loai-mau-xet-nghiems : Updates an existing loaiMauXetNghiem.
     *
     * @param loaiMauXetNghiem the loaiMauXetNghiem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loaiMauXetNghiem,
     * or with status 400 (Bad Request) if the loaiMauXetNghiem is not valid,
     * or with status 500 (Internal Server Error) if the loaiMauXetNghiem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loai-mau-xet-nghiems")
    @Timed
    public ResponseEntity<LoaiMauXetNghiem> updateLoaiMauXetNghiem(@RequestBody LoaiMauXetNghiem loaiMauXetNghiem) throws URISyntaxException {
        log.debug("REST request to update LoaiMauXetNghiem : {}", loaiMauXetNghiem);
        if (loaiMauXetNghiem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiMauXetNghiem result = loaiMauXetNghiemRepository.save(loaiMauXetNghiem);
        loaiMauXetNghiemSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loaiMauXetNghiem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loai-mau-xet-nghiems : get all the loaiMauXetNghiems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiMauXetNghiems in body
     */
    @GetMapping("/loai-mau-xet-nghiems")
    @Timed
    public ResponseEntity<List<LoaiMauXetNghiem>> getAllLoaiMauXetNghiems(Pageable pageable) {
        log.debug("REST request to get a page of LoaiMauXetNghiems");
        Page<LoaiMauXetNghiem> page = loaiMauXetNghiemRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-mau-xet-nghiems");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-mau-xet-nghiems/:id : get the "id" loaiMauXetNghiem.
     *
     * @param id the id of the loaiMauXetNghiem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiMauXetNghiem, or with status 404 (Not Found)
     */
    @GetMapping("/loai-mau-xet-nghiems/{id}")
    @Timed
    public ResponseEntity<LoaiMauXetNghiem> getLoaiMauXetNghiem(@PathVariable Long id) {
        log.debug("REST request to get LoaiMauXetNghiem : {}", id);
        Optional<LoaiMauXetNghiem> loaiMauXetNghiem = loaiMauXetNghiemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loaiMauXetNghiem);
    }

    /**
     * DELETE  /loai-mau-xet-nghiems/:id : delete the "id" loaiMauXetNghiem.
     *
     * @param id the id of the loaiMauXetNghiem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loai-mau-xet-nghiems/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoaiMauXetNghiem(@PathVariable Long id) {
        log.debug("REST request to delete LoaiMauXetNghiem : {}", id);

        loaiMauXetNghiemRepository.deleteById(id);
        loaiMauXetNghiemSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/loai-mau-xet-nghiems?query=:query : search for the loaiMauXetNghiem corresponding
     * to the query.
     *
     * @param query the query of the loaiMauXetNghiem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/loai-mau-xet-nghiems")
    @Timed
    public ResponseEntity<List<LoaiMauXetNghiem>> searchLoaiMauXetNghiems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LoaiMauXetNghiems for query {}", query);
        Page<LoaiMauXetNghiem> page = loaiMauXetNghiemSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/loai-mau-xet-nghiems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
