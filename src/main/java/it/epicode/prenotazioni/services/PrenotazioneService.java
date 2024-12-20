package it.epicode.prenotazioni.services;

import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.entity.Prenotazione;
import it.epicode.prenotazioni.entity.User;
import it.epicode.prenotazioni.repository.PrenotazioneRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final UserService userService;
    private final PostazioneService postazioneService;
    private final PrenotazioneRepo prenotazioneRepository;

    public Prenotazione save(Prenotazione prenotazione) {
        return prenotazioneRepository.save(prenotazione);
    }

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public void deleteById(Long id) {
        prenotazioneRepository.deleteById(id);
    }

    @Transactional
    public void savePrenot(User user,Postazione postazione,Prenotazione prenotazione){


        prenotazione.setUser(user);
        prenotazione.setPostazione(postazione);

        List<Prenotazione> listPre = user.getPrenotazioni();

        if (listPre == null) {
            listPre = new ArrayList<>();
        }

        listPre.add(prenotazione);
        user.setPrenotazioni(listPre);

        prenotazioneRepository.save(prenotazione);
    }



}
