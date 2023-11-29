package src;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // inizializzo Scanner

        ArrayList<Prodotto> listaCarrello = new ArrayList<>(); // creo un arraylist di prodotto per il carrello
        Carrello carrello = new Carrello(listaCarrello, in);

        ArrayList<Prodotto> listaMagazzino = new ArrayList<>(); // creo un arraylist di prodotto per il magazzino
        Magazzino magazzino = new Magazzino(listaMagazzino, in);

        // crea un prodotto
        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 600, 1, TipoProdotto.SMARTPHONE, ProduttoreProdotto.SAMSUNG);
        Prodotto sample2 = new Prodotto("Samsung", "S10", " ", 5.8, 128, 499, 800, 2, TipoProdotto.SMARTPHONE, ProduttoreProdotto.SAMSUNG);
        listaCarrello.add(sample1);

        listaMagazzino.add(sample1);
        listaMagazzino.add(sample2);


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
                    System.out.println("Grazie per averci scelto. Arrivederci!");
                    break;
                case "1": //Aggiungi  un articolo al magazzino
//                    Prodotto prodotto =
//                    magazzino.aggiungiAMagazzino(Prodotto);
                    break;
                case "2": //Ricarca un dispositivo nel magazzino
                    magazzino.ricercaDispositivo();
                    break;
                case "3": // Aggiungi elemento al carrello tramite ID
                    System.out.println("Scrivi l'ID del prodotto che vorresti aggiungere nel carrello: \n");
                   try{
                       String input1 = in.nextLine();
                       Prodotto prodotto = magazzino.verificaDisponibilitaId(input1);
                       carrello.aggiungiProdottoCarrello(prodotto);
                       magazzino.rimuoviProdottoMagazzino(prodotto);
                   }catch(Exception e){
                       System.out.println(e);
                   }
                    break;
                case "4": // Rimuovi elemento dal carrello tramite ID
                    try {
                        Prodotto prodotto = carrello.getProdotto();
                        carrello.rimuoviDalCarrello(prodotto);
                        magazzino.aggiungiAMagazzino(prodotto);
                    } catch (NullPointerException npe) {
                        System.out.println(npe);
                    }
                    break;
                case "5": // Visualizza i dispositivi presenti nel magazzino
                    break;
                case "6": // Visualizza il carrello
                    break;
                case "7": // Visualizza il prezzo totale degli articoli presenti nel carrello
                    System.out.println(Carrello.calcoloCostoTotale(listaCarrello));
                    break;
                case "8": // Visualizza il prezzo medio degli articoli presenti nel carrello
                    System.out.println();
                    System.out.println(carrello.calcoloCostoMedio());
                    System.out.println();
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
}
