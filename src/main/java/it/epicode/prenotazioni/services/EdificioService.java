package it.epicode.prenotazioni.services;

import it.epicode.prenotazioni.entity.Edificio;
import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.repository.EdificioRepo;
import it.epicode.prenotazioni.repository.PostazioneRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EdificioService {

    private final EdificioRepo edificioRepo;
    private final PostazioneRepo postazioneRepo;



    @Transactional
    public void saveEdifAndPost(Postazione postazione, Edificio edificio) {
        postazioneRepo.save(postazione);
        edificioRepo.save(edificio);
        List<Postazione> listPost = edificio.getPostazioni();
        listPost.add(postazione);
        edificio.setPostazioni(listPost);
        postazione.setEdificio(edificio);
    }

    @Transactional
    public Void addPost(Long id, Postazione postazione) {
        postazioneRepo.save(postazione);


        Edificio edificio = edificioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Edificio non trovato con id: " + id));

        List<Postazione> listPost = edificio.getPostazioni();
        listPost.add(postazione);
        postazione.setEdificio(edificio);


        edificioRepo.save(edificio);

        return null;
    }

}
