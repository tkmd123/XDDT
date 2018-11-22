package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoSoLietSi;
import vn.homtech.xddt.repository.HoSoLietSiRepository;
import vn.homtech.xddt.repository.search.HoSoLietSiSearchRepository;
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
 * REST controller for managing HoSoLietSi.
 */
@RestController
@RequestMapping("/api")
public class HoSoLietSiResource {

    private final Logger log = LoggerFactory.getLogger(HoSoLietSiResource.class);

    private static final String ENTITY_NAME = "hoSoLietSi";

    private final HoSoLietSiRepository hoSoLietSiRepository;

    private final HoSoLietSiSearchRepository hoSoLietSiSearchRepository;

    public HoSoLietSiResource(HoSoLietSiRepository hoSoLietSiRepository, HoSoLietSiSearchRepository hoSoLietSiSearchRepository) {
        this.hoSoLietSiRepository = hoSoLietSiRepository;
        this.hoSoLietSiSearchRepository = hoSoLietSiSearchRepository;
    }

    /**
     * POST  /ho-so-liet-sis : Create a new hoSoLietSi.
     *
     * @param hoSoLietSi the hoSoLietSi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoSoLietSi, or with status 400 (Bad Request) if the hoSoLietSi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ho-so-liet-sis")
    @Timed
    public ResponseEntity<HoSoLietSi> createHoSoLietSi(@RequestBody HoSoLietSi hoSoLietSi) throws URISyntaxException {
        log.debug("REST request to save HoSoLietSi : {}", hoSoLietSi);
        if (hoSoLietSi.getId() != null) {
            throw new BadRequestAlertException("A new hoSoLietSi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoSoLietSi result = hoSoLietSiRepository.save(hoSoLietSi);
        hoSoLietSiSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ho-so-liet-sis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ho-so-liet-sis : Updates an existing hoSoLietSi.
     *
     * @param hoSoLietSi the hoSoLietSi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoSoLietSi,
     * or with status 400 (Bad Request) if the hoSoLietSi is not valid,
     * or with status 500 (Internal Server Error) if the hoSoLietSi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ho-so-liet-sis")
    @Timed
    public ResponseEntity<HoSoLietSi> updateHoSoLietSi(@RequestBody HoSoLietSi hoSoLietSi) throws URISyntaxException {
        log.debug("REST request to update HoSoLietSi : {}", hoSoLietSi);
        if (hoSoLietSi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoSoLietSi result = hoSoLietSiRepository.save(hoSoLietSi);
        hoSoLietSiSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoSoLietSi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ho-so-liet-sis : get all the hoSoLietSis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hoSoLietSis in body
     */
    @GetMapping("/ho-so-liet-sis")
    @Timed
    public ResponseEntity<List<HoSoLietSi>> getAllHoSoLietSis(Pageable pageable) {
        log.debug("REST request to get a page of HoSoLietSis");
        Page<HoSoLietSi> page = hoSoLietSiRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ho-so-liet-sis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ho-so-liet-sis/:id : get the "id" hoSoLietSi.
     *
     * @param id the id of the hoSoLietSi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoSoLietSi, or with status 404 (Not Found)
     */
    @GetMapping("/ho-so-liet-sis/{id}")
    @Timed
    public ResponseEntity<HoSoLietSi> getHoSoLietSi(@PathVariable Long id) {
        log.debug("REST request to get HoSoLietSi : {}", id);
        Optional<HoSoLietSi> hoSoLietSi = hoSoLietSiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoSoLietSi);
    }

    /**
     * DELETE  /ho-so-liet-sis/:id : delete the "id" hoSoLietSi.
     *
     * @param id the id of the hoSoLietSi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ho-so-liet-sis/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoSoLietSi(@PathVariable Long id) {
        log.debug("REST request to delete HoSoLietSi : {}", id);

        hoSoLietSiRepository.deleteById(id);
        hoSoLietSiSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ho-so-liet-sis?query=:query : search for the hoSoLietSi corresponding
     * to the query.
     *
     * @param query the query of the hoSoLietSi search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/ho-so-liet-sis")
    @Timed
    public ResponseEntity<List<HoSoLietSi>> searchHoSoLietSis(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HoSoLietSis for query {}", query);
        Page<HoSoLietSi> page = hoSoLietSiSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/ho-so-liet-sis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
