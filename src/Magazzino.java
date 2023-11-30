package src;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Magazzino {
    private static ArrayList<Prodotto> lista;
    private final Scanner in;

    public ArrayList<Prodotto> getListaMagazzino() {
        return lista;
    }

    public void setListaMagazzino(ArrayList<Prodotto> listaMagazzino) {
        this.lista = listaMagazzino;
    }

    public Magazzino(ArrayList<Prodotto> listaMagazzino, Scanner in) {
        this.lista = listaMagazzino;
        this.in = in;
    }

    public ArrayList<Prodotto> aggiungiAMagazzino(Prodotto prodotto) {
        this.lista.add(prodotto);
        return lista;
    }

    public ArrayList<Prodotto> ricercaDispositivo() throws Exception {
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
                System.out.println("Grazie per averci scelto, arrivederci!");
                break;
            case "1":
                int tipo = -1;
                while (tipo < 0 || tipo > 3) {
                    System.out.println("Inserisci il numero corrispondente al tipo di dispositivo che vuoi ricercare:");
                    System.out.println("0 = Torna al menù precendente");
                    System.out.println("1 = Smartphone");
                    System.out.println("2 = Tablet");
                    System.out.println("3 = Notebook");
                    tipo = in.nextInt();
                }
                return ricercaTipo(tipo, lista);
            case "2":
                System.out.println("Inserisci produttore:");
                String produttore = in.nextLine();
                return ricercaProduttore(produttore, lista);
            case "3":
                System.out.println("Inserisci modello:");
                String modello = in.nextLine();
                return ricercaModello(modello, lista);
            case "4":
                System.out.println("Inserisci prezzo vendita:");
                double prezzoVendita = in.nextDouble();
                return ricercaPrezzoVendita(prezzoVendita, lista);
            case "5":
                System.out.println("Inserisci prezzo acquisto:");
                double prezzoAcquisto = in.nextDouble();
                return ricercaPrezzoAcquisto(prezzoAcquisto, lista);
            case "6":
                System.out.print("Inserisci il prezzo minimo (es.5,00) :");
                double prezzoMin = in.nextDouble();
                System.out.print("Inserisci il prezzo massimo (es.50,00) :");
                double prezzoMax = in.nextDouble();
                return ricercaRangePrezzo(prezzoMin, prezzoMax, lista);
        }
        throw new Exception("Something went wrong!");
    }
    public static ArrayList<Prodotto> ricercaTipo(int tipo, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        switch (tipo) {
            case 0:
                System.out.println("Stai per tornare al menù precedente");
                break;
            case 1:
                System.out.println("Questi sono gli Smartphone disponibili: ");
                for (Prodotto prodotto : lista) {
                    if (prodotto.getTipoProdotto() == TipoProdotto.SMARTPHONE) {
                        System.out.println(prodotto);
                    }
                }
                break;
            case 2:
                System.out.println("Questi sono i Tablet disponibili: ");
                for (Prodotto prodotto : lista) {
                    if (prodotto.getTipoProdotto() == TipoProdotto.TABLET) {
                        System.out.println(prodotto);
                    }
                }
                break;
            case 3:
                System.out.println("Questi sono i Notebook disponibili: ");
                for (Prodotto prodotto : lista) {
                    if (prodotto.getTipoProdotto() == TipoProdotto.NOTEBOOK) {
                        System.out.println(prodotto);
                    }
                }
                break;
        }
        return list;
    }
    public static ArrayList<Prodotto> ricercaProduttore(String produttore, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        for (Prodotto prodotto : lista) {
            if (prodotto.getProducer().equals(produttore)) {
                list.add(prodotto);
            }
        }
        return list;
    }
    public static ArrayList<Prodotto> ricercaModello(String modello, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        for (Prodotto prodotto : lista) {
            if (prodotto.getModel().equals(modello)) {
                list.add(prodotto);
            }
        }
        return list;
    }
    public static ArrayList<Prodotto> ricercaPrezzoVendita(double prezzoVendita, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        for (Prodotto prodotto : lista) {
            if (prodotto.getPriceSell() == prezzoVendita) {
                list.add(prodotto);
            }
        }
        return list;
    }
    public static ArrayList<Prodotto> ricercaPrezzoAcquisto(double prezzoAcquisto, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        for (Prodotto prodotto : lista) {
            if (prodotto.getPriceBuy() == prezzoAcquisto) {
                list.add(prodotto);
            }
        }
        return list;
    }
    public static ArrayList<Prodotto> ricercaRangePrezzo(double prezzoMin, double prezzoMax, ArrayList<Prodotto> lista) {
        ArrayList<Prodotto> list = new ArrayList<>();
        for (Prodotto prodotto : lista) {
            if (prodotto.getPriceSell() >= prezzoMin && prodotto.getPriceSell() <= prezzoMax) {
                lista.add(prodotto);
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "listaMagazzino=" + lista +
                '}';
    }

    public void printMagazzino(ArrayList<Prodotto> magazzino) {
        magazzino.forEach(System.out::println);
        System.out.println();
    }

    public Prodotto verificaDisponibilitaId(String input1) {
        for (Prodotto prodotto : this.lista) {
            if (Objects.equals(String.valueOf(prodotto.getId()), input1)) {
                return prodotto;
            }
        }
        throw new NullPointerException("Non è presente nessun prodotto con l'ID " + input1 + " all'interno del magazzino \n");
    }

    public String rimuoviProdottoMagazzino(Prodotto prodotto) {
        this.lista.remove(prodotto);
        return "Il prodotto " + prodotto + " è stato rimosso correttamente dal magazzino \n";
    }
}
