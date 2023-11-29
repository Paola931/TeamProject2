package TeamProject2.TeamProject2.src;

import java.util.ArrayList;
import java.util.Objects;
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
                return ricercaTipo(in);
            case "2":
               return ricercaProduttore(in);
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
    public Prodotto verificaDisponibilitaId(String input1){
        for (Prodotto prodotto : this.listaMagazzino) {
            if (Objects.equals(String.valueOf(prodotto.getId()), input1)) {
                return prodotto;
            }
        }
        throw new NullPointerException("Non è presente nessun prodotto con l'ID " + input1 + " all'interno del magazzino \n");
    }
    public String rimuoviProdottoMagazzino(Prodotto prodotto){
        this.listaMagazzino.remove(prodotto);
        return "Il prodotto " + prodotto + " è stato rimosso correttamente dal magazzino \n";
    }
    public String ricercaTipo(Scanner in) {
        System.out.println("Inserisci il numero corrispondente al tipo di dispostitivo che vuoi ricercare:");
        System.out.println("0 = Torna al menù precendente");
        System.out.println("1 = Smartphone");
        System.out.println("2 = Tablet");
        System.out.println("3 = Notebook");
        Integer input = in.nextInt();
        while (input != 0) {

            switch (input) {
                case 0:
                    System.out.println("Stai per tornare al menù precedente");
                    break;
                case 1:
                    System.out.println("Questi sono gli Smartphone disponibili: ");
                    for (Prodotto prodotto : this.listaMagazzino) {
                        if(prodotto.getTipoProdotto() == TipoProdotto.SMARTPHONE) {
                            System.out.println(prodotto);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Questi sono i Tablet disponibili: ");
                    for (Prodotto prodotto : this.listaMagazzino) {
                        if(prodotto.getTipoProdotto() == TipoProdotto.TABLET) {
                            System.out.println(prodotto);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Questi sono i Notebook disponibili: ");
                    for (Prodotto prodotto : this.listaMagazzino) {
                        if(prodotto.getTipoProdotto() == TipoProdotto.NOTEBOOK) {
                            System.out.println(prodotto);
                        }
                    }
                    break;
            }
            System.out.println("Inserisci il numero corrispondente al tipo di dispositivo che vuoi ricercare:");
            System.out.println("0 = Torna al menù precendente");
            System.out.println("1 = Smartphone");
            System.out.println("2 = Tablet");
            System.out.println("3 = Notebook");
            input = in.nextInt();
        }
        return null;
    }
    public String ricercaProduttore(Scanner in) {
        System.out.println("Inserisci il numero corrispondente al tipo di produttore che vuoi ricercare:");
        System.out.println("0 = Esci dal programma");
        System.out.println("1 = Samsung");
        System.out.println("2 = Apple");
        Integer input = in.nextInt();
        while (!input.equals(0)) {

            switch (input) {
                case 0:
                    System.out.println("Stai per tornare al menù precedente");
                    break;
                case 1:
                    System.out.println("Questi sono i dispositivi disponibili per produttore Samsung: ");
                    for (Prodotto prodotto : this.listaMagazzino) {
                        if(prodotto.getProduttore() == ProduttoreProdotto.SAMSUNG){
                            System.out.println(prodotto);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Questi sono i dispositivi disponibili per produttore Apple: ");
                    for (Prodotto prodotto : this.listaMagazzino) {
                        if(prodotto.getProduttore() == ProduttoreProdotto.APPLE){
                            System.out.println(prodotto);
                        }
                    }
                    break;
            }
            System.out.println("Inserisci il numero corrispondente al tipo di produttore che vuoi ricercare:");
            System.out.println("0 = Esci dal programma");
            System.out.println("1 = Samsung");
            System.out.println("2 = Apple");
            input = in.nextInt();
        }
        return null;
    }

}