package it.epicode.prenotazioni.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Edificio", query = "SELECT a FROM Edificio a")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "citta")
    private String citta;

    @OneToMany(mappedBy = "edificio", orphanRemoval = true)
    private List<Postazione> postazioni = new ArrayList<>();


    @Override
    public String toString() {
        return "Edificio " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", citta='" + citta + '\'' +
                ", postazioni=" + postazioni;
    }
}
