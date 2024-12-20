package it.epicode.prenotazioni.config;

import com.github.javafaker.Faker;
import it.epicode.prenotazioni.entity.Edificio;
import it.epicode.prenotazioni.entity.Postazione;
import it.epicode.prenotazioni.entity.Prenotazione;
import it.epicode.prenotazioni.entity.User;
import it.epicode.prenotazioni.enumeration.Tipo;
import it.epicode.prenotazioni.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class GeneralConfig {
    private final Faker faker = new Faker();


    @Bean
    @Scope("prototype")
    public User userGen() {
        User user = new User();
        user.setUsername(faker.name().username());
        user.setNomeCompleto(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        return user;
    }

    @Bean
    @Scope("prototype")
    public Edificio edificioGen() {
        Edificio edificio = new Edificio();
        edificio.setName(faker.name().name().toLowerCase(Locale.ROOT));
        edificio.setCitta(faker.address().city());
        edificio.setIndirizzo(faker.address().fullAddress());
        return edificio;
    }

    @Bean
    @Scope("prototype")
    public Postazione postazioniGen() {
        Postazione postazione = new Postazione();
        Tipo[] tipoVal = Tipo.values();
        postazione.setTipo(tipoVal[faker.random().nextInt(tipoVal.length)]);
        postazione.setDescrizione(faker.company().name());
        postazione.setNumeroMassimoOccupanti(faker.number().numberBetween(10,30));
        return postazione;
    }

    @Bean
    @Scope("prototype")
    public Prenotazione prenotazioneGen() {
        Prenotazione prenotazione = new Prenotazione();
        Tipo[] tipoVal = Tipo.values();
        LocalDate StartDate = LocalDate.of(2024,12,10);
        prenotazione.setDataPrenotazione(StartDate.plusDays(faker.number().numberBetween(0,30)));
        return prenotazione;
    }


}