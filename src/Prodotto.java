package src;

public class Prodotto {
    private String producer;
    private String model;
    private String description;
    private double displayInch;
    private int memory;
    private int priceBuy;
    private int priceSell;
    private int id;
    private TipoProdotto tipoProdotto;


    public Prodotto(String producer, String model, String description, double displayInch, int memory, int priceBuy, int priceSell, int id, TipoProdotto tipoProdotto) {
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

    public static Prodotto creaArticolo(String producer, String model, String description, double displayInch, int memory, int priceBuy, int priceSell, int id, TipoProdotto tipoProdotto) {

        return new Prodotto(producer, model, description, displayInch, memory, priceBuy, priceSell, id, tipoProdotto);
    }
}