package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.DonViThoiKy;
import vn.homtech.xddt.repository.DonViThoiKyRepository;
import vn.homtech.xddt.repository.search.DonViThoiKySearchRepository;
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
 * REST controller for managing DonViThoiKy.
 */
@RestController
@RequestMapping("/api")
public class DonViThoiKyResource {

    private final Logger log = LoggerFactory.getLogger(DonViThoiKyResource.class);

    private static final String ENTITY_NAME = "donViThoiKy";

    private final DonViThoiKyRepository donViThoiKyRepository;

    private final DonViThoiKySearchRepository donViThoiKySearchRepository;

    public DonViThoiKyResource(DonViThoiKyRepository donViThoiKyRepository, DonViThoiKySearchRepository donViThoiKySearchRepository) {
        this.donViThoiKyRepository = donViThoiKyRepository;
        this.donViThoiKySearchRepository = donViThoiKySearchRepository;
    }

    /**
     * POST  /don-vi-thoi-kies : Create a new donViThoiKy.
     *
     * @param donViThoiKy the donViThoiKy to create
     * @return the ResponseEntity with status 201 (Created) and with body the new donViThoiKy, or with status 400 (Bad Request) if the donViThoiKy has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/don-vi-thoi-kies")
    @Timed
    public ResponseEntity<DonViThoiKy> createDonViThoiKy(@RequestBody DonViThoiKy donViThoiKy) throws URISyntaxException {
        log.debug("REST request to save DonViThoiKy : {}", donViThoiKy);
        if (donViThoiKy.getId() != null) {
            throw new BadRequestAlertException("A new donViThoiKy cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DonViThoiKy result = donViThoiKyRepository.save(donViThoiKy);
        donViThoiKySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/don-vi-thoi-kies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /don-vi-thoi-kies : Updates an existing donViThoiKy.
     *
     * @param donViThoiKy the donViThoiKy to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated donViThoiKy,
     * or with status 400 (Bad Request) if the donViThoiKy is not valid,
     * or with status 500 (Internal Server Error) if the donViThoiKy couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/don-vi-thoi-kies")
    @Timed
    public ResponseEntity<DonViThoiKy> updateDonViThoiKy(@RequestBody DonViThoiKy donViThoiKy) throws URISyntaxException {
        log.debug("REST request to update DonViThoiKy : {}", donViThoiKy);
        if (donViThoiKy.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DonViThoiKy result = donViThoiKyRepository.save(donViThoiKy);
        donViThoiKySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, donViThoiKy.getId().toString()))
            .body(result);
    }

    /**
     * GET  /don-vi-thoi-kies : get all the donViThoiKies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of donViThoiKies in body
     */
    @GetMapping("/don-vi-thoi-kies")
    @Timed
    public List<DonViThoiKy> getAllDonViThoiKies() {
        log.debug("REST request to get all DonViThoiKies");
        return donViThoiKyRepository.findAll();
    }

    /**
     * GET  /don-vi-thoi-kies/:id : get the "id" donViThoiKy.
     *
     * @param id the id of the donViThoiKy to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the donViThoiKy, or with status 404 (Not Found)
     */
    @GetMapping("/don-vi-thoi-kies/{id}")
    @Timed
    public ResponseEntity<DonViThoiKy> getDonViThoiKy(@PathVariable Long id) {
        log.debug("REST request to get DonViThoiKy : {}", id);
        Optional<DonViThoiKy> donViThoiKy = donViThoiKyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(donViThoiKy);
    }

    /**
     * DELETE  /don-vi-thoi-kies/:id : delete the "id" donViThoiKy.
     *
     * @param id the id of the donViThoiKy to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/don-vi-thoi-kies/{id}")
    @Timed
    public ResponseEntity<Void> deleteDonViThoiKy(@PathVariable Long id) {
        log.debug("REST request to delete DonViThoiKy : {}", id);

        donViThoiKyRepository.deleteById(id);
        donViThoiKySearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/don-vi-thoi-kies?query=:query : search for the donViThoiKy corresponding
     * to the query.
     *
     * @param query the query of the donViThoiKy search
     * @return the result of the search
     */
    @GetMapping("/_search/don-vi-thoi-kies")
    @Timed
    public List<DonViThoiKy> searchDonViThoiKies(@RequestParam String query) {
        log.debug("REST request to search DonViThoiKies for query {}", query);
        return StreamSupport
            .stream(donViThoiKySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
