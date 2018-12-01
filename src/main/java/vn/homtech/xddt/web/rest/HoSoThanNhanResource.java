package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoSoThanNhan;
import vn.homtech.xddt.repository.HoSoThanNhanRepository;
import vn.homtech.xddt.repository.search.HoSoThanNhanSearchRepository;
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
 * REST controller for managing HoSoThanNhan.
 */
@RestController
@RequestMapping("/api")
public class HoSoThanNhanResource {

    private final Logger log = LoggerFactory.getLogger(HoSoThanNhanResource.class);

    private static final String ENTITY_NAME = "hoSoThanNhan";

    private final HoSoThanNhanRepository hoSoThanNhanRepository;

    private final HoSoThanNhanSearchRepository hoSoThanNhanSearchRepository;

    public HoSoThanNhanResource(HoSoThanNhanRepository hoSoThanNhanRepository, HoSoThanNhanSearchRepository hoSoThanNhanSearchRepository) {
        this.hoSoThanNhanRepository = hoSoThanNhanRepository;
        this.hoSoThanNhanSearchRepository = hoSoThanNhanSearchRepository;
    }

    /**
     * POST  /ho-so-than-nhans : Create a new hoSoThanNhan.
     *
     * @param hoSoThanNhan the hoSoThanNhan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoSoThanNhan, or with status 400 (Bad Request) if the hoSoThanNhan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ho-so-than-nhans")
    @Timed
    public ResponseEntity<HoSoThanNhan> createHoSoThanNhan(@RequestBody HoSoThanNhan hoSoThanNhan) throws URISyntaxException {
        log.debug("REST request to save HoSoThanNhan : {}", hoSoThanNhan);
        if (hoSoThanNhan.getId() != null) {
            throw new BadRequestAlertException("A new hoSoThanNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoSoThanNhan result = hoSoThanNhanRepository.save(hoSoThanNhan);
        hoSoThanNhanSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ho-so-than-nhans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ho-so-than-nhans : Updates an existing hoSoThanNhan.
     *
     * @param hoSoThanNhan the hoSoThanNhan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoSoThanNhan,
     * or with status 400 (Bad Request) if the hoSoThanNhan is not valid,
     * or with status 500 (Internal Server Error) if the hoSoThanNhan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ho-so-than-nhans")
    @Timed
    public ResponseEntity<HoSoThanNhan> updateHoSoThanNhan(@RequestBody HoSoThanNhan hoSoThanNhan) throws URISyntaxException {
        log.debug("REST request to update HoSoThanNhan : {}", hoSoThanNhan);
        if (hoSoThanNhan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoSoThanNhan result = hoSoThanNhanRepository.save(hoSoThanNhan);
        hoSoThanNhanSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoSoThanNhan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ho-so-than-nhans : get all the hoSoThanNhans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hoSoThanNhans in body
     */
    @GetMapping("/ho-so-than-nhans")
    @Timed
    public ResponseEntity<List<HoSoThanNhan>> getAllHoSoThanNhans(Pageable pageable) {
        log.debug("REST request to get a page of HoSoThanNhans");
        Page<HoSoThanNhan> page = hoSoThanNhanRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ho-so-than-nhans");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ho-so-than-nhans/:id : get the "id" hoSoThanNhan.
     *
     * @param id the id of the hoSoThanNhan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoSoThanNhan, or with status 404 (Not Found)
     */
    @GetMapping("/ho-so-than-nhans/{id}")
    @Timed
    public ResponseEntity<HoSoThanNhan> getHoSoThanNhan(@PathVariable Long id) {
        log.debug("REST request to get HoSoThanNhan : {}", id);
        Optional<HoSoThanNhan> hoSoThanNhan = hoSoThanNhanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoSoThanNhan);
    }

    /**
     * DELETE  /ho-so-than-nhans/:id : delete the "id" hoSoThanNhan.
     *
     * @param id the id of the hoSoThanNhan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ho-so-than-nhans/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoSoThanNhan(@PathVariable Long id) {
        log.debug("REST request to delete HoSoThanNhan : {}", id);

        hoSoThanNhanRepository.deleteById(id);
        hoSoThanNhanSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ho-so-than-nhans?query=:query : search for the hoSoThanNhan corresponding
     * to the query.
     *
     * @param query the query of the hoSoThanNhan search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/ho-so-than-nhans")
    @Timed
    public ResponseEntity<List<HoSoThanNhan>> searchHoSoThanNhans(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of HoSoThanNhans for query {}", query);
        Page<HoSoThanNhan> page = hoSoThanNhanSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/ho-so-than-nhans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
