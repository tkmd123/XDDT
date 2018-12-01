package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCRKetQua;
import vn.homtech.xddt.repository.PCRKetQuaRepository;
import vn.homtech.xddt.repository.search.PCRKetQuaSearchRepository;
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
 * REST controller for managing PCRKetQua.
 */
@RestController
@RequestMapping("/api")
public class PCRKetQuaResource {

    private final Logger log = LoggerFactory.getLogger(PCRKetQuaResource.class);

    private static final String ENTITY_NAME = "pCRKetQua";

    private final PCRKetQuaRepository pCRKetQuaRepository;

    private final PCRKetQuaSearchRepository pCRKetQuaSearchRepository;

    public PCRKetQuaResource(PCRKetQuaRepository pCRKetQuaRepository, PCRKetQuaSearchRepository pCRKetQuaSearchRepository) {
        this.pCRKetQuaRepository = pCRKetQuaRepository;
        this.pCRKetQuaSearchRepository = pCRKetQuaSearchRepository;
    }

    /**
     * POST  /pcr-ket-quas : Create a new pCRKetQua.
     *
     * @param pCRKetQua the pCRKetQua to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCRKetQua, or with status 400 (Bad Request) if the pCRKetQua has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcr-ket-quas")
    @Timed
    public ResponseEntity<PCRKetQua> createPCRKetQua(@RequestBody PCRKetQua pCRKetQua) throws URISyntaxException {
        log.debug("REST request to save PCRKetQua : {}", pCRKetQua);
        if (pCRKetQua.getId() != null) {
            throw new BadRequestAlertException("A new pCRKetQua cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCRKetQua result = pCRKetQuaRepository.save(pCRKetQua);
        pCRKetQuaSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcr-ket-quas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcr-ket-quas : Updates an existing pCRKetQua.
     *
     * @param pCRKetQua the pCRKetQua to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCRKetQua,
     * or with status 400 (Bad Request) if the pCRKetQua is not valid,
     * or with status 500 (Internal Server Error) if the pCRKetQua couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcr-ket-quas")
    @Timed
    public ResponseEntity<PCRKetQua> updatePCRKetQua(@RequestBody PCRKetQua pCRKetQua) throws URISyntaxException {
        log.debug("REST request to update PCRKetQua : {}", pCRKetQua);
        if (pCRKetQua.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCRKetQua result = pCRKetQuaRepository.save(pCRKetQua);
        pCRKetQuaSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCRKetQua.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcr-ket-quas : get all the pCRKetQuas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRKetQuas in body
     */
    @GetMapping("/pcr-ket-quas")
    @Timed
    public List<PCRKetQua> getAllPCRKetQuas() {
        log.debug("REST request to get all PCRKetQuas");
        return pCRKetQuaRepository.findAll();
    }

    /**
     * GET  /pcr-ket-quas/:id : get the "id" pCRKetQua.
     *
     * @param id the id of the pCRKetQua to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCRKetQua, or with status 404 (Not Found)
     */
    @GetMapping("/pcr-ket-quas/{id}")
    @Timed
    public ResponseEntity<PCRKetQua> getPCRKetQua(@PathVariable Long id) {
        log.debug("REST request to get PCRKetQua : {}", id);
        Optional<PCRKetQua> pCRKetQua = pCRKetQuaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCRKetQua);
    }

    /**
     * DELETE  /pcr-ket-quas/:id : delete the "id" pCRKetQua.
     *
     * @param id the id of the pCRKetQua to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcr-ket-quas/{id}")
    @Timed
    public ResponseEntity<Void> deletePCRKetQua(@PathVariable Long id) {
        log.debug("REST request to delete PCRKetQua : {}", id);

        pCRKetQuaRepository.deleteById(id);
        pCRKetQuaSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcr-ket-quas?query=:query : search for the pCRKetQua corresponding
     * to the query.
     *
     * @param query the query of the pCRKetQua search
     * @return the result of the search
     */
    @GetMapping("/_search/pcr-ket-quas")
    @Timed
    public List<PCRKetQua> searchPCRKetQuas(@RequestParam String query) {
        log.debug("REST request to search PCRKetQuas for query {}", query);
        return StreamSupport
            .stream(pCRKetQuaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
