package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.TrungTam;
import vn.homtech.xddt.repository.TrungTamRepository;
import vn.homtech.xddt.repository.search.TrungTamSearchRepository;
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
 * REST controller for managing TrungTam.
 */
@RestController
@RequestMapping("/api")
public class TrungTamResource {

    private final Logger log = LoggerFactory.getLogger(TrungTamResource.class);

    private static final String ENTITY_NAME = "trungTam";

    private final TrungTamRepository trungTamRepository;

    private final TrungTamSearchRepository trungTamSearchRepository;

    public TrungTamResource(TrungTamRepository trungTamRepository, TrungTamSearchRepository trungTamSearchRepository) {
        this.trungTamRepository = trungTamRepository;
        this.trungTamSearchRepository = trungTamSearchRepository;
    }

    /**
     * POST  /trung-tams : Create a new trungTam.
     *
     * @param trungTam the trungTam to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trungTam, or with status 400 (Bad Request) if the trungTam has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trung-tams")
    @Timed
    public ResponseEntity<TrungTam> createTrungTam(@RequestBody TrungTam trungTam) throws URISyntaxException {
        log.debug("REST request to save TrungTam : {}", trungTam);
        if (trungTam.getId() != null) {
            throw new BadRequestAlertException("A new trungTam cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrungTam result = trungTamRepository.save(trungTam);
        trungTamSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/trung-tams/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trung-tams : Updates an existing trungTam.
     *
     * @param trungTam the trungTam to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trungTam,
     * or with status 400 (Bad Request) if the trungTam is not valid,
     * or with status 500 (Internal Server Error) if the trungTam couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trung-tams")
    @Timed
    public ResponseEntity<TrungTam> updateTrungTam(@RequestBody TrungTam trungTam) throws URISyntaxException {
        log.debug("REST request to update TrungTam : {}", trungTam);
        if (trungTam.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrungTam result = trungTamRepository.save(trungTam);
        trungTamSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trungTam.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trung-tams : get all the trungTams.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trungTams in body
     */
    @GetMapping("/trung-tams")
    @Timed
    public List<TrungTam> getAllTrungTams() {
        log.debug("REST request to get all TrungTams");
        return trungTamRepository.findAll();
    }

    /**
     * GET  /trung-tams/:id : get the "id" trungTam.
     *
     * @param id the id of the trungTam to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trungTam, or with status 404 (Not Found)
     */
    @GetMapping("/trung-tams/{id}")
    @Timed
    public ResponseEntity<TrungTam> getTrungTam(@PathVariable Long id) {
        log.debug("REST request to get TrungTam : {}", id);
        Optional<TrungTam> trungTam = trungTamRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(trungTam);
    }

    /**
     * DELETE  /trung-tams/:id : delete the "id" trungTam.
     *
     * @param id the id of the trungTam to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trung-tams/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrungTam(@PathVariable Long id) {
        log.debug("REST request to delete TrungTam : {}", id);

        trungTamRepository.deleteById(id);
        trungTamSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/trung-tams?query=:query : search for the trungTam corresponding
     * to the query.
     *
     * @param query the query of the trungTam search
     * @return the result of the search
     */
    @GetMapping("/_search/trung-tams")
    @Timed
    public List<TrungTam> searchTrungTams(@RequestParam String query) {
        log.debug("REST request to search TrungTams for query {}", query);
        return StreamSupport
            .stream(trungTamSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
