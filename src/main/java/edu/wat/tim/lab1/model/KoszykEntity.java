package edu.wat.tim.lab1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="koszyk")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KoszykEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="klient_id", nullable=false)
    @JsonIgnore
    private KlientEntity klientEntity;

    @OneToMany(mappedBy = "koszykEntity", cascade = CascadeType.MERGE)
    private List<PozycjaEntity> pozycjaEntities = new ArrayList<>();
}
