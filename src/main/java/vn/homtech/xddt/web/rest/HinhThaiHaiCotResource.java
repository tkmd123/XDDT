package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HinhThaiHaiCot;
import vn.homtech.xddt.repository.HinhThaiHaiCotRepository;
import vn.homtech.xddt.repository.search.HinhThaiHaiCotSearchRepository;
import vn.homtech.xddt.web.rest.errors.BadRequestAlertException;
import vn.homtech.xddt.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing HinhThaiHaiCot.
 */
@RestController
@RequestMapping("/api")
public class HinhThaiHaiCotResource {

    private final Logger log = LoggerFactory.getLogger(HinhThaiHaiCotResource.class);

    private static final String ENTITY_NAME = "hinhThaiHaiCot";

    private final HinhThaiHaiCotRepository hinhThaiHaiCotRepository;

    private final HinhThaiHaiCotSearchRepository hinhThaiHaiCotSearchRepository;

    public HinhThaiHaiCotResource(HinhThaiHaiCotRepository hinhThaiHaiCotRepository, HinhThaiHaiCotSearchRepository hinhThaiHaiCotSearchRepository) {
        this.hinhThaiHaiCotRepository = hinhThaiHaiCotRepository;
        this.hinhThaiHaiCotSearchRepository = hinhThaiHaiCotSearchRepository;
    }

    /**
     * POST  /hinh-thai-hai-cots : Create a new hinhThaiHaiCot.
     *
     * @param hinhThaiHaiCot the hinhThaiHaiCot to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hinhThaiHaiCot, or with status 400 (Bad Request) if the hinhThaiHaiCot has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<HinhThaiHaiCot> createHinhThaiHaiCot(@RequestBody HinhThaiHaiCot hinhThaiHaiCot) throws URISyntaxException {
        log.debug("REST request to save HinhThaiHaiCot : {}", hinhThaiHaiCot);
        if (hinhThaiHaiCot.getId() != null) {
            throw new BadRequestAlertException("A new hinhThaiHaiCot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HinhThaiHaiCot result = hinhThaiHaiCotRepository.save(hinhThaiHaiCot);
        hinhThaiHaiCotSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/hinh-thai-hai-cots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hinh-thai-hai-cots : Updates an existing hinhThaiHaiCot.
     *
     * @param hinhThaiHaiCot the hinhThaiHaiCot to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hinhThaiHaiCot,
     * or with status 400 (Bad Request) if the hinhThaiHaiCot is not valid,
     * or with status 500 (Internal Server Error) if the hinhThaiHaiCot couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hinh-thai-hai-cots")
    @Timed
    public ResponseEntity<HinhThaiHaiCot> updateHinhThaiHaiCot(@RequestBody HinhThaiHaiCot hinhThaiHaiCot) throws URISyntaxException {
        log.debug("REST request to update HinhThaiHaiCot : {}", hinhThaiHaiCot);
        if (hinhThaiHaiCot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HinhThaiHaiCot result = hinhThaiHaiCotRepository.save(hinhThaiHaiCot);
        hinhThaiHaiCotSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hinhThaiHaiCot.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hinh-thai-hai-cots : get all the hinhThaiHaiCots.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hinhThaiHaiCots in body
     */
    @GetMapping("/hinh-thai-hai-cots")
    @Timed
    public List<HinhThaiHaiCot> getAllHinhThaiHaiCots() {
        log.debug("REST request to get all HinhThaiHaiCots");
        return hinhThaiHaiCotRepository.findAll();
    }

    /**
     * GET  /hinh-thai-hai-cots/:id : get the "id" hinhThaiHaiCot.
     *
     * @param id the id of the hinhThaiHaiCot to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hinhThaiHaiCot, or with status 404 (Not Found)
     */
    @GetMapping("/hinh-thai-hai-cots/{id}")
    @Timed
    public ResponseEntity<HinhThaiHaiCot> getHinhThaiHaiCot(@PathVariable Long id) {
        log.debug("REST request to get HinhThaiHaiCot : {}", id);
        Optional<HinhThaiHaiCot> hinhThaiHaiCot = hinhThaiHaiCotRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hinhThaiHaiCot);
    }

    /**
     * DELETE  /hinh-thai-hai-cots/:id : delete the "id" hinhThaiHaiCot.
     *
     * @param id the id of the hinhThaiHaiCot to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hinh-thai-hai-cots/{id}")
    @Timed
    public ResponseEntity<Void> deleteHinhThaiHaiCot(@PathVariable Long id) {
        log.debug("REST request to delete HinhThaiHaiCot : {}", id);

        hinhThaiHaiCotRepository.deleteById(id);
        hinhThaiHaiCotSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hinh-thai-hai-cots?query=:query : search for the hinhThaiHaiCot corresponding
     * to the query.
     *
     * @param query the query of the hinhThaiHaiCot search
     * @return the result of the search
     */
    @GetMapping("/_search/hinh-thai-hai-cots")
    @Timed
    public List<HinhThaiHaiCot> searchHinhThaiHaiCots(@RequestParam String query) {
        log.debug("REST request to search HinhThaiHaiCots for query {}", query);
        return StreamSupport
            .stream(hinhThaiHaiCotSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
