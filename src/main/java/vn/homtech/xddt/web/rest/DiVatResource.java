package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.DiVat;
import vn.homtech.xddt.repository.DiVatRepository;
import vn.homtech.xddt.repository.search.DiVatSearchRepository;
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
 * REST controller for managing DiVat.
 */
@RestController
@RequestMapping("/api")
public class DiVatResource {

    private final Logger log = LoggerFactory.getLogger(DiVatResource.class);

    private static final String ENTITY_NAME = "diVat";

    private final DiVatRepository diVatRepository;

    private final DiVatSearchRepository diVatSearchRepository;

    public DiVatResource(DiVatRepository diVatRepository, DiVatSearchRepository diVatSearchRepository) {
        this.diVatRepository = diVatRepository;
        this.diVatSearchRepository = diVatSearchRepository;
    }

    /**
     * POST  /di-vats : Create a new diVat.
     *
     * @param diVat the diVat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diVat, or with status 400 (Bad Request) if the diVat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/di-vats")
    @Timed
    public ResponseEntity<DiVat> createDiVat(@RequestBody DiVat diVat) throws URISyntaxException {
        log.debug("REST request to save DiVat : {}", diVat);
        if (diVat.getId() != null) {
            throw new BadRequestAlertException("A new diVat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DiVat result = diVatRepository.save(diVat);
        diVatSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/di-vats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /di-vats : Updates an existing diVat.
     *
     * @param diVat the diVat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diVat,
     * or with status 400 (Bad Request) if the diVat is not valid,
     * or with status 500 (Internal Server Error) if the diVat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/di-vats")
    @Timed
    public ResponseEntity<DiVat> updateDiVat(@RequestBody DiVat diVat) throws URISyntaxException {
        log.debug("REST request to update DiVat : {}", diVat);
        if (diVat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DiVat result = diVatRepository.save(diVat);
        diVatSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, diVat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /di-vats : get all the diVats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of diVats in body
     */
    @GetMapping("/di-vats")
    @Timed
    public List<DiVat> getAllDiVats() {
        log.debug("REST request to get all DiVats");
        return diVatRepository.findAll();
    }

    /**
     * GET  /di-vats/:id : get the "id" diVat.
     *
     * @param id the id of the diVat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diVat, or with status 404 (Not Found)
     */
    @GetMapping("/di-vats/{id}")
    @Timed
    public ResponseEntity<DiVat> getDiVat(@PathVariable Long id) {
        log.debug("REST request to get DiVat : {}", id);
        Optional<DiVat> diVat = diVatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(diVat);
    }

    /**
     * DELETE  /di-vats/:id : delete the "id" diVat.
     *
     * @param id the id of the diVat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/di-vats/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiVat(@PathVariable Long id) {
        log.debug("REST request to delete DiVat : {}", id);

        diVatRepository.deleteById(id);
        diVatSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/di-vats?query=:query : search for the diVat corresponding
     * to the query.
     *
     * @param query the query of the diVat search
     * @return the result of the search
     */
    @GetMapping("/_search/di-vats")
    @Timed
    public List<DiVat> searchDiVats(@RequestParam String query) {
        log.debug("REST request to search DiVats for query {}", query);
        return StreamSupport
            .stream(diVatSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
