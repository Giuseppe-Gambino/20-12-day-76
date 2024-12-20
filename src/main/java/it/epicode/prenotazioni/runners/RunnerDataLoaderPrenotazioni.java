package it.epicode.prenotazioni.runners;

import com.github.javafaker.Faker;
import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.entity.Prenotazione;
import it.epicode.prenotazioni.entity.User;
import it.epicode.prenotazioni.services.PostazioneService;
import it.epicode.prenotazioni.services.PrenotazioneService;
import it.epicode.prenotazioni.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(5)
public class RunnerDataLoaderPrenotazioni implements CommandLineRunner {
    private final Faker faker = new Faker();
    private final PrenotazioneService prenotazioneService;
    private final ObjectProvider<Prenotazione> prenotazioneProvider;
    private final PostazioneService postazioneService;
    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 7; i++) {
            Prenotazione prenotazione = prenotazioneProvider.getObject();
            Long randomIdUser = faker.number().numberBetween(1L,10L);
            Long randomIdPost = faker.number().numberBetween(1L,15L);
            Postazione postazione = postazioneService.findById(randomIdPost);
            User user = userService.findById(randomIdUser);

            System.out.println(postazione);
            prenotazioneService.savePrenot(user,postazione,prenotazione);
        }





    }
}
