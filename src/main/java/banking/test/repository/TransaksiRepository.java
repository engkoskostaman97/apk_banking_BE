package banking.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import banking.test.model.Transaksi;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
  
}
