package src;

public class Prodotto {
    enum Tipo {TABLET, SMARTPHONE, NOTEBOOK}
    enum Produttore {SAMSUNG,APPLE}


    private String productor;
    private String model;
    private String description;
    private double displayInch;
    private int memory;
    private int priceBuy;
    private int priceSell;
    private int id;
    private Tipo tipo;
    private Produttore produttore;


    public Prodotto(String productor, String model, String description, double displayInch, int memory, int priceBuy, int priceSell, int id, Tipo tipo,Produttore produttore) {
        this.productor = productor;
        this.model = model;
        this.description = description;
        this.displayInch = displayInch;
        this.memory = memory;
        this.priceBuy = priceBuy;
        this.priceSell = priceSell;
        this.id = id;
        this.tipo = tipo;
        this.produttore = produttore;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
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

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(int priceBuy) {
        this.priceBuy = priceBuy;
    }

    public int getPriceSell() {
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Produttore getProduttore() {
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    @Override
    public String toString() {
        return "Il Prodotto è marca " + getProductor() + ", Modello: " + getModel() + ", Misura display: " + getDisplayInch() + " Pollici, con una memoria di: " + getMemory() + "Gb. Prezzo di acquisto: " + getPriceBuy() + ", prezzo di vendita: " + getPriceSell() + ", ID dispositivo: " + getId();
    }
}