package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.AutoConfiguration;


@Entity
@Table(name="komentarz")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KomentarzEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "autor")
    private String autor;

    @Column(name = "tresc")
    private String tresc;

    @ManyToOne
    @JoinColumn(name = "produkt_id", nullable = false)
    private ProduktEntity produktEntity;

}
