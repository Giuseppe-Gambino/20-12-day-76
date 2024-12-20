package it.epicode.prenotazioni.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@NamedQuery(name = "Trova_tutto_Prenotazione", query = "SELECT a FROM Prenotazione a")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "postazione_id", nullable = false)
    private Postazione postazione;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", postazione=" + postazione.getId() +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }
}
