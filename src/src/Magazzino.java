package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Magazzino {
    private static final String URL = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8666174";
    private static final String USER = "sql8666174";
    private static final String PASSWORD = "pdsKu4WEkV";
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

    public ArrayList<Prodotto> aggiungiAMagazzino(Prodotto prodotto) throws SQLException {
        String q = "INSERT INTO Prodotto (ProdottoId, Produttore, Modello, Descrizione, Display, Memoria, PrezzoAcquisto, PrezzoVendita, Tipo) VALUES ('" + prodotto.getId() + "', '" + prodotto.getProducer() + "', '" + prodotto.getModel() + "', '" + prodotto.getDescription() + "', '" + prodotto.getDisplayInch() + "', '" + prodotto.getMemory() + "', '" + prodotto.getPriceBuy() + "', '" + prodotto.getPriceSell() + "', '" + prodotto.getTipoProdotto() + "');";
        DBManager.connection().executeUpdate(q);
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
                try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement s = c.createStatement();
                    String q1 = "SELECT * FROM `Prodotto` WHERE tipo = 'SMARTPHONE';";
                    s.executeQuery(q1);
                    for (Prodotto prodotto : lista) {
                        System.out.println(prodotto);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                System.out.println("Questi sono i Tablet disponibili: ");
                try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement s = c.createStatement();
                    String q1 = "SELECT * FROM `Prodotto` WHERE tipo = 'TABLET';";
                    s.executeQuery(q1);
                    for (Prodotto prodotto : lista) {
                        System.out.println(prodotto);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("Questi sono i Notebook disponibili: ");
                try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
                    Statement s = c.createStatement();
                    String q1 = "SELECT * FROM `Prodotto` WHERE tipo = 'NOTEBOOK';";
                    s.executeQuery(q1);
                    for (Prodotto prodotto : lista) {
                        System.out.println(prodotto);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return list;

    }

    public static ArrayList<Prodotto> ricercaProduttore(String produttore, ArrayList<Prodotto> lista) throws Exception {
        ArrayList<Prodotto> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE produttore = '" + produttore + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
//                for(Prodotto p : lista){
//                    if(p.getId() == result.getInt("id")){
//                        list.add(p);
//                    }
//                }
                System.out.println(
                        result.getString("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        if (!list.isEmpty()) {
            return null; //list;
        } else {
            throw new Exception("Non sono stati trovati risultati con i parametri di ricerca inseriti");
        }
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
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE prezzoVendita BETWEEN '" + prezzoMin + "' AND '" + prezzoMax + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                System.out.println(
                        "ID prodotto: " + result.getString("id") + " - " +
                                "Produttore: " + result.getString("produttore") + " - " +
                                "Modello: " + result.getString("modello") + " - " +
                                "Descrizione: " + result.getString("descrizione") + " - " +
                                "Pollici Display: " + result.getString("display") + " - " +
                                "GB di Memoria: " + result.getString("memoria") + " - " +
                                "Prezzo: " + result.getString("prezzoVendita") + " - " +
                                "Tipologia prodotto: " + result.getString("tipo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
