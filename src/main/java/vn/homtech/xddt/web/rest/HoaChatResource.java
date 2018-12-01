package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.HoaChat;
import vn.homtech.xddt.repository.HoaChatRepository;
import vn.homtech.xddt.repository.search.HoaChatSearchRepository;
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
 * REST controller for managing HoaChat.
 */
@RestController
@RequestMapping("/api")
public class HoaChatResource {

    private final Logger log = LoggerFactory.getLogger(HoaChatResource.class);

    private static final String ENTITY_NAME = "hoaChat";

    private final HoaChatRepository hoaChatRepository;

    private final HoaChatSearchRepository hoaChatSearchRepository;

    public HoaChatResource(HoaChatRepository hoaChatRepository, HoaChatSearchRepository hoaChatSearchRepository) {
        this.hoaChatRepository = hoaChatRepository;
        this.hoaChatSearchRepository = hoaChatSearchRepository;
    }

    /**
     * POST  /hoa-chats : Create a new hoaChat.
     *
     * @param hoaChat the hoaChat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hoaChat, or with status 400 (Bad Request) if the hoaChat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hoa-chats")
    @Timed
    public ResponseEntity<HoaChat> createHoaChat(@RequestBody HoaChat hoaChat) throws URISyntaxException {
        log.debug("REST request to save HoaChat : {}", hoaChat);
        if (hoaChat.getId() != null) {
            throw new BadRequestAlertException("A new hoaChat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HoaChat result = hoaChatRepository.save(hoaChat);
        hoaChatSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/hoa-chats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hoa-chats : Updates an existing hoaChat.
     *
     * @param hoaChat the hoaChat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hoaChat,
     * or with status 400 (Bad Request) if the hoaChat is not valid,
     * or with status 500 (Internal Server Error) if the hoaChat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hoa-chats")
    @Timed
    public ResponseEntity<HoaChat> updateHoaChat(@RequestBody HoaChat hoaChat) throws URISyntaxException {
        log.debug("REST request to update HoaChat : {}", hoaChat);
        if (hoaChat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HoaChat result = hoaChatRepository.save(hoaChat);
        hoaChatSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hoaChat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hoa-chats : get all the hoaChats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hoaChats in body
     */
    @GetMapping("/hoa-chats")
    @Timed
    public List<HoaChat> getAllHoaChats() {
        log.debug("REST request to get all HoaChats");
        return hoaChatRepository.findAll();
    }

    /**
     * GET  /hoa-chats/:id : get the "id" hoaChat.
     *
     * @param id the id of the hoaChat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hoaChat, or with status 404 (Not Found)
     */
    @GetMapping("/hoa-chats/{id}")
    @Timed
    public ResponseEntity<HoaChat> getHoaChat(@PathVariable Long id) {
        log.debug("REST request to get HoaChat : {}", id);
        Optional<HoaChat> hoaChat = hoaChatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoaChat);
    }

    /**
     * DELETE  /hoa-chats/:id : delete the "id" hoaChat.
     *
     * @param id the id of the hoaChat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hoa-chats/{id}")
    @Timed
    public ResponseEntity<Void> deleteHoaChat(@PathVariable Long id) {
        log.debug("REST request to delete HoaChat : {}", id);

        hoaChatRepository.deleteById(id);
        hoaChatSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hoa-chats?query=:query : search for the hoaChat corresponding
     * to the query.
     *
     * @param query the query of the hoaChat search
     * @return the result of the search
     */
    @GetMapping("/_search/hoa-chats")
    @Timed
    public List<HoaChat> searchHoaChats(@RequestParam String query) {
        log.debug("REST request to search HoaChats for query {}", query);
        return StreamSupport
            .stream(hoaChatSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
