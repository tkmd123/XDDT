package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.MappingDotBien;
import vn.homtech.xddt.repository.MappingDotBienRepository;
import vn.homtech.xddt.repository.search.MappingDotBienSearchRepository;
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
 * REST controller for managing MappingDotBien.
 */
@RestController
@RequestMapping("/api")
public class MappingDotBienResource {

    private final Logger log = LoggerFactory.getLogger(MappingDotBienResource.class);

    private static final String ENTITY_NAME = "mappingDotBien";

    private final MappingDotBienRepository mappingDotBienRepository;

    private final MappingDotBienSearchRepository mappingDotBienSearchRepository;

    public MappingDotBienResource(MappingDotBienRepository mappingDotBienRepository, MappingDotBienSearchRepository mappingDotBienSearchRepository) {
        this.mappingDotBienRepository = mappingDotBienRepository;
        this.mappingDotBienSearchRepository = mappingDotBienSearchRepository;
    }

    /**
     * POST  /mapping-dot-biens : Create a new mappingDotBien.
     *
     * @param mappingDotBien the mappingDotBien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mappingDotBien, or with status 400 (Bad Request) if the mappingDotBien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mapping-dot-biens")
    @Timed
    public ResponseEntity<MappingDotBien> createMappingDotBien(@RequestBody MappingDotBien mappingDotBien) throws URISyntaxException {
        log.debug("REST request to save MappingDotBien : {}", mappingDotBien);
        if (mappingDotBien.getId() != null) {
            throw new BadRequestAlertException("A new mappingDotBien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MappingDotBien result = mappingDotBienRepository.save(mappingDotBien);
        mappingDotBienSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/mapping-dot-biens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mapping-dot-biens : Updates an existing mappingDotBien.
     *
     * @param mappingDotBien the mappingDotBien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mappingDotBien,
     * or with status 400 (Bad Request) if the mappingDotBien is not valid,
     * or with status 500 (Internal Server Error) if the mappingDotBien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mapping-dot-biens")
    @Timed
    public ResponseEntity<MappingDotBien> updateMappingDotBien(@RequestBody MappingDotBien mappingDotBien) throws URISyntaxException {
        log.debug("REST request to update MappingDotBien : {}", mappingDotBien);
        if (mappingDotBien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MappingDotBien result = mappingDotBienRepository.save(mappingDotBien);
        mappingDotBienSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mappingDotBien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mapping-dot-biens : get all the mappingDotBiens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mappingDotBiens in body
     */
    @GetMapping("/mapping-dot-biens")
    @Timed
    public List<MappingDotBien> getAllMappingDotBiens() {
        log.debug("REST request to get all MappingDotBiens");
        return mappingDotBienRepository.findAll();
    }

    /**
     * GET  /mapping-dot-biens/:id : get the "id" mappingDotBien.
     *
     * @param id the id of the mappingDotBien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mappingDotBien, or with status 404 (Not Found)
     */
    @GetMapping("/mapping-dot-biens/{id}")
    @Timed
    public ResponseEntity<MappingDotBien> getMappingDotBien(@PathVariable Long id) {
        log.debug("REST request to get MappingDotBien : {}", id);
        Optional<MappingDotBien> mappingDotBien = mappingDotBienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mappingDotBien);
    }

    /**
     * DELETE  /mapping-dot-biens/:id : delete the "id" mappingDotBien.
     *
     * @param id the id of the mappingDotBien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mapping-dot-biens/{id}")
    @Timed
    public ResponseEntity<Void> deleteMappingDotBien(@PathVariable Long id) {
        log.debug("REST request to delete MappingDotBien : {}", id);

        mappingDotBienRepository.deleteById(id);
        mappingDotBienSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/mapping-dot-biens?query=:query : search for the mappingDotBien corresponding
     * to the query.
     *
     * @param query the query of the mappingDotBien search
     * @return the result of the search
     */
    @GetMapping("/_search/mapping-dot-biens")
    @Timed
    public List<MappingDotBien> searchMappingDotBiens(@RequestParam String query) {
        log.debug("REST request to search MappingDotBiens for query {}", query);
        return StreamSupport
            .stream(mappingDotBienSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
