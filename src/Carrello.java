package TeamProject2.TeamProject2.src;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Carrello {
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
        return costoTotale/this.listaCarrello.size();
    }
    @Override
    public String toString() {
        return "Carrello{" +
                "listaCarello=" + listaCarrello +
                '}';
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
}
