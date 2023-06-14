package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="pozycja")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PozycjaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iloscProdukt")
    private Integer iloscProdukt;

    @ManyToOne
    @JoinColumn(name="koszyk_id", nullable=false)
    @JsonIgnore
    private KoszykEntity koszykEntity;

    @ManyToOne
    @JoinColumn(name="produkt_id", nullable=false)
    @JsonIgnore
    private ProduktEntity produktEntity;
}
