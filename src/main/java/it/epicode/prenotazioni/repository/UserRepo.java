package it.epicode.prenotazioni.repository;

import it.epicode.prenotazioni.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}