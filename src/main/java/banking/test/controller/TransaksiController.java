package banking.test.controller;

import banking.test.model.Transaksi;
import banking.test.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaksi")
@CrossOrigin(origins = "http://localhost:3000")
public class TransaksiController {

    @Autowired
    private TransaksiService transaksiService;

    @GetMapping
    public List<Transaksi> getAllTransaksi() {
        return transaksiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaksi> getTransaksiById(@PathVariable Integer id) {
        Transaksi transaksi = transaksiService.findById(id);
        return transaksi != null ? ResponseEntity.ok(transaksi) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Transaksi> createTransaksi(@RequestBody Transaksi transaksi) {
        return ResponseEntity.ok(transaksiService.save(transaksi));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaksi> updateTransaksi(@PathVariable Integer id, @RequestBody Transaksi transaksi) {
        try {
            Transaksi updatedTransaksi = transaksiService.updateTransaksi(id, transaksi);
            return ResponseEntity.ok(updatedTransaksi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaksi(@PathVariable Integer id) {
        transaksiService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
