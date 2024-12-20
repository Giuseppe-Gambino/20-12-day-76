package it.epicode.prenotazioni.entity;

import it.epicode.prenotazioni.enumeration.Tipo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Postazione", query = "SELECT a FROM Postazione a")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "descrizione")
    private String descrizione;

    @Enumerated
    @Column(name = "tipo")
    private Tipo tipo;

    @Column(name = "numero_massimo_occupanti")
    private Integer numeroMassimoOccupanti;

    @OneToMany(mappedBy = "postazione", fetch = FetchType.EAGER)
    private List<Prenotazione> prenotazioni;


    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @Override
    public String toString() {
        return "Postazione " +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipo=" + tipo +
                ", numeroMassimoOccupanti=" + numeroMassimoOccupanti +
                ", edificio=" + edificio.getName();
    }
}
