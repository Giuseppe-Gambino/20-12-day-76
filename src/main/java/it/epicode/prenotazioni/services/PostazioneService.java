package it.epicode.prenotazioni.services;

import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.enumeration.Tipo;
import it.epicode.prenotazioni.repository.PostazioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostazioneService {

    @Autowired
    public PostazioneRepo postazioneRepo;

    public Postazione save(Postazione postazione) {
        return postazioneRepo.save(postazione);
    }

    public Postazione findById(Long id) {
        return postazioneRepo.findById(id).orElseThrow(() -> new RuntimeException("Postazione non trovata"));
    }

//    public List<Postazione> findDisponibili(String tipo, String citta, LocalDate data) {
//        return postazioneRepo.findDisponibili(tipo, citta, data);
//    }

        public List<Postazione> findDisponibiliByDate(LocalDate data) {
        return postazioneRepo.findDisponibili(data);
    }

    public List<Postazione> findPostazioniByCittaAndTipo(String citta, Tipo tipo) {
       return postazioneRepo.findPostazioniByCittaAndTipo(citta, tipo);
    }

    public List<Postazione> findDisponibili(String citta, Tipo tipo, LocalDate data) {
        return postazioneRepo.findAvailablePostazioniByCittaAndTipo(citta, tipo, data);
    }
}
