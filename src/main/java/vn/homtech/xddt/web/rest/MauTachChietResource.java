package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.MauTachChiet;
import vn.homtech.xddt.repository.MauTachChietRepository;
import vn.homtech.xddt.repository.search.MauTachChietSearchRepository;
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
 * REST controller for managing MauTachChiet.
 */
@RestController
@RequestMapping("/api")
public class MauTachChietResource {

    private final Logger log = LoggerFactory.getLogger(MauTachChietResource.class);

    private static final String ENTITY_NAME = "mauTachChiet";

    private final MauTachChietRepository mauTachChietRepository;

    private final MauTachChietSearchRepository mauTachChietSearchRepository;

    public MauTachChietResource(MauTachChietRepository mauTachChietRepository, MauTachChietSearchRepository mauTachChietSearchRepository) {
        this.mauTachChietRepository = mauTachChietRepository;
        this.mauTachChietSearchRepository = mauTachChietSearchRepository;
    }

    /**
     * POST  /mau-tach-chiets : Create a new mauTachChiet.
     *
     * @param mauTachChiet the mauTachChiet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mauTachChiet, or with status 400 (Bad Request) if the mauTachChiet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mau-tach-chiets")
    @Timed
    public ResponseEntity<MauTachChiet> createMauTachChiet(@RequestBody MauTachChiet mauTachChiet) throws URISyntaxException {
        log.debug("REST request to save MauTachChiet : {}", mauTachChiet);
        if (mauTachChiet.getId() != null) {
            throw new BadRequestAlertException("A new mauTachChiet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MauTachChiet result = mauTachChietRepository.save(mauTachChiet);
        mauTachChietSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/mau-tach-chiets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mau-tach-chiets : Updates an existing mauTachChiet.
     *
     * @param mauTachChiet the mauTachChiet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mauTachChiet,
     * or with status 400 (Bad Request) if the mauTachChiet is not valid,
     * or with status 500 (Internal Server Error) if the mauTachChiet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mau-tach-chiets")
    @Timed
    public ResponseEntity<MauTachChiet> updateMauTachChiet(@RequestBody MauTachChiet mauTachChiet) throws URISyntaxException {
        log.debug("REST request to update MauTachChiet : {}", mauTachChiet);
        if (mauTachChiet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MauTachChiet result = mauTachChietRepository.save(mauTachChiet);
        mauTachChietSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mauTachChiet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mau-tach-chiets : get all the mauTachChiets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of mauTachChiets in body
     */
    @GetMapping("/mau-tach-chiets")
    @Timed
    public List<MauTachChiet> getAllMauTachChiets() {
        log.debug("REST request to get all MauTachChiets");
        return mauTachChietRepository.findAll();
    }

    /**
     * GET  /mau-tach-chiets/:id : get the "id" mauTachChiet.
     *
     * @param id the id of the mauTachChiet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mauTachChiet, or with status 404 (Not Found)
     */
    @GetMapping("/mau-tach-chiets/{id}")
    @Timed
    public ResponseEntity<MauTachChiet> getMauTachChiet(@PathVariable Long id) {
        log.debug("REST request to get MauTachChiet : {}", id);
        Optional<MauTachChiet> mauTachChiet = mauTachChietRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mauTachChiet);
    }

    /**
     * DELETE  /mau-tach-chiets/:id : delete the "id" mauTachChiet.
     *
     * @param id the id of the mauTachChiet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mau-tach-chiets/{id}")
    @Timed
    public ResponseEntity<Void> deleteMauTachChiet(@PathVariable Long id) {
        log.debug("REST request to delete MauTachChiet : {}", id);

        mauTachChietRepository.deleteById(id);
        mauTachChietSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/mau-tach-chiets?query=:query : search for the mauTachChiet corresponding
     * to the query.
     *
     * @param query the query of the mauTachChiet search
     * @return the result of the search
     */
    @GetMapping("/_search/mau-tach-chiets")
    @Timed
    public List<MauTachChiet> searchMauTachChiets(@RequestParam String query) {
        log.debug("REST request to search MauTachChiets for query {}", query);
        return StreamSupport
            .stream(mauTachChietSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
