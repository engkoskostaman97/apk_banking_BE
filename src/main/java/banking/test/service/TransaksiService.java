package banking.test.service;

import banking.test.model.Rekening;
import banking.test.model.Transaksi;
import banking.test.repository.RekeningRepository;
import banking.test.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private RekeningRepository rekeningRepository;

    public List<Transaksi> findAll() {
        return transaksiRepository.findAll();
    }

    public Transaksi findById(Integer id) {
        return transaksiRepository.findById(id).orElse(null);
    }

    public Transaksi save(Transaksi transaksi) {
        // Periksa apakah rekening sudah ada di database
        Rekening rekening = rekeningRepository.findByNomorRekening(
            transaksi.getNomorRekening().getNomorRekening()
        );

        if (rekening == null) {
            rekening = transaksi.getNomorRekening();
            rekeningRepository.save(rekening);
        }

        // Kaitkan rekening dengan transaksi
        transaksi.setNomorRekening(rekening);

        // Periksa jika transaksi adalah debit, kurangi saldo
        if ("debit".equalsIgnoreCase(transaksi.getJenisTransaksi())) {
            if (rekening.getSaldo() >= transaksi.getJumlahTransaksi()) {
                rekening.setSaldo(rekening.getSaldo() - transaksi.getJumlahTransaksi());
                rekeningRepository.save(rekening);
            } else {
                throw new RuntimeException("Saldo tidak cukup untuk transaksi debit.");
            }
        }

        
        if ("kredit".equalsIgnoreCase(transaksi.getJenisTransaksi())) {
            if (rekening.getSaldo() >= transaksi.getJumlahTransaksi()) {
                rekening.setSaldo(rekening.getSaldo() - transaksi.getJumlahTransaksi());
                rekeningRepository.save(rekening);
            } else {
                throw new RuntimeException("Saldo tidak cukup untuk transaksi debit.");
            }
        }

        return transaksiRepository.save(transaksi);
    }

    public void deleteById(Integer id) {
        transaksiRepository.deleteById(id);
    }

    public Transaksi updateTransaksi(Integer id, Transaksi transaksi) {
        Transaksi transaksiLama = transaksiRepository.findById(id).orElse(null);

        if (transaksiLama == null) {
            throw new RuntimeException("Transaksi dengan ID " + id + " tidak ditemukan.");
        }

        Rekening rekening = rekeningRepository.findByNomorRekening(
            transaksi.getNomorRekening().getNomorRekening()
        );

        if (rekening == null) {
            rekening = transaksi.getNomorRekening();
            rekeningRepository.save(rekening);
        }

        if ("debit".equalsIgnoreCase(transaksi.getJenisTransaksi())) {
            if (rekening.getSaldo() >= transaksi.getJumlahTransaksi()) {
                rekening.setSaldo(rekening.getSaldo() - transaksi.getJumlahTransaksi());
                rekeningRepository.save(rekening);
            } else {
                throw new RuntimeException("Saldo tidak cukup untuk transaksi debit.");
            }
        }

        if ("kredit".equalsIgnoreCase(transaksi.getJenisTransaksi())) {
            if (rekening.getSaldo() >= transaksi.getJumlahTransaksi()) {
                rekening.setSaldo(rekening.getSaldo() - transaksi.getJumlahTransaksi());
                rekeningRepository.save(rekening);
            } else {
                throw new RuntimeException("Saldo tidak cukup untuk transaksi debit.");
            }
        }

        transaksi.setId(id);
        transaksi.setNomorRekening(rekening);

        return transaksiRepository.save(transaksi);
    }
}
