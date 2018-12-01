package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PCRPhanUngChuan;
import vn.homtech.xddt.repository.PCRPhanUngChuanRepository;
import vn.homtech.xddt.repository.search.PCRPhanUngChuanSearchRepository;
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
 * REST controller for managing PCRPhanUngChuan.
 */
@RestController
@RequestMapping("/api")
public class PCRPhanUngChuanResource {

    private final Logger log = LoggerFactory.getLogger(PCRPhanUngChuanResource.class);

    private static final String ENTITY_NAME = "pCRPhanUngChuan";

    private final PCRPhanUngChuanRepository pCRPhanUngChuanRepository;

    private final PCRPhanUngChuanSearchRepository pCRPhanUngChuanSearchRepository;

    public PCRPhanUngChuanResource(PCRPhanUngChuanRepository pCRPhanUngChuanRepository, PCRPhanUngChuanSearchRepository pCRPhanUngChuanSearchRepository) {
        this.pCRPhanUngChuanRepository = pCRPhanUngChuanRepository;
        this.pCRPhanUngChuanSearchRepository = pCRPhanUngChuanSearchRepository;
    }

    /**
     * POST  /pcr-phan-ung-chuans : Create a new pCRPhanUngChuan.
     *
     * @param pCRPhanUngChuan the pCRPhanUngChuan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pCRPhanUngChuan, or with status 400 (Bad Request) if the pCRPhanUngChuan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pcr-phan-ung-chuans")
    @Timed
    public ResponseEntity<PCRPhanUngChuan> createPCRPhanUngChuan(@RequestBody PCRPhanUngChuan pCRPhanUngChuan) throws URISyntaxException {
        log.debug("REST request to save PCRPhanUngChuan : {}", pCRPhanUngChuan);
        if (pCRPhanUngChuan.getId() != null) {
            throw new BadRequestAlertException("A new pCRPhanUngChuan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PCRPhanUngChuan result = pCRPhanUngChuanRepository.save(pCRPhanUngChuan);
        pCRPhanUngChuanSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/pcr-phan-ung-chuans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pcr-phan-ung-chuans : Updates an existing pCRPhanUngChuan.
     *
     * @param pCRPhanUngChuan the pCRPhanUngChuan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pCRPhanUngChuan,
     * or with status 400 (Bad Request) if the pCRPhanUngChuan is not valid,
     * or with status 500 (Internal Server Error) if the pCRPhanUngChuan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pcr-phan-ung-chuans")
    @Timed
    public ResponseEntity<PCRPhanUngChuan> updatePCRPhanUngChuan(@RequestBody PCRPhanUngChuan pCRPhanUngChuan) throws URISyntaxException {
        log.debug("REST request to update PCRPhanUngChuan : {}", pCRPhanUngChuan);
        if (pCRPhanUngChuan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PCRPhanUngChuan result = pCRPhanUngChuanRepository.save(pCRPhanUngChuan);
        pCRPhanUngChuanSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pCRPhanUngChuan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pcr-phan-ung-chuans : get all the pCRPhanUngChuans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pCRPhanUngChuans in body
     */
    @GetMapping("/pcr-phan-ung-chuans")
    @Timed
    public List<PCRPhanUngChuan> getAllPCRPhanUngChuans() {
        log.debug("REST request to get all PCRPhanUngChuans");
        return pCRPhanUngChuanRepository.findAll();
    }

    /**
     * GET  /pcr-phan-ung-chuans/:id : get the "id" pCRPhanUngChuan.
     *
     * @param id the id of the pCRPhanUngChuan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pCRPhanUngChuan, or with status 404 (Not Found)
     */
    @GetMapping("/pcr-phan-ung-chuans/{id}")
    @Timed
    public ResponseEntity<PCRPhanUngChuan> getPCRPhanUngChuan(@PathVariable Long id) {
        log.debug("REST request to get PCRPhanUngChuan : {}", id);
        Optional<PCRPhanUngChuan> pCRPhanUngChuan = pCRPhanUngChuanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pCRPhanUngChuan);
    }

    /**
     * DELETE  /pcr-phan-ung-chuans/:id : delete the "id" pCRPhanUngChuan.
     *
     * @param id the id of the pCRPhanUngChuan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pcr-phan-ung-chuans/{id}")
    @Timed
    public ResponseEntity<Void> deletePCRPhanUngChuan(@PathVariable Long id) {
        log.debug("REST request to delete PCRPhanUngChuan : {}", id);

        pCRPhanUngChuanRepository.deleteById(id);
        pCRPhanUngChuanSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/pcr-phan-ung-chuans?query=:query : search for the pCRPhanUngChuan corresponding
     * to the query.
     *
     * @param query the query of the pCRPhanUngChuan search
     * @return the result of the search
     */
    @GetMapping("/_search/pcr-phan-ung-chuans")
    @Timed
    public List<PCRPhanUngChuan> searchPCRPhanUngChuans(@RequestParam String query) {
        log.debug("REST request to search PCRPhanUngChuans for query {}", query);
        return StreamSupport
            .stream(pCRPhanUngChuanSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
