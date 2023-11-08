package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // inizializzo Scanner
        ArrayList<Prodotto> listaMagazzino = new ArrayList<>(); // creo un arraylist di prodotto per il magazzino
        ArrayList<Prodotto> listaCarrello = new ArrayList<>(); // creo un arraylist di prodotto per il carrello
        ArrayList<ArrayList<Prodotto>> listaTotale = new ArrayList<>(); // creo un arraylist di prodotto che contieni sia il magazzino che il carrello

        // crea un prodotto e aggiungilo all'arraylist del magazzino e poi al magazzino stesso
        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 599, 1, Prodotto.Tipo.SMARTPHONE, Prodotto.Produttore.SAMSUNG);
        listaMagazzino.add(sample1);
        Magazzino magazzino = new Magazzino(listaMagazzino);
        magazzino.printMagazzino(listaMagazzino);

        // inizializzo ricerca dispositivo
        // ricercaDispositivo(in, listaMagazzino);

        //MENU PRINCIPALE
        String input = Integer.toString(1);
        System.out.println("Inserisci un numero per iniziare un operazione:");
        while (!input.equals("0")) {
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Aggiungi  un articolo al magazzino");
            System.out.println("2 = Ricarca un dispositivo nel magazzino");
            System.out.println("3 = Aggiungi elemento al carrello tramite ID");
            System.out.println("4 = Rimuovi elemento dal carrello tramite ID");
            System.out.println("5 = Visualizza i dispositivi presenti nel magazzino");
            System.out.println("6 = Visualizza il carrello");
            System.out.println("7 = Visualizza il prezzo totale degli articoli presenti nel carrello");
            System.out.println("8 = Visualizza il prezzo medio degli articoli presenti nel carrello");
            System.out.println("9 = Completa il tuo acquisto");

            switch (input) {
                case "0": //Esci dal programma
                    System.out.println("Arrivederci e grazie per averci scelto!");
                    break;
                case "1": //Aggiungi  un articolo al magazzino
                    break;
                case "2": //Ricarca un dispositivo nel magazzino
                    ricercaDispositivo(in,listaMagazzino);
                    break;
                case "3": // Aggiungi elemento al carrello tramite ID
                    aggiungiCarrelloID(in, listaMagazzino,listaCarrello);
                    break;
                case "4": // Rimuovi elemento dal carrello tramite ID
                    rimuoviCarrello(in,listaMagazzino,listaCarrello,listaTotale);
                    break;
                case "5": // Visualizza i dispositivi presenti nel magazzino
                    break;
                case "6": // Visualizza il carrello
                    break;
                case "7": // Visualizza il prezzo totale degli articoli presenti nel carrello
                    calcoloCostoTotale(listaCarrello);
                    break;
                case "8": // Visualizza il prezzo medio degli articoli presenti nel carrello
                    calcoloCostoMedio(listaCarrello);
                    break;
                case "9": //  Completa il tuo acquisto
                    break;
                default:
                    System.out.println("Valore non supportato: " + input);
                    System.out.println();
            }
        }
    }


    private static void calcoloCostoMedio(ArrayList<Prodotto> listaCarrello) {
        double costoTotale = 0;
        if (!listaCarrello.isEmpty()) {
            for (Prodotto prodotto : listaCarrello) {
                costoTotale += prodotto.getPriceSell();
            }
            System.out.println(costoTotale / listaCarrello.size());
        } else {
            System.out.println("Il tuo carrello è vuoto");
        }
        System.out.println();
    }

    public static void ricercaDispositivo(Scanner in, ArrayList<Prodotto> listaMagazzino) {
        System.out.println("Seleziona in che modo vuoi ricercare:");
        System.out.println("0 = esci dalla funzione di ricerca");
        System.out.println("1 = per tipo");
        System.out.println("2 = per produttore");
        System.out.println("3 = per modello");
        System.out.println("4 = per prezzo di vendita");
        System.out.println("5 = per prezzo di acquisto");
        System.out.println("6 = ricerca specifica per range di prezzo");
        System.out.println();
        String input = in.nextLine();
        while (true) {
            try {
                int inputInt = Integer.parseInt(input);
                if (inputInt >= 0 && inputInt <= 6) {
                    break;
                }
                System.out.println();
                System.out.println("Valore non supportato: " + input);
                System.out.println();
                System.out.println("Seleziona in che modo vuoi ricercare:");
                System.out.println("0 = esci dalla funzione di ricerca");
                System.out.println("1 = per tipo");
                System.out.println("2 = per produttore");
                System.out.println("3 = per modello");
                System.out.println("4 = per prezzo di vendita");
                System.out.println("5 = per prezzo di acquisto");
                System.out.println("6 = ricerca specifica per range di prezzo");
                System.out.println();
                input = in.nextLine();
            } catch (NumberFormatException nfe) {
                System.out.println();
                System.out.println("Valore non supportato: " + input);
                System.out.println();
                System.out.println("Seleziona in che modo vuoi ricercare:");
                System.out.println("0 = esci dalla funzione di ricerca");
                System.out.println("1 = per tipo");
                System.out.println("2 = per produttore");
                System.out.println("3 = per modello");
                System.out.println("4 = per prezzo di vendita");
                System.out.println("5 = per prezzo di acquisto");
                System.out.println("6 = ricerca specifica per range di prezzo");
                System.out.println();
                input = in.nextLine();
            }
        }
        System.out.println();
        switch (input) {
            case "0":
                break;
            case "1":
                ricercaTipo(in, listaMagazzino);
                break;
            case "2":
               ricercaProduttore(in, listaMagazzino);
                break;
            case "3":
//                ricercaModello(in, listaMagazzino);
                break;
            case "4":
                ricercaPrezzoVendita(in, listaMagazzino);
                break;
            case "5":
//                ricercaPrezzoAcquisto(in, listaMagazzino);
                break;
            case "6":
//                ricercaRangePrezzo(in, listaMagazzino);
                break;
            default:
                System.out.println("Valore non supportato: " + input);
                System.out.println();
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

    private static void rimuoviCarrello(Scanner in, ArrayList<Prodotto> listaMagazzino, ArrayList<Prodotto> listaCarrello, ArrayList<ArrayList<Prodotto>> listaCompleta) {
        boolean bool = true;
        System.out.println("Scrivi l'ID del prodotto che vuoi rimuovere dal carrello:");
        System.out.println();
        String input = in.nextLine();
        System.out.println();
        for (Prodotto prodotto : listaCarrello) {
            if (Objects.equals(String.valueOf(prodotto.getId()), input)) {
                listaCarrello.remove(prodotto);
                listaMagazzino.add(prodotto);
                bool = false;
                break;
            }
        }
        listaCompleta.add(listaCarrello);
        listaCompleta.add(listaMagazzino);
        if (bool) {
            System.out.println("Non è presente un dispositivo con l'ID " + input + " all'interno del carrello");
            System.out.println();
        }
    }

    private static void aggiungiCarrelloID(Scanner in, ArrayList<Prodotto> listaMagazzino, ArrayList<Prodotto> listaCarrello) {
        boolean validoID = true;
        System.out.println("Scrivi l'ID del prodotto che desideri aggiungere al carrello:");
        System.out.println();
        String input = in.nextLine();
        System.out.println();
        for (Prodotto prodotto : listaCarrello) {
            if (Objects.equals(String.valueOf(prodotto.getId()), input)) {
                listaCarrello.add(prodotto);
                listaMagazzino.remove(prodotto);
            }
        }
        if (!validoID) {
            System.out.println("Non è presente un dispositivo con l'ID " + input + " all'interno del carrello");
            System.out.println();
        }
    }
    private static void calcoloCostoTotale(ArrayList<Prodotto> listaCarrello) {
        double costoTotale = 0;
        if (!listaCarrello.isEmpty()) {
            for (Prodotto prodotto : listaCarrello) {
                costoTotale += prodotto.getPriceSell();
            }
        }else{
            System.out.println("Il tuo carrello è vuoto");
        }
        System.out.println();
    }
    public static void ricercaTipo(Scanner in, ArrayList<Prodotto> listaMagazzino){
        String input = Integer.toString(1);
        System.out.println("Inserisci il numero corrispondente al tipo di dispostitivo che vuoi ricercare:");
        while (!input.equals("0")) {
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Smartphone");
            System.out.println("2 = Tablet");
            System.out.println("3 = Notebook");

            switch(input){
                case "0":
                    System.out.println("Stai per tornare al menù precedente");
                    break;
                case "1":
                    System.out.println("Questi sono gli Smartphone disponibili: ");
                    for(Prodotto prodotto : listaMagazzino){
                        System.out.println(Prodotto.Tipo.SMARTPHONE);
                    }
                    break;
                case "2":
                    System.out.println("Questi sono i Tablet disponibili: ");
                    for(Prodotto prodotto : listaMagazzino){
                        System.out.println(Prodotto.Tipo.TABLET);
                    }
                    break;
                case "3":
                    System.out.println("Questi sono i Notebook disponibili: ");
                    for(Prodotto prodotto : listaMagazzino){
                        System.out.println(Prodotto.Tipo.NOTEBOOK);
                    }
                    break;

            }
        }

    }
    public static void ricercaProduttore(Scanner in, ArrayList<Prodotto> listaMagazzino){
        String input = Integer.toString(1);
        System.out.println("Inserisci il numero corrispondente al tipo di produttore che vuoi ricercare:");
        while (!input.equals("0")) {
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Samsung");
            System.out.println("2 = Apple");

            switch(input){
                case "0":
                    System.out.println("Stai per tornare al menù precedente");
                    break;
                case "1":
                    System.out.println("Questi sono i dispositivi disponibili per produttore Samsung: ");
                    for(Prodotto prodotto : listaMagazzino){
                        System.out.println(Prodotto.Produttore.SAMSUNG);
                    }
                    break;
                case "2":
                    System.out.println("Questi sono i dispositivi disponibili per produttore Apple: ");
                    for(Prodotto prodotto : listaMagazzino){
                        System.out.println(Prodotto.Produttore.APPLE);
                    }
                    break;
            }
        }
    }
}
