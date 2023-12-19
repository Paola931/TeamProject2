package src;

import java.sql.*;

public class Login {
    private static final String URL = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8666174";
    private static final String USER = "sql8666174";
    private static final String PASSWORD = "pdsKu4WEkV";

    public boolean checkPresenza(String email, String password) {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q1 = "SELECT * FROM `Utenti` WHERE email = '" + email + "' AND password = '" + password + "';";
            ResultSet result = s.executeQuery(q1);
            if (result.next()) {
                return true;
            }
            return false;
        }  catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Ruolo checkRuolo(String email, String password) throws Exception {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if(checkPresenza(email, password)) {
                Statement s = c.createStatement();
                String q1 = "SELECT * FROM `Utenti` WHERE email = '" + email + "' AND password = '" + password + "';";
                ResultSet result = s.executeQuery(q1);
                result.next();
                return Ruolo.valueOf(result.getString("ruolo"));
            } else {
                throw new Exception("Utente non trovato");
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
