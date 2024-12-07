package banking.test.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaksi {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "nomor_rekening", referencedColumnName = "nomor_rekening", nullable = false)
    private Rekening nomorRekening;

    @Column(name = "jenis_transaksi", nullable = false)
    private String jenisTransaksi; // 'debit' atau 'kredit'

    @Column(name = "jumlah_transaksi", nullable = false)
    private Float jumlahTransaksi;

    @Column(name = "tanggal_transaksi", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalTransaksi;
}