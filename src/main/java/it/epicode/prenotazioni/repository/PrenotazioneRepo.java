package it.epicode.prenotazioni.repository;

import it.epicode.prenotazioni.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioneRepo extends JpaRepository<Prenotazione, Long> {
}