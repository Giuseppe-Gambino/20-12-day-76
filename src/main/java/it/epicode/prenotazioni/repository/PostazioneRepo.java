package it.epicode.prenotazioni.repository;

import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.enumeration.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostazioneRepo extends JpaRepository<Postazione, Long> {


    @Query("SELECT p FROM Postazione p WHERE p.id NOT IN (SELECT pr.postazione.id FROM Prenotazione pr WHERE pr.dataPrenotazione = :data)")
    List<Postazione> findDisponibili(@Param("data") LocalDate data);

    @Query("SELECT p FROM Postazione p JOIN p.edificio e WHERE e.citta = :citta AND p.tipo = :tipo")
    List<Postazione> findPostazioniByCittaAndTipo(@Param("citta") String citta, @Param("tipo") Tipo tipo);

    @Query("SELECT p FROM Postazione p " +
            "JOIN p.edificio e " +
            "WHERE e.citta = :citta " +
            "AND p.tipo = :tipo " +
            "AND p.id NOT IN (SELECT pr.postazione.id FROM Prenotazione pr WHERE pr.dataPrenotazione = :data)")
    List<Postazione> findAvailablePostazioniByCittaAndTipo(
            @Param("citta") String citta,
            @Param("tipo") Tipo tipo,
            @Param("data") LocalDate data);

}