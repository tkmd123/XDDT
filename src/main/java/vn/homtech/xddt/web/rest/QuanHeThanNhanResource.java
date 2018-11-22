package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.QuanHeThanNhan;
import vn.homtech.xddt.repository.QuanHeThanNhanRepository;
import vn.homtech.xddt.repository.search.QuanHeThanNhanSearchRepository;
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
 * REST controller for managing QuanHeThanNhan.
 */
@RestController
@RequestMapping("/api")
public class QuanHeThanNhanResource {

    private final Logger log = LoggerFactory.getLogger(QuanHeThanNhanResource.class);

    private static final String ENTITY_NAME = "quanHeThanNhan";

    private final QuanHeThanNhanRepository quanHeThanNhanRepository;

    private final QuanHeThanNhanSearchRepository quanHeThanNhanSearchRepository;

    public QuanHeThanNhanResource(QuanHeThanNhanRepository quanHeThanNhanRepository, QuanHeThanNhanSearchRepository quanHeThanNhanSearchRepository) {
        this.quanHeThanNhanRepository = quanHeThanNhanRepository;
        this.quanHeThanNhanSearchRepository = quanHeThanNhanSearchRepository;
    }

    /**
     * POST  /quan-he-than-nhans : Create a new quanHeThanNhan.
     *
     * @param quanHeThanNhan the quanHeThanNhan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quanHeThanNhan, or with status 400 (Bad Request) if the quanHeThanNhan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quan-he-than-nhans")
    @Timed
    public ResponseEntity<QuanHeThanNhan> createQuanHeThanNhan(@RequestBody QuanHeThanNhan quanHeThanNhan) throws URISyntaxException {
        log.debug("REST request to save QuanHeThanNhan : {}", quanHeThanNhan);
        if (quanHeThanNhan.getId() != null) {
            throw new BadRequestAlertException("A new quanHeThanNhan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuanHeThanNhan result = quanHeThanNhanRepository.save(quanHeThanNhan);
        quanHeThanNhanSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/quan-he-than-nhans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quan-he-than-nhans : Updates an existing quanHeThanNhan.
     *
     * @param quanHeThanNhan the quanHeThanNhan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quanHeThanNhan,
     * or with status 400 (Bad Request) if the quanHeThanNhan is not valid,
     * or with status 500 (Internal Server Error) if the quanHeThanNhan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quan-he-than-nhans")
    @Timed
    public ResponseEntity<QuanHeThanNhan> updateQuanHeThanNhan(@RequestBody QuanHeThanNhan quanHeThanNhan) throws URISyntaxException {
        log.debug("REST request to update QuanHeThanNhan : {}", quanHeThanNhan);
        if (quanHeThanNhan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuanHeThanNhan result = quanHeThanNhanRepository.save(quanHeThanNhan);
        quanHeThanNhanSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quanHeThanNhan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quan-he-than-nhans : get all the quanHeThanNhans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quanHeThanNhans in body
     */
    @GetMapping("/quan-he-than-nhans")
    @Timed
    public List<QuanHeThanNhan> getAllQuanHeThanNhans() {
        log.debug("REST request to get all QuanHeThanNhans");
        return quanHeThanNhanRepository.findAll();
    }

    /**
     * GET  /quan-he-than-nhans/:id : get the "id" quanHeThanNhan.
     *
     * @param id the id of the quanHeThanNhan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quanHeThanNhan, or with status 404 (Not Found)
     */
    @GetMapping("/quan-he-than-nhans/{id}")
    @Timed
    public ResponseEntity<QuanHeThanNhan> getQuanHeThanNhan(@PathVariable Long id) {
        log.debug("REST request to get QuanHeThanNhan : {}", id);
        Optional<QuanHeThanNhan> quanHeThanNhan = quanHeThanNhanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quanHeThanNhan);
    }

    /**
     * DELETE  /quan-he-than-nhans/:id : delete the "id" quanHeThanNhan.
     *
     * @param id the id of the quanHeThanNhan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quan-he-than-nhans/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuanHeThanNhan(@PathVariable Long id) {
        log.debug("REST request to delete QuanHeThanNhan : {}", id);

        quanHeThanNhanRepository.deleteById(id);
        quanHeThanNhanSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/quan-he-than-nhans?query=:query : search for the quanHeThanNhan corresponding
     * to the query.
     *
     * @param query the query of the quanHeThanNhan search
     * @return the result of the search
     */
    @GetMapping("/_search/quan-he-than-nhans")
    @Timed
    public List<QuanHeThanNhan> searchQuanHeThanNhans(@RequestParam String query) {
        log.debug("REST request to search QuanHeThanNhans for query {}", query);
        return StreamSupport
            .stream(quanHeThanNhanSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
