package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HaiCotLietSi;
import vn.homtech.xddt.repository.HaiCotLietSiRepository;
import vn.homtech.xddt.repository.search.HaiCotLietSiSearchRepository;
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
 * REST controller for managing HaiCotLietSi.
 */
@RestController
@RequestMapping("/api")
public class HaiCotLietSiResource {

    private final Logger log = LoggerFactory.getLogger(HaiCotLietSiResource.class);

    private static final String ENTITY_NAME = "haiCotLietSi";

    private final HaiCotLietSiRepository haiCotLietSiRepository;

    private final HaiCotLietSiSearchRepository haiCotLietSiSearchRepository;

    public HaiCotLietSiResource(HaiCotLietSiRepository haiCotLietSiRepository, HaiCotLietSiSearchRepository haiCotLietSiSearchRepository) {
        this.haiCotLietSiRepository = haiCotLietSiRepository;
        this.haiCotLietSiSearchRepository = haiCotLietSiSearchRepository;
    }

    /**
     * POST  /hai-cot-liet-sis : Create a new haiCotLietSi.
     *
     * @param haiCotLietSi the haiCotLietSi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new haiCotLietSi, or with status 400 (Bad Request) if the haiCotLietSi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hai-cot-liet-sis")
    @Timed
    public ResponseEntity<HaiCotLietSi> createHaiCotLietSi(@RequestBody HaiCotLietSi haiCotLietSi) throws URISyntaxException {
        log.debug("REST request to save HaiCotLietSi : {}", haiCotLietSi);
        if (haiCotLietSi.getId() != null) {
            throw new BadRequestAlertException("A new haiCotLietSi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HaiCotLietSi result = haiCotLietSiRepository.save(haiCotLietSi);
        haiCotLietSiSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/hai-cot-liet-sis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hai-cot-liet-sis : Updates an existing haiCotLietSi.
     *
     * @param haiCotLietSi the haiCotLietSi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated haiCotLietSi,
     * or with status 400 (Bad Request) if the haiCotLietSi is not valid,
     * or with status 500 (Internal Server Error) if the haiCotLietSi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hai-cot-liet-sis")
    @Timed
    public ResponseEntity<HaiCotLietSi> updateHaiCotLietSi(@RequestBody HaiCotLietSi haiCotLietSi) throws URISyntaxException {
        log.debug("REST request to update HaiCotLietSi : {}", haiCotLietSi);
        if (haiCotLietSi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HaiCotLietSi result = haiCotLietSiRepository.save(haiCotLietSi);
        haiCotLietSiSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, haiCotLietSi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hai-cot-liet-sis : get all the haiCotLietSis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of haiCotLietSis in body
     */
    @GetMapping("/hai-cot-liet-sis")
    @Timed
    public List<HaiCotLietSi> getAllHaiCotLietSis() {
        log.debug("REST request to get all HaiCotLietSis");
        return haiCotLietSiRepository.findAll();
    }

    /**
     * GET  /hai-cot-liet-sis/:id : get the "id" haiCotLietSi.
     *
     * @param id the id of the haiCotLietSi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the haiCotLietSi, or with status 404 (Not Found)
     */
    @GetMapping("/hai-cot-liet-sis/{id}")
    @Timed
    public ResponseEntity<HaiCotLietSi> getHaiCotLietSi(@PathVariable Long id) {
        log.debug("REST request to get HaiCotLietSi : {}", id);
        Optional<HaiCotLietSi> haiCotLietSi = haiCotLietSiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(haiCotLietSi);
    }

    /**
     * DELETE  /hai-cot-liet-sis/:id : delete the "id" haiCotLietSi.
     *
     * @param id the id of the haiCotLietSi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hai-cot-liet-sis/{id}")
    @Timed
    public ResponseEntity<Void> deleteHaiCotLietSi(@PathVariable Long id) {
        log.debug("REST request to delete HaiCotLietSi : {}", id);

        haiCotLietSiRepository.deleteById(id);
        haiCotLietSiSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hai-cot-liet-sis?query=:query : search for the haiCotLietSi corresponding
     * to the query.
     *
     * @param query the query of the haiCotLietSi search
     * @return the result of the search
     */
    @GetMapping("/_search/hai-cot-liet-sis")
    @Timed
    public List<HaiCotLietSi> searchHaiCotLietSis(@RequestParam String query) {
        log.debug("REST request to search HaiCotLietSis for query {}", query);
        return StreamSupport
            .stream(haiCotLietSiSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
