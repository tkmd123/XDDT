package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.VungADN;
import vn.homtech.xddt.repository.VungADNRepository;
import vn.homtech.xddt.repository.search.VungADNSearchRepository;
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
 * REST controller for managing VungADN.
 */
@RestController
@RequestMapping("/api")
public class VungADNResource {

    private final Logger log = LoggerFactory.getLogger(VungADNResource.class);

    private static final String ENTITY_NAME = "vungADN";

    private final VungADNRepository vungADNRepository;

    private final VungADNSearchRepository vungADNSearchRepository;

    public VungADNResource(VungADNRepository vungADNRepository, VungADNSearchRepository vungADNSearchRepository) {
        this.vungADNRepository = vungADNRepository;
        this.vungADNSearchRepository = vungADNSearchRepository;
    }

    /**
     * POST  /vung-adns : Create a new vungADN.
     *
     * @param vungADN the vungADN to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vungADN, or with status 400 (Bad Request) if the vungADN has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vung-adns")
    @Timed
    public ResponseEntity<VungADN> createVungADN(@RequestBody VungADN vungADN) throws URISyntaxException {
        log.debug("REST request to save VungADN : {}", vungADN);
        if (vungADN.getId() != null) {
            throw new BadRequestAlertException("A new vungADN cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VungADN result = vungADNRepository.save(vungADN);
        vungADNSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/vung-adns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vung-adns : Updates an existing vungADN.
     *
     * @param vungADN the vungADN to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vungADN,
     * or with status 400 (Bad Request) if the vungADN is not valid,
     * or with status 500 (Internal Server Error) if the vungADN couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vung-adns")
    @Timed
    public ResponseEntity<VungADN> updateVungADN(@RequestBody VungADN vungADN) throws URISyntaxException {
        log.debug("REST request to update VungADN : {}", vungADN);
        if (vungADN.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VungADN result = vungADNRepository.save(vungADN);
        vungADNSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vungADN.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vung-adns : get all the vungADNS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of vungADNS in body
     */
    @GetMapping("/vung-adns")
    @Timed
    public List<VungADN> getAllVungADNS() {
        log.debug("REST request to get all VungADNS");
        return vungADNRepository.findAll();
    }

    /**
     * GET  /vung-adns/:id : get the "id" vungADN.
     *
     * @param id the id of the vungADN to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vungADN, or with status 404 (Not Found)
     */
    @GetMapping("/vung-adns/{id}")
    @Timed
    public ResponseEntity<VungADN> getVungADN(@PathVariable Long id) {
        log.debug("REST request to get VungADN : {}", id);
        Optional<VungADN> vungADN = vungADNRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vungADN);
    }

    /**
     * DELETE  /vung-adns/:id : delete the "id" vungADN.
     *
     * @param id the id of the vungADN to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vung-adns/{id}")
    @Timed
    public ResponseEntity<Void> deleteVungADN(@PathVariable Long id) {
        log.debug("REST request to delete VungADN : {}", id);

        vungADNRepository.deleteById(id);
        vungADNSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/vung-adns?query=:query : search for the vungADN corresponding
     * to the query.
     *
     * @param query the query of the vungADN search
     * @return the result of the search
     */
    @GetMapping("/_search/vung-adns")
    @Timed
    public List<VungADN> searchVungADNS(@RequestParam String query) {
        log.debug("REST request to search VungADNS for query {}", query);
        return StreamSupport
            .stream(vungADNSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
