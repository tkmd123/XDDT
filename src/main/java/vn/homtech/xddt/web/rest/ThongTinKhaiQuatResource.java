package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.ThongTinKhaiQuat;
import vn.homtech.xddt.repository.ThongTinKhaiQuatRepository;
import vn.homtech.xddt.repository.search.ThongTinKhaiQuatSearchRepository;
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
 * REST controller for managing ThongTinKhaiQuat.
 */
@RestController
@RequestMapping("/api")
public class ThongTinKhaiQuatResource {

    private final Logger log = LoggerFactory.getLogger(ThongTinKhaiQuatResource.class);

    private static final String ENTITY_NAME = "thongTinKhaiQuat";

    private final ThongTinKhaiQuatRepository thongTinKhaiQuatRepository;

    private final ThongTinKhaiQuatSearchRepository thongTinKhaiQuatSearchRepository;

    public ThongTinKhaiQuatResource(ThongTinKhaiQuatRepository thongTinKhaiQuatRepository, ThongTinKhaiQuatSearchRepository thongTinKhaiQuatSearchRepository) {
        this.thongTinKhaiQuatRepository = thongTinKhaiQuatRepository;
        this.thongTinKhaiQuatSearchRepository = thongTinKhaiQuatSearchRepository;
    }

    /**
     * POST  /thong-tin-khai-quats : Create a new thongTinKhaiQuat.
     *
     * @param thongTinKhaiQuat the thongTinKhaiQuat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new thongTinKhaiQuat, or with status 400 (Bad Request) if the thongTinKhaiQuat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/thong-tin-khai-quats")
    @Timed
    public ResponseEntity<ThongTinKhaiQuat> createThongTinKhaiQuat(@RequestBody ThongTinKhaiQuat thongTinKhaiQuat) throws URISyntaxException {
        log.debug("REST request to save ThongTinKhaiQuat : {}", thongTinKhaiQuat);
        if (thongTinKhaiQuat.getId() != null) {
            throw new BadRequestAlertException("A new thongTinKhaiQuat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongTinKhaiQuat result = thongTinKhaiQuatRepository.save(thongTinKhaiQuat);
        thongTinKhaiQuatSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/thong-tin-khai-quats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /thong-tin-khai-quats : Updates an existing thongTinKhaiQuat.
     *
     * @param thongTinKhaiQuat the thongTinKhaiQuat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated thongTinKhaiQuat,
     * or with status 400 (Bad Request) if the thongTinKhaiQuat is not valid,
     * or with status 500 (Internal Server Error) if the thongTinKhaiQuat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/thong-tin-khai-quats")
    @Timed
    public ResponseEntity<ThongTinKhaiQuat> updateThongTinKhaiQuat(@RequestBody ThongTinKhaiQuat thongTinKhaiQuat) throws URISyntaxException {
        log.debug("REST request to update ThongTinKhaiQuat : {}", thongTinKhaiQuat);
        if (thongTinKhaiQuat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongTinKhaiQuat result = thongTinKhaiQuatRepository.save(thongTinKhaiQuat);
        thongTinKhaiQuatSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, thongTinKhaiQuat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /thong-tin-khai-quats : get all the thongTinKhaiQuats.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of thongTinKhaiQuats in body
     */
    @GetMapping("/thong-tin-khai-quats")
    @Timed
    public ResponseEntity<List<ThongTinKhaiQuat>> getAllThongTinKhaiQuats(Pageable pageable) {
        log.debug("REST request to get a page of ThongTinKhaiQuats");
        Page<ThongTinKhaiQuat> page = thongTinKhaiQuatRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/thong-tin-khai-quats");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /thong-tin-khai-quats/:id : get the "id" thongTinKhaiQuat.
     *
     * @param id the id of the thongTinKhaiQuat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the thongTinKhaiQuat, or with status 404 (Not Found)
     */
    @GetMapping("/thong-tin-khai-quats/{id}")
    @Timed
    public ResponseEntity<ThongTinKhaiQuat> getThongTinKhaiQuat(@PathVariable Long id) {
        log.debug("REST request to get ThongTinKhaiQuat : {}", id);
        Optional<ThongTinKhaiQuat> thongTinKhaiQuat = thongTinKhaiQuatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thongTinKhaiQuat);
    }

    /**
     * DELETE  /thong-tin-khai-quats/:id : delete the "id" thongTinKhaiQuat.
     *
     * @param id the id of the thongTinKhaiQuat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/thong-tin-khai-quats/{id}")
    @Timed
    public ResponseEntity<Void> deleteThongTinKhaiQuat(@PathVariable Long id) {
        log.debug("REST request to delete ThongTinKhaiQuat : {}", id);

        thongTinKhaiQuatRepository.deleteById(id);
        thongTinKhaiQuatSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/thong-tin-khai-quats?query=:query : search for the thongTinKhaiQuat corresponding
     * to the query.
     *
     * @param query the query of the thongTinKhaiQuat search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/thong-tin-khai-quats")
    @Timed
    public ResponseEntity<List<ThongTinKhaiQuat>> searchThongTinKhaiQuats(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ThongTinKhaiQuats for query {}", query);
        Page<ThongTinKhaiQuat> page = thongTinKhaiQuatSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/thong-tin-khai-quats");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
