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

    public ArrayList<Prodotto> aggiungiAMagazzino(Prodotto prodotto, int quantita) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (controllaPresenzaMagazzino(prodotto)) {
                String q = "UPDATE Magazzino SET quantità = quantità + '" + quantita + "' WHERE IdProdotto = '" + prodotto.getId() + "';";
                s.executeUpdate(q);
            } else {
                String q = "INSERT INTO Magazzino (IdProdotto,quantità) VALUES ('" + prodotto.getId() + "', '" + quantita + "');";
                s.executeUpdate(q);
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    private boolean controllaPresenzaMagazzino(Prodotto prodotto) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Magazzino` WHERE produttore = '" + prodotto.getProducer() + "' AND modello = '" + prodotto.getModel() + "' AND descrizione = '" + prodotto.getDescription() + "' " +
                    /*AND display = '" + prodotto.getDisplayInch()+ "'*/ " AND memoria = '" + prodotto.getMemory() + "' AND prezzoAcquisto = '" + prodotto.getPriceBuy() + "' AND prezzoVendita = '" + prodotto.getPriceSell() + "' AND tipo = '" + prodotto.getTipoProdotto() + "';";
            ResultSet result = s.executeQuery(q1);
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public ArrayList<String> ricercaDispositivoAdmin() throws Exception {
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
                    throw new Exception("Grazie per averci scelto, arrivederci!");
                case "1":
                    int tipo = -1;
                    while (tipo < 0 || tipo > 3) {
                        System.out.println("Inserisci il numero corrispondente al tipo di dispositivo che vuoi ricercare:");
                        System.out.println("0 = Torna al menù precendente");
                        System.out.println("1 = Smartphone");
                        System.out.println("2 = Tablet");
                        System.out.println("3 = Notebook");
                        tipo = in.nextInt();
                        String i = in.nextLine();
                    }
                    return ricercaTipo(tipo);
                case "2":
                    System.out.println("Inserisci produttore:");
                    String produttore = in.nextLine();
                    return ricercaProduttore(produttore);
                case "3":
                    System.out.println("Inserisci modello:");
                    String modello = in.nextLine();
                    return ricercaModello(modello);
                case "4":
                    System.out.println("Inserisci prezzo vendita:");
                    double prezzoVendita = in.nextDouble();
                    return ricercaPrezzoVendita(prezzoVendita);
                case "5":
                    System.out.println("Inserisci prezzo acquisto:");
                    double prezzoAcquisto = in.nextDouble();
                    return ricercaPrezzoAcquisto(prezzoAcquisto);
                case "6":
                    System.out.print("Inserisci il prezzo minimo (es.5,00) :");
                    double prezzoMin = in.nextDouble();
                    System.out.print("Inserisci il prezzo massimo (es.50,00) :");
                    double prezzoMax = in.nextDouble();
                    return ricercaRangePrezzo(prezzoMin, prezzoMax);
                default:
                    throw new Exception("Qualcosa è andato storto!");
            }
    }
    public ArrayList<String> ricercaDispositivoAcquirente() throws Exception {
        System.out.println("Seleziona in che modo vuoi ricercare:");
        System.out.println("0 = esci dalla funzione di ricerca");
        System.out.println("1 = per tipo");
        System.out.println("2 = per produttore");
        System.out.println("3 = per modello");
        System.out.println("4 = per prezzo di vendita");
        System.out.println("5 = ricerca specifica per range di prezzo");
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
                System.out.println("5 = ricerca specifica per range di prezzo");
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
                System.out.println("5 = ricerca specifica per range di prezzo");
                System.out.println();
                input = in.nextLine();
            }
        }
        System.out.println();
        switch (input) {
            case "0":
                throw new Exception("Grazie per averci scelto, arrivederci!");
            case "1":
                int tipo = -1;
                while (tipo < 0 || tipo > 3) {
                    System.out.println("Inserisci il numero corrispondente al tipo di dispositivo che vuoi ricercare:");
                    System.out.println("0 = Torna al menù precendente");
                    System.out.println("1 = Smartphone");
                    System.out.println("2 = Tablet");
                    System.out.println("3 = Notebook");
                    tipo = in.nextInt();
                    String i = in.nextLine();
                }
                return ricercaTipo(tipo);
            case "2":
                System.out.println("Inserisci produttore:");
                String produttore = in.nextLine();
                return ricercaProduttore(produttore);
            case "3":
                System.out.println("Inserisci modello:");
                String modello = in.nextLine();
                return ricercaModello(modello);
            case "4":
                System.out.println("Inserisci prezzo vendita:");
                double prezzoVendita = in.nextDouble();
                return ricercaPrezzoVendita(prezzoVendita);
            case "5":
                System.out.print("Inserisci il prezzo minimo (es.5,00) :");
                double prezzoMin = in.nextDouble();
                System.out.print("Inserisci il prezzo massimo (es.50,00) :");
                double prezzoMax = in.nextDouble();
                return ricercaRangePrezzo(prezzoMin, prezzoMax);
            default:
                throw new Exception("Qualcosa è andato storto!");
        }
    }

    public static ArrayList<String> ricercaTipo(int tipo) throws Exception {
        ArrayList<String> list = new ArrayList<>();
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
                    ResultSet result = s.executeQuery(q1);
                    while (result.next()) {
                        String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                                "Produttore: " + result.getString("produttore") + " - " +
                                "Modello: " + result.getString("modello") + " - " +
                                "Descrizione: " + result.getString("descrizione") + " - " +
                                "Pollici Display: " + result.getString("display") + " - " +
                                "GB di Memoria: " + result.getString("memoria") + " - " +
                                "Prezzo: " + result.getString("prezzoVendita") + " - " +
                                "Tipologia prodotto: " + result.getString("tipo");
                        list.add(prodotto);
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
                    ResultSet result = s.executeQuery(q1);
                    while (result.next()) {
                        String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                                "Produttore: " + result.getString("produttore") + " - " +
                                "Modello: " + result.getString("modello") + " - " +
                                "Descrizione: " + result.getString("descrizione") + " - " +
                                "Pollici Display: " + result.getString("display") + " - " +
                                "GB di Memoria: " + result.getString("memoria") + " - " +
                                "Prezzo: " + result.getString("prezzoVendita") + " - " +
                                "Tipologia prodotto: " + result.getString("tipo");
                        list.add(prodotto);
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
                    ResultSet result = s.executeQuery(q1);
                    while (result.next()) {
                        String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                                "Produttore: " + result.getString("produttore") + " - " +
                                "Modello: " + result.getString("modello") + " - " +
                                "Descrizione: " + result.getString("descrizione") + " - " +
                                "Pollici Display: " + result.getString("display") + " - " +
                                "GB di Memoria: " + result.getString("memoria") + " - " +
                                "Prezzo: " + result.getString("prezzoVendita") + " - " +
                                "Tipologia prodotto: " + result.getString("tipo");
                        list.add(prodotto);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non sono stati trovati risultati con i parametri di ricerca inseriti");
        }

    }

    public static ArrayList<String> ricercaProduttore(String produttore) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE produttore = '" + produttore + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                        "Produttore: " + result.getString("produttore") + " - " +
                        "Modello: " + result.getString("modello") + " - " +
                        "Descrizione: " + result.getString("descrizione") + " - " +
                        "Pollici Display: " + result.getString("display") + " - " +
                        "GB di Memoria: " + result.getString("memoria") + " - " +
                        "Prezzo: " + result.getString("prezzoVendita") + " - " +
                        "Tipologia prodotto: " + result.getString("tipo");
                list.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list);
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non sono stati trovati risultati con i parametri di ricerca inseriti");
        }
    }

    public static ArrayList<String> ricercaModello(String modello) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE modello = '" + modello + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                        "Produttore: " + result.getString("produttore") + " - " +
                        "Modello: " + result.getString("modello") + " - " +
                        "Descrizione: " + result.getString("descrizione") + " - " +
                        "Pollici Display: " + result.getString("display") + " - " +
                        "GB di Memoria: " + result.getString("memoria") + " - " +
                        "Prezzo: " + result.getString("prezzoVendita") + " - " +
                        "Tipologia prodotto: " + result.getString("tipo");
                list.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non sono stati trovati risultati con i parametri di ricerca inseriti");
        }
    }

    public static ArrayList<String> ricercaPrezzoVendita(double prezzoVendita) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE prezzoVendita = '" + prezzoVendita + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                        "Produttore: " + result.getString("produttore") + " - " +
                        "Modello: " + result.getString("modello") + " - " +
                        "Descrizione: " + result.getString("descrizione") + " - " +
                        "Pollici Display: " + result.getString("display") + " - " +
                        "GB di Memoria: " + result.getString("memoria") + " - " +
                        "Prezzo: " + result.getString("prezzoVendita") + " - " +
                        "Tipologia prodotto: " + result.getString("tipo");
                list.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non è stato trovato alcun prodotto con il valore di ricerca inserito, la invitiamo a riprovare");

        }
    }

    public static ArrayList<String> ricercaPrezzoAcquisto(double prezzoAcquisto) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE prezzoAcquisto = '" + prezzoAcquisto + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                        "Produttore: " + result.getString("produttore") + " - " +
                        "Modello: " + result.getString("modello") + " - " +
                        "Descrizione: " + result.getString("descrizione") + " - " +
                        "Pollici Display: " + result.getString("display") + " - " +
                        "GB di Memoria: " + result.getString("memoria") + " - " +
                        "Prezzo: " + result.getString("prezzoVendita") + " - " +
                        "Tipologia prodotto: " + result.getString("tipo");
                list.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non è stato trovato alcun prodotto con il valore di ricerca inserito, la invitiamo a riprovare");
        }
    }

    public static ArrayList<String> ricercaRangePrezzo(double prezzoMin, double prezzoMax) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Prodotto` WHERE prezzoVendita BETWEEN '" + prezzoMin + "' AND '" + prezzoMax + "';";
            s.executeQuery(q1);

            ResultSet result = s.executeQuery(q1);
            while (result.next()) {
                String prodotto = "ID prodotto: " + result.getString("id") + " - " +
                        "Produttore: " + result.getString("produttore") + " - " +
                        "Modello: " + result.getString("modello") + " - " +
                        "Descrizione: " + result.getString("descrizione") + " - " +
                        "Pollici Display: " + result.getString("display") + " - " +
                        "GB di Memoria: " + result.getString("memoria") + " - " +
                        "Prezzo: " + result.getString("prezzoVendita") + " - " +
                        "Tipologia prodotto: " + result.getString("tipo");
                list.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new Exception("Non è stato trovato alcun prodotto con il valore di ricerca inserito, la invitiamo a riprovare");
        }
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "listaMagazzino=" + lista +
                '}';
    }

    public int controllaQuantità(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Magazzino` WHERE prodottoId = '" + id + "';";
            ResultSet result = s.executeQuery(q1);
            if (result.next()) {
                return result.getInt("quantità");
            }
            return 0;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public String aggiungiProdottoId(int id) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (controllaQuantità(id) > 0) {
                String q = "UPDATE Magazzino SET quantità = quantità + 1 WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            } else {
                String q = "INSERT INTO `Magazzino` (prodottoId)\n" +
                        "SELECT id\n" +
                        "FROM `Prodotto`\n" +
                        "WHERE id = '" + id + "';";
                s.executeUpdate(q);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Il prodotto con id: " + id + " è stato aggiunto correttamente al magazzino\n";
    }

    public String rimuoviProdotto(int id) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (controllaQuantità(id) > 1) {
                String q = "UPDATE Magazzino SET quantità = quantità - 1 WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            } else if (controllaQuantità(id) == 1) {
                String q = "DELETE FROM `Magazzino`" + " WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            } else {
                throw new Exception("Prodotto non disponibile nel magazzino");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Il prodotto con id: " + id + " è stato rimosso correttamente dal magazzino\n";
    }

    public void visualizza() {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            Statement s1 = c.createStatement();
            String q = "SELECT * FROM `Magazzino`;";
            ResultSet result = s.executeQuery(q);
            while (result.next()) {
                String q1 = "SELECT * FROM Prodotto" + " WHERE id = '" + result.getInt("prodottoId") + "';";
                ResultSet result1 = s1.executeQuery(q1);
                result1.next();
                System.out.println("Id: " + result1.getInt("id") + ", Produttore: " + result1.getString("produttore") + ", Modello: " + result1.getString("modello") + ", Descrizione: " + result1.getString("descrizione") + ", Display: " + result1.getFloat("display") + ", Memoria: " + result1.getInt("memoria") + ", Prezzo: " + result1.getFloat("prezzoVendita") + ", Tipo: " + TipoProdotto.valueOf(result1.getString("tipo")) + ", Quantità: " + result.getInt("quantità"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
