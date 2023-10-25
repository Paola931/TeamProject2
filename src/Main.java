package src;

import java.util.Scanner;

public class Main {



    public static void main(String[]args){

        System.out.println("Seleziona un numero: ");
        Scanner scan = new Scanner(System.in); // Aggiunto costruttore scanner, aggiunto in automatico "Import java.util.scanner"

        Prodotto sample1 = new Prodotto("Samsung", "S10", " ", 5.8, 128,499, 599, 1);
        System.out.println(sample1);
    }
}
