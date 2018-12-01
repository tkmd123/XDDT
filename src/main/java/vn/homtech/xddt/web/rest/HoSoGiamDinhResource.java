package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoSoGiamDinh;
import vn.homtech.xddt.repository.HoSoGiamDinhRepository;
import vn.homtech.xddt.repository.search.HoSoGiamDinhSearchRepository;
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
 * REST controller for managing HoSoGiamDinh.
 */
@RestController
@RequestMapping("/api")
public class HoSoGiamDinhResource {

    private final Logger log = LoggerFactory.getLogger(HoSoGiamDinhResource.class);

    private static final String ENTITY_NAME = "hoSoGiamDinh";

    private final HoSoGiamDinhRepository hoSoGiamDinhRepository;

    private final HoSoGiamDinhSearchRepository hoSoGiamDinhSearchRepository;

    public HoSoGiamDinhResource(HoSoGiamDinhRepository hoSoGiamDinhRepository, HoSoGiamDinhSearchRepository hoSoGiamDinhSearchRepository) {
        this.hoSoGiamDinhRepository = hoSoGiamDinhRepository;
        this.hoSoGiamDinhSearchRepository = hoSoGiamDinhSearchRepository;
    }

    /**
     * POST  /ho-so-giam-dinhs : Create a new hoSoGiamDinh.
     *
     * @param hoSoGiamDinh the hoSoGiamDinh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoSoGiamDinh, or with status 400 (Bad Request) if the hoSoGiamDinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ho-so-giam-dinhs")
    @Timed
    public ResponseEntity<HoSoGiamDinh> createHoSoGiamDinh(@RequestBody HoSoGiamDinh hoSoGiamDinh) throws URISyntaxException {
        log.debug("REST request to save HoSoGiamDinh : {}", hoSoGiamDinh);
        if (hoSoGiamDinh.getId() != null) {
            throw new BadRequestAlertException("A new hoSoGiamDinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoSoGiamDinh result = hoSoGiamDinhRepository.save(hoSoGiamDinh);
        hoSoGiamDinhSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ho-so-giam-dinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ho-so-giam-dinhs : Updates an existing hoSoGiamDinh.
     *
     * @param hoSoGiamDinh the hoSoGiamDinh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoSoGiamDinh,
     * or with status 400 (Bad Request) if the hoSoGiamDinh is not valid,
     * or with status 500 (Internal Server Error) if the hoSoGiamDinh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ho-so-giam-dinhs")
    @Timed
    public ResponseEntity<HoSoGiamDinh> updateHoSoGiamDinh(@RequestBody HoSoGiamDinh hoSoGiamDinh) throws URISyntaxException {
        log.debug("REST request to update HoSoGiamDinh : {}", hoSoGiamDinh);
        if (hoSoGiamDinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoSoGiamDinh result = hoSoGiamDinhRepository.save(hoSoGiamDinh);
        hoSoGiamDinhSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoSoGiamDinh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ho-so-giam-dinhs : get all the hoSoGiamDinhs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of hoSoGiamDinhs in body
     */
    @GetMapping("/ho-so-giam-dinhs")
    @Timed
    public List<HoSoGiamDinh> getAllHoSoGiamDinhs(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all HoSoGiamDinhs");
        return hoSoGiamDinhRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /ho-so-giam-dinhs/:id : get the "id" hoSoGiamDinh.
     *
     * @param id the id of the hoSoGiamDinh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoSoGiamDinh, or with status 404 (Not Found)
     */
    @GetMapping("/ho-so-giam-dinhs/{id}")
    @Timed
    public ResponseEntity<HoSoGiamDinh> getHoSoGiamDinh(@PathVariable Long id) {
        log.debug("REST request to get HoSoGiamDinh : {}", id);
        Optional<HoSoGiamDinh> hoSoGiamDinh = hoSoGiamDinhRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(hoSoGiamDinh);
    }

    /**
     * DELETE  /ho-so-giam-dinhs/:id : delete the "id" hoSoGiamDinh.
     *
     * @param id the id of the hoSoGiamDinh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ho-so-giam-dinhs/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoSoGiamDinh(@PathVariable Long id) {
        log.debug("REST request to delete HoSoGiamDinh : {}", id);

        hoSoGiamDinhRepository.deleteById(id);
        hoSoGiamDinhSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ho-so-giam-dinhs?query=:query : search for the hoSoGiamDinh corresponding
     * to the query.
     *
     * @param query the query of the hoSoGiamDinh search
     * @return the result of the search
     */
    @GetMapping("/_search/ho-so-giam-dinhs")
    @Timed
    public List<HoSoGiamDinh> searchHoSoGiamDinhs(@RequestParam String query) {
        log.debug("REST request to search HoSoGiamDinhs for query {}", query);
        return StreamSupport
            .stream(hoSoGiamDinhSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
