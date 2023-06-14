package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="klient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KlientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "adres_email")
    private String email;

    @OneToMany(mappedBy = "klientEntity", cascade = CascadeType.ALL)
    private List<KoszykEntity> koszykEntities = new ArrayList<>();


}
