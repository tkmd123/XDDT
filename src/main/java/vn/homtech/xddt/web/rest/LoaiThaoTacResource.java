package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.LoaiThaoTac;
import vn.homtech.xddt.repository.LoaiThaoTacRepository;
import vn.homtech.xddt.repository.search.LoaiThaoTacSearchRepository;
import vn.homtech.xddt.web.rest.errors.BadRequestAlertException;
import vn.homtech.xddt.web.rest.util.HeaderUtil;
import vn.homtech.xddt.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing LoaiThaoTac.
 */
@RestController
@RequestMapping("/api")
public class LoaiThaoTacResource {

    private final Logger log = LoggerFactory.getLogger(LoaiThaoTacResource.class);

    private static final String ENTITY_NAME = "loaiThaoTac";

    private final LoaiThaoTacRepository loaiThaoTacRepository;

    private final LoaiThaoTacSearchRepository loaiThaoTacSearchRepository;

    public LoaiThaoTacResource(LoaiThaoTacRepository loaiThaoTacRepository, LoaiThaoTacSearchRepository loaiThaoTacSearchRepository) {
        this.loaiThaoTacRepository = loaiThaoTacRepository;
        this.loaiThaoTacSearchRepository = loaiThaoTacSearchRepository;
    }

    /**
     * POST  /loai-thao-tacs : Create a new loaiThaoTac.
     *
     * @param loaiThaoTac the loaiThaoTac to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loaiThaoTac, or with status 400 (Bad Request) if the loaiThaoTac has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loai-thao-tacs")
    @Timed
    public ResponseEntity<LoaiThaoTac> createLoaiThaoTac(@RequestBody LoaiThaoTac loaiThaoTac) throws URISyntaxException {
        log.debug("REST request to save LoaiThaoTac : {}", loaiThaoTac);
        if (loaiThaoTac.getId() != null) {
            throw new BadRequestAlertException("A new loaiThaoTac cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoaiThaoTac result = loaiThaoTacRepository.save(loaiThaoTac);
        loaiThaoTacSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/loai-thao-tacs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loai-thao-tacs : Updates an existing loaiThaoTac.
     *
     * @param loaiThaoTac the loaiThaoTac to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loaiThaoTac,
     * or with status 400 (Bad Request) if the loaiThaoTac is not valid,
     * or with status 500 (Internal Server Error) if the loaiThaoTac couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loai-thao-tacs")
    @Timed
    public ResponseEntity<LoaiThaoTac> updateLoaiThaoTac(@RequestBody LoaiThaoTac loaiThaoTac) throws URISyntaxException {
        log.debug("REST request to update LoaiThaoTac : {}", loaiThaoTac);
        if (loaiThaoTac.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoaiThaoTac result = loaiThaoTacRepository.save(loaiThaoTac);
        loaiThaoTacSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loaiThaoTac.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loai-thao-tacs : get all the loaiThaoTacs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loaiThaoTacs in body
     */
    @GetMapping("/loai-thao-tacs")
    @Timed
    public ResponseEntity<List<LoaiThaoTac>> getAllLoaiThaoTacs(Pageable pageable) {
        log.debug("REST request to get a page of LoaiThaoTacs");
        Page<LoaiThaoTac> page = loaiThaoTacRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loai-thao-tacs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /loai-thao-tacs/:id : get the "id" loaiThaoTac.
     *
     * @param id the id of the loaiThaoTac to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loaiThaoTac, or with status 404 (Not Found)
     */
    @GetMapping("/loai-thao-tacs/{id}")
    @Timed
    public ResponseEntity<LoaiThaoTac> getLoaiThaoTac(@PathVariable Long id) {
        log.debug("REST request to get LoaiThaoTac : {}", id);
        Optional<LoaiThaoTac> loaiThaoTac = loaiThaoTacRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loaiThaoTac);
    }

    /**
     * DELETE  /loai-thao-tacs/:id : delete the "id" loaiThaoTac.
     *
     * @param id the id of the loaiThaoTac to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loai-thao-tacs/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoaiThaoTac(@PathVariable Long id) {
        log.debug("REST request to delete LoaiThaoTac : {}", id);

        loaiThaoTacRepository.deleteById(id);
        loaiThaoTacSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/loai-thao-tacs?query=:query : search for the loaiThaoTac corresponding
     * to the query.
     *
     * @param query the query of the loaiThaoTac search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/loai-thao-tacs")
    @Timed
    public ResponseEntity<List<LoaiThaoTac>> searchLoaiThaoTacs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of LoaiThaoTacs for query {}", query);
        Page<LoaiThaoTac> page = loaiThaoTacSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/loai-thao-tacs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
