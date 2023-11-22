package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Magazzino {
    private ArrayList<Prodotto> listaMagazzino;
    private final Scanner in;

    public Magazzino(ArrayList<Prodotto> listaMagazzino, Scanner in) {
        this.listaMagazzino = listaMagazzino;
        this.in = in;
    }

    public String aggiungiAMagazzino(Prodotto prodotto) {
        this.listaMagazzino.add(prodotto);
        return "Il prodotto " + prodotto + " è stato aggiunto correttamente al magazzino\n";
    }

    public String ricercaDispositivo() {
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
//                return ricercaTipo(this.in, this.listaMagazzino);
                break;
            case "2":
//                return ricercaProduttore(this.in, this.listaMagazzino);
                break;
            case "3":
                return ricercaModello(this.in, this.listaMagazzino);
            case "4":
//                return ricercaPrezzoVendita(this.in, this.listaMagazzino);
                break;
            case "5":
//                return ricercaPrezzoAcquisto(this.in, this.listaMagazzino);
                break;
            case "6":
//                return ricercaRangePrezzo(this.in, this.listaMagazzino);
                break;
            default:
                System.out.println("Valore non supportato: " + input);
                System.out.println();
        }
        return null;
    }

    public String ricercaModello(Scanner in, ArrayList<Prodotto> listaMagazzino) {
        System.out.println("Inserisci modello: ");
        String modello = in.nextLine();
        for (int i = 0; i < listaMagazzino.size(); i++) {
            if (modello.equalsIgnoreCase(listaMagazzino.get(i).getModel())) {
                System.out.println(listaMagazzino.get(i));
            }
        }
        return "ok";
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "listaMagazzino=" + listaMagazzino +
                '}';
    }

    public void printMagazzino(ArrayList<Prodotto> magazzino) {
        magazzino.forEach(System.out::println);
        System.out.println();
    }
}