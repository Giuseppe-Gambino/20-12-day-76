package it.epicode.prenotazioni.runners;

import com.github.javafaker.Faker;
import it.epicode.prenotazioni.entity.Edificio;
import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.entity.User;
import it.epicode.prenotazioni.repository.EdificioRepo;
import it.epicode.prenotazioni.repository.UserRepo;
import it.epicode.prenotazioni.services.EdificioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class RunnerDataLoader implements CommandLineRunner {

    private final Faker faker = new Faker();
    private final UserRepo userRepo;
    private final EdificioService edificioService;
    private final ObjectProvider<User> usersProvider;
    private final ObjectProvider<Edificio> edificioProvider;
    private final ObjectProvider<Postazione> postazionesProvider;

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 10; i++) {
            User user = usersProvider.getObject();
            userRepo.save(user);
        }

        for (int i = 0; i < 15; i++) {
            Edificio edificio = edificioProvider.getObject();
            Postazione postazione = postazionesProvider.getObject();
            edificioService.saveEdifAndPost(postazione,edificio);
        }

        for (int i = 0; i < 15; i++) {
            Postazione postazione = postazionesProvider.getObject();
            Long randomId = faker.number().numberBetween(1L,15L);
            edificioService.addPost(randomId, postazione);
        }


    }
}
