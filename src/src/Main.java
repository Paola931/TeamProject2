package src;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // inizializzo variabili
        Scanner in = new Scanner(System.in);
        Login login = new Login();
        Magazzino magazzino = new Magazzino(new ArrayList<Prodotto>(),in);
        Carrello carrello = new Carrello(new ArrayList<Prodotto>(),in);

        //LOGIN
        String email;
        do {
            System.out.println("Scrivi la tua email:");
            email = in.nextLine();
        } while(email.isEmpty());
        String password;
        do {
            System.out.println("Scrivi la tua password:");
            password = in.nextLine();
        } while(password.isEmpty());
        try {
            Ruolo ruolo = login.checkRuolo(email, password);
            if(ruolo == Ruolo.ADMIN || ruolo == Ruolo.DEVELOPER) {
                MenuAdmin.main();
            } else if(ruolo == Ruolo.USER) {
//                MenuUtente.main();
            } else {
                System.out.println("Utente non trovato");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void ricercaPrezzoVendita(Scanner in, ArrayList<Prodotto> listaMagazzino) {
        boolean bool = true;
        System.out.println("Scrivi il prezzo di vendita che vuoi ricercare:");
        System.out.println();
        String input = in.nextLine();
        while (true) {
            try {
                Integer.parseInt(input);
                break;
            } catch (NumberFormatException nfe) {
                System.out.println();
                System.out.println("Valore non supportato: " + input);
                System.out.println();
                System.out.println("Scrivi il prezzo di vendita che vuoi ricercare:");
                System.out.println();
                input = in.nextLine();
            }
        }
        System.out.println();
        System.out.println("Ecco gli elementi trovati nel magazzino corrispondenti ai tuoi parametri di ricerca:");
        System.out.println();
        for (Prodotto prodotto : listaMagazzino) {
            if (prodotto.getPriceSell() == Integer.parseInt(input)) {
                bool = false;
                System.out.println(prodotto);
            }
        }
        if (bool) {
            System.out.println("Non sono stati trovati risultati.");
        }
        System.out.println();
    }

    public static double calcoloCostoTotale(ArrayList<Prodotto> listaCarrello) {
        double costoTotale = 0;
        if (!listaCarrello.isEmpty()) {
            for (Prodotto prodotto : listaCarrello) {
                costoTotale += prodotto.getPriceSell();
            }
        }else{
            System.out.println("Il tuo carrello è vuoto");
        }
        return costoTotale;
    }
}
