package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoaChatTachChiet;
import vn.homtech.xddt.repository.HoaChatTachChietRepository;
import vn.homtech.xddt.repository.search.HoaChatTachChietSearchRepository;
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
 * REST controller for managing HoaChatTachChiet.
 */
@RestController
@RequestMapping("/api")
public class HoaChatTachChietResource {

    private final Logger log = LoggerFactory.getLogger(HoaChatTachChietResource.class);

    private static final String ENTITY_NAME = "hoaChatTachChiet";

    private final HoaChatTachChietRepository hoaChatTachChietRepository;

    private final HoaChatTachChietSearchRepository hoaChatTachChietSearchRepository;

    public HoaChatTachChietResource(HoaChatTachChietRepository hoaChatTachChietRepository, HoaChatTachChietSearchRepository hoaChatTachChietSearchRepository) {
        this.hoaChatTachChietRepository = hoaChatTachChietRepository;
        this.hoaChatTachChietSearchRepository = hoaChatTachChietSearchRepository;
    }

    /**
     * POST  /hoa-chat-tach-chiets : Create a new hoaChatTachChiet.
     *
     * @param hoaChatTachChiet the hoaChatTachChiet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoaChatTachChiet, or with status 400 (Bad Request) if the hoaChatTachChiet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hoa-chat-tach-chiets")
    @Timed
    public ResponseEntity<HoaChatTachChiet> createHoaChatTachChiet(@RequestBody HoaChatTachChiet hoaChatTachChiet) throws URISyntaxException {
        log.debug("REST request to save HoaChatTachChiet : {}", hoaChatTachChiet);
        if (hoaChatTachChiet.getId() != null) {
            throw new BadRequestAlertException("A new hoaChatTachChiet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoaChatTachChiet result = hoaChatTachChietRepository.save(hoaChatTachChiet);
        hoaChatTachChietSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/hoa-chat-tach-chiets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hoa-chat-tach-chiets : Updates an existing hoaChatTachChiet.
     *
     * @param hoaChatTachChiet the hoaChatTachChiet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoaChatTachChiet,
     * or with status 400 (Bad Request) if the hoaChatTachChiet is not valid,
     * or with status 500 (Internal Server Error) if the hoaChatTachChiet couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hoa-chat-tach-chiets")
    @Timed
    public ResponseEntity<HoaChatTachChiet> updateHoaChatTachChiet(@RequestBody HoaChatTachChiet hoaChatTachChiet) throws URISyntaxException {
        log.debug("REST request to update HoaChatTachChiet : {}", hoaChatTachChiet);
        if (hoaChatTachChiet.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoaChatTachChiet result = hoaChatTachChietRepository.save(hoaChatTachChiet);
        hoaChatTachChietSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoaChatTachChiet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hoa-chat-tach-chiets : get all the hoaChatTachChiets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hoaChatTachChiets in body
     */
    @GetMapping("/hoa-chat-tach-chiets")
    @Timed
    public List<HoaChatTachChiet> getAllHoaChatTachChiets() {
        log.debug("REST request to get all HoaChatTachChiets");
        return hoaChatTachChietRepository.findAll();
    }

    /**
     * GET  /hoa-chat-tach-chiets/:id : get the "id" hoaChatTachChiet.
     *
     * @param id the id of the hoaChatTachChiet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoaChatTachChiet, or with status 404 (Not Found)
     */
    @GetMapping("/hoa-chat-tach-chiets/{id}")
    @Timed
    public ResponseEntity<HoaChatTachChiet> getHoaChatTachChiet(@PathVariable Long id) {
        log.debug("REST request to get HoaChatTachChiet : {}", id);
        Optional<HoaChatTachChiet> hoaChatTachChiet = hoaChatTachChietRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoaChatTachChiet);
    }

    /**
     * DELETE  /hoa-chat-tach-chiets/:id : delete the "id" hoaChatTachChiet.
     *
     * @param id the id of the hoaChatTachChiet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hoa-chat-tach-chiets/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoaChatTachChiet(@PathVariable Long id) {
        log.debug("REST request to delete HoaChatTachChiet : {}", id);

        hoaChatTachChietRepository.deleteById(id);
        hoaChatTachChietSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hoa-chat-tach-chiets?query=:query : search for the hoaChatTachChiet corresponding
     * to the query.
     *
     * @param query the query of the hoaChatTachChiet search
     * @return the result of the search
     */
    @GetMapping("/_search/hoa-chat-tach-chiets")
    @Timed
    public List<HoaChatTachChiet> searchHoaChatTachChiets(@RequestParam String query) {
        log.debug("REST request to search HoaChatTachChiets for query {}", query);
        return StreamSupport
            .stream(hoaChatTachChietSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
