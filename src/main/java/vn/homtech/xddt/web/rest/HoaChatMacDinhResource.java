package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoaChatMacDinh;
import vn.homtech.xddt.repository.HoaChatMacDinhRepository;
import vn.homtech.xddt.repository.search.HoaChatMacDinhSearchRepository;
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
 * REST controller for managing HoaChatMacDinh.
 */
@RestController
@RequestMapping("/api")
public class HoaChatMacDinhResource {

    private final Logger log = LoggerFactory.getLogger(HoaChatMacDinhResource.class);

    private static final String ENTITY_NAME = "hoaChatMacDinh";

    private final HoaChatMacDinhRepository hoaChatMacDinhRepository;

    private final HoaChatMacDinhSearchRepository hoaChatMacDinhSearchRepository;

    public HoaChatMacDinhResource(HoaChatMacDinhRepository hoaChatMacDinhRepository, HoaChatMacDinhSearchRepository hoaChatMacDinhSearchRepository) {
        this.hoaChatMacDinhRepository = hoaChatMacDinhRepository;
        this.hoaChatMacDinhSearchRepository = hoaChatMacDinhSearchRepository;
    }

    /**
     * POST  /hoa-chat-mac-dinhs : Create a new hoaChatMacDinh.
     *
     * @param hoaChatMacDinh the hoaChatMacDinh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoaChatMacDinh, or with status 400 (Bad Request) if the hoaChatMacDinh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hoa-chat-mac-dinhs")
    @Timed
    public ResponseEntity<HoaChatMacDinh> createHoaChatMacDinh(@RequestBody HoaChatMacDinh hoaChatMacDinh) throws URISyntaxException {
        log.debug("REST request to save HoaChatMacDinh : {}", hoaChatMacDinh);
        if (hoaChatMacDinh.getId() != null) {
            throw new BadRequestAlertException("A new hoaChatMacDinh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoaChatMacDinh result = hoaChatMacDinhRepository.save(hoaChatMacDinh);
        hoaChatMacDinhSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/hoa-chat-mac-dinhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hoa-chat-mac-dinhs : Updates an existing hoaChatMacDinh.
     *
     * @param hoaChatMacDinh the hoaChatMacDinh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoaChatMacDinh,
     * or with status 400 (Bad Request) if the hoaChatMacDinh is not valid,
     * or with status 500 (Internal Server Error) if the hoaChatMacDinh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hoa-chat-mac-dinhs")
    @Timed
    public ResponseEntity<HoaChatMacDinh> updateHoaChatMacDinh(@RequestBody HoaChatMacDinh hoaChatMacDinh) throws URISyntaxException {
        log.debug("REST request to update HoaChatMacDinh : {}", hoaChatMacDinh);
        if (hoaChatMacDinh.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoaChatMacDinh result = hoaChatMacDinhRepository.save(hoaChatMacDinh);
        hoaChatMacDinhSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoaChatMacDinh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hoa-chat-mac-dinhs : get all the hoaChatMacDinhs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hoaChatMacDinhs in body
     */
    @GetMapping("/hoa-chat-mac-dinhs")
    @Timed
    public List<HoaChatMacDinh> getAllHoaChatMacDinhs() {
        log.debug("REST request to get all HoaChatMacDinhs");
        return hoaChatMacDinhRepository.findAll();
    }

    /**
     * GET  /hoa-chat-mac-dinhs/:id : get the "id" hoaChatMacDinh.
     *
     * @param id the id of the hoaChatMacDinh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoaChatMacDinh, or with status 404 (Not Found)
     */
    @GetMapping("/hoa-chat-mac-dinhs/{id}")
    @Timed
    public ResponseEntity<HoaChatMacDinh> getHoaChatMacDinh(@PathVariable Long id) {
        log.debug("REST request to get HoaChatMacDinh : {}", id);
        Optional<HoaChatMacDinh> hoaChatMacDinh = hoaChatMacDinhRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoaChatMacDinh);
    }

    /**
     * DELETE  /hoa-chat-mac-dinhs/:id : delete the "id" hoaChatMacDinh.
     *
     * @param id the id of the hoaChatMacDinh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hoa-chat-mac-dinhs/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoaChatMacDinh(@PathVariable Long id) {
        log.debug("REST request to delete HoaChatMacDinh : {}", id);

        hoaChatMacDinhRepository.deleteById(id);
        hoaChatMacDinhSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hoa-chat-mac-dinhs?query=:query : search for the hoaChatMacDinh corresponding
     * to the query.
     *
     * @param query the query of the hoaChatMacDinh search
     * @return the result of the search
     */
    @GetMapping("/_search/hoa-chat-mac-dinhs")
    @Timed
    public List<HoaChatMacDinh> searchHoaChatMacDinhs(@RequestParam String query) {
        log.debug("REST request to search HoaChatMacDinhs for query {}", query);
        return StreamSupport
            .stream(hoaChatMacDinhSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
