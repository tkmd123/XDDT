package vn.homtech.xddt.web.rest;

import com.codahale.metrics.annotation.Timed;
import vn.homtech.xddt.domain.NhanVien;
import vn.homtech.xddt.repository.NhanVienRepository;
import vn.homtech.xddt.repository.search.NhanVienSearchRepository;
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
 * REST controller for managing NhanVien.
 */
@RestController
@RequestMapping("/api")
public class NhanVienResource {

    private final Logger log = LoggerFactory.getLogger(NhanVienResource.class);

    private static final String ENTITY_NAME = "nhanVien";

    private final NhanVienRepository nhanVienRepository;

    private final NhanVienSearchRepository nhanVienSearchRepository;

    public NhanVienResource(NhanVienRepository nhanVienRepository, NhanVienSearchRepository nhanVienSearchRepository) {
        this.nhanVienRepository = nhanVienRepository;
        this.nhanVienSearchRepository = nhanVienSearchRepository;
    }

    /**
     * POST  /nhan-viens : Create a new nhanVien.
     *
     * @param nhanVien the nhanVien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nhanVien, or with status 400 (Bad Request) if the nhanVien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nhan-viens")
    @Timed
    public ResponseEntity<NhanVien> createNhanVien(@RequestBody NhanVien nhanVien) throws URISyntaxException {
        log.debug("REST request to save NhanVien : {}", nhanVien);
        if (nhanVien.getId() != null) {
            throw new BadRequestAlertException("A new nhanVien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NhanVien result = nhanVienRepository.save(nhanVien);
        nhanVienSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/nhan-viens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nhan-viens : Updates an existing nhanVien.
     *
     * @param nhanVien the nhanVien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nhanVien,
     * or with status 400 (Bad Request) if the nhanVien is not valid,
     * or with status 500 (Internal Server Error) if the nhanVien couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nhan-viens")
    @Timed
    public ResponseEntity<NhanVien> updateNhanVien(@RequestBody NhanVien nhanVien) throws URISyntaxException {
        log.debug("REST request to update NhanVien : {}", nhanVien);
        if (nhanVien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NhanVien result = nhanVienRepository.save(nhanVien);
        nhanVienSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nhanVien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nhan-viens : get all the nhanViens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nhanViens in body
     */
    @GetMapping("/nhan-viens")
    @Timed
    public List<NhanVien> getAllNhanViens() {
        log.debug("REST request to get all NhanViens");
        return nhanVienRepository.findAll();
    }

    /**
     * GET  /nhan-viens/:id : get the "id" nhanVien.
     *
     * @param id the id of the nhanVien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nhanVien, or with status 404 (Not Found)
     */
    @GetMapping("/nhan-viens/{id}")
    @Timed
    public ResponseEntity<NhanVien> getNhanVien(@PathVariable Long id) {
        log.debug("REST request to get NhanVien : {}", id);
        Optional<NhanVien> nhanVien = nhanVienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nhanVien);
    }

    /**
     * DELETE  /nhan-viens/:id : delete the "id" nhanVien.
     *
     * @param id the id of the nhanVien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nhan-viens/{id}")
    @Timed
    public ResponseEntity<Void> deleteNhanVien(@PathVariable Long id) {
        log.debug("REST request to delete NhanVien : {}", id);

        nhanVienRepository.deleteById(id);
        nhanVienSearchRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/nhan-viens?query=:query : search for the nhanVien corresponding
     * to the query.
     *
     * @param query the query of the nhanVien search
     * @return the result of the search
     */
    @GetMapping("/_search/nhan-viens")
    @Timed
    public List<NhanVien> searchNhanViens(@RequestParam String query) {
        log.debug("REST request to search NhanViens for query {}", query);
        return StreamSupport
            .stream(nhanVienSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
