package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[]args){
        Scanner in = new Scanner(System.in); // inizializzo Scanner
        ArrayList<Prodotto> listaMagazzino = new ArrayList<>(); // creo un arraylist di prodotto

        // crea un prodotto e aggiungilo all'arraylist e poi al magazzino
        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128,499, 599, 1);
        listaMagazzino.add(sample1);
        Magazzino magazzino = new Magazzino(listaMagazzino);
        magazzino.printMagazzino(listaMagazzino);
    }
    private static void calcoloCostoMedio(ArrayList<Prodotto> listaCarrello) {
        double costoTotale = 0;
        if(!listaCarrello.isEmpty()) {
            for(Prodotto prodotto : listaCarrello) {
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
        while(true) {
            try {
                int inputInt = Integer.parseInt(input);
                if(inputInt >= 0 && inputInt <= 6) {
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
            case "0" :
                break;
            case "1" :
//                ricercaTipo(in, listaMagazzino);
                break;
            case "2" :
//                ricercaProduttore(in, listaMagazzino);
                break;
            case "3" :
//                ricercaModello(in, listaMagazzino);
                break;
            case "4" :
//                ricercaPrezzoVendita(in, listaMagazzino);
                break;
            case "5" :
//                ricercaPrezzoAcquisto(in, listaMagazzino);
                break;
            case "6" :
//                ricercaRangePrezzo(in, listaMagazzino);
                break;
            default:
                System.out.println("Valore non supportato: " + input);
                System.out.println();
        }
    }
}
