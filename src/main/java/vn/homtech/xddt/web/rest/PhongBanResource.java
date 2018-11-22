package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.PhongBan;
import vn.homtech.xddt.repository.PhongBanRepository;
import vn.homtech.xddt.repository.search.PhongBanSearchRepository;
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
 * REST controller for managing PhongBan.
 */
@RestController
@RequestMapping("/api")
public class PhongBanResource {

    private final Logger log = LoggerFactory.getLogger(PhongBanResource.class);

    private static final String ENTITY_NAME = "phongBan";

    private final PhongBanRepository phongBanRepository;

    private final PhongBanSearchRepository phongBanSearchRepository;

    public PhongBanResource(PhongBanRepository phongBanRepository, PhongBanSearchRepository phongBanSearchRepository) {
        this.phongBanRepository = phongBanRepository;
        this.phongBanSearchRepository = phongBanSearchRepository;
    }

    /**
     * POST  /phong-bans : Create a new phongBan.
     *
     * @param phongBan the phongBan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new phongBan, or with status 400 (Bad Request) if the phongBan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/phong-bans")
    @Timed
    public ResponseEntity<PhongBan> createPhongBan(@RequestBody PhongBan phongBan) throws URISyntaxException {
        log.debug("REST request to save PhongBan : {}", phongBan);
        if (phongBan.getId() != null) {
            throw new BadRequestAlertException("A new phongBan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhongBan result = phongBanRepository.save(phongBan);
        phongBanSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/phong-bans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /phong-bans : Updates an existing phongBan.
     *
     * @param phongBan the phongBan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated phongBan,
     * or with status 400 (Bad Request) if the phongBan is not valid,
     * or with status 500 (Internal Server Error) if the phongBan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/phong-bans")
    @Timed
    public ResponseEntity<PhongBan> updatePhongBan(@RequestBody PhongBan phongBan) throws URISyntaxException {
        log.debug("REST request to update PhongBan : {}", phongBan);
        if (phongBan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhongBan result = phongBanRepository.save(phongBan);
        phongBanSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, phongBan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /phong-bans : get all the phongBans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of phongBans in body
     */
    @GetMapping("/phong-bans")
    @Timed
    public List<PhongBan> getAllPhongBans() {
        log.debug("REST request to get all PhongBans");
        return phongBanRepository.findAll();
    }

    /**
     * GET  /phong-bans/:id : get the "id" phongBan.
     *
     * @param id the id of the phongBan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the phongBan, or with status 404 (Not Found)
     */
    @GetMapping("/phong-bans/{id}")
    @Timed
    public ResponseEntity<PhongBan> getPhongBan(@PathVariable Long id) {
        log.debug("REST request to get PhongBan : {}", id);
        Optional<PhongBan> phongBan = phongBanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(phongBan);
    }

    /**
     * DELETE  /phong-bans/:id : delete the "id" phongBan.
     *
     * @param id the id of the phongBan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/phong-bans/{id}")
    @Timed
    public ResponseEntity<Void> deletePhongBan(@PathVariable Long id) {
        log.debug("REST request to delete PhongBan : {}", id);

        phongBanRepository.deleteById(id);
        phongBanSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/phong-bans?query=:query : search for the phongBan corresponding
     * to the query.
     *
     * @param query the query of the phongBan search
     * @return the result of the search
     */
    @GetMapping("/_search/phong-bans")
    @Timed
    public List<PhongBan> searchPhongBans(@RequestParam String query) {
        log.debug("REST request to search PhongBans for query {}", query);
        return StreamSupport
            .stream(phongBanSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
