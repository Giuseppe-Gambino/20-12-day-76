package it.epicode.prenotazioni.runners;

import it.epicode.prenotazioni.entity.User;
import it.epicode.prenotazioni.entity.Prenotazione;
import it.epicode.prenotazioni.entity.Postazione;


import it.epicode.prenotazioni.enumeration.Tipo;
import it.epicode.prenotazioni.services.PostazioneService;
import it.epicode.prenotazioni.services.PrenotazioneService;
import it.epicode.prenotazioni.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
@Order(10)
public class menuRunner implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private PostazioneService postazioneService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n====== MENU GESTIONE PRENOTAZIONI ======");
            System.out.println("1. Crea un nuovo utente");
            System.out.println("2. Aggiungi una nuova prenotazione");
            System.out.println("3. Visualizza tutte le prenotazioni");
            System.out.println("4. Trova postazioni disponibili per una data");
            System.out.println("5. Elimina una prenotazione");
            System.out.println("6 trova postazioni in base alla citta e tipo");
            System.out.println("7. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma la newline

            switch (scelta) {
                case 1:
                    creaNuovoUtente(scanner);
                    break;
                case 2:
                    aggiungiNuovaPrenotazione(scanner);
                    break;
                case 3:
                    visualizzaTutteLePrenotazioni();
                    break;
                case 4:
                    trovaPostazioniDisponibili(scanner);
                    break;
                case 5:
                    eliminaPrenotazione(scanner);
                    break;
                case 6:
                    findPostazioniByCittaAndTipo(scanner);
                    break;
                case 7:
                    running = false;
                    System.out.println("Arrivederci!");
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    private void creaNuovoUtente(Scanner scanner) {
        System.out.print("Inserisci username: ");
        String username = scanner.nextLine();
        System.out.print("Inserisci nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("Inserisci email: ");
        String email = scanner.nextLine();

        User user = new User();
        user.setUsername(username);
        user.setNomeCompleto(nomeCompleto);
        user.setEmail(email);

        userService.save(user);
        System.out.println("Utente creato con successo!");
    }

    private void aggiungiNuovaPrenotazione(Scanner scanner) {
        System.out.print("Inserisci ID utente: ");
        Long userId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Inserisci ID postazione: ");
        Long postazioneId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Inserisci data prenotazione (yyyy-MM-dd): ");
        String dataStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataStr);

        User user = userService.findById(userId);
        List<Prenotazione> prenots = user.getPrenotazioni();

        for (Prenotazione pre : prenots) {
            if (pre.getDataPrenotazione().equals(data)) { // Usa equals per confrontare le date
                System.out.println("L'utente ha già una prenotazione per la data specificata.");
                return;
            }
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUser(user);
        prenotazione.setPostazione(postazioneService.findById(postazioneId));
        prenotazione.setDataPrenotazione(data);

        prenotazioneService.save(prenotazione);
        System.out.println("Prenotazione aggiunta con successo!");
    }

    private void visualizzaTutteLePrenotazioni() {
        System.out.println("\n====== ELENCO PRENOTAZIONI ======");
        prenotazioneService.findAll().forEach(System.out::println);
    }

    private void trovaPostazioniDisponibili(Scanner scanner) {

            System.out.print("Inserisci data desiderata (yyyy-MM-dd): ");
            String dataStr = scanner.nextLine();
            LocalDate data = LocalDate.parse(dataStr);

            postazioneService.findDisponibiliByDate(data).forEach(System.out::println);
    }

    private void findPostazioniByCittaAndTipo(Scanner scanner) {
        System.out.println("Inserisci città:");
        String citta = scanner.nextLine();

        System.out.println("Inserisci tipo (PRIVATO, OPENSPACE, SALA_RIUNIONI):");
        Tipo tipo;
        try {
            tipo = Tipo.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo non valido. Assicurati di usare PRIVATO, OPENSPACE, o SALA_RIUNIONI.");
            return;
        }


        List<Postazione> postazioni = postazioneService.findPostazioniByCittaAndTipo(citta, tipo);

        if (postazioni.isEmpty()) {
            System.out.println("Nessuna postazione trovata per la città e il tipo specificati.");
        } else {
            postazioni.forEach(System.out::println);
        }
    }





    private void eliminaPrenotazione(Scanner scanner) {
        System.out.print("Inserisci ID della prenotazione da eliminare: ");
        Long id = scanner.nextLong();

        prenotazioneService.deleteById(id);
        System.out.println("Prenotazione eliminata con successo!");
    }
}
