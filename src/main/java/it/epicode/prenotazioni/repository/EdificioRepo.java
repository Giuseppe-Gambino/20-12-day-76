package it.epicode.prenotazioni.repository;

import it.epicode.prenotazioni.entity.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EdificioRepo extends JpaRepository<Edificio, Long> {


}