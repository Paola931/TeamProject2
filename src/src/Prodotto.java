package src;

import java.sql.*;
import java.util.Scanner;

public class Prodotto {
    private static final String URL = "jdbc:mysql://sql8.freesqldatabase.com:3306/sql8666174";
    private static final String USER = "sql8666174";
    private static final String PASSWORD = "pdsKu4WEkV";
    private String producer;
    private String model;
    private String description;
    private double displayInch;
    private double memory;
    private double priceBuy;
    private double priceSell;
    private int id;
    private TipoProdotto tipoProdotto;


    public Prodotto(String producer, String model, String description, double displayInch, double memory, double priceBuy, double priceSell, int id, TipoProdotto tipoProdotto) {
        this.producer = producer;
        this.model = model;
        this.description = description;
        this.displayInch = displayInch;
        this.memory = memory;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.id = id;
        this.tipoProdotto = tipoProdotto;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        if (!description.isEmpty()) {
            return description;
        }
        return "No description";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDisplayInch() {
        return displayInch;
    }

    public void setDisplayInch(int displayInch) {
        this.displayInch = displayInch;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisplayInch(double displayInch) {
        this.displayInch = displayInch;
    }

    public TipoProdotto getTipoProdotto() {
        return tipoProdotto;
    }

    public void setTipo(TipoProdotto tipoProdotto) {
        this.tipoProdotto = tipoProdotto;
    }

    @Override
    public String toString() {
        return "Il Prodotto è marca " + getProducer() + ", Modello: " + getModel() + ", Misura display: " + getDisplayInch() + " Pollici, con una memoria di: " + getMemory() + "Gb. Prezzo di acquisto: " + getPriceBuy() + ", prezzo di vendita: " + getPriceSell() + ", ID dispositivo: " + getId();
    }

    public static Prodotto creaArticolo() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Scrivi la marca del prodotto che desideri registrare:");
        String producer = in.nextLine();
        while (producer.isEmpty()) {
            System.out.println("Inserisci un valore valido.");
            System.out.println("Scrivi la marca del prodotto che desideri registrare:");
            producer = in.nextLine();
        }
        System.out.println("Scrivi il modello del prodotto che desideri registrare:");
        String model = in.nextLine();
        while (model.isEmpty()) {
            System.out.println("Inserisci un valore valido.");
            System.out.println("Scrivi il modello del prodotto che desideri registrare:");
            model = in.nextLine();
        }
        System.out.println("Aggiungi la descrizione del prodotto che desideri registrare:");
        String description = in.nextLine();
        double displayInch;
        while (true) {
            try {
                System.out.println("Scrivi la grandezza dello schermo del prodotto che desideri registrare:");
                displayInch = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Inserisci un valore valido.");
            }
        }
        double memory;
        while (true) {
            try {
                System.out.println("Scrivi la memoria del prodotto che desideri registrare:");
                memory = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Inserisci un valore valido.");
            }
        }
        double priceBuy;
        while (true) {
            try {
                System.out.println("Scrivi il prezzo di acquisto del prodotto che desideri registrare:");
                priceBuy = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Inserisci un valore valido.");
            }
        }
        double priceSell;
        while (true) {
            try {
                System.out.println("Scrivi il prezzo di vendita del prodotto che desideri registrare:");
                priceSell = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Inserisci un valore valido.");
            }
        }
        int id = 0;
        String tipoTemp = "";
        TipoProdotto tipoProdotto = TipoProdotto.NOTFUND;
        while (!tipoTemp.equalsIgnoreCase("smartphone") && !tipoTemp.equalsIgnoreCase("notebook") && !tipoTemp.equalsIgnoreCase("tablet")) {
            System.out.println("Scrivi il tipo di prodotto che desideri registrare:\nOpzioni:\n - smartphone\n - notebook\n - tablet");
            tipoTemp = in.nextLine();
        }
        switch (tipoTemp.toLowerCase()) {
            case "smartphone":
                tipoProdotto = TipoProdotto.SMARTPHONE;
                break;
            case "notebook":
                tipoProdotto = TipoProdotto.NOTEBOOK;
                break;
            case "tablet":
                tipoProdotto = TipoProdotto.TABLET;
                break;
        }
        Prodotto prodotto = new Prodotto(producer, model, description, displayInch, memory, priceBuy, priceSell, id, tipoProdotto);
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if(!controllaPresenza(prodotto)) {
                Statement s = c.createStatement();
                String q = "INSERT INTO `Prodotto` (produttore, modello, descrizione, display, memoria, prezzoAcquisto, prezzoVendita, tipo) VALUES" +
                        "('" + producer + "', '" + model + "', '" + description + "', '" + displayInch + "', '" + memory + "', '" + priceBuy + "', '" + priceSell + "', '" + tipoTemp + "');";
                s.executeUpdate(q);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
        prodotto.setId(prodotto.getNewId());
        return prodotto;
    }

    public static boolean controllaPresenza(Prodotto prodotto) throws Exception {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q = "SELECT * FROM Prodotto WHERE produttore = '" + prodotto.getProducer() + "' AND modello = '" + prodotto.getModel() + "' AND descrizione = '" + prodotto.getDescription() + "' AND memoria = '" + prodotto.getMemory() + "' AND prezzoAcquisto = '" + prodotto.getPriceBuy() + "' AND prezzoVendita = '" + prodotto.getPriceSell() + "';";
            ResultSet result = s.executeQuery(q);
            return result.next();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int getNewId() throws Exception {
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement s = c.createStatement();
            String q = "SELECT * FROM Prodotto WHERE produttore = '" + this.getProducer() + "' AND modello = '" + this.getModel() + "' AND descrizione = '" + this.getDescription() + "' AND memoria = '" + this.getMemory() + "' AND prezzoAcquisto = '" + this.getPriceBuy() + "' AND prezzoVendita = '" + this.getPriceSell() + "';";
            ResultSet result = s.executeQuery(q);
            if(result.next()) {
                return result.getInt("id");
            } else {
                throw new Exception("Qualcosa è andato storto!");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}