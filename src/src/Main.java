package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8666174";
    private static final String USER = "sql8666174";
    private static final String PASSWORD = "pdsKu4WEkV";

    public static void main(String[] args) {
        // inizializzo variabili
        Scanner in = new Scanner(System.in);
        Login login = new Login();
        //Magazzino magazzino = new Magazzino(new ArrayList<Prodotto>(), in);
        Carrello carrello = new Carrello(new ArrayList<Prodotto>(), in);


        //START
        String YoN;
        do{
            System.out.println("Hai già un account o desideri registrarti? \n" +
                    "-Accedi  per passare al LOGIN \n" +
                    "-Registrati  per passare alla REGISTRAZIONE \n" +
                    "-Esci per uscire dal programma");
             YoN = in.nextLine();
        }while(!Objects.equals(YoN, "accedi") && !Objects.equals(YoN, "registrati") && !Objects.equals(YoN, "esci"));
        switch (YoN.toLowerCase()) {
            case "accedi":
                startLogin();
                break;
            case "registrati":
                startSignUp();
                break;
            case "esci":
                break;
        }

    }
    //REGISTRAZIONE
    public static void startSignUp() {
        Scanner in = new Scanner(System.in);
        String nome;
        do {
            System.out.println("Inserisci il nome");
            nome = in.nextLine();
        } while (nome.isEmpty());

        String cognome;
        do {
            System.out.println("Inserisci il cognome");
            cognome = in.nextLine();
        } while (cognome.isEmpty());

        String mail;
        do {
            System.out.println("Inserisci la mail");
            mail = in.nextLine();
        } while (mail.isEmpty());

        String password;
        do {
            System.out.println("Inserisci la password");
            password = in.nextLine();
        } while (password.isEmpty());

        Ruolo ruolo = Ruolo.USER;
        String bool;
        do {
            System.out.println("Sei un venditore? (true/false)");
            bool = in.nextLine();
            if (bool.equals("true")) {
                ruolo = Ruolo.VENDITORE;
            }else if(bool.equals("false")){
                ruolo = Ruolo.USER;
            }
        } while (!bool.equals("true") && !bool.equals("false"));

        String nomeAzienda = null;
        if (bool.equals("true")) {
            do {
                System.out.println("Inserisci il nome della tua Azienda");
                nomeAzienda = in.nextLine();
            } while (nomeAzienda.isEmpty());
        }

        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            if(bool.equals("true")){
                String q = "INSERT INTO `Utenti` (nome, cognome, email, password, ruolo, nomeAzienda) VALUES ('" + nome + "', '" + cognome + "', '" + mail + "'," +
                        " '" + password + "', '" + ruolo + "', '" + nomeAzienda + "');";
                s.executeUpdate(q);
            }else{
                String q = "INSERT INTO `Utenti` (nome, cognome, email, password, ruolo) VALUES ('" + nome + "', '" + cognome + "', '" + mail + "'," +
                        " '" + password + "', '" + ruolo + "');";
                s.executeUpdate(q);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            startSignUp();
        }
        System.out.println("\nStai per essere indirizzato al LOGIN");
        startLogin();
    }

    //LOGIN
    public static void startLogin() {
        Login login = new Login();
        Scanner in = new Scanner(System.in);
        String email;
        do {
            System.out.println("Scrivi la tua email:");
            email = in.nextLine();
        } while (email.isEmpty());
        String password;
        do {
            System.out.println("Scrivi la tua password:");
            password = in.nextLine();
        } while (password.isEmpty());
        try {
            Ruolo ruolo = login.checkRuolo(email, password);
            if (ruolo == Ruolo.VENDITORE) {
                MenuAdmin.main();
            } else if (ruolo == Ruolo.USER) {
                MenuUtente.main();
            } else {
                System.out.println("Utente non trovato");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
