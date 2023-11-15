package src;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // inizializzo Scanner
        ArrayList<Prodotto> listaMagazzino = new ArrayList<>(); // creo un arraylist di prodotto per il magazzino
        ArrayList<Prodotto> listaCarrello = new ArrayList<>(); // creo un arraylist di prodotto per il carrello
        ArrayList<ArrayList<Prodotto>> listaTotale = new ArrayList<>(); // creo un arraylist di prodotto che contieni sia il magazzino che il carrello
        Carrello carrello = new Carrello(listaCarrello, in);
        Magazzino magazzino = new Magazzino(listaMagazzino, in);
        // crea un prodotto e aggiungilo all'arraylist del magazzino e poi al magazzino stesso
        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 599, 1, Prodotto.Tipo.SMARTPHONE, Prodotto.Produttore.SAMSUNG);
        Prodotto sample2 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 599, 2, Prodotto.Tipo.SMARTPHONE, Prodotto.Produttore.SAMSUNG);
        listaCarrello.add(sample1);

        // inizializzo ricerca dispositivo
        // ricercaDispositivo(in, listaMagazzino);

        //MENU PRINCIPALE
        String input = Integer.toString(1);
        System.out.println("Inserisci un numero per iniziare un operazione:");
        while (!input.equals("0")) {
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Aggiungi  un articolo al magazzino");
            System.out.println("2 = Ricerca un dispositivo nel magazzino");
            System.out.println("3 = Aggiungi elemento al carrello tramite ID");
            System.out.println("4 = Rimuovi elemento dal carrello tramite ID");
            System.out.println("5 = Visualizza i dispositivi presenti nel magazzino");
            System.out.println("6 = Visualizza il carrello");
            System.out.println("7 = Visualizza il prezzo totale degli articoli presenti nel carrello");
            System.out.println("8 = Visualizza il prezzo medio degli articoli presenti nel carrello");
            System.out.println("9 = Completa il tuo acquisto");
            System.out.println();
            input = in.nextLine();

            switch (input) {
                case "0": //Esci dal programma
                    System.out.println("Arrivederci e grazie per averci scelto!");
                    break;
                case "1": //Aggiungi  un articolo al magazzino
                    break;
                case "2": //Ricarca un dispositivo nel magazzino
//                    magazzino.ricercaDispositivo();
                    break;
                case "3": // Aggiungi elemento al carrello tramite ID
                    aggiungiCarrelloId(in, listaMagazzino,listaCarrello);
                    break;
                case "4": // Rimuovi elemento dal carrello tramite ID
                    try {
                        Prodotto prodotto = carrello.getProdotto();
                        carrello.rimuoviDalCarrello(prodotto);
                        magazzino.aggiungiAMagazzino(prodotto);
                    } catch(NullPointerException npe) {
                        System.out.println(npe);
                    }
                    break;
                case "5": // Visualizza i dispositivi presenti nel magazzino
                    break;
                case "6": // Visualizza il carrello
                    break;
                case "7": // Visualizza il prezzo totale degli articoli presenti nel carrello
                    calcoloCostoTotale(listaCarrello);
                    break;
                case "8": // Visualizza il prezzo medio degli articoli presenti nel carrello
                    System.out.println(carrello.calcoloCostoMedio());
                    break;
                case "9": //  Completa il tuo acquisto
                    break;
                default:
                    System.out.println("Valore non supportato: " + input);
                    System.out.println();
            }
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

    private static void aggiungiCarrelloId(Scanner in, ArrayList<Prodotto> listaMagazzino, ArrayList<Prodotto> listaCarrello) {
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
