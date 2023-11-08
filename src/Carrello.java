package src;

import java.util.ArrayList;

public class Carrello {

    public Carrello(ArrayList<Prodotto> listaCarrello) {
        this.listaCarrello = listaCarrello;
    }

    @Override
    public String toString() {
        return "Prodotti nel carrello: " + getlistaCarrello() + " ";
    }

    ArrayList<Prodotto> listaCarrello = new ArrayList<>();

    public ArrayList<Prodotto> getlistaCarrello() {
        return listaCarrello;
    }

    public void setlistaCarrello(ArrayList<Prodotto> listaCarrello) {
        this.listaCarrello = listaCarrello;
    }
    public void printCarrello(ArrayList<Prodotto> carrello) {
        carrello.forEach(System.out::println);
        System.out.println();
    }
}
