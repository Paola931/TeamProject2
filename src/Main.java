package src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {



    public static void main(String[]args){

        System.out.println("Seleziona un numero: ");
        Scanner scan = new Scanner(System.in); // Aggiunto costruttore scanner, aggiunto in automatico "Import java.util.scanner"

        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128,499, 599, 1);
        System.out.println(sample1);
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
}
