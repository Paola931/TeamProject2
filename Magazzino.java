public class Magazzino {
    enum TipoProdotto{
        TABLET,
        SMARTPHONE,
        NOTEBOOK
    }
    enum MarcaProduttore{
        SAMSUNG,
        APPLE
    }
    String modello, descrizione, dimensioneDisplay, spazioArchiviazione;
    int prezzoAcquisto, prezzoVendita, id;
    TipoProdotto tipo;
    MarcaProduttore marca;

    public Magazzino(String modello, String descrizione, String dimensioneDisplay, String spazioArchiviazione, int prezzoAcquisto, int prezzoVendita, int id, TipoProdotto tipo,MarcaProduttore marca) {
        this.modello= modello;
        this.descrizione=descrizione;
        this.dimensioneDisplay=dimensioneDisplay;
        this.spazioArchiviazione=spazioArchiviazione;
        this.prezzoAcquisto=prezzoAcquisto;
        this.prezzoVendita=prezzoVendita;
        this.id=id;
        this.tipo=tipo;
        this.marca=marca;
    }


    public String getModello() {
        return modello;
    }


    public String getDescrizione() {
        return descrizione;
    }
    public String getDimensioneDisplay() {
        return dimensioneDisplay;
    }
    public String getSpazioArchiviazione() {
        return spazioArchiviazione;
    }
    public int getPrezzoAcquisto() {
        return prezzoAcquisto;
    }
    public int getPrezzoVendita() {
        return prezzoVendita;
    }
    public int getId() {
        return id;
    }
    public TipoProdotto getTipo() {
        return tipo;
    }

    public MarcaProduttore getMarca() {
        return marca;
    }
}
