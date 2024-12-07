package banking.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import banking.test.model.Rekening;
import banking.test.repository.RekeningRepository;

import java.util.List;

@Service
public class RekeningService {
    @Autowired
    private RekeningRepository rekeningRepository;

    public List<Rekening> findAll() {
        return rekeningRepository.findAll();
    }

    public Rekening findById(Integer id) {
        return rekeningRepository.findById(id).orElse(null);
    }

    public Rekening save(Rekening rekening) {
        return rekeningRepository.save(rekening);
    }

    public void deleteById(Integer id) {
        rekeningRepository.deleteById(id);
    }
}
