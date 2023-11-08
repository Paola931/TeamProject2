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
        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 599, 1);
        Prodotto sample2 = new Prodotto("Samsung1", "S102", " ", 5.8, 128, 499, 599, 1);
        listaMagazzino.add(sample1);
        listaMagazzino.add(sample2);
        listaCarrello.add(sample1);
        listaCarrello.add(sample2);
        Magazzino magazzino = new Magazzino(listaMagazzino);
        magazzino.printMagazzino(listaMagazzino);
        System.out.println(magazzino);

        Carrello carrello = new Carrello(listaCarrello);
        System.out.println(carrello);
        // inizializzo ricerca dispositivo
         ricercaDispositivo(in, listaMagazzino);
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
                if (inputInt >= 0 && inputInt <= 7) {
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
                System.out.println("7 = visualizza magazzino");
                System.out.println();
                input = in.nextLine();
            }
        }
        System.out.println();
        switch (input) {
            case "0":
                break;
            case "1":
//                ricercaTipo(in, listaMagazzino);
                break;
            case "2":
//                ricercaProduttore(in, listaMagazzino);
                break;
            case "3":
//                ricercaModello(in, listaMagazzino);
                break;
            case "4":
                ricercaPrezzoVendita(in, listaMagazzino);
                break;
            case "5":
                ricercaPrezzoAcquisto(in, listaMagazzino);
                break;
            case "6":
//                ricercaRangePrezzo(in, listaMagazzino);
            case "7":
                visualizzaMagazzino(listaMagazzino);
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

    private static void visualizzaMagazzino(ArrayList<Prodotto> listaMagazzino) {
        for (Prodotto prodotto : listaMagazzino) {
            System.out.println("Elenco magazzino: ");
            System.out.println(prodotto + " ");
        }
    }

    public static void ricercaPrezzoAcquisto(Scanner in, ArrayList<Prodotto> listaMagazzino) {
        boolean bool = true;
        System.out.println("Scrivi il prezzo di acquisto che vuoi ricercare:");
        System.out.println();
        String input = in.nextLine();

        for (Prodotto prodotto : listaMagazzino) {
            if (prodotto.getPriceBuy() == Integer.parseInt(input)) {
                bool = false;
                System.out.println(prodotto);
            }
        }
        if (bool) {
            System.out.println("Non sono stati trovati risultati");
        }

    }

}
