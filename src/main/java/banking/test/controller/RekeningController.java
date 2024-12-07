package banking.test.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import banking.test.model.Rekening;
import banking.test.service.RekeningService;

import java.util.List;

@RestController
@RequestMapping("/api/rekening")
@CrossOrigin(origins = "http://localhost:3000")
public class RekeningController {
    @Autowired
    private RekeningService rekeningService;

    @GetMapping
    public List<Rekening> getAllRekening() {
        return rekeningService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rekening> getRekeningById(@PathVariable Integer id) {
        Rekening rekening = rekeningService.findById(id);
        return rekening != null ? ResponseEntity.ok(rekening) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Rekening createRekening(@RequestBody Rekening rekening) {
        return rekeningService.save(rekening);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rekening> updateRekening(@PathVariable Integer id, @RequestBody Rekening rekening) {
        rekening.setId(id);
        return ResponseEntity.ok(rekeningService.save(rekening));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRekening(@PathVariable Integer id) {
        rekeningService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
