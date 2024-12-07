package banking.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import banking.test.model.Rekening;

public interface RekeningRepository extends JpaRepository<Rekening, Integer> {
  Rekening findByNomorRekening(String nomorRekening);
}