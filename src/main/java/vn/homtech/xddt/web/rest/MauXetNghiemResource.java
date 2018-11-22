package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.MauXetNghiem;
import vn.homtech.xddt.repository.MauXetNghiemRepository;
import vn.homtech.xddt.repository.search.MauXetNghiemSearchRepository;
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
 * REST controller for managing MauXetNghiem.
 */
@RestController
@RequestMapping("/api")
public class MauXetNghiemResource {

    private final Logger log = LoggerFactory.getLogger(MauXetNghiemResource.class);

    private static final String ENTITY_NAME = "mauXetNghiem";

    private final MauXetNghiemRepository mauXetNghiemRepository;

    private final MauXetNghiemSearchRepository mauXetNghiemSearchRepository;

    public MauXetNghiemResource(MauXetNghiemRepository mauXetNghiemRepository, MauXetNghiemSearchRepository mauXetNghiemSearchRepository) {
        this.mauXetNghiemRepository = mauXetNghiemRepository;
        this.mauXetNghiemSearchRepository = mauXetNghiemSearchRepository;
    }

    /**
     * POST  /mau-xet-nghiems : Create a new mauXetNghiem.
     *
     * @param mauXetNghiem the mauXetNghiem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mauXetNghiem, or with status 400 (Bad Request) if the mauXetNghiem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mau-xet-nghiems")
    @Timed
    public ResponseEntity<MauXetNghiem> createMauXetNghiem(@RequestBody MauXetNghiem mauXetNghiem) throws URISyntaxException {
        log.debug("REST request to save MauXetNghiem : {}", mauXetNghiem);
        if (mauXetNghiem.getId() != null) {
            throw new BadRequestAlertException("A new mauXetNghiem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MauXetNghiem result = mauXetNghiemRepository.save(mauXetNghiem);
        mauXetNghiemSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/mau-xet-nghiems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mau-xet-nghiems : Updates an existing mauXetNghiem.
     *
     * @param mauXetNghiem the mauXetNghiem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mauXetNghiem,
     * or with status 400 (Bad Request) if the mauXetNghiem is not valid,
     * or with status 500 (Internal Server Error) if the mauXetNghiem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mau-xet-nghiems")
    @Timed
    public ResponseEntity<MauXetNghiem> updateMauXetNghiem(@RequestBody MauXetNghiem mauXetNghiem) throws URISyntaxException {
        log.debug("REST request to update MauXetNghiem : {}", mauXetNghiem);
        if (mauXetNghiem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MauXetNghiem result = mauXetNghiemRepository.save(mauXetNghiem);
        mauXetNghiemSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mauXetNghiem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mau-xet-nghiems : get all the mauXetNghiems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mauXetNghiems in body
     */
    @GetMapping("/mau-xet-nghiems")
    @Timed
    public ResponseEntity<List<MauXetNghiem>> getAllMauXetNghiems(Pageable pageable) {
        log.debug("REST request to get a page of MauXetNghiems");
        Page<MauXetNghiem> page = mauXetNghiemRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mau-xet-nghiems");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /mau-xet-nghiems/:id : get the "id" mauXetNghiem.
     *
     * @param id the id of the mauXetNghiem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mauXetNghiem, or with status 404 (Not Found)
     */
    @GetMapping("/mau-xet-nghiems/{id}")
    @Timed
    public ResponseEntity<MauXetNghiem> getMauXetNghiem(@PathVariable Long id) {
        log.debug("REST request to get MauXetNghiem : {}", id);
        Optional<MauXetNghiem> mauXetNghiem = mauXetNghiemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mauXetNghiem);
    }

    /**
     * DELETE  /mau-xet-nghiems/:id : delete the "id" mauXetNghiem.
     *
     * @param id the id of the mauXetNghiem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mau-xet-nghiems/{id}")
    @Timed
    public ResponseEntity<Void> deleteMauXetNghiem(@PathVariable Long id) {
        log.debug("REST request to delete MauXetNghiem : {}", id);

        mauXetNghiemRepository.deleteById(id);
        mauXetNghiemSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/mau-xet-nghiems?query=:query : search for the mauXetNghiem corresponding
     * to the query.
     *
     * @param query the query of the mauXetNghiem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/mau-xet-nghiems")
    @Timed
    public ResponseEntity<List<MauXetNghiem>> searchMauXetNghiems(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of MauXetNghiems for query {}", query);
        Page<MauXetNghiem> page = mauXetNghiemSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/mau-xet-nghiems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
