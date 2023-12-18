package src;

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

    public String rimuoviDalCarrello(Prodotto prodotto) {
        this.listaCarrello.remove(prodotto);
        return "Il prodotto " + prodotto + " è stato rimosso correttamente dal carrello\n";
    }

    public double calcoloCostoMedio() {
        double costoTotale = calcoloCostoTotale(this.listaCarrello);
        return costoTotale / this.listaCarrello.size();
    }

    public static double calcoloCostoTotale(ArrayList<Prodotto> listaCarrello) {
        double costoTotale = 0;
        if (!listaCarrello.isEmpty()) {
            for (Prodotto prodotto : listaCarrello) {
                costoTotale += prodotto.getPriceSell();
            }
        } else {
            System.out.println("Il tuo carrello è vuoto");
        }
        return costoTotale;
    }

    public String aggiungiProdottoCarrello(int id) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if (!controllaPresenzaCarrello(id)) {
                String q1 = "INSERT INTO `Carrello` (ProdottoId, produttore,  modello, prezzoVendita,quantità)\n" +
                        "SELECT id, produttore, modello, prezzoVendita, quantità\n" +
                        "FROM `Prodotto`\n" +
                        "WHERE prodottoId = '" + id + "';";
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
            }else{
                String q = "UPDATE Carrello SET quantità = quantità + 1 WHERE prodottoId = '" + id + "';";
                s.executeUpdate(q);
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Il prodotto con id: " + id + " è stato aggiunto correttamente al carrello \n";
    }

    private boolean controllaPresenzaCarrello(int id) throws SQLException {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Carrello` WHERE prodottoId = '" + id + "';";
            ResultSet result = s.executeQuery(q1);
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}

    /*
    // Crea la query SQL
            String sql = "SELECT * FROM Carrello WHERE id_prodotto = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idProdottoDesiderato);

            // Esegui la query
            ResultSet resultSet = statement.executeQuery();

            // Verifica se ci sono risultati
            if (resultSet.next()) {
                // Il prodotto è nel carrello
                System.out.println("Il prodotto è nel carrello!");
            } else {
                // Il prodotto non è nel carrello
                System.out.println("Il prodotto NON è nel carrello!");
            }
     */

