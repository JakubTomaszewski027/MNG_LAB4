package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="produkt")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProduktEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jednostka_miary")
    private String miara;

    @Column(name = "opis")
    private String opis;

    @OneToMany (mappedBy = "produktEntity", cascade = CascadeType.MERGE)
    private List<PozycjaEntity> pozycjaEntities = new ArrayList<>();
}
