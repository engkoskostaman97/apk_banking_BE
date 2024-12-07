package banking.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "rekening")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rekening {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nama_pemilik", nullable = false)
    private String namaPemilik;

    @Column(name = "nomor_rekening", unique = true, nullable = false)
    private String nomorRekening;

    @JsonCreator
    public Rekening(@JsonProperty("nomorRekening") String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    @Column(name = "saldo", nullable = false)
    private Float saldo;

    @Column(name = "tanggal_pembuatan", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalPembuatan;
}