package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.ThongTinADN;
import vn.homtech.xddt.repository.ThongTinADNRepository;
import vn.homtech.xddt.repository.search.ThongTinADNSearchRepository;
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
 * REST controller for managing ThongTinADN.
 */
@RestController
@RequestMapping("/api")
public class ThongTinADNResource {

    private final Logger log = LoggerFactory.getLogger(ThongTinADNResource.class);

    private static final String ENTITY_NAME = "thongTinADN";

    private final ThongTinADNRepository thongTinADNRepository;

    private final ThongTinADNSearchRepository thongTinADNSearchRepository;

    public ThongTinADNResource(ThongTinADNRepository thongTinADNRepository, ThongTinADNSearchRepository thongTinADNSearchRepository) {
        this.thongTinADNRepository = thongTinADNRepository;
        this.thongTinADNSearchRepository = thongTinADNSearchRepository;
    }

    /**
     * POST  /thong-tin-adns : Create a new thongTinADN.
     *
     * @param thongTinADN the thongTinADN to create
     * @return the ResponseEntity with status 201 (Created) and with body the new thongTinADN, or with status 400 (Bad Request) if the thongTinADN has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/thong-tin-adns")
    @Timed
    public ResponseEntity<ThongTinADN> createThongTinADN(@RequestBody ThongTinADN thongTinADN) throws URISyntaxException {
        log.debug("REST request to save ThongTinADN : {}", thongTinADN);
        if (thongTinADN.getId() != null) {
            throw new BadRequestAlertException("A new thongTinADN cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ThongTinADN result = thongTinADNRepository.save(thongTinADN);
        thongTinADNSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/thong-tin-adns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /thong-tin-adns : Updates an existing thongTinADN.
     *
     * @param thongTinADN the thongTinADN to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated thongTinADN,
     * or with status 400 (Bad Request) if the thongTinADN is not valid,
     * or with status 500 (Internal Server Error) if the thongTinADN couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/thong-tin-adns")
    @Timed
    public ResponseEntity<ThongTinADN> updateThongTinADN(@RequestBody ThongTinADN thongTinADN) throws URISyntaxException {
        log.debug("REST request to update ThongTinADN : {}", thongTinADN);
        if (thongTinADN.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ThongTinADN result = thongTinADNRepository.save(thongTinADN);
        thongTinADNSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, thongTinADN.getId().toString()))
            .body(result);
    }

    /**
     * GET  /thong-tin-adns : get all the thongTinADNS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of thongTinADNS in body
     */
    @GetMapping("/thong-tin-adns")
    @Timed
    public ResponseEntity<List<ThongTinADN>> getAllThongTinADNS(Pageable pageable) {
        log.debug("REST request to get a page of ThongTinADNS");
        Page<ThongTinADN> page = thongTinADNRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/thong-tin-adns");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /thong-tin-adns/:id : get the "id" thongTinADN.
     *
     * @param id the id of the thongTinADN to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the thongTinADN, or with status 404 (Not Found)
     */
    @GetMapping("/thong-tin-adns/{id}")
    @Timed
    public ResponseEntity<ThongTinADN> getThongTinADN(@PathVariable Long id) {
        log.debug("REST request to get ThongTinADN : {}", id);
        Optional<ThongTinADN> thongTinADN = thongTinADNRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(thongTinADN);
    }

    /**
     * DELETE  /thong-tin-adns/:id : delete the "id" thongTinADN.
     *
     * @param id the id of the thongTinADN to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/thong-tin-adns/{id}")
    @Timed
    public ResponseEntity<Void> deleteThongTinADN(@PathVariable Long id) {
        log.debug("REST request to delete ThongTinADN : {}", id);

        thongTinADNRepository.deleteById(id);
        thongTinADNSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/thong-tin-adns?query=:query : search for the thongTinADN corresponding
     * to the query.
     *
     * @param query the query of the thongTinADN search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/thong-tin-adns")
    @Timed
    public ResponseEntity<List<ThongTinADN>> searchThongTinADNS(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ThongTinADNS for query {}", query);
        Page<ThongTinADN> page = thongTinADNSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/thong-tin-adns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
