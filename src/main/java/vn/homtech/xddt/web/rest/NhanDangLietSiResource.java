package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.NhanDangLietSi;
import vn.homtech.xddt.repository.NhanDangLietSiRepository;
import vn.homtech.xddt.repository.search.NhanDangLietSiSearchRepository;
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
 * REST controller for managing NhanDangLietSi.
 */
@RestController
@RequestMapping("/api")
public class NhanDangLietSiResource {

    private final Logger log = LoggerFactory.getLogger(NhanDangLietSiResource.class);

    private static final String ENTITY_NAME = "nhanDangLietSi";

    private final NhanDangLietSiRepository nhanDangLietSiRepository;

    private final NhanDangLietSiSearchRepository nhanDangLietSiSearchRepository;

    public NhanDangLietSiResource(NhanDangLietSiRepository nhanDangLietSiRepository, NhanDangLietSiSearchRepository nhanDangLietSiSearchRepository) {
        this.nhanDangLietSiRepository = nhanDangLietSiRepository;
        this.nhanDangLietSiSearchRepository = nhanDangLietSiSearchRepository;
    }

    /**
     * POST  /nhan-dang-liet-sis : Create a new nhanDangLietSi.
     *
     * @param nhanDangLietSi the nhanDangLietSi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nhanDangLietSi, or with status 400 (Bad Request) if the nhanDangLietSi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nhan-dang-liet-sis")
    @Timed
    public ResponseEntity<NhanDangLietSi> createNhanDangLietSi(@RequestBody NhanDangLietSi nhanDangLietSi) throws URISyntaxException {
        log.debug("REST request to save NhanDangLietSi : {}", nhanDangLietSi);
        if (nhanDangLietSi.getId() != null) {
            throw new BadRequestAlertException("A new nhanDangLietSi cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanDangLietSi result = nhanDangLietSiRepository.save(nhanDangLietSi);
        nhanDangLietSiSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/nhan-dang-liet-sis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nhan-dang-liet-sis : Updates an existing nhanDangLietSi.
     *
     * @param nhanDangLietSi the nhanDangLietSi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nhanDangLietSi,
     * or with status 400 (Bad Request) if the nhanDangLietSi is not valid,
     * or with status 500 (Internal Server Error) if the nhanDangLietSi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nhan-dang-liet-sis")
    @Timed
    public ResponseEntity<NhanDangLietSi> updateNhanDangLietSi(@RequestBody NhanDangLietSi nhanDangLietSi) throws URISyntaxException {
        log.debug("REST request to update NhanDangLietSi : {}", nhanDangLietSi);
        if (nhanDangLietSi.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanDangLietSi result = nhanDangLietSiRepository.save(nhanDangLietSi);
        nhanDangLietSiSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nhanDangLietSi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nhan-dang-liet-sis : get all the nhanDangLietSis.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nhanDangLietSis in body
     */
    @GetMapping("/nhan-dang-liet-sis")
    @Timed
    public List<NhanDangLietSi> getAllNhanDangLietSis() {
        log.debug("REST request to get all NhanDangLietSis");
        return nhanDangLietSiRepository.findAll();
    }

    /**
     * GET  /nhan-dang-liet-sis/:id : get the "id" nhanDangLietSi.
     *
     * @param id the id of the nhanDangLietSi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nhanDangLietSi, or with status 404 (Not Found)
     */
    @GetMapping("/nhan-dang-liet-sis/{id}")
    @Timed
    public ResponseEntity<NhanDangLietSi> getNhanDangLietSi(@PathVariable Long id) {
        log.debug("REST request to get NhanDangLietSi : {}", id);
        Optional<NhanDangLietSi> nhanDangLietSi = nhanDangLietSiRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nhanDangLietSi);
    }

    /**
     * DELETE  /nhan-dang-liet-sis/:id : delete the "id" nhanDangLietSi.
     *
     * @param id the id of the nhanDangLietSi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nhan-dang-liet-sis/{id}")
    @Timed
    public ResponseEntity<Void> deleteNhanDangLietSi(@PathVariable Long id) {
        log.debug("REST request to delete NhanDangLietSi : {}", id);

        nhanDangLietSiRepository.deleteById(id);
        nhanDangLietSiSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/nhan-dang-liet-sis?query=:query : search for the nhanDangLietSi corresponding
     * to the query.
     *
     * @param query the query of the nhanDangLietSi search
     * @return the result of the search
     */
    @GetMapping("/_search/nhan-dang-liet-sis")
    @Timed
    public List<NhanDangLietSi> searchNhanDangLietSis(@RequestParam String query) {
        log.debug("REST request to search NhanDangLietSis for query {}", query);
        return StreamSupport
            .stream(nhanDangLietSiSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
