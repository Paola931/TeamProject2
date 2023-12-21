package src;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Carrello {
    private static final String URL = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8666174";
    private static final String USER = "sql8666174";
    private static final String PASSWORD = "pdsKu4WEkV";
    private ArrayList<Prodotto> listaCarrello;
    private final Scanner in;

    public Carrello(ArrayList<Prodotto> listaCarrello, Scanner in) {
        this.listaCarrello = listaCarrello;
        this.in = in;
    }

    public Prodotto getProdotto() {
        System.out.println("\nScrivi l'ID del prodotto che vuoi rimuovere dal carrello:\n");
        String input = this.in.nextLine();
        System.out.println();
        for (Prodotto prodotto : this.listaCarrello) {
            if (Objects.equals(String.valueOf(prodotto.getId()), input)) {
                return prodotto;
            }
        }
        throw new NullPointerException("Non è presente un prodotto con l'id " + input + " all'interno del carrello\n");
    }

    public double calcoloCostoTotale() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            double cost = 0;
            Statement s = c.createStatement();
            String q = "SELECT * FROM `Carrello`;";
            ResultSet result = s.executeQuery(q);
            while(result.next()) {
                cost += result.getFloat("prezzoVendita") * result.getInt("quantità");
            }
            return cost;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    public double calcoloCostoMedio() throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            double sum = calcoloCostoTotale();
            double media = 0;
            Statement s = c.createStatement();
            String q = "SELECT SUM(quantità) AS totaleProdotti FROM `Carrello`;";
            ResultSet result = s.executeQuery(q);
            while(result.next()) {
                System.out.println(result.getInt("totaleProdotti"));
                media = sum / result.getInt("totaleProdotti");
            }
            System.out.println("La media è: ");
            return media;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    private int controllaQuantità(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Carrello` WHERE prodottoId = '" + id + "';";
            ResultSet result = s.executeQuery(q1);
            if (result.next()) {
                return result.getInt("quantità");
            }
            return 0;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
    public String aggiungiProdotto(int id) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (controllaQuantità(id) > 0) {
                String q = "UPDATE Carrello SET quantità = quantità + 1 WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            }else{
                String q1 = "INSERT INTO `Carrello` (prodottoId)\n" +
                        "SELECT id\n" +
                        "FROM `Prodotto`\n" +
                        "WHERE id = '" + id + "';";
                s.executeUpdate(q1);
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Il prodotto con id: " + id + " è stato aggiunto correttamente al carrello \n";
    }
    public String rimuoviProdottoId(int id) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (controllaQuantità(id) > 1) {
                String q = "UPDATE Carrello SET quantità = quantità - 1 WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            }else if (controllaQuantità(id) == 1){
                String q = "DELETE FROM `Carrello`" + " WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            } else {
                throw new Exception("Prodotto non presente nel carrello");
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Il prodotto con id: " + id + " è stato rimosso correttamente dal carrello\n";
    }

    public void visualizza() {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            Statement s1 = c.createStatement();
            String q = "SELECT * FROM `Carrello`;";
            ResultSet result = s.executeQuery(q);
            while(result.next()) {
                String q1 = "SELECT * FROM Prodotto" + " WHERE id = '" + result.getInt("prodottoId") + "';";
                ResultSet result1 = s1.executeQuery(q1);
                result1.next();
                System.out.println("Id: " + result1.getInt("id") + ", Produttore: " + result1.getString("produttore") + ", Modello: " + result1.getString("modello") + ", Descrizione: " + result1.getString("descrizione") + ", Display: " + result1.getFloat("display") + ", Memoria: " + result1.getInt("memoria") + ", Prezzo: " + result1.getFloat("prezzoVendita") + ", Tipo: " + TipoProdotto.valueOf(result1.getString("tipo")) + ", Quantità: " + result.getInt("quantità"));
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}