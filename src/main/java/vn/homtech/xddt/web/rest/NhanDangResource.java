package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.NhanDang;
import vn.homtech.xddt.repository.NhanDangRepository;
import vn.homtech.xddt.repository.search.NhanDangSearchRepository;
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
 * REST controller for managing NhanDang.
 */
@RestController
@RequestMapping("/api")
public class NhanDangResource {

    private final Logger log = LoggerFactory.getLogger(NhanDangResource.class);

    private static final String ENTITY_NAME = "nhanDang";

    private final NhanDangRepository nhanDangRepository;

    private final NhanDangSearchRepository nhanDangSearchRepository;

    public NhanDangResource(NhanDangRepository nhanDangRepository, NhanDangSearchRepository nhanDangSearchRepository) {
        this.nhanDangRepository = nhanDangRepository;
        this.nhanDangSearchRepository = nhanDangSearchRepository;
    }

    /**
     * POST  /nhan-dangs : Create a new nhanDang.
     *
     * @param nhanDang the nhanDang to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nhanDang, or with status 400 (Bad Request) if the nhanDang has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nhan-dangs")
    @Timed
    public ResponseEntity<NhanDang> createNhanDang(@RequestBody NhanDang nhanDang) throws URISyntaxException {
        log.debug("REST request to save NhanDang : {}", nhanDang);
        if (nhanDang.getId() != null) {
            throw new BadRequestAlertException("A new nhanDang cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanDang result = nhanDangRepository.save(nhanDang);
        nhanDangSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/nhan-dangs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nhan-dangs : Updates an existing nhanDang.
     *
     * @param nhanDang the nhanDang to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nhanDang,
     * or with status 400 (Bad Request) if the nhanDang is not valid,
     * or with status 500 (Internal Server Error) if the nhanDang couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nhan-dangs")
    @Timed
    public ResponseEntity<NhanDang> updateNhanDang(@RequestBody NhanDang nhanDang) throws URISyntaxException {
        log.debug("REST request to update NhanDang : {}", nhanDang);
        if (nhanDang.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanDang result = nhanDangRepository.save(nhanDang);
        nhanDangSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nhanDang.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nhan-dangs : get all the nhanDangs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nhanDangs in body
     */
    @GetMapping("/nhan-dangs")
    @Timed
    public List<NhanDang> getAllNhanDangs() {
        log.debug("REST request to get all NhanDangs");
        return nhanDangRepository.findAll();
    }

    /**
     * GET  /nhan-dangs/:id : get the "id" nhanDang.
     *
     * @param id the id of the nhanDang to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nhanDang, or with status 404 (Not Found)
     */
    @GetMapping("/nhan-dangs/{id}")
    @Timed
    public ResponseEntity<NhanDang> getNhanDang(@PathVariable Long id) {
        log.debug("REST request to get NhanDang : {}", id);
        Optional<NhanDang> nhanDang = nhanDangRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nhanDang);
    }

    /**
     * DELETE  /nhan-dangs/:id : delete the "id" nhanDang.
     *
     * @param id the id of the nhanDang to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nhan-dangs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNhanDang(@PathVariable Long id) {
        log.debug("REST request to delete NhanDang : {}", id);

        nhanDangRepository.deleteById(id);
        nhanDangSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/nhan-dangs?query=:query : search for the nhanDang corresponding
     * to the query.
     *
     * @param query the query of the nhanDang search
     * @return the result of the search
     */
    @GetMapping("/_search/nhan-dangs")
    @Timed
    public List<NhanDang> searchNhanDangs(@RequestParam String query) {
        log.debug("REST request to search NhanDangs for query {}", query);
        return StreamSupport
            .stream(nhanDangSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
